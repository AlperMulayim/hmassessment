FROM openjdk:17
COPY dressrecommenderapi/target/dressrecommenderapi-0.0.1-SNAPSHOT.jar  /dressrecommenderapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dressrecommenderapi-0.0.1-SNAPSHOT.jar"]