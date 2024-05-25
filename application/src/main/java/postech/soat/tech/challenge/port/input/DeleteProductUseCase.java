package postech.soat.tech.challenge.port.input;

import postech.soat.tech.challenge.port.output.ProductRepository;

public final class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}