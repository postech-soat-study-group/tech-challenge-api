package postech.soat.tech.challenge.integration.tests;


import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.config.DefaultResources;
import postech.soat.tech.challenge.persistence.entity.CustomerEntity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class SampleIntegrationTest extends DefaultResources {

    @Test
    void shouldGetAllCustomers() {
        TypeRef<ApiResponse<CustomerEntity>> typeReference = new TypeRef<>() {};
        CustomerEntity createdCustomer = given()
                .contentType(ContentType.JSON)
                .body(new CustomerEntity(0, "1111111", "John Doe", "email@email.com", "123456"))
                .when()
                .post("/api/sample/customer")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(typeReference)
                .getData();


        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/sample/customer/all")
                .then()
                .statusCode(200)
                .and()
                .body("data", hasSize(1))
                .body("data[0].name", equalTo(createdCustomer.getName()))
                .body("data[0].email", equalTo(createdCustomer.getEmail()))
                .body("data[0].phone", equalTo(createdCustomer.getPhone()))
                .body("data[0].cpf", equalTo(createdCustomer.getCpf()))
                .body("data[0].id", equalTo(1))
                .body("status", equalTo("SUCCESS"));
    }

}
