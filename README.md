# Objective
Expose a REST End Point , which converts a given integer input to it's equivalent Roman Numeral Literal

## Roman Numeral Reference
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

```shell
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

Roman numerals are written largest to smallest from left to right. 
There are some special cases - No numeral can be repeated in succession more than thrice. Thereby, for example - the number four is written as IV and not IIII.
Because the one is on the left of five, we subtract it making four. 

The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.

## Considerations

In terms of performance, if we do want to consider cache - the consideration is two folds 

1) Since the input values are rage bound ( 1 to 3999) - should we maybe have a cache of the entire valid input integers (or)
2) Maintain a cache of the input values that we have processed - i.e. cache is request based ( the Result for a given input is cached) - chose this way.
3) Have a TreeMap, load all the reference Roman Numerals ( this will be a one time activity, in our case on start up ). Treemap has a handy floor method.
4) The side effect of using Spring's default Cache ( vs may be Redis or Caffiene or other cache mechanisms) was either do a 
- Scheduled cache evict
- An API end point to help clear the cache on demand. (chose this way)
- Tested whether caching is happening at all by introducing a time delay.
- 
5) Time permitting would like to add a kill switch or a feature flag to enable / disable the service (from a configuration)

## Code Quality 
- Sonar Lint  / Sonar Qube plugins on local for static code analysis
- Intellij code coverage plugins to measure code coverage 

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

## Running the application in Docker
Depoly in docker locally by installing docker desktop and cd to projects directory (where DockerFile is present) and run the bellow commands

```shell
docker login

docker build --tag=roman-conversion:latest .
docker run -p 8081:80 romain-conversion
```

## Swagger UI 

http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

To chek the health of the application, 1) use springboot provided [actuator](http://localhost:8080/actuator/health) or 2) App defined [HealthCheck](http://localhost:8080/)
## Deploying the application

