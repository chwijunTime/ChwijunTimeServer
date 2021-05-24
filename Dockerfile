FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV H2_PATH =/h2-console

ENTRYPOINT ["java", "-Dspring.profiles.active=real", "-jar", "/app.jar", "--spring.h2.console.path=${H2_PATH}"]