package postech.soat.tech.challenge.port.input.order;

import postech.soat.tech.challenge.model.Customer;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.model.order.combo.ComboItem;
import postech.soat.tech.challenge.port.output.CustomerRepository;
import postech.soat.tech.challenge.port.output.ProductRepository;
import postech.soat.tech.challenge.port.output.order.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    Logger logger = Logger.getLogger(CreateOrderUseCase.class.getName());

    public CreateOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Order create(List<Map<Long, Integer>> mappedCombos, long customerId) {
        logger.info("Order received!");

        List<Combo> combos = createCombos(mappedCombos);
        Customer customer = findCustomer(customerId);

        return orderRepository.save(new Order(combos, customer));
    }

    private List<Combo> createCombos(List<Map<Long, Integer>> mappedCombos) {
        List<Combo> combos = new ArrayList<>();
        mappedCombos.forEach(map -> {
            List<ComboItem> comboItems = createComboItems(map);
            combos.add(new Combo(null, comboItems, LocalDateTime.now(), LocalDateTime.now()));
        });
        return combos;
    }

    private List<ComboItem> createComboItems(Map<Long, Integer> map) {
        List<ComboItem> comboItems = new ArrayList<>();
        map.forEach((productId, quantity) -> {
            Product product = findProduct(productId);
            comboItems.add(new ComboItem(product, quantity));
        });
        return comboItems;
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    private Customer findCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
