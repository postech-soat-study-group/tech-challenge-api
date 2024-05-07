## Tech challenge API

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
