# Używamy obrazu Javy jako bazowego
FROM openjdk:21-jdk-slim

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy plik JAR do kontenera
COPY target/cinema-ticket-reservation-app-0.0.1-SNAPSHOT.jar cinema-ticket-reservation-app.jar

# Ustawiamy zmienną środowiskową dla Javy
ENV JAVA_OPTS="-Xmx1024m"

# Otwieramy port 8080
EXPOSE 8080

# Uruchamiamy aplikację
ENTRYPOINT ["java", "-jar", "cinema-ticket-reservation-app.jar"]