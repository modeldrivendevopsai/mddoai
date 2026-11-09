# Build context is ai/ (see docker-compose.yml), not this folder alone:
# package.json depends on "design-system": "file:../design-system" and
# "orchestrator-types": "file:../orchestrator-types", two real sibling ai/
# packages (see their own READMEs — shared, non-deployed dependencies, not
# services), and Docker can't COPY from outside its build context.
# /design-system, /orchestrator-types, and /app sit as siblings in the image
# so each relative file: path resolves the same way it does on the host.
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
COPY ui-host/package.json ui-host/package-lock.json ./
RUN npm ci
COPY ui-host/. .
# Where the browser loads each Module Federation remote from, read by
# vite.config.ts when it builds (docker-compose.yml passes the paths this
# image's own proxy serves them under).
ARG VITE_REMOTE_CHAT_URL
ARG VITE_REMOTE_STEPPER_URL
ARG VITE_REMOTE_STAGE_DOCS_URL
ARG VITE_REMOTE_STAGE_SERIALIZATION_URL
ARG VITE_REMOTE_STAGE_PIM_URL
ARG VITE_REMOTE_STAGE_PSM_URL
ARG VITE_REMOTE_STAGE_ATL_URL
ARG VITE_REMOTE_STAGE_ACCELEO_URL
ARG VITE_REMOTE_STAGE_GENERATION_URL
RUN npm run build

# Only the built files and the web server that serves them, and proxies the
# remotes and the backends, on one port.
FROM nginx:1.30-alpine
COPY ui-host/nginx/default.conf.template /etc/nginx/templates/default.conf.template
COPY --from=build /app/dist /usr/share/nginx/html
