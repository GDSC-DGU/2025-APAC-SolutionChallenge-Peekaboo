from sqlalchemy import Column, ForeignKey, Integer, String

from app.db.base import Base


class Symptoms(Base):
    __tablename__ = "symptoms"

    id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(100))
    e_name = Column(String(100))
    disease_const_id = Column(Integer, ForeignKey("disease_const.id"))
