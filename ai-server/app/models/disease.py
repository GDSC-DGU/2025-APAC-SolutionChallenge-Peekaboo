from sqlalchemy import Column, ForeignKey, Integer

from app.db.base import Base


class Disease(Base):
    __tablename__ = "disease"

    id = Column(Integer, primary_key=True, autoincrement=True)
    ranking = Column(Integer)
    percent = Column(Integer, name="percent")
    diagnosis_id = Column(Integer, ForeignKey("diagnosis.id"))
    disease_const_id = Column(Integer, ForeignKey("disease_const.id"))
