# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage (slim JRE only)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/newsagservice.jar /app/newsagservice.jar
ENTRYPOINT ["java", "-jar", "/app/newsagservice.jar"]
