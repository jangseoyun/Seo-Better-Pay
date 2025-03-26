FROM eclipse-temurin:17-jdk AS builder
ARG JAR_FILE
COPY ${JAR_FILE} /seo-better-pay.jar
ENTRYPOINT ["java","-jar", "/seo-better-pay.jar"]