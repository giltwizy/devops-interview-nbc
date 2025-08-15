# ===== Stage 1: Build =====
FROM eclipse-temurin:24-jdk-alpine AS builder

# Install Maven
RUN apk update && apk add --no-cache maven

# Set working directory for build
WORKDIR /build

# Copy pom.xml and source files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ===== Stage 2: Runtime =====
FROM eclipse-temurin:24-jdk-alpine

# Set timezone
ENV TZ="Africa/Dar_es_Salaam"

# Create app directory and non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup \
    && mkdir /app \
    && chown -R appuser:appgroup /app

# Copy jar from build stage
COPY --from=builder /build/target/*.jar /app/app.jar

# Switch to non-root user
USER appuser

# Expose volume
VOLUME ["/app"]

EXPOSE 8765

# Run application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]