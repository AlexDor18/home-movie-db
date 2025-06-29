---
sidebar_label: 'Architecture Constraints'
sidebar_position: 2
---

# Architecture Constraints {#section-architecture-constraints}

## Organisational Constraints

- The source code must be in a **public Github repository**.
- The **documentation** must follow the **arc42 standard** and be publicly accessible (e.g., on readthedocs).
- **Important architectural decisions** must be recorded in **ADRs**.

## Technological Constraints

- Allowed programming languages: **Java, C#, Python, TypeScript**
- **Frontend, backend, and persistence layer** (e.g., database) must be implemented as separate layers.
- The backend must communicate with **at least one external service** (e.g., Google APIs, TMDB).
- There must be **at least one publicly accessible endpoint** and **at least one secured endpoint** (e.g., login).
- **ChatGPT/Copilot** are allowed as tools, but must not generate the entire project content.

## Quality Constraints

- The software must have a **documented and implemented test concept** covering the entire test pyramid:
    - **Unit tests**
    - **Integration tests**
    - **End-to-end tests**
    - **Penetration tests** (e.g., integration test of the security logic)
- There must be a **working Github pipeline**.
- A tool for **static code analysis** must show no open issues and **test coverage** must be at least **80%**.
- **Resilient architectural patterns** must be used for connecting external services.
- The project must be **publicly accessible** and, after checkout, runnable with a **maximum of two commands** (e.g., via Docker Compose or shell script).




