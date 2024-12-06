FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/src/app

COPY target/backend-*.jar app.jar

CMD ["java","-jar","app.jar"]