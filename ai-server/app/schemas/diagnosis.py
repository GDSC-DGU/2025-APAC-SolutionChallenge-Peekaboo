from __future__ import annotations

from typing import Annotated

from fastapi import File, Form, UploadFile
from pydantic import BaseModel
from schemas.base import BaseResponse


class DiagnosisRequest(BaseModel):
    @classmethod
    def as_form(
        cls,
        affected_area: Annotated[str, Form(...)],
        symptoms: Annotated[str, Form(...)],
        image: Annotated[UploadFile, File(...)],
    ) -> DiagnosisRequest:
        return cls(affected_area=affected_area, symptoms=symptoms, image=image)


class Symptom(BaseModel):
    name: str


class Drug(BaseModel):
    name: str
    efficacy: str


class Disease(BaseModel):
    diseaseId: int
    diseaseName: str
    ranking: int
    percent: int
    description: str | None = None
    type: str
    site: str
    reason: str
    mild: str
    severe: str
    preventive: str
    caution: str
    symptoms: list[Symptom] | None = None
    drugs: list[Drug] | None = None


class Data(BaseModel):
    customDescription: str | None = None
    diseaseList: list[Disease]


class DiagnosisResponse(BaseResponse[Data]):
    success: bool = True
    data: Data
    error: str | None = None
