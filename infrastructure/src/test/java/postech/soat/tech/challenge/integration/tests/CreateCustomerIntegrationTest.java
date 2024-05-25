package postech.soat.tech.challenge.integration.tests;


import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import postech.soat.tech.challenge.api.request.CustomerDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.config.DefaultResources;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.persistence.entity.CustomerEntity;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class CreateCustomerIntegrationTest extends DefaultResources {

    @Test
    void shouldGetAllCustomers() {
        TypeRef<ApiResponse<CustomerDTO>> typeReference = new TypeRef<>() {};
        CustomerDTO createdCustomer = given()
                .contentType(ContentType.JSON)
                .body(new Customer(0, "1111111", "John Doe", "email@email.com", "123456"))
                .when()
                .post("/api/customer")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .as(typeReference)
                .getData();

        assertThat(createdCustomer.getId()).isEqualTo(1);
        assertThat(createdCustomer.getCpf()).isEqualTo("1111111");
        assertThat(createdCustomer.getName()).isEqualTo("John Doe");
        assertThat(createdCustomer.getEmail()).isEqualTo("email@email.com");
        assertThat(createdCustomer.getPhone()).isEqualTo("123456");
    }

}
