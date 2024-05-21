package postech.soat.tech.challenge.repository;

import org.springframework.data.repository.CrudRepository;
import postech.soat.tech.challenge.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
}
