package postech.soat.tech.challenge.model.order.combo;

import lombok.Getter;
import postech.soat.tech.challenge.model.Product;

import java.math.BigDecimal;

@Getter
public class ComboItem {
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
        if (quantity <= 0 || quantity >= 99) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
}
