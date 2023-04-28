FROM openjdk:1.8
EXPOSE 8080
ADD target/dev-integration.jar dev-integration.jar
ENTRYPOINT ["java" , "-jar" ,"/dev-integration.jar"]