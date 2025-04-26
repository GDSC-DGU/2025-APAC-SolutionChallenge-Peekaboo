from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.core.config import settings
from app.schemas.response import DefaultResponse

app = FastAPI()


@app.get("/", response_model=DefaultResponse)
def read_root() -> str:
    return DefaultResponse(message="Hello, World!")


app.add_middleware(
    CORSMiddleware,
    allow_origins=settings.cors_origins,
    allow_credentials=True,
    allow_methods=settings.cors_methods,
    allow_headers=settings.cors_headers,
)
