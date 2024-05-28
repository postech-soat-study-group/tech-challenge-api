package postech.soat.tech.challenge.persistence.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import postech.soat.tech.challenge.model.order.combo.ComboItem;
import postech.soat.tech.challenge.persistence.entity.ProductEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "combo_item")
public class ComboItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "combo_item_id_seq")
    @SequenceGenerator(name = "combo_item_id_seq", sequenceName = "combo_item_id_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combo_id", nullable = false)
    private ComboEntity combo;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private Integer quantity;

    public ComboItemEntity(ProductEntity product, Integer quantity, ComboEntity combo) {
        this.product = product;
        this.quantity = quantity;
        this.combo = combo;
    }

    public static ComboItem toComboItem(ComboItemEntity comboItemEntity) {
        return new ComboItem(
                ProductEntity.toProduct(comboItemEntity.getProduct()),
                comboItemEntity.getQuantity()
        );
    }

    public static ComboItemEntity toComboItemEntity(ComboItem comboItem, ComboEntity comboEntity) {
        return new ComboItemEntity(
                ProductEntity.toProductEntity(comboItem.product()),
                comboItem.quantity(),
                comboEntity
        );
    }
}
