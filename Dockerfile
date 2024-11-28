FROM openjdk:22-jdk AS builder
RUN apt-get update && apt-get install -y maven

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk
COPY --from=builder target/smarthome_backend-0.0.1-SNAPSHOT.jar /smarthome.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/smarthome.jar"]
