package postech.soat.tech.challenge.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import postech.soat.tech.challenge.model.Customer;
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
    private Customer customer;
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

    public Order(List<Combo> combos, Customer customer) {
        this.combos = combos;
        this.customer = customer;
        this.status = OrderStatus.RECEIVED;
        this.value = BigDecimal.ZERO;

        this.validate();
        this.calculateValue();
        this.calculateTimeEstimate();
    }

    public Order(List<Combo> combos, Customer customer, OrderStatus status) {
        this.combos = combos;
        this.customer = customer;
        this.status = status;
        this.value = BigDecimal.ZERO;

        this.validate();
        this.calculateValue();
        this.calculateTimeEstimate();
    }

    public Order(Long id, List<Combo> combos, Customer customer, OrderStatus status) {
        this.id = id;
        this.combos = combos;
        this.customer = customer;
        this.status = status;
        this.value = BigDecimal.ZERO;

        this.validate();
        this.calculateValue();
        this.calculateTimeEstimate();
    }

    public Order(Long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    private void validate() {
        if (combos == null || combos.isEmpty()) {
            var domainValidationResult = new DomainValidationResult();
            domainValidationResult.addError("An order must have at least one combo");
            throw new DomainInvalidException(domainValidationResult.getErrors(), domainValidationResult.getErrorsMessage());
        }
        if (customer == null) {
            var domainValidationResult = new DomainValidationResult();
            domainValidationResult.addError("An order must have a customer");
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
