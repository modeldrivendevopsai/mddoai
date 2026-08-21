# Build context is ai/ (see docker-compose.yml), not this folder alone:
# package.json depends on "design-system": "file:../design-system", a real
# sibling ai/ package (see ai/design-system/README.md — a shared UI-kit
# dependency, not a deployed service), and Docker can't COPY from outside
# its build context. /design-system and /app sit as siblings in the image
# so the relative file: path resolves the same way it does on the host.
FROM node:22-alpine
WORKDIR /design-system
COPY design-system/package.json design-system/package-lock.json ./
RUN npm ci
COPY design-system/. .

WORKDIR /app
COPY ui-host/package.json ui-host/package-lock.json ./
RUN npm ci
COPY ui-host/. .
RUN npm run build
CMD ["sh", "-c", "cp -r /app/dist/. /output/"]
