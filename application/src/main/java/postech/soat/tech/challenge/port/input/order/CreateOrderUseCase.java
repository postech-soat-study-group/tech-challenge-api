package postech.soat.tech.challenge.port.input.order;

import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.OrderStatus;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.model.order.combo.ComboItem;
import postech.soat.tech.challenge.port.input.CreateCustomerUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CreateOrderUseCase {
    Logger logger = Logger.getLogger(CreateCustomerUseCase.class.getName());

    public Order create(List<Map<Long, Integer>> combos) {
        // The real implementation depends on the data access layer
        logger.info("Order received!");
        var product = new Product(1L, "Product", "description", BigDecimal.TEN, 10, Category.BEVERAGE, 10);
        return new Order(
                1L,
                List.of(new Combo(1L, List.of(new ComboItem(product, 1)),LocalDateTime.now(), LocalDateTime.now())),
                BigDecimal.TEN,
                1L,
                OrderStatus.RECEIVED,
                10,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
