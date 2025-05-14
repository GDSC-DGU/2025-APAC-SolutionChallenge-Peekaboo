from sqlalchemy.orm import relationship

from app.models.allergy import Allergy
from app.models.user import User

User.allergies = relationship(
    "Allergy", back_populates="user", cascade="save-update, merge"
)
User.onboarding_diseases = relationship(
    "OnboardingDisease", back_populates="user", cascade="save-update, merge"
)
User.diagnoses = relationship(
    "Diagnosis", back_populates="user", cascade="save-update, merge"
)
Allergy.user = relationship("User", back_populates="allergies")
