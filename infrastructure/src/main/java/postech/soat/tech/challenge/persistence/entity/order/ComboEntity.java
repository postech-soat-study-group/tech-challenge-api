package postech.soat.tech.challenge.persistence.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.model.order.combo.ComboItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "combo")
public class ComboEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "combo_id_seq")
    @SequenceGenerator(name = "combo_id_seq", sequenceName = "combo_id_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComboItemEntity> items;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ComboEntity(Long id, List<ComboItemEntity> items, OrderEntity order) {
        this.id = id;
        this.items = items;
        this.order = order;
    }

    public static Combo toCombo(ComboEntity comboEntity) {
        List<ComboItem> comboItems = comboEntity.getItems().stream()
                .map(ComboItemEntity::toComboItem)
                .collect(Collectors.toList());

        return new Combo(comboItems);
    }

    public static ComboEntity toComboEntity(Combo combo, OrderEntity order) {
        ComboEntity comboEntity = new ComboEntity();
        List<ComboItemEntity> comboItemEntities = combo.getItems().stream()
                .map(comboItem -> ComboItemEntity.toComboItemEntity(comboItem, comboEntity))
                .collect(Collectors.toList());

        comboEntity.setItems(comboItemEntities);
        comboEntity.setOrder(order);

        return comboEntity;
    }

}
