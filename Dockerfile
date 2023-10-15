FROM openjdk:20
EXPOSE 8080
ADD build/libs/employee-service-*.jar employee-service.jar
ENTRYPOINT ["java","-jar","employee-service.jar"]