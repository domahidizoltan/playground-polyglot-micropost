FROM openjdk:8u171-jdk-alpine3.7 AS build
WORKDIR /app
COPY . .

RUN apk --no-cache add ca-certificates wget && \
wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub && \
wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-2.29-r0.apk && \
apk add glibc-2.29-r0.apk

RUN ./gradlew shadowJar -x test -x generateDbJooqSchemaSource

###
FROM openjdk:8u171-jre-alpine3.7

WORKDIR /app
COPY --from=build /app/build/libs/micropost-service-0.1-all.jar micropost-service.jar

EXPOSE 8010
ENTRYPOINT java -jar /app/micropost-service.jar
