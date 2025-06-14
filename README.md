[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AlexDor18_home-movie-db&metric=alert_status&token=38143e1418c153b6a1b75b3afa1976766cf39412)](https://sonarcloud.io/summary/new_code?id=AlexDor18_home-movie-db) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AlexDor18_home-movie-db&metric=coverage&token=38143e1418c153b6a1b75b3afa1976766cf39412)](https://sonarcloud.io/summary/new_code?id=AlexDor18_home-movie-db) 

# home-movie-db
Docker application for managing your movies at home

# Run application

## TMDB API Key
To run home-movie-db you have create an account at [The Movie Database (TMDB)](https://www.themoviedb.org/signup?language=de).
With an account you are able to obtain an [API Key](https://www.themoviedb.org/settings/api) for TMDB.
Use the "API Read Access Token".

## Insert Use Api Key
After getting an Api Key you have to add it as environment variable on your system under TMDB_API_KEY or replace ${TMDB_API_KEY} it in the docker-compose.yml.

## Database credentials
In the docker compose file are default db access key set. Replace them with new ones to prevent unwanted access.

## Run application with docker-compose
To run the application with docker-compose just run ```docker-compose up``` from the root of this repository.
When all all actions are performed successfully the application can be accessed form http://{host}:8080.