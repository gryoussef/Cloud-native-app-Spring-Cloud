FROM openjdk:8-jre-alpine
ADD target/api-gateway.jar  api-gateway.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar", "api-gateway.jar"]