FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/cinema-ticket-reservation-app-0.0.1-SNAPSHOT.jar cinema-ticket-reservation-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "cinema-ticket-reservation-app.jar"]