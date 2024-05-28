package postech.soat.tech.challenge.port.input.order;

import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.port.output.order.OrderRepository;

public class UpdateOrderUseCase {
    private final OrderRepository orderRepository;
    public UpdateOrderUseCase(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public Order updateOrder(Order order) {
        Order newOrder = orderRepository.findById(order.getId());
        return orderRepository.save(
                new Order(
                    order.getId(),
                    newOrder.getCombos(),
                    newOrder.getCustomer(),
                    order.getStatus()
                )
        );
    }
}
