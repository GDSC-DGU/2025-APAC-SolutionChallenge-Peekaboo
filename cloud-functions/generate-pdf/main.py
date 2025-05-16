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


translate_client = translate.Client()

env = Environment(loader=FileSystemLoader("templates"))
template = env.get_template("template.html")
BUCKET_NAME = os.environ.get("BUCKET_NAME", "baebaepdf")


def translated(value, lang):
    """function to translate text based on the language"""
    if not value:
        return value

    def detect_and_translate(text):
        """optimized function to detect and translate text"""
        result = translate_client.translate(text, target_language=lang)
        return (
            text
            if result["detectedSourceLanguage"] == lang
            else result["translatedText"]
        )

    if isinstance(value, list):
        return [detect_and_translate(str(item)) for item in value]

    if isinstance(value, dict):
        return value.get(lang, value.get("ko", ""))

    return detect_and_translate(str(value))


def convert_html_to_pdf(source_html: str) -> bytes:
    """HTML → PDF function"""
    result = BytesIO()
    pisa_status = pisa.CreatePDF(
        StringIO(source_html),
        dest=result,
        encoding="UTF-8",
        link_callback=lambda uri: uri,
    )
    if pisa_status.err:
        raise ValueError("PDF creation failed")
    return result.getvalue()


@functions_framework.http
def generate_pdf(request):
    """PDF creation function"""
    try:
        request_json = request.get_json(silent=True)
        if not request_json:
            return jsonify({"error": "JSON payload required"}), 400

        lang = request_json.get("lang", "ko")
        location = "USA"
        if request_json.get("location", "") == "ko":
            location = "KOREA"

        data = {
            "diagnosis_date": datetime.now().strftime("%Y-%m-%d"),
            "name": translated(request_json.get("name", ""), lang),
            "sex": translated(request_json.get("sex", ""), lang),
            "location": location,
            "skin_type": translated(request_json.get("skin_type", ""), lang),
            "birth": request_json.get("birth", ""),
            "blood_type": request_json.get("blood_type", ""),
            "symptom": translated(request_json.get("symptom", ""), lang),
            "affected_area": translated(request_json.get("affected_area", ""), lang),
            "disease_name": translated(request_json.get("disease_name", ""), lang),
            "ai_description": translated(request_json.get("ai_description", ""), lang),
        }
        allergies = translated(request_json.get("allergies", []), lang)
        histories = translated(request_json.get("histories", []), lang)
        zipped = zip_longest(allergies, histories, fillvalue=" ")
        html = template.render(data=data, zipped=zipped)
        pdf_bytes = convert_html_to_pdf(html)

        timestamp = datetime.now().strftime("%Y%m%d-%H%M%S")
        filename = f"diagnosis_report_{lang}_{timestamp}.pdf"
        blob_path = f"reports/{filename}"

        # storage_client = storage.Client(credentials = credentials, project = credentials.project_id)
        storage_client = storage.Client()
        bucket = storage_client.bucket(BUCKET_NAME)
        blob = bucket.blob(blob_path)
        blob.upload_from_string(pdf_bytes, content_type="application/pdf")

        return jsonify(
            {
                "status": "success",
                "url": f"https://storage.googleapis.com/{BUCKET_NAME}/{blob.name}",
            }
        )

    except Exception as error:
        print(f"[ERROR] {datetime.now()} - {str(error)}")
        return jsonify(
            {
                "status": "error",
                "message": "PDF generation failed",
                "detail": str(error),
            }
        ), 500
