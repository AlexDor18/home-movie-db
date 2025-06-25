CREATE TABLE user_entity (
  id BIGINT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  prename VARCHAR(255),
  surname VARCHAR(255)
);

CREATE TABLE actor_entity (
  id BIGINT PRIMARY KEY,
  prename VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL
);

CREATE TABLE movie_entity (
  id BIGINT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  overview TEXT,
  thumbnail_uri VARCHAR(255),
  adult BOOLEAN NOT NULL,
  release_date DATE,
  original_language VARCHAR(255),
  original_title VARCHAR(255)
);

CREATE TABLE genres_entity (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Create many-to-many relationship tables

CREATE TABLE user_entity_movies (
  owned_by_user_id BIGINT NOT NULL,
  movies_id BIGINT NOT NULL,
  PRIMARY KEY (owned_by_user_id, movies_id),
  FOREIGN KEY (owned_by_user_id) REFERENCES user_entity(id),
  FOREIGN KEY (movies_id) REFERENCES movie_entity(id)
);

CREATE TABLE movie_entity_genres (
  movies_id BIGINT NOT NULL,
  genres_id BIGINT NOT NULL,
  PRIMARY KEY (movies_id, genres_id),
  FOREIGN KEY (movies_id) REFERENCES movie_entity(id),
  FOREIGN KEY (genres_id) REFERENCES genres_entity(id)
);

CREATE SEQUENCE IF NOT EXISTS user_entity_seq START WITH 1 INCREMENT BY 50;

INSERT INTO user_entity (id, username, password, surname, prename)
VALUES (99, 'default_user', 'default_password', 'integration', 'test');

