package postech.soat.tech.challenge.model.order;

import lombok.Getter;
import postech.soat.tech.challenge.model.order.combo.Combo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {
    private Long id;
    private List<Combo> combos;
    private BigDecimal value;
    private Long clientId;
    private OrderStatus status;
    // TODO: implement timeToPrepare on Products to allow this property to work
    private float timeEstimate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(List<Combo> combos, Long clientId) {
        this.combos = combos;
        this.clientId = clientId;
        this.status = OrderStatus.RECEIVED;

        this.calculateValue();
    }

    private void calculateValue() {
        this.value = BigDecimal.ZERO;
        for (Combo combo : combos) {
            this.value = this.value.add(combo.calculateValue());
        }
    }
}
