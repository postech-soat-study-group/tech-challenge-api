package postech.soat.tech.challenge.persistence.repository;

import org.springframework.stereotype.Component;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.persistence.entity.ProductEntity;
import postech.soat.tech.challenge.port.output.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public JpaProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductEntity.toProductEntity(product);
        jpaProductRepository.save(productEntity);
        return ProductEntity.toProduct(productEntity);
    }

    @Override
    public List<Product> findAll() {
        return StreamSupport.stream(jpaProductRepository.findAll().spliterator(), false)
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Optional<ProductEntity> productEntity = jpaProductRepository.findById(id);
        productEntity.ifPresent(jpaProductRepository::delete);
    }

    @Override
    public List<Product> findAllByCategory(String category) {
        return jpaProductRepository.findAllByCategory(category).stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(ProductEntity::toProduct);
    }
}
