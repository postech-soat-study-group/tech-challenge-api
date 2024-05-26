package postech.soat.tech.challenge.port.input;

import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.port.output.ProductRepository;

public final class UpdateProductUseCase {

    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}