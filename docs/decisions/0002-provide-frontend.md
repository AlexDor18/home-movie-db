# Provide Frontend

## Context and Problem Statement

The generated frontend files need to be provided by an server. 
All browser needs to load the files via this server.
Also this Server has to be able to redirect URL request that are not provieded by the server to the index.html at root directory.

## Considered Options

* Nginx
* Apache Tomcat
* NextJS
* Spring Boot

## Decision Outcome

Chosen option: "Spring Boot", because the whole project can be processed via maven and be packaged in a single jar file which can be used in a docker file.
