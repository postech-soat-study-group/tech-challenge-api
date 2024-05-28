package postech.soat.tech.challenge.port.input.order;

import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.port.input.models.UpdateOrderApplicationDTO;
import postech.soat.tech.challenge.port.output.order.OrderRepository;

public class UpdateOrderUseCase {
    private final OrderRepository orderRepository;
    public UpdateOrderUseCase(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public Order updateOrder(UpdateOrderApplicationDTO order) {
        Order newOrder = orderRepository.findById(order.getId());
        newOrder.nextOrderStatus(order.getStatus());
        return orderRepository.save(newOrder);
    }
}
