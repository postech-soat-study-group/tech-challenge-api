# Stage 1: Build
FROM amazoncorretto:21-alpine-jdk as build

WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .

COPY application/build.gradle application/
COPY domain/build.gradle domain/
COPY infrastructure/build.gradle infrastructure/

RUN chmod +x ./gradlew
RUN ./gradlew downloadDependencies

COPY domain/src domain/src
COPY infrastructure/src infrastructure/src

RUN ./gradlew clean build

# Stage 2: Run
FROM amazoncorretto:21-alpine-jdk

RUN addgroup -S fiap && adduser -S tech-challenge -G fiap
USER tech-challenge

EXPOSE 8080
WORKDIR /app
COPY --from=build /workspace/app/infrastructure/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
