# springboot-sample-app

[![Build Status]()]()
[![Coverage Status]()]()

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- JDK 1.8
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
mvn spring-boot:run -Dapp.profiles=test
java -jar target/roman-conversion-0.0.1-SNAPSHOT.jar
```
## Swagger UI 

http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

To chek the health of the application, 1) use springboot provided [actuator](http://localhost:8080/actuator/health) or 2) App defined [HealthCheck](http://localhost:8080/)
## Deploying the application

