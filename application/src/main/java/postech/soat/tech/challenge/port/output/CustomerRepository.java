package postech.soat.tech.challenge.port.output;

import postech.soat.tech.challenge.model.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
}
