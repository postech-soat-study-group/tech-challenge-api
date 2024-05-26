package postech.soat.tech.challenge.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
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
    @Column(columnDefinition = "float8")
    private BigDecimal price;
    @Column
    private Integer quantity;
    @Column
    private String category;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ProductEntity(Long id, String name, String description, BigDecimal price, Integer quantity, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public static ProductEntity toProductEntity(Product product) {
        return new ProductEntity(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCategory().name());
    }

    public static Product toProduct(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getQuantity(), Category.valueOf(productEntity.getCategory()));
    }
}
