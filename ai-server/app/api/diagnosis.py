import logging
import re

from fastapi import APIRouter, Depends, HTTPException, status
from sqlalchemy.orm import Session

from app.db.dependency import get_db
from app.dependencies import (
    get_cv_service,
    get_llm_service,
    get_prompt_service,
    get_rag_service,
    get_translation_service,
)
from app.models.enums import DISEASE_ID_MAP
from app.schemas.diagnosis import DiagnosisRequest, DiagnosisResponse, DiagnosisResult
from app.services.diagnosis_service import (
    CVService,
    DiagnosisService,
    LLMService,
    PromptService,
    RagService,
    TranslationService,
)

logger = logging.getLogger(__name__)

router = APIRouter(tags=["diagnosis"])


def parse_llm_result(llm_text: str) -> list[DiagnosisResult]:
    """
    Parse the LLM result text to extract diagnosis results.
    """
    results = []
    pattern = re.compile(r"\d+\.\s*(.+?)\s*/\s*(\d+)%\s*/\s*Reason:\s*(.+)")
    for line in llm_text.splitlines():
        match = pattern.match(line.strip())
        if match:
            disease_name = match.group(1).strip()
            probability = int(match.group(2))
            reason = match.group(3).strip()
            disease_id = DISEASE_ID_MAP.get(disease_name)
            if disease_id is not None:
                results.append(
                    DiagnosisResult(
                        id=disease_id,
                        disease=disease_name,
                        probability=probability,
                        reason=reason,
                    )
                )
    return results


@router.post("/diagnose", response_model=DiagnosisResponse)
async def diagnose(
    diagnosis_request: DiagnosisRequest = Depends(DiagnosisRequest.as_form),
    lang: str = "en",
    db: Session = Depends(get_db),
    cv_service: CVService = Depends(get_cv_service),
    prompt_service: PromptService = Depends(get_prompt_service),
    rag_service: RagService = Depends(get_rag_service),
    llm_service: LLMService = Depends(get_llm_service),
    translation_service: TranslationService = Depends(get_translation_service),
) -> DiagnosisResponse:
    """
    Perform diagnosis based on symptoms, affected area, and image.
    - **lang**: Response language (en/ko/ja/zh-CN), defaults to en
    """
    try:
        diagnosis_service = DiagnosisService(
            cv_service=cv_service,
            prompt_service=prompt_service,
            rag_service=rag_service,
            llm_service=llm_service,
            translation_service=translation_service,
        )
        user_inputs = {
            "symptoms": diagnosis_request.symptoms,
            "affected_area": diagnosis_request.affected_area,
        }

        llm_result_en = await diagnosis_service.run(
            db=db,
            user_id=diagnosis_request.userId,
            user_inputs=user_inputs,
            image_file=diagnosis_request.image,
        )

        results = parse_llm_result(llm_result_en)

        if lang.lower() != "en":
            for result in results:
                try:
                    result.disease = translation_service.conditional_translate(
                        result.disease, lang
                    )
                    result.reason = translation_service.conditional_translate(
                        result.reason, lang
                    )
                except Exception as e:
                    logger.error(f"Translation error: {e}")

        return DiagnosisResponse(data=results)
    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=str(e),
        )
