FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN ["pwd"]
RUN ["ls"]
RUN ["cd home/hanbin/Kim_Sanghyeon/"]
RUN ["ls"]
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=real", "/app.jar", "--spring.config.location=file:/home/hanbin/Kim_Sanghyeon/key.yml"]