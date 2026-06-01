FROM python:3.11-slim

RUN apt-get update \
    && apt-get install -y --no-install-recommends graphviz \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY proyecto/PM_microservice/ /app/

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 9000 9001 9002 9003 9004 9005 9006 9007 9008 9009

CMD ["sh", "-c", "python start_scripts.py && tail -f /dev/null"]
