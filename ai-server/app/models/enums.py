from enum import Enum


class EProvider(str, Enum):
    GOOGLE = "GOOGLE"
    KAKAO = "KAKAO"
    NAVER = "NAVER"
    APPLE = "APPLE"


class ERole(str, Enum):
    USER = "USER"
    ADMIN = "ADMIN"

    @property
    def security_name(self) -> str:
        return f"ROLE_{self.value}"


DISEASE_ID_MAP = {
    "Miliaria": 1,
    "Chickenpox": 2,
    "Roseola": 3,
    "Contact Dermatitis": 4,
    "Milia": 5,
    "Erythema Toxicum Neonatorum": 6,
    "Aplasia Cutis Congenita": 7,
    "Measles": 8,
    "Atopic Dermatitis": 9,
    "Seborrheic Dermatitis": 10,
    "Impetigo": 11,
    "Ringworm": 12,
    "No Problem": 13,
}
