FROM openjdk:22-jdk AS builder

RUN apt-get update && apt-get install -y curl unzip
RUN curl -sL https://dlcdn.apache.org/maven/maven-3/3.9.0/binaries/apache-maven-3.9.0-bin.tar.gz | tar xz -C /opt/ \
    && ln -s /opt/apache-maven-3.9.0/bin/mvn /usr/bin/mvn

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk
COPY --from=builder target/smarthome_backend-0.0.1-SNAPSHOT.jar /smarthome.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/smarthome.jar"]
