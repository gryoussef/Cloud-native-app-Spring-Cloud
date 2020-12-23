FROM openjdk:8-jre-alpine
ADD target/eureka-server.jar  eureka-server.jar
EXPOSE 8763
ENTRYPOINT ["java","-jar", "eureka-server.jar"]