# Tech challenge API
This project represents the application developed during the Postgraduate of Software Architecture, FIAP - SOAT 7 - Group 41.
His purpose is to serve a local food (like a restaurant) application, with initial controllers to serve routes to manage products, customers and orders.

## Pre-requisites
This project runs on Java 21 and Docker. Locally, you can run whatever JDK implementation you prefer. You should also install  
docker compose to run the project with all the dependencies.

Since we are using gradlew, you don't need to install Gradle. The project will download the necessary dependencies for you.
## How to run
### Terminal
Follow the steps below to run the project in your terminal:

Build:
```bash
./gradlew clean build
```

Run:
```bash
./gradlew :infrastructure:bootRun
```

### IntelliJ
There is no special configuration needed. But keep in mind that the lifecycle of the application is managed by the `infrastructure` 
module. So to run the application, you should run the `infrastructure` module. 

In practice, this is the Main class:
```bash 
java/postech/soat/tech/challenge/Main.java
```

### Docker
The easiest way to run the project is by using Docker. The `docker-compose.yml` file is already configured to run the
application with all the dependencies.

Just run the following command:
```bash
docker-compose up --build
```

To create a postgres database locally for testing purpose, you can run the following command:
```bash
docker run --name postgres-burger-store -p 5432:5432 -e POSTGRES_USER=burger-user -e POSTGRES_PASSWORD=burger-password -e POSTGRES_DB=burger-store -d postgres:16.3
```

:warning: The first build is always slower than the subsequent ones. This is because there is no local cache to speed up the process.
## Documentation
The Open API 3 documentation is available at `v3/api-docs` and the Swagger interface at the `/swagger-ui.html` endpoint. 

Example:
```bash
curl http://localhost:8080/v3/api-docs
```

## Health Checks
We are using the [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html) 
to provide health checks for the application. The health checks are available at the `/actuator/health` endpoint.

Example:
```bash 
curl http://localhost:8080/actuator/health
```
Response:
```{"status":"UP"}```

**Note**: Once we add the database, we will have to add a custom health check to check the database connection.
