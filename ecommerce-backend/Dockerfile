# Multi-stage build: compile with Maven, run with a minimal JRE
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml mvnw* ./
COPY .mvn .mvn
COPY src ./src
# Use Maven to build the jar
RUN mvn -B -DskipTests package

# Runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copy jar produced by the build stage (assumes standard target name)
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
