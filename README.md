Build with:
* Java: 1.8
* Maven: 3.6.1
* Docker Client/Server: 18.09.2
* Docker API version: 1.39

How to run it:
1. 'cd falcon-task' ---> 'mvn clean package'
2. 'cd falcon-task-producer' ---> 'mvn clean package docker:build'
3. 'cd ../falcon-task-consumer' ---> 'mvn clean package docker:build'
4. 'cd ..' ---> 'docker-compose up -d'

Kafka Topics UI:
* http://localhost:8085/#/

PHP My Admin (for MySql):
* http://localhost:9191
* user: falcon
* pass: falcon

H2 Management Console:
* http://localhost:8080/console
* URL - jdbc:h2:mem:falcon
* user: sa
* pass: