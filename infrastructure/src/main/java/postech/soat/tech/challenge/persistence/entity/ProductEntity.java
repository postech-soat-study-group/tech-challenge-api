package postech.soat.tech.challenge.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import postech.soat.tech.challenge.model.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    private int quantity;

    public static ProductEntity toProductEntity(Product product) {
        return new ProductEntity(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }

    public static Product toProduct(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getQuantity());
    }
}
