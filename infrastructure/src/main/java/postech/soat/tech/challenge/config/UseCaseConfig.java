package postech.soat.tech.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import postech.soat.tech.challenge.persistence.repository.JpaCustomerRepository;
import postech.soat.tech.challenge.persistence.repository.JpaCustomerRepositoryAdapter;
import postech.soat.tech.challenge.persistence.repository.JpaProductRepository;
import postech.soat.tech.challenge.persistence.repository.JpaProductRepositoryAdapter;
import postech.soat.tech.challenge.port.input.CreateCustomerUseCase;
import postech.soat.tech.challenge.port.input.CreateProductUseCase;
import postech.soat.tech.challenge.port.input.DeleteProductUseCase;
import postech.soat.tech.challenge.port.input.FindProductUseCase;
import postech.soat.tech.challenge.port.input.order.CreateOrderUseCase;
import postech.soat.tech.challenge.port.output.CustomerRepository;
import postech.soat.tech.challenge.port.input.UpdateProductUseCase;
import postech.soat.tech.challenge.port.output.ProductRepository;

@Configuration
@EnableJpaRepositories(basePackages = "postech.soat.tech.challenge.persistence.repository")
public class UseCaseConfig {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public UseCaseConfig(
            JpaProductRepository jpaProductRepository,
            JpaCustomerRepository jpaCustomerRepository
    ) {
        this.productRepository = new JpaProductRepositoryAdapter(jpaProductRepository);
        this.customerRepository = new JpaCustomerRepositoryAdapter(jpaCustomerRepository);
    }

    @Bean
    public CreateProductUseCase createProductUseCase() {
        return new CreateProductUseCase(productRepository);
    }

    @Bean
    public FindProductUseCase findProductUseCase() {
        return new FindProductUseCase(productRepository);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase() {
        return new UpdateProductUseCase(productRepository);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase() {
        return new DeleteProductUseCase(productRepository);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase() {
        return new CreateCustomerUseCase(customerRepository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new CreateOrderUseCase();
    }
}