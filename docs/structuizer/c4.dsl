workspace {

    model {
        benutzer = person "Benutzer" {
            description "Ein Endnutzer des Systems"
        }

        admin = person "Administrator" {
            description "Eine Person, die das System betreut"
        }

        tmdb = softwareSystem "TMDB" {
            description "The Movie Database – Externer Filmdaten-Service"
            tags "External"
        }

        homeMovieDb = softwareSystem "Home Movie DB" {
            description "Verwaltet Filme für Zuhause"

            webApp = container "Webanwendung" {
                technology "React (Frontend)"
                description "Stellt die Benutzeroberfläche bereit"
                
                react = component "React" {
                    technology "React"
                    description "Rendering der HTML componenten"
                }
                
                redux = component "Store" {
                    technology "Redux (RTK Query)"
                    description "Handling information and api calls"
                }
                
                react -> redux "Verwendet"
                redux -> moviesWeb "Fragt Filme ab"
                redux -> userWeb "Fragt Benutzer ab"
            }

            database = container "Datenbank" {
                technology "PostgreSQL"
                description "Speichert Informationen"
            }

            api = container "REST API" {
                technology "Spring Boot"
                description "Backend-API für Webanwendung und externe Zugriffe"

                moviesWeb = component "MoviesWeb" {
                    technology "Spring REST Controller"
                    description "Stellt Endpunkte für Filme bereit"
                }
                userWeb = component "UserWeb" {
                    technology "Spring REST Controller"
                    description "Stellt Endpunkte für Benutzer bereit"
                }
                movieService = component "MovieService" {
                    technology "Spring Service"
                    description "Geschäftslogik für Filme"
                }
                
                userService = component "UserService" {
                    technology "Spring Service"
                    description "Geschäftslogik für Nutzer"
                }
                
                movieRepository = component "MovieRepository" {
                    technology "Spring Data JPA"
                    description "Zugriff auf Filme in der Datenbank"
                }
                
                userRepository = component "UserRepository" {
                    technology "Spring Data JPA"
                    description "Zugriff auf alle Nutzer in der Datenbank"
                }
                
                tmdbMovieInformations = component "TmdbMovieInformations" {
                    technology "Spring Service"
                    description "Kommuniziert mit TMDB API"
                }
                movieMapper = component "MovieMapper" {
                    technology "MapStruct"
                    description "Mapppt zwischen DTOs und Nachrichten"
                }

                moviesWeb -> movieService "Verwendet"
                moviesWeb -> movieMapper "Verwendet"
                userWeb -> userService "Verwendet"
                movieService -> movieRepository "Verwendet"
                movieService -> tmdbMovieInformations "Fragt Filmdaten an"
                movieService -> userService "Frag authetifizierten Benutzer an"
                userService -> userRepository "Verwendet"
                movieRepository -> database "Verwendet"
                userRepository -> database "Verwendet"
                tmdbMovieInformations -> tmdb "Verwendet TMDB API"
            }

            benutzer -> webApp "Verwendet"
            admin -> webApp "Verwendet"
            webApp -> api "Spricht mit"
            api -> database "Liest/Schreibt"
            api -> tmdb "Fragt Filmdaten an"
        }
    }

    views {
        systemContext homeMovieDb {
            include *
            autolayout lr
            title "Systemkontextdiagramm"
        }

        container homeMovieDb {
            include *
            autolayout lr
            title "Containerdiagramm Backend"
        }

        component webApp {
            include *
            autolayout lr
            title "Containerdiagram WebApp"
        }

        component api {
            include *
            autolayout lr
            title "Komponentendiagramm (API)"
        }

        theme default
    }
}