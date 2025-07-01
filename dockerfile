# Use official OpenJDK base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy built JAR file (we'll build it in the next step)
COPY target/tradesocio-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]