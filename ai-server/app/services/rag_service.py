from langchain_huggingface import HuggingFaceEmbeddings
from qdrant_client import QdrantClient

from app.core.config import settings


class RagService:
    def __init__(self):
        self.client = QdrantClient(
            url=settings.qdrant_url,
            api_key=settings.qdrant_api_key,
            timeout=60,
            check_compatibility=False,
        )
        self.huggingface_api_token = settings.huggingface_api_token
        self.embeddings = HuggingFaceEmbeddings(
            model_name="sentence-transformers/paraphrase-multilingual-MiniLM-L12-v2"
        )

    def get_context(self, query: str, k: int = 3) -> str:
        try:
            query_embedding = self.embeddings.embed_query(query)
        except Exception as e:
            return f"Embedding generation failed: {str(e)}"

        try:
            search_result = self.client.search(
                collection_name="disease_collection",
                query_vector=query_embedding,
                limit=k,
                with_payload=["disease", "description"],
            )
        except Exception as e:
            return f"Qdrant search failed: {str(e)}"

        if not search_result:
            return "No relevant disease context found."

        return "\n\n".join(
            f"{item.payload.get('disease', 'Unknown')}:\n"
            f"{item.payload.get('description', 'No description')}"
            for item in search_result
        )
