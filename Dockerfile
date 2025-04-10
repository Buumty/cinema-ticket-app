FROM maven:3.8.6-openjdk-21-slim AS build

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy plik pom.xml (lub build.gradle, jeśli używasz Gradle)
COPY pom.xml ./

# Pobieramy zależności
RUN mvn dependency:go-offline

# Kopiujemy kod źródłowy
COPY src ./src

# Budujemy aplikację
RUN mvn clean install

# Kopiujemy wygenerowany plik JAR do kontenera
FROM openjdk:21-jdk-slim

WORKDIR /app

# Kopiujemy plik JAR z poprzedniego etapu
COPY --from=build /app/target/cinema-ticket-reservation-app-0.0.1-SNAPSHOT.jar cinema-ticket-reservation-app.jar

# Otwieramy port 8080
EXPOSE 8080

# Uruchamiamy aplikację
ENTRYPOINT ["java", "-jar", "cinema-ticket-reservation-app.jar"]