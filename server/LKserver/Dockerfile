# Use the official OpenJDK base image with Java 11 as the runtime
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from your local build context to the container
COPY . .

# Expose port 8000 for the Spring Boot application
EXPOSE 8000

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]