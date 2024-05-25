package postech.soat.tech.challenge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import postech.soat.tech.challenge.api.request.CustomerDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.port.input.CreateCustomerUseCase;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = createCustomerUseCase.createCustomer(customerDTO.toCustomer());
        return new ApiResponse<>(CustomerDTO.toCustomerDTO(createdCustomer));
    }
}