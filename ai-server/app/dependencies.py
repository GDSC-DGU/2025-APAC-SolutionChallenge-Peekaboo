from google.cloud import aiplatform

from app.core.config import settings
from app.core.logging import logger
from app.services.cv_service import CVService
from app.services.llm_service import LLMService
from app.services.prompt_service import PromptService
from app.services.rag_service import RagService
from app.services.translation_service import TranslationService


def get_cv_service() -> CVService:
    """Get the CVService instance."""
    try:
        aiplatform.init(project=settings.vertex_ai_project_id, location=settings.region)
        logger.info("Vertex AI initialized successfully in get_cv_service.")
        return CVService()
    except Exception:
        logger.exception("Failed to initialize Vertex AI in get_cv_service.")
        raise


def get_prompt_service() -> PromptService:
    logger.info("PromptService initialized with directory")
    return PromptService(prompt_dir=settings.PROMPT_DIR)


def get_rag_service() -> RagService:
    logger.info("RagService initialized.")
    return RagService()


def get_llm_service() -> LLMService:
    logger.info("LLMService initialized with model")
    return LLMService(model=settings.LLM_MODEL)


def get_translation_service() -> TranslationService:
    logger.info("TranslationService initialized.")
    return TranslationService()
