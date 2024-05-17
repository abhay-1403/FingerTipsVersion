# Start with the official OpenJDK Docker image, with tag 17-jdk-alpine
FROM openjdk:17-jdk-alpine

# Argument for specifying the application's WAR file
ARG WAR_FILE=target/*.war

# Copy application WAR into the container
COPY ${WAR_FILE} app.war

# Specify the command that should be executed when the Docker container starts up
ENTRYPOINT ["java","-jar","/app.war"]