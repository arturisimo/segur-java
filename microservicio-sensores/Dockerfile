FROM openjdk:8-jdk-alpine
ADD target/microservicio-sensores-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8100
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]