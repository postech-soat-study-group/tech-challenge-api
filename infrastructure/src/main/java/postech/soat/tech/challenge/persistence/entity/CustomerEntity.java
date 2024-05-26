package postech.soat.tech.challenge.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import postech.soat.tech.challenge.model.Customer;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    private long id;
    @Column
    private String cpf;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;

    public static CustomerEntity toCustomerEntity(Customer customer) {
        return new CustomerEntity(customer.getId(), customer.getCpf(), customer.getName(), customer.getEmail(), customer.getPhone());
    }

    public static Customer toCustomer(CustomerEntity customerEntity) {
        return new Customer(customerEntity.getId(), customerEntity.getName(), customerEntity.getCpf(), customerEntity.getEmail(), customerEntity.getPhone());
    }

}
