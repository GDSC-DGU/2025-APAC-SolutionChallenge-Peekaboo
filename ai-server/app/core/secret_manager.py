import os
import tempfile

from google.cloud import secretmanager

from app.core.logging import logger


def get_secret(secret_id: str, project_id: str) -> str:
    """
    Retrieve a secret from Google Secret Manager.
    """
    client = secretmanager.SecretManagerServiceClient()
    name = f"projects/{project_id}/secrets/{secret_id}/versions/latest"
    try:
        response = client.access_secret_version(request={"name": name})
        logger.info(f"Successfully retrieved secret: {secret_id}")
        return response.payload.data.decode("UTF-8")
    except Exception:
        logger.exception(f"Failed to retrieve secret: {secret_id}")
        raise


def set_gcp_credentials_from_secret(secret_id: str, project_id: str) -> str:
    """
    Set GCP credentials from a secret stored in Google Secret Manager.
    """
    try:
        json_str = get_secret(secret_id, project_id)
        with tempfile.NamedTemporaryFile(delete=False, suffix=".json") as f:
            f.write(json_str.encode("utf-8"))
            temp_json_path = f.name
        os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = temp_json_path
        logger.info(f"Set GOOGLE_APPLICATION_CREDENTIALS from secret: {secret_id}")
        return temp_json_path
    except Exception:
        logger.exception(
            f"Failed to set GOOGLE_APPLICATION_CREDENTIALS from secret: {secret_id}"
        )
        raise
