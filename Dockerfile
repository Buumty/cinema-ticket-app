# Etap 1: Budowanie aplikacji
FROM openjdk:21-slim AS build

# Instalowanie Mavena w kontenerze
RUN apt-get update && apt-get install -y maven

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy plik pom.xml, aby pobrać zależności
COPY pom.xml ./

# Pobieramy zależności w trybie offline, aby przyspieszyć budowanie
RUN mvn dependency:go-offline

# Kopiujemy źródła aplikacji do kontenera
COPY src ./src

# Budujemy aplikację i tworzymy plik JAR
RUN mvn clean package -DskipTests

# Etap 2: Uruchomienie aplikacji w kontenerze
FROM openjdk:21-slim

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy wygenerowany plik JAR z poprzedniego etapu
COPY --from=build /app/target/cinema-ticket-reservation-app-0.0.1-SNAPSHOT.jar cinema-ticket-reservation-app.jar

# Otwieramy port 8080
EXPOSE 8080

# Uruchamiamy aplikację
ENTRYPOINT ["java", "-jar", "cinema-ticket-reservation-app.jar"]