package postech.soat.tech.challenge.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.model.InvalidModelException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private Long id;
    private List<Combo> combos;
    private BigDecimal value;
    private Long customerId;
    private OrderStatus status;
    // TODO: implement timeToPrepare on Products to allow this property to work
    private float timeEstimate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(List<Combo> combos) {
        this.combos = combos;
        this.status = OrderStatus.RECEIVED;
        this.value = BigDecimal.ZERO;

        this.validate();
        this.calculateValue();
    }

    public Order(List<Combo> combos, Long clientId) {
        this.combos = combos;
        this.customerId = clientId;
        this.status = OrderStatus.RECEIVED;
        this.value = BigDecimal.ZERO;

        this.validate();
        this.calculateValue();
    }

    private void validate() {
        if (combos == null || combos.isEmpty()) {
            throw new InvalidModelException(this.getClass().getSimpleName(), "An order must have at least one combo");
        }
    }

    private void calculateValue() {
        for (Combo combo : combos) {
            this.value = this.value.add(combo.calculateValue());
        }
    }
}