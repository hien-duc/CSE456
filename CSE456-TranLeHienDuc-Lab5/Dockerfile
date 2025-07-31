# Base image with OpenJDK 21
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the packaged JAR into container
COPY target/CSE456-TranLeHienDuc-Lab5-1.0-SNAPSHOT.jar app.jar

# Command to run your app
ENTRYPOINT ["java", "-jar", "app.jar"]
