FROM openjdk:17-slim AS build

WORKDIR /app

# Copy the specific JAR file into the container
COPY build/libs/security-service-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
