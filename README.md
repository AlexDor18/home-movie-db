[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AlexDor18_home-movie-db&metric=alert_status&token=38143e1418c153b6a1b75b3afa1976766cf39412)](https://sonarcloud.io/summary/new_code?id=AlexDor18_home-movie-db) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AlexDor18_home-movie-db&metric=coverage&token=38143e1418c153b6a1b75b3afa1976766cf39412)](https://sonarcloud.io/summary/new_code?id=AlexDor18_home-movie-db) 

# home-movie-db
Home Movie Database is a web application designed to manage and organize your personal movie collection. The application utilizes the TMDB API to fetch movie data and provides a user-friendly interface to browse and search through your collection.

# Getting started

## System requirements
To run home-movie-db you need Docker and Docker Compose to be installed on your system. You also need an internet connection on your system to install all components required by the container. You also need an internet connection to retrieve information from The Movie Database (TMDB).


## TMDB API Key
To run home-movie-db you have to create an account at [The Movie Database (TMDB)](https://www.themoviedb.org/signup?language=de).
With an account, you are able to obtain an [API Key](https://www.themoviedb.org/settings/api) for TMDB.
Use the "API Read Access Token".

## Insert API Key
After obtaining an API Key, you have to add it as an environment variable on your system under TMDB_API_KEY or replace `${TMDB_API_KEY}` in the docker-compose.yml.

## Database credentials
The docker-compose file contains default database access keys. Replace them with new ones to prevent unwanted access.

## Run application with docker-compose
To run the application with docker-compose just run ```docker-compose up``` from the root of this repository.
When all actions are performed successfully, the application can be accessed from http://{host}:8080.