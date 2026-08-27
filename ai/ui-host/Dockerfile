# Build context is ai/ (see docker-compose.yml), not this folder alone:
# package.json depends on "design-system": "file:../design-system" and
# "orchestrator-types": "file:../orchestrator-types", two real sibling ai/
# packages (see their own READMEs — shared, non-deployed dependencies, not
# services), and Docker can't COPY from outside its build context.
# /design-system, /orchestrator-types, and /app sit as siblings in the image
# so each relative file: path resolves the same way it does on the host.
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
COPY ui-host/package.json ui-host/package-lock.json ./
RUN npm ci
COPY ui-host/. .
RUN npm run build
CMD ["sh", "-c", "cp -r /app/dist/. /output/"]
