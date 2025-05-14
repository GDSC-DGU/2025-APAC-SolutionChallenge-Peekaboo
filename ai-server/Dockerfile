FROM python:3.12-slim

RUN pip install --no-cache-dir poetry

WORKDIR /app

COPY pyproject.toml poetry.lock /app/

RUN poetry config virtualenvs.create false \
  && poetry install --no-interaction --no-ansi --only main --no-root

COPY ./app /app/app

RUN apt-get update && apt-get install -y tree
RUN tree /app

ENV PYTHONPATH=/app/app

EXPOSE 8080

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080"]
