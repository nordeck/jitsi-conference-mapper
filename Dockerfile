FROM maven:3.8.6-ibm-semeru-17-focal AS builder

COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM ibm-semeru-runtimes:open-17-jdk-focal
EXPOSE 8082

COPY --from=builder /app/target/JitsiConferenceMapper.jar /application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
