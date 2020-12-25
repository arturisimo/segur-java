FROM openjdk:8-jdk-alpine
ADD target/microservicio-sensores-0.0.1-SNAPSHOT.jar app.jar

RUN apk --no-cache add curl

EXPOSE 8100
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
CMD ["/bin/bash"]

#ENV WAIT_VERSION 2.7.2
#ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
#RUN chmod +x /wait