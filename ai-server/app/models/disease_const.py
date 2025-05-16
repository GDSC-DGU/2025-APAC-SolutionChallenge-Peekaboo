from sqlalchemy import BigInteger, Column, Integer, String
from sqlalchemy.orm import relationship

from app.db.base import Base


class DiseaseConst(Base):
    __tablename__ = "disease_const"

    id = Column(Integer, primary_key=True, autoincrement=True)

    rating = Column(BigInteger)
    e_rating = Column(BigInteger)

    name = Column(String)
    e_name = Column(String)

    description = Column(String)
    e_description = Column(String)

    type = Column(String)
    e_type = Column(String)

    site = Column(String)
    e_site = Column(String)

    reason = Column(String)
    e_reason = Column(String)

    mild = Column(String)
    e_mild = Column(String)

    severe = Column(String)
    e_severe = Column(String)

    preventive = Column(String)
    e_preventive = Column(String)

    caution = Column(String)
    e_caution = Column(String)

    drugs = relationship("Drug", back_populates="disease_const", cascade="merge")
    symptoms = relationship("Symptoms", back_populates="disease_const", cascade="merge")
    diseases = relationship("Disease", back_populates="disease_const", cascade="merge")
