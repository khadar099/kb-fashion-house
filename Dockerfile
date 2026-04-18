# Use a lightweight Java runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy your jar file
COPY target/kb-fashion-house-0.0.1-SNAPSHOT.jar  kb-fashion-house-0.0.1-SNAPSHOT.jar

# Expose port (match your Spring Boot port)
EXPOSE 8181

# Run the application
ENTRYPOINT ["java","-jar","kb-fashion-house-0.0.1-SNAPSHOT.jar"]
