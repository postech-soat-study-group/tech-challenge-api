package postech.soat.tech.challenge.port.output;

import postech.soat.tech.challenge.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    List<Product> findAll();

    void delete(Long id);

    List<Product> findAllByCategory(String category);

    Optional<Product> findById(Long id);
}