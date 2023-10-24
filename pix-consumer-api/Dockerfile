FROM alpine:3.16.2

WORKDIR /java-17/src/app/

RUN apk update --no-cache &&\
    apk upgrade --no-cache &&\
    apk add --no-cache openjdk17-jre

COPY /build/libs/pix-consumer-api-0.0.1-SNAPSHOT.jar /java-17/src/app/

CMD java -jar /java-17/src/app/pix-consumer-api-0.0.1-SNAPSHOT.jar