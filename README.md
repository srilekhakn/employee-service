# employee-service

Software requirments:
1. Java 17 (Oracle)
2. Latest version of docker (I tested it on Docker version 24.0.2, build cb74dfc current latest)

List of functionalities implemented in the application
1. API's for create, update, get , get all, delete Employee
2. for each crud operation event is triggered to kafka topic employee-events
3. Consumer consumes the employee-events and for delete event the record is deleted from db (Please check here KafkaMessageConsumer.class)
4. Used dockerized mongo db as a Database and Dockerized Kafka for subscriber and consumer
5. Test cases for API's (Controller) and Service class

To run the application :
1. git clone https://github.com/srilekhakn/employee-service
2. cd  employee-service
3. git checkout master
4. ./gradlew build (Executes all the testcases)
5. ./gradlew bootjar
6. mv build/libs/employee-service-0.0.1-SNAPSHOT.jar ./employee-service.jar
7. Open new terminal at this location and run "docker-compose up" (Make sure to have latest version of docker)
8. java -jar employee-service.jar
9. Access the API documentation at http://localhost:8080/swagger-ui/index.html and tryit out in the same page
10. You can see the logs for API requests and message sent to Kafka and consumer consuming data
