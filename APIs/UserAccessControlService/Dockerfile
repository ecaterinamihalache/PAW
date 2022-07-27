FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./generate-hs256-secret.sh /generate-hs256-secret.sh
RUN ./generate-hs256-secret.sh secret.key
ENTRYPOINT ["java","-jar","app.jar"]
