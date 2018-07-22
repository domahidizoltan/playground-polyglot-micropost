FROM openjdk:8u171-jdk-alpine3.7
WORKDIR /
ADD build/libs/micropost-service-0.1-all.jar micropost-service.jar
EXPOSE 8010
ENTRYPOINT java -jar micropost-service.jar
