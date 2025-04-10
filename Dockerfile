FROM openjdk:21-jdk-slim

# Ustawiamy katalog roboczy w kontenerze
WORKDIR /app

# Kopiujemy plik pom.xml i źródła do katalogu roboczego
COPY pom.xml ./

# Pobieramy zależności, aby przyspieszyć późniejsze budowanie
RUN mvn dependency:go-offline

# Kopiujemy cały kod źródłowy do katalogu roboczego
COPY src ./src

# Budujemy aplikację
RUN mvn clean install

# Kopiujemy gotowy plik JAR do kontenera
COPY target/cinema-ticket-reservation-app-0.0.1-SNAPSHOT.jar cinema-ticket-reservation-app.jar

# Otwieramy port 8080
EXPOSE 8080

# Określamy komendę uruchamiającą aplikację
ENTRYPOINT ["java", "-jar", "cinema-ticket-reservation-app.jar"]