FROM openjdk:8
COPY ./target/demo-0.0.1-SNAPSHOT.jar game.jar
EXPOSE 8081
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://game-container-db:27017/","-jar","-Djava.rmi.server.hostname=0.0.0.0", "game.jar"]