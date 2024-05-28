package postech.soat.tech.challenge.port.output.order;

import postech.soat.tech.challenge.model.order.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);

    List<Order> findAll();

    Order findById(Long id);

    List<Order> findByStatus(String status);
}