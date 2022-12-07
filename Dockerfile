FROM maven:3-openjdk-17 AS builder

COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-alpine
EXPOSE 8082

COPY --from=builder /app/target/JitsiConferenceMapper.jar /application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
