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

    public void nextOrderStatus(OrderStatus nextStatus) {
        var validationMessage = "";
        if (this.status == OrderStatus.RECEIVED && nextStatus != OrderStatus.SENT_TO_KITCHEN) {
            validationMessage = String.format(
                    "When order status is \"%s\" the new status can be \"%s\"",
                    this.status,
                    OrderStatus.SENT_TO_KITCHEN
            );
        } else if (this.status == OrderStatus.SENT_TO_KITCHEN && nextStatus != OrderStatus.IN_PREPARATION && nextStatus != OrderStatus.REJECTED) {
            validationMessage = String.format(
                    "When order status is \"%s\" the new status can be \"%s\" or \"%s\"",
                    this.status,
                    OrderStatus.IN_PREPARATION,
                    OrderStatus.REJECTED
            );
        } else if (this.status == OrderStatus.FINISHED) {
            validationMessage = String.format(
                    "When order status is \"%s\" the status can't be changed",
                    this.status
            );
        } else if (this.status == OrderStatus.IN_PREPARATION && nextStatus != OrderStatus.READY) {
            validationMessage = String.format(
                    "When order status is \"%s\" the new status can be \"%s\"",
                    this.status,
                    OrderStatus.READY
            );
        } else if (this.status == OrderStatus.READY && nextStatus != OrderStatus.FINISHED) {
            validationMessage = String.format(
                    "When order status is \"%s\" the new status can be \"%s\"",
                    this.status,
                    OrderStatus.FINISHED
            );
        }

        if (!validationMessage.isEmpty()) {
            var domainValidationResult = new DomainValidationResult();
            domainValidationResult.addError(validationMessage);
            throw new DomainInvalidException(domainValidationResult.getErrors(), domainValidationResult.getErrorsMessage());
        }

        this.status = nextStatus;
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
