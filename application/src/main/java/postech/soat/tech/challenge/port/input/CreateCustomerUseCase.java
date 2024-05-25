package postech.soat.tech.challenge.port.input;

import lombok.RequiredArgsConstructor;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.port.output.CustomerRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        Optional<Customer> optExistingCustomer = customerRepository.findByCpf(customer.getCpf());
        if(optExistingCustomer.isPresent()) {
            return optExistingCustomer.get();
        }
        return customerRepository.save(customer);
    }

}
