package postech.soat.tech.challenge.model.order.combo;

import org.junit.jupiter.api.Test;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.InvalidModelException;
import postech.soat.tech.challenge.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComboTest {

    @Test
    public void whenComboItemsAreValid_thenCreateCombo() {
        var sandwich = new Product(1L, "Product",  "description", BigDecimal.ONE,  1,  Category.SANDWICH, 1);
        var dessert = new Product(2L, "Product",  "description", BigDecimal.ONE,  1,  Category.DESSERT, 1);
        var sideDish = new Product(3L, "Product",  "description", BigDecimal.ONE,  1,  Category.SIDEDISH, 1);
        var beverage = new Product(4L, "Product",  "description", BigDecimal.ONE,  1,  Category.BEVERAGE, 1);

        var sandwichItem = new ComboItem(sandwich, 1);
        var dessertItem = new ComboItem(dessert, 1);
        var sideDishItem = new ComboItem(sideDish, 1);
        var beverageItem = new ComboItem(beverage, 1);

        var combo = new Combo(List.of(sandwichItem, dessertItem, sideDishItem, beverageItem));

        assertNotNull(combo);
    }
    @Test
    public void whenComboItemsIsNull_thenThrowInvalidModelException() {
        var exception = assertThrows(InvalidModelException.class, () -> new Combo(null));

        assertEquals("Invalid property on Combo: A Combo cannot exist without items", exception.getMessage());
    }

    @Test
    public void whenComboItemsIsEmpty_thenThrowInvalidModelException() {
        var exception = assertThrows(InvalidModelException.class, () -> new Combo(List.of()));

        assertEquals("Invalid property on Combo: A Combo cannot exist without items", exception.getMessage());
    }

    @Test
    public void whenComboHasTwoDifferentItemsInTheSameCategory_thenThrowInvalidModelException() {
        var fakeProduct1 = new Product(1L, "Product1",  "description", BigDecimal.ONE,  1,  Category.BEVERAGE, 1);
        var fakeProduct2 = new Product(2L, "Product2",  "description", BigDecimal.ONE,  1,  Category.BEVERAGE, 1);

        var fakeComboItem1 = new ComboItem(fakeProduct1, 1);
        var fakeComboItem2 = new ComboItem(fakeProduct2, 1);

        var exception = assertThrows(InvalidModelException.class, () -> new Combo(List.of(fakeComboItem1, fakeComboItem2)));

        assertEquals("Invalid property on Combo: A Combo cannot have duplicated categories: [BEVERAGE]", exception.getMessage());
    }

    @Test
    public void whenComboHasTwoGroupsOfDifferentItemsInTheSameCategory_thenThrowInvalidModelException() {
        var itemList = getDuplicatedComboItems(Category.BEVERAGE, Category.DESSERT);

        var exception = assertThrows(InvalidModelException.class, () -> new Combo(itemList));
        var message = exception.getMessage();

        assertTrue(message.contains("BEVERAGE"));
        assertTrue(message.contains("DESSERT"));
        assertTrue(message.contains("Invalid property on Combo: A Combo cannot have duplicated categories: "));
    }

    private static List<ComboItem> getDuplicatedComboItems(Category category1, Category category2) {
        var fakeProduct1 = new Product(1L, "Product1",  "description", BigDecimal.ONE,  1,  category1, 1);
        var fakeProduct2 = new Product(2L, "Product2",  "description", BigDecimal.ONE,  1,  category1, 1);
        var fakeProduct3 = new Product(3L, "Product3",  "description", BigDecimal.ONE,  1,  category2, 1);
        var fakeProduct4 = new Product(4L, "Product4",  "description", BigDecimal.ONE,  1,  category2, 1);

        var fakeComboItem1 = new ComboItem(fakeProduct1, 1);
        var fakeComboItem2 = new ComboItem(fakeProduct2, 1);
        var fakeComboItem3 = new ComboItem(fakeProduct3, 1);
        var fakeComboItem4 = new ComboItem(fakeProduct4, 1);

        return List.of(fakeComboItem1, fakeComboItem2, fakeComboItem3, fakeComboItem4);
    }
}
