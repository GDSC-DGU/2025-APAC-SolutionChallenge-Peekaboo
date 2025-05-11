from typing import Any

from models.user import User
from sqlalchemy.orm import Session


def get_user(db: Session, user_id: int) -> User | None:
    """
    Retrieve a User object from the database by user_id.

    Args:
        db (Session): SQLAlchemy database session.
        user_id (int): ID of the user to retrieve.

    Returns:
        User | None: The User object if found, otherwise None.
    """
    return db.query(User).filter(User.id == user_id).first()


def get_patient_info(db: Session, user_id: int) -> dict[str, Any]:
    """
    Retrieve patient information from the database.

    Args:
        db (Session): SQLAlchemy database session.
        user_id (int): ID of the user to retrieve information for.

    Returns:
        dict: A dictionary containing patient information or an empty dict if not found.
    """
    user = db.query(User).filter(User.id == user_id).first()
    if user is None:
        return {}

    return {
        "location": user.location,
        "birth": user.birth,
        "gender": user.gender,
        "blood_type": user.blood_type,
        "skin_type": user.skin_type,
        "allergies": user.allergies,
        "onboarding_diseases": user.onboarding_diseases,
    }
