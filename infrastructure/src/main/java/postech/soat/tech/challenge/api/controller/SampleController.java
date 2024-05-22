package postech.soat.tech.challenge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.entity.Customer;
import postech.soat.tech.challenge.repository.CustomerRepository;

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
    public ApiResponse<Customer> inserirCliente(@RequestBody Customer customer) {
        return new ApiResponse<>(customerRepository.save(customer));
    }
}
