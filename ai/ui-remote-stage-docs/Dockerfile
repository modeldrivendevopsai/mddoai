# Build context is ai/ (see docker-compose.yml), not this folder alone: same
# reasoning as ui-host/Dockerfile — package.json depends on
# "design-system": "file:../design-system", a real sibling ai/ package, and
# Docker can't COPY from outside its build context.
FROM node:22-alpine
WORKDIR /design-system
COPY design-system/package.json design-system/package-lock.json ./
RUN npm ci
COPY design-system/. .

WORKDIR /app
COPY ui-remote-stage-docs/package.json ui-remote-stage-docs/package-lock.json ./
RUN npm ci
COPY ui-remote-stage-docs/. .
RUN npm run build
CMD ["sh", "-c", "cp -r /app/dist/. /output/"]
