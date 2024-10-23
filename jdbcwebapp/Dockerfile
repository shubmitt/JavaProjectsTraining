# base image for Java
# Use Maven to build the application
FROM maven:3.8.3-openjdk-17 AS builder

# Set the working directory
WORKDIR /jdbcwebapp

# Copy the pom.xml and any other necessary files to download dependencies
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean install -DskipTests

# Use a lightweight Tomcat image to run the application
FROM tomcat:10.1.24-jdk17

# Remove the default web apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file from the builder stage to the Tomcat webapps directory
COPY --from=builder /jdbcwebapp/target/jdbcwebapp.war /usr/local/tomcat/webapps/

# Expose the port that Tomcat runs on
EXPOSE 8080

# Command to run Tomcat (the default command is already set)
CMD ["catalina.sh", "run"]