# Build context is ai/ (see docker-compose.yml), not this folder alone: same
# reasoning as ui-host/Dockerfile — package.json depends on
# "design-system": "file:../design-system" and "orchestrator-types":
# "file:../orchestrator-types", two real sibling ai/ packages, and Docker
# can't COPY from outside its build context.
FROM node:22-alpine AS build
WORKDIR /design-system
COPY design-system/package.json design-system/package-lock.json ./
RUN npm ci
COPY design-system/. .

WORKDIR /orchestrator-types
COPY orchestrator-types/package.json orchestrator-types/package-lock.json ./
RUN npm ci
COPY orchestrator-types/. .

WORKDIR /app
COPY ui-remote-chat/package.json ui-remote-chat/package-lock.json ./
RUN npm ci
COPY ui-remote-chat/. .
# The path ui-host's proxy serves this remote under (docker-compose.yml
# passes it). The built files reference their chunks by that path, since the
# browser loads them through ui-host's origin, not this container's.
ARG PUBLIC_PATH=/
RUN npm run build -- --base="$PUBLIC_PATH"

# Only the built files, served by the stock nginx configuration. ui-host's
# proxy is the one origin the browser uses, so this needs no port of its own.
FROM nginx:1.30-alpine
COPY --from=build /app/dist /usr/share/nginx/html
