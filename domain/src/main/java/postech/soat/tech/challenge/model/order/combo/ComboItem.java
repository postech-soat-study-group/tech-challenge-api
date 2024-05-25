package postech.soat.tech.challenge.model.order.combo;

import lombok.Getter;
import postech.soat.tech.challenge.model.Product;

import java.math.BigDecimal;

@Getter
public class ComboItem {
    public static final int MIN_QUANTITY_ALLOWED = 1;
    public static final int MAX_QUANTITY_ALLOWED = 99;

    private Product product;
    private int quantity;

    public ComboItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        validate();
    }

    public BigDecimal calculateValue() {
        return BigDecimal.valueOf(product.getPrice() * quantity);
    }

    private void validate() {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity < MIN_QUANTITY_ALLOWED) {
            throw new IllegalArgumentException("Quantity must be greater than %s".formatted(MIN_QUANTITY_ALLOWED));
        }

        if (quantity > MAX_QUANTITY_ALLOWED) {
            throw new IllegalArgumentException("Quantity must be less than %s".formatted(MAX_QUANTITY_ALLOWED));
        }
    }
}
