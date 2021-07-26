FROM maven:3.6.0-jdk-8-slim AS build

LABEL maintainer="Hugo Moraes"

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -DskipTests -P docker

FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/clients-handler-0.0.1-SNAPSHOT.jar clients-handler-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","clients-handler-0.0.1-SNAPSHOT.jar"]