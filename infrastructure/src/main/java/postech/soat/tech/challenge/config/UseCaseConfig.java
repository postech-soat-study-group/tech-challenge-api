package postech.soat.tech.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import postech.soat.tech.challenge.persistence.InMemoryProductRepository;
import postech.soat.tech.challenge.produto.port.input.CreateProductUseCase;
import postech.soat.tech.challenge.produto.port.input.FindProductUseCase;
import postech.soat.tech.challenge.produto.port.output.ProductRepository;

@Configuration
public class UseCaseConfig {

    private static final ProductRepository PRODUTO_REPOSITORY = new InMemoryProductRepository();

    @Bean
    public CreateProductUseCase createProdutoUseCase() {
        return new CreateProductUseCase(PRODUTO_REPOSITORY);
    }

    @Bean
    public FindProductUseCase findProdutoUseCase() {
        return new FindProductUseCase(PRODUTO_REPOSITORY);
    }
}