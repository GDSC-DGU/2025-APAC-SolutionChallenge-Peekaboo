from sqlalchemy import Column, ForeignKey, Integer, String
from sqlalchemy.orm import relationship

from app.db.base import Base


class Allergy(Base):
    __tablename__ = "allergy"

    id = Column(Integer, primary_key=True, autoincrement=True)
    description = Column(String(255))
    user_id = Column(String(36), ForeignKey("users.id"))

    user = relationship("User", back_populates="allergies")
