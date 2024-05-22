package postech.soat.tech.challenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "customer")
public class Customer {

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

}
