import functions_framework
from bs4 import BeautifulSoup
import html
from urllib.parse import urljoin
import re
import requests
import json
from google.cloud import translate_v2 as translate

LANG_MAP = {"ko": "ko", "zh": "zh-CN", "jp": "ja", "en": "en"}


def translate_text(text, target_lang="en"):
    client = translate.Client()
    result = client.translate(text, target_language=target_lang)
    return result["translatedText"]


@functions_framework.http
def get_latest_article_info(request):
    try:
        req_lang = request.args.get("target_lang", "ko").lower()
        target_lang = LANG_MAP.get(req_lang, "ko")  # 기본값 한글

        base_url = "https://dportal.kdca.go.kr"
        list_url = "https://dportal.kdca.go.kr/pot/bbs/BD_selectBbsList.do?q_bbsSn=1009"
        headers = {"User-Agent": "Mozilla/5.0"}

        response = requests.get(list_url, headers=headers)
        if response.status_code != 200:
            return (
                json.dumps(
                    {
                        "success": False,
                        "error": f"List page error {response.status_code}",
                        "data": None,
                    }
                ),
                500,
                {"Content-Type": "application/json"},
            )

        soup = BeautifulSoup(response.text, "html.parser")
        urls = [
            link["href"]
            for link in soup.find_all("a", href=True)
            if "q_bbsDocNo=" in link["href"]
        ]
        if not urls:
            return (
                json.dumps(
                    {"success": False, "error": "No document URLs found", "data": None}
                ),
                404,
                {"Content-Type": "application/json"},
            )

        latest_url = max(
            urls, key=lambda x: int(x.split("q_bbsDocNo=")[1].split("&")[0])
        )
        full_url = urljoin(base_url, latest_url)

        detail = requests.get(full_url, headers=headers)
        if detail.status_code != 200:
            return (
                json.dumps(
                    {
                        "success": False,
                        "error": f"Detail page error {detail.status_code}",
                        "data": None,
                    }
                ),
                500,
                {"Content-Type": "application/json"},
            )

        detail_soup = BeautifulSoup(detail.text, "html.parser")
        content_div = detail_soup.find("div", class_="article-content")
        if not content_div:
            return (
                json.dumps(
                    {
                        "success": False,
                        "error": "No article-content found",
                        "data": None,
                    }
                ),
                404,
                {"Content-Type": "application/json"},
            )

        lines = []
        for line in content_div.get_text().split("\n"):
            if match := re.match(r"^- *\(([^/]+)/([^)]+)\)\s*(.*)", line.strip()):
                name = match[1].strip()
                location = match[2].strip()
                description = match[3].strip()

                if target_lang != "ko":
                    name = translate_text(name, target_lang)
                    location = translate_text(location, target_lang)
                    description = translate_text(description, target_lang)
                    description = html.unescape(description)

                lines.append(
                    {"name": name, "location": location, "description": description}
                )

        return (
            json.dumps(
                {"success": True, "error": None, "data": {"weeklyInfo": lines}},
                ensure_ascii=False,
            ),
            200,
            {"Content-Type": "application/json"},
        )

    except Exception as e:
        print(f"Error: {e}")
        return (
            json.dumps({"success": False, "error": str(e), "data": None}),
            500,
            {"Content-Type": "application/json"},
        )
