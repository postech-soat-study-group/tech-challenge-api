package postech.soat.tech.challenge.model.order.combo;

import lombok.Getter;
import postech.soat.tech.challenge.model.InvalidModelException;
import postech.soat.tech.challenge.model.Product;

import java.math.BigDecimal;

@Getter
public record ComboItem(Product product, int quantity) {
    public static final int MIN_QUANTITY_ALLOWED = 1;
    public static final int MAX_QUANTITY_ALLOWED = 99;

    public ComboItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        validate();
    }

    public BigDecimal calculateValue() {
        return BigDecimal.valueOf(product.getPrice() * quantity);
    }

    private void validate() {
        var className = this.getClass().getSimpleName();

        if (product == null) {
            throw new InvalidModelException(className, "A ComboItem cannot exist without a product");
        }

        if (quantity < MIN_QUANTITY_ALLOWED) {
            var errorMessage = "Quantity must be greater than %s".formatted(MIN_QUANTITY_ALLOWED);
            throw new InvalidModelException(className, errorMessage);
        }

        if (quantity > MAX_QUANTITY_ALLOWED) {
            var errorMessage = "Quantity must be less than %s".formatted(MAX_QUANTITY_ALLOWED);
            throw new InvalidModelException(className, errorMessage);
        }
    }
}
