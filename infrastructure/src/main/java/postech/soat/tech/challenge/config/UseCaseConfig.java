package postech.soat.tech.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import postech.soat.tech.challenge.persistence.repository.JpaProductRepository;
import postech.soat.tech.challenge.persistence.repository.JpaProductRepositoryAdapter;
import postech.soat.tech.challenge.port.input.CreateProductUseCase;
import postech.soat.tech.challenge.port.input.FindProductUseCase;
import postech.soat.tech.challenge.port.output.ProductRepository;

@Configuration
@EnableJpaRepositories(basePackages = "postech.soat.tech.challenge.persistence.repository")
public class UseCaseConfig {

    @Autowired
    JpaProductRepository jpaProductRepository;
    ProductRepository productRepository;

    @Autowired
    public UseCaseConfig(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.productRepository = new JpaProductRepositoryAdapter(jpaProductRepository);
    }

    @Bean
    public CreateProductUseCase createProductUseCase() {
        return new CreateProductUseCase(productRepository);
    }

    @Bean
    public FindProductUseCase findProductUseCase() {
        return new FindProductUseCase(productRepository);
    }
}