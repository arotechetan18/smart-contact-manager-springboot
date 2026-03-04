# Use official OpenJDK image
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Install Maven and build project
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run jar file
CMD ["java", "-jar", "target/smart-contact-manager-0.0.1-SNAPSHOT.jar"]