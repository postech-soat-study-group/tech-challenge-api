package postech.soat.tech.challenge.model.order.combo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.InvalidModelException;
import postech.soat.tech.challenge.model.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ComboItemTest {

    private Product fakeProduct;

    @BeforeEach
    void setup() {
        fakeProduct = new Product(1L, "Product",  "description", BigDecimal.ONE,  1,  Category.BEVERAGE);
    }

    @Test
    public void whenProductIsNull_thenThrowInvalidModelException() {
        var exception = assertThrows(InvalidModelException.class, () -> new ComboItem(null, 1));

        assertEquals(
                "Invalid property on ComboItem: A ComboItem cannot exist without a product",
                exception.getMessage()
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { -1000, -1, 0 })
    public void whenQuantityIsSmallerThanMinimum_thenThrowInvalidModelException(int invalidMinQuantity) {
        var exception = assertThrows(InvalidModelException.class, () -> new ComboItem(fakeProduct, invalidMinQuantity));

        assertEquals(
                "Invalid property on ComboItem: Quantity must be greater than or equal to 1",
                exception.getMessage()
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 101, 999999 })
    public void whenQuantityIsGreaterThanMaximum_thenThrowInvalidModelException(int invalidMaxQuantity) {
        var exception = assertThrows(InvalidModelException.class, () -> new ComboItem(fakeProduct, invalidMaxQuantity));

        assertEquals(
                "Invalid property on ComboItem: Quantity must be less than or equal to 99",
                exception.getMessage()
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 98, 99 })
    public void whenProductAndQuantityAreValid_thenCreateComboItem(int quantity) {
        var comboItem = new ComboItem(fakeProduct, quantity);

        assertEquals(fakeProduct, comboItem.product());
        assertEquals(quantity, comboItem.quantity());
    }

    @Test
    public void whenCalculateValue_thenReturnProductPriceTimesQuantity() {
        var quantity = 10;
        var comboItem = new ComboItem(fakeProduct, quantity);

        var expected = BigDecimal.valueOf(quantity).multiply(fakeProduct.getPrice());

        assertEquals(expected, comboItem.calculateValue());
    }
}
