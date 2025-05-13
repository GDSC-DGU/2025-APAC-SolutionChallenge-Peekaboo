import uuid
from typing import Any

from models.user import User
from sqlalchemy.orm import Session

from app.core.logging import logger


def get_user(db: Session, id: str) -> User | None:
    """
    Retrieve a User object from the database by id.
    """
    try:
        user = db.query(User).filter(User.id == uuid.UUID(id).bytes).first()
        if user:
            logger.info(f"User {id} retrieved successfully.")
        else:
            logger.warning(f"User {id} not found.")
        return user
    except Exception:
        logger.exception(f"Exception occurred while retrieving user {id}.")
        return None


def get_patient_info(db: Session, id: str) -> dict[str, Any]:
    """
    Retrieve patient information from the database.
    """
    try:
        user = db.query(User).filter(User.id == uuid.UUID(id).bytes).first()
        if user is None:
            logger.warning(f"Patient info for user {id} not found.")
            return {}
        logger.info(f"Patient info for user {id} retrieved successfully.")
        return {
            "location": user.location,
            "birth": user.birth,
            "gender": user.gender,
            "blood_type": user.blood_type,
            "skin_type": user.skin_type,
            "allergies": [a.description for a in user.allergies],
            "onboarding_diseases": [d.description for d in user.onboarding_diseases],
        }
    except Exception:
        logger.exception(
            f"Exception occurred while retrieving patient info for user {id}."
        )
        return {}
