# Roman-Conversion Application Objective
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

Roman numerals are written largest to smallest from left to right.  There are some special cases - No numeral can be repeated in succession more than thrice. Thereby, for example - the number four is written as IV and not IIII.
Because the one is on the left of five, we subtract it making four. The same principle applies to the number nine, which is written as IX. 

These are other instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.

Wikipedia Reference - https://en.wikipedia.org/wiki/Roman_numerals

## Requirements

For building and running the application you need:

- JDK 1.8
- [Maven 3](https://maven.apache.org)

## Running the application

Several ways to run a Spring Boot application. If running locally - One way is to execute the `main` method in the `com.service.romanconversion.RomanConversionApplication` class from your IDE.
Alternatively we can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) so:

```shell
mvn spring-boot:run
mvn spring-boot:run -Dapp.profiles=<<Choose Your Profile - Dev, Prod , Test >>
java -jar target/roman-conversion-0.0.1-SNAPSHOT.jar
```

## Running the application in Docker
Deploy in docker locally by installing docker desktop and cd to projects directory (where DockerFile is present) and run the bellow commands

```shell
docker login

docker build --tag=roman-conversion:latest .
docker run -p 8081:80 romain-conversion
```

## Considerations / Approach 

1) In terms of application performance, if we do want to consider cache - the consideration is two folds :
   - Since the input values are rage bound ( 1 to 3999) - maybe have a cache of the entire valid input integers - run this once on start up (or)
   - Maintain a cache of the result for a given input value ( Chose this approach )
2) Have a TreeMap, load all the reference Roman Numerals ( this will be a one time activity, in our case on start up ). Treemap has a handy floor method that we used in our logic to calculate roman numeral.
3) The side effect of using Spring's default Cache ( vs may be Redis or any other cache mechanisms) was either do a
  - Scheduled cache evict
  - An API end point to help clear the cache on demand. (chose this approach)
  - Tested whether local caching is happening at all by introducing a time delay and saw that subsequent calls that matched cache criteria were being served from cache

## Swagger UI 

http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

To chek the health of the application, 1) use springboot provided [actuator](http://localhost:8080/actuator/health) or 2) App defined [HealthCheck](http://localhost:8080/)

## Code Quality
- Sonar Lint  / Sonar Qube plugins for static code analysis
- Code coverage plugins to measure code coverage 

# Monitoring & Metrics 
- Relying on springboot provided [Health](http://localhost:8081/actuator/health) [Metrics](http://localhost:8081/actuator/metrics) end points. 
- These management endpoints have been enabled in application.properties. 

For metrics, Spring Boot Actuator provides an auto-configuration for Micrometer, that serves as ab application metrics facade with support for various monitoring systems. We may use any of the supported systems,
included in application.properties are sample configurations for New Relic.

[Actuator Metrics](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.metrics)

# Dependencies

ed spring-boot-actuator module, that provides all of Spring Boot’s production-ready features.

```shell
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
```
Spring boot - Swagger UI dependency 

```shell
	<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.5.2</version>
		</dependency>
```
Thyme leaf for custom error.html

```shell
    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```
