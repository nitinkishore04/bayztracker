FROM openjdk:8
COPY ./target/bayztracker-0.0.1-SNAPSHOT.jar bayztracker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","bayztracker-0.0.1-SNAPSHOT.jar"]