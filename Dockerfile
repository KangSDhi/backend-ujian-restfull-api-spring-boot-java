FROM eclipse-temurin:17-jdk-alpine
LABEL authors="kangsdhi"

VOLUME /tmp
COPY build/libs/backend-ujian-restfull-api-spring-boot-java-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]