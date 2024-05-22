package postech.soat.tech.challenge.integration.tests;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import postech.soat.tech.challenge.config.DefaultResources;
import postech.soat.tech.challenge.entity.Customer;
import postech.soat.tech.challenge.repository.CustomerRepository;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class SampleIntegrationTest extends DefaultResources {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void shouldGetAllCustomers() {

        customerRepository.save(
                new Customer(0, "1111111", "John Doe", "email@email.com", "123456")
        );

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/sample/customer/all")
                .then()
                .statusCode(200)
                .and()
                .body("data", hasSize(1))
                .body("data[0].name", equalTo("John Doe"))
                .body("data[0].email", equalTo("email@email.com"))
                .body("data[0].phone", equalTo("123456"))
                .body("data[0].id", equalTo(1))
                .body("status", equalTo("SUCCESS"))
                .body("data[0].id", equalTo(1));
    }

}
