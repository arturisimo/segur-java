FROM openjdk:8-jdk-alpine
ADD target/zuul-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 7000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]