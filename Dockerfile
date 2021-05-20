FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=real", "-jar", "/app.jar", "--spring.config.location=/home/hanbin/Kim_Sanghyeon/deploy_chwijuntime.yml"]