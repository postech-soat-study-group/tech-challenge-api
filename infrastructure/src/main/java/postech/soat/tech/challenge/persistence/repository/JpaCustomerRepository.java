package postech.soat.tech.challenge.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import postech.soat.tech.challenge.persistence.entity.CustomerEntity;

import java.util.Optional;

public interface JpaCustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCpf(String cpf);
}
