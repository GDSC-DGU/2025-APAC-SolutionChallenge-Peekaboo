import base64

from fastapi import HTTPException, UploadFile
from google.cloud import aiplatform

from app.core.config import settings
from app.core.logging import logger
from app.schemas.diagnosis import CVResult


class CVService:
    def __init__(self):
        self.project = settings.vertex_ai_project_id
        self.location = settings.region
        self.vertex_ai_endpoint = settings.vertex_ai_endpoint
        self.endpoint = aiplatform.Endpoint(self.vertex_ai_endpoint)

    async def analyze_image(self, image: UploadFile) -> dict:
        try:
            image_bytes = await image.read()
            encoded_image = base64.b64encode(image_bytes).decode("utf-8")

            instances = [{"content": encoded_image}]
            response = self.endpoint.predict(instances=instances)

            prediction = response.predictions[0] if response.predictions else {}

            classes = zip(
                prediction.get("displayNames", []), prediction.get("confidences", [])
            )

            sorted_classes = sorted(classes, key=lambda x: x[1], reverse=True)[
                :3
            ]  # extract top 3 results

            top3_results: list[CVResult] = [
                CVResult(name=name, confidence=round(conf * 100, 2))
                for name, conf in sorted_classes
            ]
            return {
                "top3_diseases": [result.dict() for result in top3_results],
            }

        except IndexError:
            logger.error("No predictions returned from Vertex AI")
            raise HTTPException(500, "Model did not return any predictions")
        except Exception as e:
            logger.error(f"Vertex AI error: {str(e)}")
            raise HTTPException(500, "Image analysis failed") from e
