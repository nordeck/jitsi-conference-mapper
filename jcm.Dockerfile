FROM maven:3-openjdk-17 AS builder

COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-alpine

COPY --from=builder /app/target/JitsiConferenceMapper-0.0.1-SNAPSHOT.jar /application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
