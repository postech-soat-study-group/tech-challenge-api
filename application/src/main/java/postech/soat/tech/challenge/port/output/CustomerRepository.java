package postech.soat.tech.challenge.port.output;

import postech.soat.tech.challenge.model.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findById(long customerId);
}
