package postech.soat.tech.challenge.persistence.repository;

import org.springframework.stereotype.Component;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.persistence.entity.CustomerEntity;
import postech.soat.tech.challenge.port.output.CustomerRepository;

import java.util.Optional;

@Component
public class JpaCustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;

    public JpaCustomerRepositoryAdapter(JpaCustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.toCustomerEntity(customer);
        customerEntity = jpaCustomerRepository.save(customerEntity);
        return CustomerEntity.toCustomer(customerEntity);
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        Optional<CustomerEntity> optCustomerEntity = jpaCustomerRepository.findByCpf(cpf);
        return optCustomerEntity.map(CustomerEntity::toCustomer);
    }

    @Override
    public Optional<Customer> findById(long customerId) {
        Optional<CustomerEntity> optCustomerEntity = jpaCustomerRepository.findById(customerId);
        return optCustomerEntity.map(CustomerEntity::toCustomer);
    }
}
