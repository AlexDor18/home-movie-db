---
sidebar_label: 'Building Block View'
sidebar_position: 5
---
# Building Block View {#section-building-block-view}

## Whitebox Overall System {#_whitebox_overall_system}

![c4 system context diagram](/c4Diagrams/c4-system-context.png)

#### Motivation
Motivation: The application should focus on managing the needs of the user and not managing the complete environment around movies and series.

#### Contained Building Blocks
Home Movie DB:
Managing the movies relevant for the users.

TMDB:
Service which provides all information about movies and series.


## Level 1
![c4 container diagram](/c4Diagrams/c4-container.png)

### Webanwendung {#webanwendung}
Provides the user interface for the application. Shows all movies in the user's collection and enables them to search for movies from TMDB. Also gives the user the possibility of adding and removing movies from their collection.

Uses the backend's REST API.

### REST API {#rest_api}
Contains the application's business logic. Also handles the connection to TMDB and PostgreSQL DB.
Presents a REST API and uses TMDB's REST API.


### Database {#database}
Persists information about users and movies.

## Level 2 {#_level_2}


![c4 component diagram backend](/c4Diagrams/c4-component-backend.png)


### Movies- / UsersWeb {#web_components}
Both classes contain the Spring REST controllers which access services containing the business logic. Be aware that to access both controllers under "/api/", a constant needs to be added at the beginning of both classes. Otherwise, it can collide with the SPA frontend when API URLs do not start with /api.

### UserService {#userService}
The user service provides information about users. It uses Spring Boot Security to get the authenticated user.

### MovieService {#movieService}
This service provides information about movies. The service uses TMDB for unknown movies and the database when the movie is persisted.

### TmdbMovieInformations {#tmdbMovieInformations}
This service manages the requests to TMDB. It uses a customized REST client to access all TMDB endpoints without having to add the API key to every request manually.
