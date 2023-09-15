FROM openjdk:17
COPY build/libs/userapi-0.0.1-SNAPSHOT.jar /userapi/src/userapi-0.0.1-SNAPSHOT.jar
CMD java -jar userapi/src/userapi-0.0.1-SNAPSHOT.jar
