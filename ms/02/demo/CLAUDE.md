# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 4.0.6 REST API demo application using Java 21 and Maven. Single-module project with a minimal footprint — all endpoints live directly in `DemoApplication.java`.

## Commands

```bash
# Build
./mvnw clean package

# Run locally
./mvnw spring-boot:run

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=DemoApplicationTests

# Build and run via Docker
docker build -t demo .
docker run -p 8080:8080 demo
```

## Architecture

The entire application is a single class (`src/main/java/com/example/demo/DemoApplication.java`) annotated with both `@SpringBootApplication` and `@RestController`. There is no layered structure — controllers, services, and repositories should be added as separate classes when needed.

**Endpoints:**
- `GET /` — returns a plain string
- `GET /hello?name=World` — greeting with optional query param
- `GET /test` — health-check style string

**Configuration:** `src/main/resources/application.yaml` — currently only sets `spring.application.name: demo-novy-server`. The default port is 8080.

**Dependencies:** `spring-boot-starter-webmvc` (runtime) and `spring-boot-starter-webmvc-test` (test scope) only — no database, no security, no additional frameworks.

**Docker:** Multi-stage build — Maven/JDK 21 for compilation, `eclipse-temurin:21-jre` for the runtime image. Tests are skipped during Docker build (`-DskipTests`).
