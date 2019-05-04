FROM openjdk:8-jdk-alpine

LABEL maintainer="Daniel Dimitrov"

ENV KAFKA_BOOTSTRAP_SERVER ""
ENV KAFKA_GROUP_ID ""
ENV TOPICS ""

RUN apk update && apk add ca-certificates wget && update-ca-certificates

ADD falcon-task-view*.jar /falcon-task-view.jar

ENTRYPOINT java -jar /falcon-task-view.jar $KAFKA_BOOTSTRAP_SERVER $KAFKA_GROUP_ID $TOPICS
