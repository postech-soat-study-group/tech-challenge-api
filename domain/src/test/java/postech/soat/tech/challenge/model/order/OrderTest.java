package postech.soat.tech.challenge.model.order;

import org.junit.jupiter.api.Test;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.model.order.combo.ComboItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    public void whenOrderIsValidAndThereIsNoCustomerId_thenCreateOrder() {
        var combos = getComboList();
        var order = new Order(combos);

        assertNotNull(order);
        assertEquals(combos, order.getCombos());
        assertEquals(OrderStatus.RECEIVED, order.getStatus());
        assertEquals(combos.getFirst().calculateValue(), order.getValue());
        assertNull(order.getCustomerId());
    }

    @Test
    public void whenOrderIsValidAndHasCustomerId_thenCreateOrder() {
        var combos = getComboList();
        var customerId  = 99L;
        var order = new Order(combos, customerId);

        assertNotNull(order);
        assertEquals(combos, order.getCombos());
        assertEquals(OrderStatus.RECEIVED, order.getStatus());
        assertEquals(combos.getFirst().calculateValue(), order.getValue());
        assertEquals(customerId, order.getCustomerId());
    }

    private List<Combo> getComboList() {
        var fakeProduct = new Product(1L, "Product1",  "description", BigDecimal.ONE,  1,  Category.DESSERT);
        var fakeComboItem = new ComboItem(fakeProduct, 1);

        return List.of(new Combo(List.of(fakeComboItem)));
    }
}
