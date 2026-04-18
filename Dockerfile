# Use a lightweight Java runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy your jar file
COPY target/your-app.jar app.jar

# Expose port (match your Spring Boot port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
