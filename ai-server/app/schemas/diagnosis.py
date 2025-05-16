from __future__ import annotations

from typing import Annotated

from fastapi import File, Form, UploadFile
from pydantic import BaseModel

from .base_response import BaseResponse


class DiagnosisRequest(BaseModel):
    userId: str
    affected_area: str
    symptoms: str
    image: UploadFile

    @classmethod
    def as_form(
        cls,
        userId: Annotated[str, Form(...)],
        affected_area: Annotated[str, Form(...)],
        symptoms: Annotated[str, Form(...)],
        image: Annotated[UploadFile, File(...)],
    ) -> DiagnosisRequest:
        return cls(
            userId=userId, affected_area=affected_area, symptoms=symptoms, image=image
        )

    class Config:
        arbitrary_types_allowed = True


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


class CVResult(BaseModel):
    name: str
    confidence: float


class DiagnosisResult(BaseModel):
    id: int
    disease: str
    probability: int
    reason: str


class DiagnosisResponse(BaseResponse):
    success: bool = True
    data: list[DiagnosisResult]
    error: str | None = None
