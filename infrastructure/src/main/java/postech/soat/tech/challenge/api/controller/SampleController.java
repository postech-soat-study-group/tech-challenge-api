package postech.soat.tech.challenge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.persistence.entity.Customer;
import postech.soat.tech.challenge.persistence.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sample")
@RequiredArgsConstructor
public class SampleController {


    private final CustomerRepository customerRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> sampleEndpoint() {
        return new ApiResponse<>("Hello, World!");
    }

    @PostMapping("/customer")
    public ApiResponse<Customer> addCustomer(@RequestBody Customer customer) {
        return new ApiResponse<>(customerRepository.save(customer));
    }

    @GetMapping("/customer/all")
    public ApiResponse<List<Customer>> listCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return new ApiResponse<>(customers);
    }
}
