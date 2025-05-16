from langchain_google_genai import (
    ChatGoogleGenerativeAI,
    HarmBlockThreshold,
    HarmCategory,
)

from app.core.config import settings


class LLMService:
    def __init__(
        self,
        model: str = settings.LLM_MODEL,
        temperature: float = 0.3,
        max_output_tokens: int = 2048,
        safety_settings: dict | None = None,
    ):
        """
        Args:
            model (str): model name, default is settings.LLM_MODEL
            temperature (float): Controls the diversity of the generated text.
            max_output_tokens (int): Maximum number of output tokens. Default is 2048.
            safety_settings (dict|None): Safety settings. Default is None.
        """
        self.llm = ChatGoogleGenerativeAI(
            model=model,
            safety_settings={
                HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT: HarmBlockThreshold.BLOCK_NONE,  # noqa: E501
                HarmCategory.HARM_CATEGORY_HARASSMENT: HarmBlockThreshold.BLOCK_NONE,
                HarmCategory.HARM_CATEGORY_HATE_SPEECH: HarmBlockThreshold.BLOCK_NONE,
                HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT: HarmBlockThreshold.BLOCK_NONE,  # noqa: E501
            },
            temperature=temperature,
            max_output_tokens=max_output_tokens,
        )

    async def generate(self, prompt: str) -> str:
        response = await self.llm.ainvoke(prompt)
        return response.content
