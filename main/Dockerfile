FROM eclipse-temurin:24-jre-alpine

WORKDIR /app

COPY . /app/

RUN chmod +x /app/cli

ENTRYPOINT ["/app/cli"]
