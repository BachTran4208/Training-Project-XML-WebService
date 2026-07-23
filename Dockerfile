# BUILD STAGE
FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /app

COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B dependency:go-offline

COPY src ./src
RUN mvn clean package


# RUNTIME STAGE
FROM eclipse-temurin:25-jre
WORKDIR /app

## default if not passed through build-arg
ARG USER_UID=2000
ARG GROUP_GID=2000

RUN apt-get update && apt-get install -y curl \
    && groupadd -g ${GROUP_GID} appgroup \
    && useradd -u ${USER_UID} -g appgroup appuser \
    && rm -rf /var/lib/apt/lists/*


# RUN groupadd -g ${GROUP_GID} appgroup && useradd -u ${USER_UID} -g appgroup appuser

COPY --from=builder --chown=appuser:appgroup /app/target/*.jar app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java","-jar", "app.jar"]
