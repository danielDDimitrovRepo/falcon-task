Build with:
* Java: 1.8
* Maven: 3.6.1
* Docker Client/Server: 18.09.2
* Docker API version: 1.39

How to run it:
1. 'cd falcon-task' ---> 'mvn clean install'
2. 'cd falcon-task-producer' ---> 'mvn clean package docker:build'
3. 'cd ../falcon-task-consumer' ---> 'mvn clean package docker:build'
4. 'cd ../falcon-task-view' ---> 'mvn clean package docker:build'
5. 'cd ..' ---> 'docker-compose up -d'

How To Use:
* Make a POST request to 'http://localhost:8080/camel/producer/send-falcon-message' with 'application/json' body, e.g.: {"message": "test"}
* Validate that the message is received and saved by performing a GET request to 'http://localhost:8081/view/falcon'
* You can also use PHP My Admin to validate that the message was persisted and Kafka Topic UI to inspect the kafka topic

Kafka Topics UI:
* http://localhost:8085/#/

PHP My Admin (for MySql):
* http://localhost:9191
* user: falcon
* pass: falcon