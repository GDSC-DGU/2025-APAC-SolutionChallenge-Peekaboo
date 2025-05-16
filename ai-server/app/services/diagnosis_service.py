from uuid import UUID

from fastapi import UploadFile
from sqlalchemy.orm import Session

from app.crud.user import get_patient_info

from .cv_service import CVService
from .llm_service import LLMService
from .prompt_service import PromptService
from .rag_service import RagService
from .translation_service import TranslationService


class DiagnosisService:
    def __init__(
        self,
        cv_service: CVService,
        prompt_service: PromptService,
        translation_service: TranslationService,
        rag_service: RagService,
        llm_service: LLMService,
    ):
        self.cv_service = cv_service
        self.prompt_service = prompt_service
        self.translation_service = translation_service
        self.rag_service = rag_service
        self.llm_service = llm_service

    def _get_patient_info(self, db: Session, user_id: UUID) -> str:
        """formaating patient info for the prompt"""
        info = get_patient_info(db, user_id)

        return (
            f"location: {info.get('location', 'Unknown')}\n"
            f"birth: {info.get('birth', 'Unknown')}\n"
            f"gender: {info.get('gender', 'Unknown')}\n"
            f"blood_type: {info.get('blood_type', 'Unknown')}\n"
            f"skin_type: {info.get('skin_type', 'Unknown')}\n"
            f"allergies: {', '.join(info.get('allergies', [])) or 'None'}\n"
            f"onboarding_diseases: {', '.join(info.get('onboarding_diseases', [])) or 'None'}\n"  # noqa: E501
        )

    def _parse_request_info(self, user_inputs: dict) -> dict:
        """Parse and validate user inputs"""
        symptoms = user_inputs.get("symptoms", "").strip()
        affected_area = user_inputs.get("affected_area", "").strip()
        return {
            "symptoms": symptoms,
            "affected_area": affected_area,
        }

    async def run(
        self,
        db: Session,
        user_id: UUID,
        user_inputs: dict,
        image_file: UploadFile,
        lang: str = "en",
    ) -> str:
        patient_info = self._get_patient_info(db, user_id)
        parsed_inputs = self._parse_request_info(user_inputs)
        image_analysis = await self.cv_service.analyze_image(image_file)
        context = self.rag_service.get_context(
            query=f"{parsed_inputs['symptoms']} {parsed_inputs['affected_area']}"
        )
        inputs = {
            "patient_info": patient_info,
            "image_analysis": image_analysis,
            "symptoms": parsed_inputs["symptoms"],
            "affected_area": parsed_inputs["affected_area"],
            "allergies": patient_info.split("allergies:")[1],
            "medical_history": patient_info.split("onboarding_diseases:")[1],
            # medical_history == onboarding_diseases
            "context": context,
        }

        prompt_data = self.prompt_service.load_prompt()
        prompt = self.prompt_service.build_prompt(prompt_data, inputs)
        llm_result = await self.llm_service.generate(prompt)

        translate_result = self.translation_service.conditional_translate(
            llm_result, lang
        )

        return translate_result
