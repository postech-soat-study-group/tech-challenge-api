package postech.soat.tech.challenge.port.input;

import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.port.output.CustomerRepository;

import java.util.Optional;

public class FindCustomerUseCase {
    private final CustomerRepository customerRepository;

    public FindCustomerUseCase(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }

    public Optional<Customer> findByCpf(String cpf) { return customerRepository.findByCpf(cpf); }
}
