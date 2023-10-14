FROM openjdk:20
EXPOSE 8080
ARG JAR_FILE=target/employee-service.jar
ADD ${JAR_FILE} employee-service.jar
ENTRYPOINT ["java","-jar","/employee-service.jar"]