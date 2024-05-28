package postech.soat.tech.challenge.model.order;

import org.junit.jupiter.api.Test;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.model.order.combo.ComboItem;
import postech.soat.tech.challenge.validation.DomainInvalidException;

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
        assertNull(order.getCustomer());
        assertEquals(20, order.getTimeEstimate());
    }

    @Test
    public void whenOrderIsValidAndHasCustomerId_thenCreateOrder() {
        var combos = getComboList();
        var customer  = new Customer(99, "Ronaldinho", "01682912078", "ronaldinho@gaucho.com.br", "51999887766");
        var order = new Order(combos, customer);

        assertNotNull(order);
        assertEquals(combos, order.getCombos());
        assertEquals(OrderStatus.RECEIVED, order.getStatus());
        assertEquals(combos.getFirst().calculateValue(), order.getValue());
        assertEquals(customer, order.getCustomer());
        assertEquals(20, order.getTimeEstimate());
    }

    @Test
    public void whenComboListIsNull_thenThrowInvalidModelException() {
        var exception = assertThrows(DomainInvalidException.class, () -> new Order(null));

        assertEquals("An order must have at least one combo", exception.getMessage());
    }

    @Test
    public void whenComboListIsEmpty_thenThrowInvalidModelException() {
        var exception = assertThrows(DomainInvalidException.class, () -> new Order(List.of()));

        assertEquals("An order must have at least one combo", exception.getMessage());
    }

    private List<Combo> getComboList() {
        var fakeProduct = new Product(1L, "Product1",  "description", BigDecimal.ONE,  1,  Category.DESSERT, 20);
        var fakeComboItem = new ComboItem(fakeProduct, 1);

        return List.of(new Combo(List.of(fakeComboItem)));
    }
}
