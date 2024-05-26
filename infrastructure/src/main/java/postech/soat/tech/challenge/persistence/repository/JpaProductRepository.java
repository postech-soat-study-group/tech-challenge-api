package postech.soat.tech.challenge.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import postech.soat.tech.challenge.persistence.entity.ProductEntity;

import java.util.List;

public interface JpaProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategory(String category);
}
