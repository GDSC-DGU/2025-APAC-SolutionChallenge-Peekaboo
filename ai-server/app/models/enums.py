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
