package postech.soat.tech.challenge.model.order.combo;

import postech.soat.tech.challenge.model.InvalidModelException;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.validation.DomainInvalidException;
import postech.soat.tech.challenge.validation.DomainValidationResult;

import java.math.BigDecimal;

public record ComboItem(Product product, int quantity) {
    public static final int MIN_QUANTITY_ALLOWED = 1;
    public static final int MAX_QUANTITY_ALLOWED = 99;

    public ComboItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        validate();
    }

    public BigDecimal calculateValue() {
        return BigDecimal.valueOf(quantity).multiply(product.getPrice());
    }

    public int calculateTimeToPrepare() {
        return product.getTimeToPrepareMinutes() * quantity;
    }

    private void validate() {
        var domainValidationResult = new DomainValidationResult();

        if (product == null) {
            domainValidationResult.addError("A ComboItem cannot exist without a product");
        }

        if (quantity < MIN_QUANTITY_ALLOWED) {
            var errorMessage = "Quantity must be greater than or equal to %s".formatted(MIN_QUANTITY_ALLOWED);
            domainValidationResult.addError(errorMessage);
        }

        if (quantity > MAX_QUANTITY_ALLOWED) {
            var errorMessage = "Quantity must be less than or equal to %s".formatted(MAX_QUANTITY_ALLOWED);
            domainValidationResult.addError(errorMessage);
        }

        if(!domainValidationResult.isValid()) {
            throw new DomainInvalidException(domainValidationResult.getErrors(), domainValidationResult.getErrorsMessage());
        }
    }
}
