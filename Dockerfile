FROM openjdk:8-jdk-alpine
COPY target/roman-conversion-0.0.1-SNAPSHOT.jar roman-conversion-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/roman-conversion-0.0.1-SNAPSHOT.jar"]