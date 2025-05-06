from sqlalchemy import Column, ForeignKey, Integer, String

from app.db.base import Base


class PdfUrl(Base):
    __tablename__ = "pdf_url"

    id = Column(Integer, primary_key=True, autoincrement=True)
    url = Column(String(500))
    language = Column(String(50), name="langusage")
    diagnosis_id = Column(Integer, ForeignKey("diagnosis.id"))


5
