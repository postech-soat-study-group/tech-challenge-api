package postech.soat.tech.challenge.port.input;

import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.port.output.ProductRepository;

public final class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}