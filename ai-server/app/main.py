from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.api import diagnosis
from app.core.config import settings
from app.core.secret_manager import set_gcp_credentials_from_secret

set_gcp_credentials_from_secret(
    secret_id="service-account-json", project_id=settings.project_id
)

app = FastAPI()
app.include_router(diagnosis.router)

app.add_middleware(
    CORSMiddleware,
    allow_origins=settings.cors_origins,
    allow_credentials=True,
    allow_methods=settings.cors_methods,
    allow_headers=settings.cors_headers,
)
