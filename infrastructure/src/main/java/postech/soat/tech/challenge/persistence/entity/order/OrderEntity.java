package postech.soat.tech.challenge.persistence.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.OrderStatus;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.persistence.entity.CustomerEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "customer_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_order_id_seq")
    @SequenceGenerator(name = "customer_order_id_seq", sequenceName = "customer_order_id_seq", allocationSize = 1)
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComboEntity> combos;
    @Column(name = "order_value")
    private BigDecimal value;
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    private String status;
    private int timeEstimate;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public OrderEntity(Long id, List<ComboEntity> combos, BigDecimal value, CustomerEntity customer, String status, int timeEstimate) {
        this.id = id;
        this.combos = combos;
        this.value = value;
        this.customer = customer;
        this.status = status;
        this.timeEstimate = timeEstimate;
    }

    public static OrderEntity toOrderEntity(Order order) {
        if (order == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();

        List<ComboEntity> comboEntities = order.getCombos().stream()
                .map(combo -> ComboEntity.toComboEntity(combo, orderEntity))
                .collect(Collectors.toList());

        orderEntity.setId(order.getId());
        orderEntity.setCombos(comboEntities);
        orderEntity.setValue(order.getValue());
        orderEntity.setCustomer(CustomerEntity.toCustomerEntity(order.getCustomer()));
        orderEntity.setStatus(order.getStatus().name());
        orderEntity.setTimeEstimate(order.getTimeEstimate());

        return orderEntity;
    }

    public static Order toOrder(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        List<Combo> combos = orderEntity.getCombos().stream()
                .map(ComboEntity::toCombo)
                .collect(Collectors.toList());

        return new Order(
                orderEntity.getId(),
                combos,
                orderEntity.getValue(),
                CustomerEntity.toCustomer(orderEntity.getCustomer()),
                OrderStatus.valueOf(orderEntity.getStatus()),
                orderEntity.getTimeEstimate(),
                orderEntity.getCreatedAt(),
                orderEntity.getUpdatedAt()
        );
    }

}
