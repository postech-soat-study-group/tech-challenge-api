package postech.soat.tech.challenge.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.validation.DomainInvalidException;
import postech.soat.tech.challenge.validation.DomainValidationResult;

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
    private int timeEstimate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(List<Combo> combos) {
        this.combos = combos;
        this.status = OrderStatus.RECEIVED;
        this.value = BigDecimal.ZERO;

        this.validate();
        this.calculateValue();
        this.calculateTimeEstimate();
    }

    public Order(List<Combo> combos, Long clientId) {
        this.combos = combos;
        this.customerId = clientId;
        this.status = OrderStatus.RECEIVED;
        this.value = BigDecimal.ZERO;

        this.validate();
        this.calculateValue();
        this.calculateTimeEstimate();
    }

    private void validate() {
        if (combos == null || combos.isEmpty()) {
            var domainValidationResult = new DomainValidationResult();
            domainValidationResult.addError("An order must have at least one combo");
            throw new DomainInvalidException(domainValidationResult.getErrors(), domainValidationResult.getErrorsMessage());
        }
    }

    private void calculateValue() {
        for (Combo combo : combos) {
            this.value = this.value.add(combo.calculateValue());
        }
    }

    private void calculateTimeEstimate() {
        for (Combo combo : combos) {
            this.timeEstimate += combo.calculateTimeToPrepare();
        }
    }
}
