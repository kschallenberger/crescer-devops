# FROM maven:3.8.4-openjdk-17-slim
# WORKDIR /app
# COPY pom.xml /app
# RUN mvn dependency:go-offline
# COPY src/ ./src/
# RUN mvn package
# EXPOSE 80
# CMD ["java", "-jar", "target/hellodocker-0.0.1-SNAPSHOT.jar", "--server.port=80"]

FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build app/target/hellodocker-0.0.1-SNAPSHOT.jar /app/hellodocker-0.0.1.jar
EXPOSE 80
ENTRYPOINT [ "java","-jar","/app/hellodocker-0.0.1.jar","--server.port=80" ]
