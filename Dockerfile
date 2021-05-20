FROM openjdk:11-jre-slim-buster

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ARG YML_FILE=classpath:/key.yml
COPY ${YML_FILE} key.yml

ENTRYPOINT ["java", "-Dspring.profiles.active=real", "-jar", "/app.jar"]