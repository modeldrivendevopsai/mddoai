# MDDOAI GitLab CI Integration Guide
This document briefly describes how to integrate MDDOAI into a GitLab CI/CD pipeline to automatically generate project-specific pipelines from a SWArch model.

## Overview
MDDOAI enables **model-driven CI/CD generation** by transforming a software architecture model (`.swarch`) into an executable GitLab pipeline.

## GitLab CI integration guide:
https://gitlab.com/model-driven-devops-ai/hello-java-ci/-/blob/main/README.md?ref_type=heads

### Hello Java CI repository
The **Hello Java CI repository** provides the complete, step-by-step, runnable example, including pipeline configuration, CI/CD variables, and authentication setup.

- Demo repository:
  https://gitlab.com/model-driven-devops-ai/hello-java-ci
  
The Hello Java CI repository demonstrates:
- how to configure the master pipeline,
- how to integrate a SWArch model,
- and how MDDOAI generates and triggers project-specific pipelines in practice.

## Supported GitLab integration capabilities
MDDOAI provides the following GitLab CI integration capabilities, which are demonstrated through its observable behavior:
- **Model-driven pipeline generation**: MDDOAI consumes a `.swarch` software architecture model as input and produces a GitLab CI pipeline definition as output. Changes in the model directly result in changes in the generated pipeline, demonstrating that the pipeline is derived from the model rather than manually authored.
- **Docker-based execution**: The transformation process is executed entirely via the MDDOAI Docker image. All required dependencies are packaged inside the container, and no local installation is required beyond Docker support.
- **CI/CD system independence**: MDDOAI does not depend on GitLab-specific APIs, environment variables, or UI features. It operates as a standalone command-line transformation tool and can be invoked from any CI/CD system capable of running Docker containers.
- **Pipeline-as-code output**: The generated output is a native `.gitlab-ci.yml` file that follows standard GitLab CI syntax and can be executed directly by GitLab CI without additional tooling or adapters.
