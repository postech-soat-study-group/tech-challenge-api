package postech.soat.tech.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import postech.soat.tech.challenge.persistence.repository.JpaCustomerRepository;
import postech.soat.tech.challenge.persistence.repository.JpaCustomerRepositoryAdapter;
import postech.soat.tech.challenge.persistence.repository.JpaProductRepository;
import postech.soat.tech.challenge.persistence.repository.JpaProductRepositoryAdapter;
import postech.soat.tech.challenge.persistence.repository.order.JpaOrderRepository;
import postech.soat.tech.challenge.persistence.repository.order.JpaOrderRepositoryAdapter;
import postech.soat.tech.challenge.port.input.*;
import postech.soat.tech.challenge.port.input.order.CreateOrderUseCase;
import postech.soat.tech.challenge.port.input.order.FindOrderUseCase;
import postech.soat.tech.challenge.port.input.order.UpdateOrderUseCase;
import postech.soat.tech.challenge.port.output.CustomerRepository;
import postech.soat.tech.challenge.port.output.ProductRepository;
import postech.soat.tech.challenge.port.output.order.OrderRepository;

@Configuration
@EnableJpaRepositories(basePackages = "postech.soat.tech.challenge.persistence.repository")
public class UseCaseConfig {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public UseCaseConfig(
            JpaProductRepository jpaProductRepository,
            JpaCustomerRepository jpaCustomerRepository,
            JpaOrderRepository jpaOrderRepository
    ) {
        this.productRepository = new JpaProductRepositoryAdapter(jpaProductRepository);
        this.customerRepository = new JpaCustomerRepositoryAdapter(jpaCustomerRepository);
        this.orderRepository = new JpaOrderRepositoryAdapter(jpaOrderRepository);
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
    public FindCustomerUseCase findCustomerUseCase() {
        return new FindCustomerUseCase(customerRepository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new CreateOrderUseCase(orderRepository, productRepository, customerRepository);
    }

    @Bean
    public FindOrderUseCase findOrderUseCase() { return new FindOrderUseCase(orderRepository); }

    @Bean
    public UpdateOrderUseCase updateOrderUseCase() { return new UpdateOrderUseCase(orderRepository); }
}