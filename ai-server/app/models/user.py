import uuid
from datetime import datetime

from sqlalchemy import Column, Date, DateTime, Enum, String
from sqlalchemy.orm import relationship

from app.db.base import Base
from app.models.allergy import Allergy
from app.models.enums import EProvider, ERole
from app.models.onboarding_disease import OnboardingDisease


class User(Base):
    __tablename__ = "users"

    id = Column(String(36), primary_key=True, default=uuid.uuid4)
    serial_id = Column(String(255), nullable=False, unique=True)
    nickname = Column(String(20))
    login_id = Column(String(255), name="login_id")
    role = Column(Enum(ERole), nullable=False, name="user_role")
    provider = Column(Enum(EProvider), nullable=False, name="login_provider")
    profile_image_url = Column(String(2048))
    refresh_token = Column(String(255))
    fcm_token = Column(String(255))
    email = Column(String(255))
    password = Column(String(255))
    profile = Column(String(255))
    created_at = Column(DateTime, default=datetime.now)
    location = Column(String(255))
    language = Column(String(50))
    birth = Column(Date)
    gender = Column(String(50))
    skin_type = Column(String(50))
    blood_type = Column(String(50))

    allergies = relationship(Allergy, back_populates="user")
    onboarding_diseases = relationship(
        OnboardingDisease, back_populates="user", cascade="save-update, merge"
    )
