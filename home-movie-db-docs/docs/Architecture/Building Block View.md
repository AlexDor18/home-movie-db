---
sidebar_label: 'Building Block View'
sidebar_position: 5
---
# Building Block View {#section-building-block-view}

## Whitebox Overall System {#_whitebox_overall_system}

![c4 system context diagram](/c4Diagrams/c4-system-context.png)

#### Motivation
The application should focus on managing the needs of the user and not managing the complete environment around movies and series

#### Contained Building Blocks
Home Movie DB:
Managing the movies relevent for the users.

TMDB:
Service which provides all informations around movies and series. 


## Level 1
![c4 container diagram](/c4Diagrams/c4-container.png)

### Webanwendung {#webanwendung}
Provides the user interface for the application. Shows all movies of the users collection and enable them to search movies from TMDB. Also gives the user the possibility of adding and removing movies from the users collection.

Uses the backend's REST API. 

### REST API {#rest_api}
Contains the application's business logic. Also handels the connection to TMDB and postgreSQL DB.
Presents a REST API and uses TMDB's REST API. 


### Database {#database}
Persists informations about users and movies.

## Level 2 {#_level_2}


![c4 component diagram backend](/c4Diagrams/c4-component-backend.png)


### Movies- / UsersWeb {#web_components}
Both classes contains the spring rest controllers which accesses sevices containing the business logic. Be aware to access both controlles under "/api/*" a contant needed to be added at the beginning of both classes. Otherwise it can collide with the SPA frontend when api urls do not start with /api. 

### UserService {#userService}
The user service provides informations about users. It uses spring boot security to get authenticated user.  

### MovieService {#movieService}
This service provides informations about movies. The service uses TMDB for unknown movies and database when the movie is persisted.

### TmdbMovieInformations {#tmdbMovieInformations}
This Service manages the request to TMDB. It uses a customized REST Client to access all TMDB endpoint without added e.g. the Api-Key to every request by hand.
