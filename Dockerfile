# Use a base image with JDK
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file (replace 'your-app.jar' later with actual name)
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
