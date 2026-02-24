# Step 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Only copy the pom.xml first to cache dependencies (makes builds faster)
COPY pom.xml .
RUN mvn dependency:go-offline

# Now copy the rest of the code
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Use the PORT environment variable provided by Render
CMD ["java", "-jar", "app.jar", "--server.port=${PORT}"]