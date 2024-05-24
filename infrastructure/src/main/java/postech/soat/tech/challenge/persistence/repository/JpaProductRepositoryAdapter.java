package postech.soat.tech.challenge.persistence.repository;

import org.springframework.stereotype.Component;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.persistence.entity.ProductEntity;
import postech.soat.tech.challenge.port.output.ProductRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class JpaProductRepositoryAdapter implements ProductRepository {

    private JpaProductRepository jpaProductRepository;

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
}
