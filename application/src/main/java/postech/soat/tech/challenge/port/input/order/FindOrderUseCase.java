package postech.soat.tech.challenge.port.input.order;

import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.OrderStatus;
import postech.soat.tech.challenge.port.output.order.OrderRepository;

import java.util.List;

public class FindOrderUseCase {
    private final OrderRepository orderRepository;

    public FindOrderUseCase(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public Order findOrder(long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> findOrderByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status.toString());
    }

}
