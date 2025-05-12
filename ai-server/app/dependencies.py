from google.cloud import aiplatform

from app.core.config import settings
from app.services.cv_service import CVService
from app.services.llm_service import LLMService
from app.services.prompt_service import PromptService
from app.services.rag_service import RagService


def get_cv_service() -> CVService:
    """Get the CVService instance."""
    aiplatform.init(project=settings.project_id, location=settings.region)
    return CVService()


def get_prompt_service() -> PromptService:
    return PromptService(prompt_dir=settings.PROMPT_DIR)


def get_rag_service() -> RagService:
    return RagService()


def get_llm_service() -> LLMService:
    return LLMService(model=settings.LLM_MODEL)


async def get_current_user_id() -> int:
    return 1  # 임시 사용자 ID
