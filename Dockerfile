FROM maven:3.8.6-eclipse-temurin-17 AS builder

COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM eclipse-temurin:17
EXPOSE 8082

COPY --from=builder /app/target/JitsiConferenceMapper.jar /application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
