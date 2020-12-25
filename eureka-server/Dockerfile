FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/eureka-server-0.0.1-SNAPSHOT.jar app.jar
RUN apk --no-cache add curl
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
CMD ["/bin/sh"]