FROM eclipse-temurin:17-jdk as builder
ARG JAR_FILE
COPY ${JAR_FILE} /seo-better-pay.jar
ENTRYPOINT ["java","-jar", "/seo-better-pay.jar"]