from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    db_host: str
    db_port: int
    db_user: str
    db_password: str
    db_name: str
    debug: bool = False

    allow_origins: str = "*"
    allow_methods: str = "*"
    allow_headers: str = "*"
    allow_credentials: bool = True

    qdrant_api_key: str
    gemini_api_key: str

    project_id: str
    region: str
    vertex_ai_endpoint_id: str

    @property
    def database_url(self) -> str:
        return f"mysql+pymysql://{self.db_user}:{self.db_password}@{self.db_host}:{self.db_port}/{self.db_name}"

    @property
    def vertex_ai_endpoint(self) -> str:
        return (
            f"projects/{self.project_id}/"
            f"locations/{self.region}/"
            f"endpoints/{self.vertex_ai_endpoint_id}"
        )

    @property
    def cors_origins(self) -> list[str]:
        return (
            [o.strip() for o in self.allow_origins.split(",")]
            if self.allow_origins
            else ["*"]
        )

    @property
    def cors_methods(self) -> list[str]:
        return (
            [m.strip() for m in self.allow_methods.split(",")]
            if self.allow_methods
            else ["*"]
        )

    @property
    def cors_headers(self) -> list[str]:
        return (
            [h.strip() for h in self.allow_headers.split(",")]
            if self.allow_headers
            else ["*"]
        )

    class Config:
        env_file = ".env"


settings = Settings()
