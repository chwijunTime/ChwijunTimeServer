FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY /src/resources/*.yml application.yml
ENTRYPOINT ["java", "-Dspring.profiles.active=real", "-jar", "/app.jar", "--spring.config.location=./application.yml"]