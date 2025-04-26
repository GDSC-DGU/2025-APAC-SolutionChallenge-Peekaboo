from pydantic import BaseModel


class DefaultResponse(BaseModel):
    message: str


class ErrorResponse(BaseModel):
    error: str
    detail: str
