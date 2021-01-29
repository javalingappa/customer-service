# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/product_catalog-1.1.0-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

COPY src/main/resources/application.properties application.properties

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

WORKDIR /opt/app/

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]