import os
import functions_framework
from datetime import datetime
from flask import jsonify
from google.cloud import storage
from google.oauth2 import service_account
from jinja2 import Environment, FileSystemLoader
from xhtml2pdf import pisa
from io import BytesIO, StringIO
from google.cloud import translate_v2 as translate
from itertools import zip_longest

# GCP 인증 정보 초기화
#credentials = service_account.Credentials.from_service_account_file("credentials.json")

translate_client = translate.Client()

# 환경 설정
env = Environment(loader=FileSystemLoader("templates"))
template = env.get_template("template.html")
BUCKET_NAME = os.environ.get("BUCKET_NAME", "baebaepdf")


def translated(value, lang):
    """실시간 동적 번역 수행 함수"""
    if not value:  # 빈 값 처리
        return value

    def detect_and_translate(text):
        """단일 텍스트 번역 최적화"""
        result = translate_client.translate(text, target_language=lang)
        return text if result['detectedSourceLanguage'] == lang else result['translatedText']

    # 리스트 처리 (알러지, 병력 등)
    if isinstance(value, list):
        return [detect_and_translate(str(item)) for item in value]

    # 딕셔너리 처리 (다국어 사전 입력 대비)
    if isinstance(value, dict):
        return value.get(lang, value.get('ko', ''))

    # 단일 문자열 처리
    return detect_and_translate(str(value))


def convert_html_to_pdf(source_html: str) -> bytes:
    """HTML → PDF 변환 핵심 로직"""
    result = BytesIO()
    pisa_status = pisa.CreatePDF(
        StringIO(source_html),
        dest=result,
        encoding='UTF-8',
        link_callback=lambda uri: uri  # 외부 리소스 차단
    )
    if pisa_status.err:
        raise ValueError("PDF 생성 실패: HTML 파싱 오류")
    return result.getvalue()


@functions_framework.http
def generate_pdf(request):
    """PDF 생성 HTTP 엔드포인트"""
    print("Asdfasdfsa")
    try:
        request_json = request.get_json(silent=True)
        if not request_json:
            return jsonify({"error": "JSON payload required"}), 400

        lang = request_json.get('lang', 'ko')
        location = 'USA'
        if request_json.get('location', '') == 'ko' :
            location = 'KOREA'

        # 데이터 구조화 및 번역
        data = {
            "diagnosis_date": datetime.now().strftime("%Y-%m-%d"),
            "name": translated(request_json.get('name', ''), lang),
            "sex": translated(request_json.get('sex', ''), lang),
            "location": location,
            "skin_type": translated(request_json.get('skin_type', ''), lang),
            "birth": request_json.get('birth', ''),
            "blood_type": request_json.get('blood_type', ''),
            "symptom": translated(request_json.get('symptom', ''),lang),
            "affected_area": translated(request_json.get('affected_area', ''), lang),
            "disease_name": translated(request_json.get('disease_name', ''), lang),
            "ai_description": translated(request_json.get('ai_description', ''), lang)
        }
        allergies = translated(request_json.get('allergies', []), lang)
        histories = translated(request_json.get('histories', []), lang)
        zipped = zip_longest(allergies, histories, fillvalue=' ')
        print(data)
        # PDF 생성 파이프라인
        html = template.render(data=data, zipped=zipped)
        pdf_bytes = convert_html_to_pdf(html)

        # 파일 이름 및 GCS 업로드
        timestamp = datetime.now().strftime("%Y%m%d-%H%M%S")
        filename = f"diagnosis_report_{lang}_{timestamp}.pdf"
        blob_path = f"reports/{filename}"

        #storage_client = storage.Client(credentials = credentials, project = credentials.project_id)
        storage_client = storage.Client()
        bucket = storage_client.bucket(BUCKET_NAME)
        blob = bucket.blob(blob_path)
        blob.upload_from_string(pdf_bytes, content_type="application/pdf")

        return jsonify({
            "status": "success",
            "url": f"https://storage.googleapis.com/{BUCKET_NAME}/{blob.name}"
        })

    except Exception as error:
        # 상세 오류 로깅 (Cloud Logging 연동)
        print(f"[ERROR] {datetime.now()} - {str(error)}")
        return jsonify({
            "status": "error",
            "message": "PDF 생성 과정에서 오류 발생",
            "detail": str(error)
        }), 500
