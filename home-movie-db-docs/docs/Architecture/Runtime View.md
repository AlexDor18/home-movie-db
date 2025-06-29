---
sidebar_label: 'Runtime View'
sidebar_position: 6
---
# Runtime View {#section-runtime-view}

## Search Movie {#search-movie}
![search movie diagram](/arc42/searchFlowDiagram.png)

Der Benutzer sucht nach einem Film über die REST-API. Die Anfrage wird durch Spring Security authentifiziert und vom SearchWeb Controller entgegengenommen. Der MoviesService nutzt den MovieAdapter (z.B. TmdbMovieInformations), um die TMDB API abzufragen. Die Antwort wird gemappt und als Liste von MovieMessages an den Benutzer zurückgegeben.

---

## Add Movie {#add-movie}
![add movie diagram](/arc42/saveMovieFlowDiagram.png)

Beim Hinzufügen eines Films sendet der Benutzer eine Anfrage an die REST-API. Nach Authentifizierung wird die Anfrage vom MoviesWeb Controller an den MoviesService weitergeleitet. Der MovieAdapter fragt die TMDB API ab, mappt die Antwort und speichert den Film über das MovieRepository in der Datenbank. Das Ergebnis wird als MovieMessage zurückgegeben.

---

## Delete Movie {#delete-movie}
![delte movie diagram](/arc42/deleteMovieFlowDiagram.png)

Der Benutzer löscht einen Film über die REST-API. Nach Authentifizierung wird die Anfrage vom MoviesWeb Controller an den MoviesService weitergegeben, der das MovieRepository nutzt, um den Film aus der Datenbank zu entfernen. Der Status wird an den Benutzer zurückgegeben.

---

## User Login {#login}
![login diagram](/arc42/loginFlowDiagram.png)

Der Benutzer meldet sich über die REST-API an. Die Authentifizierung erfolgt durch Spring Security. Bei Erfolg erhält der Benutzer ein Token oder eine Session.

---

## Get Movies {#get-all-movies}
![get all movies diagram](/arc42/getAllMoviesFlowDiagram.png)

Der Benutzer fragt seine Filmliste über die REST-API ab. Nach Authentifizierung wird die Anfrage vom MoviesWeb Controller an den MoviesService weitergeleitet, der die Filme aus der Datenbank liest und als MovieMessage-Liste zurückgibt.

---

## User Registration {#new-user}
![new user diagram](/arc42/newUserFlowDiagram.png)

Der Benutzer registriert sich über die REST-API. Der LoginWeb Controller mappt die Anfrage und ruft den UserServiceImpl auf. Es wird geprüft, ob der Benutzername bereits existiert. Falls nicht, wird ein neuer Benutzer angelegt und gespeichert. Die Antwort enthält die
