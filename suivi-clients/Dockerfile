FROM openjdk:8-jre-alpine
ADD target/bureauEtude-backend.jar  bureauEtude-backend.jar
EXPOSE 7777
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "bureauEtude-backend.jar"]
