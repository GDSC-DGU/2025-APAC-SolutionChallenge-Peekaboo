from sqlalchemy import Column, Date, ForeignKey, Integer, String
from sqlalchemy.orm import relationship

from app.db.base import Base


class Diagnosis(Base):
    __tablename__ = "diagnosis"

    id = Column(Integer, primary_key=True, autoincrement=True)
    part = Column(String(50))
    image_url = Column(String(500))
    description = Column(String(1000))
    ko_custom_description = Column(String(1000))
    en_custom_description = Column(String(1000))
    create_at = Column(Date)
    user_id = Column(String(36), ForeignKey("users.id"))
    user = relationship("User", back_populates="diagnoses")
    diseases = relationship(
        "Disease", back_populates="diagnosis", cascade="save-update, merge"
    )
    pdf_urls = relationship(
        "PdfUrl", back_populates="diagnosis", cascade="save-update, merge"
    )
