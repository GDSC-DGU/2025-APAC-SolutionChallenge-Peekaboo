import re

from fastapi import APIRouter, Depends, File, Form, HTTPException, UploadFile, status
from sqlalchemy.orm import Session

from app.db.dependency import get_db
from app.dependencies import (
    get_current_user_id,
    get_cv_service,
    get_llm_service,
    get_prompt_service,
    get_rag_service,
)
from app.models.enums import DISEASE_ID_MAP
from app.schemas.diagnosis import DiagnosisResponse, DiagnosisResult
from app.services.diagnosis_service import (
    CVService,
    DiagnosisService,
    LLMService,
    PromptService,
    RagService,
)

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
    symptoms: str = Form(...),
    affected_area: str = Form(...),
    image: UploadFile = File(...),
    db: Session = Depends(get_db),
    user_id: int = Depends(get_current_user_id),
    cv_service: CVService = Depends(get_cv_service),
    prompt_service: PromptService = Depends(get_prompt_service),
    rag_service: RagService = Depends(get_rag_service),
    llm_service: LLMService = Depends(get_llm_service),
) -> DiagnosisResponse:
    """
    Perform diagnosis based on symptoms, affected area, and image.
    """
    try:
        diagnosis_service = DiagnosisService(
            cv_service=cv_service,
            prompt_service=prompt_service,
            rag_service=rag_service,
            llm_service=llm_service,
        )
        user_inputs = {
            "symptoms": symptoms,
            "affected_area": affected_area,
        }
        llm_result_text = await diagnosis_service.run(
            db=db,
            user_id=user_id,
            user_inputs=user_inputs,
            image_file=image,
        )
        results = parse_llm_result(llm_result_text)
        return DiagnosisResponse(data=results)
    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=str(e),
        )
