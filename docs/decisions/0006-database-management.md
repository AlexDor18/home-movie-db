# Database Management

## Context and Problem Statement

The database of the project needs to be created on initialization of the server. It needs to be decided which technology needs to be used to mangage the database

## Considered Options

* JPA
* Flyway
* Custom SQL Script

## Decision Outcome

Chosen option: "JPA", because this project will contain its own database and doesn't need to be connected to multiple databases.
