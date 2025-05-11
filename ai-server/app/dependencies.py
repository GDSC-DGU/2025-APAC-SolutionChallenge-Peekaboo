from google.cloud import aiplatform

from app.core.config import settings
from app.services.cv_service import CVService


def get_cv_service() -> CVService:
    """Get the CVService instance."""
    aiplatform.init(project=settings.project_id, location=settings.region)
    return CVService()
