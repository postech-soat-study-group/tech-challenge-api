package postech.soat.tech.challenge.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import postech.soat.tech.challenge.persistence.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
