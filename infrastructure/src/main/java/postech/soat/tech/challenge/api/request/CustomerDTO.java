package postech.soat.tech.challenge.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import postech.soat.tech.challenge.model.Customer;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;

    public static CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getCpf(), customer.getEmail(), customer.getPhone());
    }

    public Customer toCustomer() {
        return new Customer(id, name, cpf, email, phone);
    }
}
