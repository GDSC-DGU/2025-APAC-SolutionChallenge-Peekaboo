from google.api_core.exceptions import GoogleAPIError
from google.cloud import translate_v2 as translate

from app.core.logging import logger


class TranslationService:
    def __init__(self):
        self.client = translate.Client()

    def conditional_translate(self, text: str, target_lang: str) -> str:
        """
        Translate text to target_lang using Google Translate.
        """
        try:
            result = self.client.translate(text, target_language=target_lang)
            return result["translatedText"]
        except GoogleAPIError as api_err:
            logger.error(f"GoogleAPIError during translation: {api_err}")
            raise
        except Exception as e:
            logger.exception(
                f"Unexpected error during translation to '{target_lang}': {e}"
            )
            raise
