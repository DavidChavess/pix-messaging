FROM alpine:3.16.2

WORKDIR /java-17/src/app/

RUN apk update --no-cache &&\
    apk upgrade --no-cache &&\
    apk add --no-cache openjdk17-jre

ARG JAR_FILE=build/libs/*jar
COPY ${JAR_FILE} app.jar

CMD java -jar /java-17/src/app/app.jar