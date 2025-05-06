from sqlalchemy import Column, ForeignKey, Integer, String

from app.db.base import Base


class Drug(Base):
    __tablename__ = "drug"

    id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(100))
    e_name = Column(String(100))
    efficacy = Column(String(500))
    e_efficacy = Column(String(500))
    disease_const_id = Column(Integer, ForeignKey("disease_const.id"))
