package postech.soat.tech.challenge.repository;

import org.springframework.data.repository.CrudRepository;
import postech.soat.tech.challenge.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
