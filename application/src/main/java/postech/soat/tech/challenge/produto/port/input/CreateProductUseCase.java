package postech.soat.tech.challenge.produto.port.input;

import postech.soat.tech.challenge.pedido.model.Product;
import postech.soat.tech.challenge.produto.port.output.ProductRepository;

public final class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduto(Product product) {
        return productRepository.save(product);
    }
}