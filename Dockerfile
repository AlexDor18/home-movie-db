# Stage 1: Build the JAR file with Maven
FROM maven:3-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Pprod

# Stage 2: Create a Java container and run the JAR file
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/home-movie-db-backend/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]