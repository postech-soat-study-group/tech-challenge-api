## Tech challenge API

### Pre-requisites
This project runs on Java 21 and Docker. Locally, you can run whatever JDK implementation you prefer. You should also install  
docker compose to run the project with all the dependencies.

Since we are using gradlew, you don't need to install Gradle. The project will download the necessary dependencies for you.
### How to run

#### Terminal
Follow the steps below to run the project in your terminal:

Build:
```bash
./gradlew clean build
```

Run:
```bash
./gradlew :infrastructure:bootRun
```

#### IntelliJ
There is no special configuration needed. But keep in mind that the lifecycle of the application is managed by the `infrastructure` 
module. So to run the application, you should run the `infrastructure` module. 

In practice, this is the Main class:
```bash 
java/postech/soat/tech/challenge/Main.java
```

#### Docker

### Documentation
The Open API 3 documentation is available at `v3/api-docs` and the Swagger interface at the `/swagger-ui.html` endpoint. 

Example:
```bash
curl http://localhost:8080/v3/api-docs
```

### Health Checks
We are using the [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html) 
to provide health checks for the application. The health checks are available at the `/actuator/health` endpoint.

Example:
```bash 
curl http://localhost:8080/actuator/health
```
Response:
```{"status":"UP"}```

**Note**: Once we add the database, we will have to add a custom health check to check the database connection.
