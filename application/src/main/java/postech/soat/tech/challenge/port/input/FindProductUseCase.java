package postech.soat.tech.challenge.port.input;

import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.port.output.ProductRepository;

import java.util.List;

public final class FindProductUseCase {

    private final ProductRepository productRepository;

    public FindProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }
}