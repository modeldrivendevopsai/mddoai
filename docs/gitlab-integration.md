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
