---
sidebar_label: 'Architecture Constraints'
sidebar_position: 2
---

# Architecture Constraints {#section-architecture-constraints}

## Organisational Constraints

- Der Source-Code muss in einem **öffentlichen Github-Repository** liegen.
- Die **Dokumentation** muss nach dem **arc42-Standard** erfolgen und öffentlich einsehbar (z.B. auf readthedocs) sein.
- **Wichtige Architekturentscheidungen** sind in **ADRs** festzuhalten.

## Technological Constraints

- Erlaubte Programmiersprachen: **Java, C#, Python, TypeScript**
- **Frontend, Backend und Persistenzschicht** (z.B. Datenbank) müssen als getrennte Schichten implementiert werden.
- Das Backend muss mit **mindestens einem externen Service** (z.B. Google-APIs, TMDB) kommunizieren.
- Es muss **mindestens ein öffentlich erreichbarer Endpunkt** und **mindestens ein abgesicherter Endpunkt** (z.B. Login) existieren.
- **ChatGPT/Copilot** sind als Hilfsmittel erlaubt, dürfen aber nicht den gesamten Projektinhalt generieren.

## Quality Constraints

- Die Software muss ein **dokumentiertes und implementiertes Testkonzept** besitzen, das die komplette Testpyramide abdeckt:
    - **Unit-Tests**
    - **Integration-Tests**
    - **End-to-End-Tests**
    - **Penetrationstests** (z.B. Integrationstest der Sicherheitslogik)
- Es muss eine **lauffähige Github-Pipeline** existieren.
- Ein Tool zur **statischen Codeanalyse** darf keine offenen Issues zeigen und die **Testabdeckung** muss mindestens **80%** betragen.
- Für die Anbindung externer Services sind **ausfallsichere Architekturpatterns** zu verwenden.
- Das Projekt muss **öffentlich zugänglich** und nach dem Auschecken mit **maximal zwei Befehlen lauffähig** sein (z.B. via Docker Compose oder Shellscript).




