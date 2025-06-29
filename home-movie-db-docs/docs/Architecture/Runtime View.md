---
sidebar_label: 'Runtime View'
sidebar_position: 6
---
# Runtime View {#section-runtime-view}

## Search Movie {#search-movie}
![search movie diagram](/arc42/searchFlowDiagram.png)

The user searches for a movie via the REST API. The request is authenticated by Spring Security and handled by the SearchWeb Controller. The MoviesService uses the MovieAdapter (e.g., TmdbMovieInformations) to query the TMDB API. The response is mapped and returned to the user as a list of MovieMessages.

---

## Add Movie {#add-movie}
![add movie diagram](/arc42/saveMovieFlowDiagram.png)

When adding a movie, the user sends a request to the REST API. After authentication, the request is forwarded by the MoviesWeb Controller to the MoviesService. The MovieAdapter queries the TMDB API, maps the response, and stores the movie in the database via the MovieRepository. The result is returned as a MovieMessage.

---

## Delete Movie {#delete-movie}
![delte movie diagram](/arc42/deleteMovieFlowDiagram.png)

The user deletes a movie via the REST API. After authentication, the request is forwarded by the MoviesWeb Controller to the MoviesService, which uses the MovieRepository to remove the movie from the database. The status is returned to the user.

---

## User Login {#login}
![login diagram](/arc42/loginFlowDiagram.png)

The user logs in via the REST API. Authentication is handled by Spring Security. If successful, the user receives a token or session.

---

## Get Movies {#get-all-movies}
![get all movies diagram](/arc42/getAllMoviesFlowDiagram.png)

The user requests their movie list via the REST API. After authentication, the request is forwarded by the MoviesWeb Controller to the MoviesService, which reads the movies from the database and returns them as a list of MovieMessages.

---

## User Registration {#new-user}
![new user diagram](/arc42/newUserFlowDiagram.png)

The user registers via the REST API. The LoginWeb Controller maps the request and calls the UserServiceImpl. It checks whether the username already exists. If not, a new user is created and saved. The response contains the user data or an error message if the name is already taken.
