package postech.soat.tech.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import postech.soat.tech.challenge.persistence.InMemoryProdutoRepository;
import postech.soat.tech.challenge.produto.port.input.CreateProdutoUseCase;
import postech.soat.tech.challenge.produto.port.input.FindProdutoUseCase;
import postech.soat.tech.challenge.produto.port.output.ProdutoRepository;
import postech.soat.tech.challenge.produto.service.ProdutoService;

@Configuration
public class UseCaseConfig {

    private static final ProdutoRepository PRODUTO_REPOSITORY = new InMemoryProdutoRepository();

    @Bean
    public CreateProdutoUseCase createProdutoUseCase() {
        return new ProdutoService(PRODUTO_REPOSITORY);
    }
    @Bean
    public FindProdutoUseCase findProdutoUseCase() {
        return new ProdutoService(PRODUTO_REPOSITORY);
    }
}