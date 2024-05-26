package postech.soat.tech.challenge.integration.tests;


import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import postech.soat.tech.challenge.api.request.CustomerDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.config.DefaultResources;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateCustomerIntegrationTest extends DefaultResources {

    @Test
    void shouldCreateCustomer() {
        CustomerDTO createdCustomer = createCustomer("81651786011", "John Doe", "email@email.com", "123456");

        assertThat(createdCustomer.getId()).isGreaterThan(0);
        assertThat(createdCustomer.getCpf()).isEqualTo("81651786011");
        assertThat(createdCustomer.getName()).isEqualTo("John Doe");
        assertThat(createdCustomer.getEmail()).isEqualTo("email@email.com");
        assertThat(createdCustomer.getPhone()).isEqualTo("123456");
    }

    @Test
    void shouldReturnCustomerAlreadyCreatedInCaseTriesToCreateItWithTheSameCPF() {
        // Create a customer
        CustomerDTO customerCreatedFirstPlace = createCustomer("81651786011", "John Doe", "email@email.com", "123456");
        // Tries to create it again, but with the same CPF, should return the customer already created
        CustomerDTO customerDTO = createCustomer("81651786011", "John Doe", "email@email.com", "123456");

        assertThat(customerCreatedFirstPlace.getId()).isEqualTo(customerDTO.getId());
        assertThat(customerCreatedFirstPlace.getCpf()).isEqualTo(customerDTO.getCpf());
        assertThat(customerCreatedFirstPlace.getName()).isEqualTo(customerDTO.getName());
        assertThat(customerCreatedFirstPlace.getEmail()).isEqualTo(customerDTO.getEmail());
        assertThat(customerCreatedFirstPlace.getPhone()).isEqualTo(customerDTO.getPhone());
    }

    @ParameterizedTest
    @MethodSource("getInvalidCustomers")
    void shouldNotCreateInvalidCustomer(CustomerDTO invalidCustomerDTO){
        given()
                .contentType(ContentType.JSON)
                .body(invalidCustomerDTO)
                .when()
                .post("/api/customer")
                .then()
                .statusCode(400);
    }

    private static Stream<Arguments> getInvalidCustomers() {
        return Stream.of(
            Arguments.of(new CustomerDTO(0, "", "John Doe", "email", "123456")),
            Arguments.of(new CustomerDTO(0, "1111111", "", "email", "123456")),
            Arguments.of(new CustomerDTO(0, "1111111", "John Doe", "", "")),
            Arguments.of(new CustomerDTO(0, "12345678911", "John Doe", "", ""))
        );
    }

    private CustomerDTO createCustomer(String cpf, String name, String email, String phone) {
        return given()
                .contentType(ContentType.JSON)
                .body(new CustomerDTO(0, name, cpf, email, phone))
                .when()
                .post("/api/customer")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .as(new TypeRef<ApiResponse<CustomerDTO>>() {})
                .getData();
    }

}
