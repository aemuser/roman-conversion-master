# Point Mechanism
• Find and reference a specification for Roman numerals online. Wikipedia is acceptable.
• Provide clear and concise documentation:  

README.md with:

• How to build and run your project.
• Your engineering and testing methodology
• Your packaging layout
• Dependency attribution
Inline documentation in your source code
• Tests
• Error Handling

## Extension

Include additional DevOps capabilities in your project to represent how you
would prepare your project for ease of operation in a production environment (e.g. metrics,
monitoring, logging, etc.). Add tooling to build a runnable Docker container for your service if
you are familiar with Docker.

# Design Considerations

In terms of performance, if we do want to consider cache - the consideration is two folds 

1) Since the input values are rage bound - should we may have a cache of the entire valid input integers vs 
2) Maintain a cache of the input values that we have processed - i.e. cache is request based 

Went with #2.


# Roman-Conversion Application

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

