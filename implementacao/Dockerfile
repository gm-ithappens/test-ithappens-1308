FROM openjdk:8-alpine

MAINTAINER Felipe Alexandre

EXPOSE 8080

RUN apk update && apk add bash

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY target/api-test-1308-pulse-0.0.1-SNAPSHOT.jar $PROJECT_HOME/app.jar

WORKDIR $PROJECT_HOME

ENTRYPOINT ["java","-jar","app.jar"]
