package postech.soat.tech.challenge.usecases;

import org.junit.jupiter.api.Test;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.port.input.CreateCustomerUseCase;
import postech.soat.tech.challenge.port.output.CustomerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateCustomerUseCaseTest {


    @Test
    public void shouldCreateCustomer() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerRepository);
        Customer customer = new Customer(0, "John Doe", "81651786011", "email", "123456");
        when(customerRepository.findByCpf(customer.getCpf())).thenReturn(Optional.empty());
        when(customerRepository.save(customer)).thenReturn(new Customer(1, "John Doe", "81651786011", "email", "123456"));
        Customer customerCreated = createCustomerUseCase.createCustomer(customer);
        assertEquals(customerCreated.getId(), 1);
    }

    @Test
    public void shouldReturnCustomerAlreadyCreatedInCaseTriesToCreateItWithTheSameCPF() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerRepository);
        Customer customer = new Customer(0, "John Doe", "81651786011", "email", "123456");
        when(customerRepository.findByCpf(customer.getCpf())).thenReturn(Optional.of(new Customer(1, "John Doe", "81651786011", "email", "123456")));
        Customer customerCreated = createCustomerUseCase.createCustomer(customer);
        assertEquals(customerCreated.getId(), 1);
    }


}
