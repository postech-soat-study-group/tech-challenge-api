package postech.soat.tech.challenge.port.input;

import lombok.RequiredArgsConstructor;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.port.output.CustomerRepository;

import java.util.Optional;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    Logger logger = Logger.getLogger(CreateCustomerUseCase.class.getName());
    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        Optional<Customer> optExistingCustomer = customerRepository.findByCpf(customer.getCpf());
        if(optExistingCustomer.isPresent()) {
            logger.warning("Customer already exists with CPF: " + customer.getCpf());
            return optExistingCustomer.get();
        }
        return customerRepository.save(customer);
    }

}
