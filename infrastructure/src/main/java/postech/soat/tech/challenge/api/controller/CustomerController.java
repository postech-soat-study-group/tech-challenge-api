package postech.soat.tech.challenge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.request.CustomerDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.port.input.CreateCustomerUseCase;
import postech.soat.tech.challenge.port.input.FindCustomerUseCase;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerUseCase findCustomerUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = createCustomerUseCase.createCustomer(customerDTO.toCustomer());
        return new ApiResponse<>(CustomerDTO.toCustomerDTO(createdCustomer));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CustomerDTO> getCustomerByCpf(@RequestParam(required = false) String cpf) {
        Optional<Customer> customer = findCustomerUseCase.findByCpf(cpf);
        return customer.map(value -> new ApiResponse<>(CustomerDTO.toCustomerDTO(value))).orElseGet(ApiResponse::new);
    }
}