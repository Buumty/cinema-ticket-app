
FROM openjdk:21-slim AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/target/cinema-ticket-reservation-app-0.0.1-SNAPSHOT.jar cinema-ticket-reservation-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "cinema-ticket-reservation-app.jar"]