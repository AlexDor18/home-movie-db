---
sidebar_label: 'Architecture Constraints'
sidebar_position: 2
---

# Architecture Constraints {#section-architecture-constraints}

## Backend Server (ADR-0000)

### Context and Problem Statement

The Requirments of this project are that a backend need to process data from an external resource. Also the backend need to store data in a persistance layer.
Also some requests made to the server needs to be protected.

### Considered Options

* Spring Boot
* Jakarta EE Server
* Express JS Server

### Decision Outcome

Chosen option: "Spring Boot", because Spring Boot Server are more easily to setup and provide featues for most of the named requirements.

## Frontend Framework (ADR-0001)

### Context and Problem Statement

The Frontend needs to use a backend server to retrive data via REST and show them to the user.

### Considered Options

* React
* Angular
* Vue

### Decision Outcome

Chosen option: "React", because the involved programmer is familiar with this technology.

## Frontend Provisioning (ADR-0002)

### Context and Problem Statement

The generated frontend files need to be provided by an server. 
All browser needs to load the files via this server.
Also this Server has to be able to redirect URL request that are not provieded by the server to the index.html at root directory.

### Considered Options

* Nginx
* Apache Tomcat
* NextJS
* Spring Boot

### Decision Outcome

Chosen option: "Spring Boot", because the whole project can be processed via maven and be packaged in a single jar file which can be used in a docker file.

## Database (ADR-0003)

### Context and Problem Statement

Data needs to be stored in Database. It has to decided which database scheme will be used.

### Considered Options

* SQL
* NoSQL

### Decision Outcome

Chosen option: "SQL", because the developer is more familiar with sql DBs.

## Frontend Client-Store (ADR-0004)

### Context and Problem Statement

Data from backend needs to be used by multiple components in this application. So external data needs to be

### Considered Options

* Redux
* Tanstack Query

### Decision Outcome

Chosen option: "Redux", because both libraries have similar features and most developer are familiar with redux.

## TMDB Adapter Implementation (ADR-0005)

### Context and Problem Statement

There are several possibilities to implement the Adapter to TMDB.

### Considered Options

* Spring REST Client
* TMDB Libraray

### Decision Outcome

Chosen option: "Spring REST Client", because only few featre of the library are required and can be better manged by self implemented adapter.

## Database Management (ADR-0006)

### Context and Problem Statement

The database of the project needs to be created on initialization of the server. It needs to be decided which technology needs to be used to mangage the database

### Considered Options

* JPA
* Flyway
* Custom SQL Script

### Decision Outcome

Chosen option: "JPA", because this project will contain its own database and doesn't need to be connected to multiple databases.

## Authetication Mechanism (ADR-0007)

### Context and Problem Statement

To show user related content a user has to autheticate himself. A mechanism is needed to authenticate a user

### Considered Options

* Form Login
* Basic Auth
* JWT / OIDC

### Decision Outcome

Chosen option: "Form Login", because it is a standalone application which does not need the same auth mechanism on mutliple applications. Also for jwt a identity provider needed to be configured.
