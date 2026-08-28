# Build context is ai/ (see docker-compose.yml), not this folder alone: same
# reasoning as ui-host/Dockerfile — package.json depends on
# "design-system": "file:../design-system" and "orchestrator-types":
# "file:../orchestrator-types", two real sibling ai/ packages, and Docker
# can't COPY from outside its build context.
FROM node:22-alpine
WORKDIR /design-system
COPY design-system/package.json design-system/package-lock.json ./
RUN npm ci
COPY design-system/. .

WORKDIR /orchestrator-types
COPY orchestrator-types/package.json orchestrator-types/package-lock.json ./
RUN npm ci
COPY orchestrator-types/. .

WORKDIR /app
COPY ui-remote-stepper/package.json ui-remote-stepper/package-lock.json ./
RUN npm ci
COPY ui-remote-stepper/. .
RUN npm run build
CMD ["sh", "-c", "cp -r /app/dist/. /output/"]
