---
sidebar_position: 1
---

# Welcome to Home Movie DB

# Getting Started
Follow the follwing steps to start home-movie-db on your docker infrastructure.

## TMDB API Key
To run home-movie-db you have create an account at [The Movie Database (TMDB)](https://www.themoviedb.org/signup?language=de).
With an account you are able to obtain an [API Key](https://www.themoviedb.org/settings/api) for TMDB.
Use the "API Read Access Token".

## Insert Use Api Key
After getting an Api Key you have to add it as environment variable on your system under TMDB_API_KEY or replace ```${TMDB_API_KEY}``` it in the docker-compose.yml.

## Database credentials
In the docker compose file are default db access keys set. Replace them with new ones to prevent unwanted access.

## Run application with docker-compose
To run the application with docker-compose just run ```docker-compose up``` from the root of this repository.
When all all actions are performed successfully the application can be accessed form [localhost](http://{host}:8080).


