package postech.soat.tech.challenge.persistence.repository.order;

import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.persistence.entity.order.OrderEntity;
import postech.soat.tech.challenge.port.output.order.OrderRepository;

import java.util.List;

public class JpaOrderRepositoryAdapter implements OrderRepository {
    private final JpaOrderRepository jpaOrderRepository;

    public JpaOrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderEntity.toOrderEntity(order);
        jpaOrderRepository.save(orderEntity);
        return OrderEntity.toOrder(orderEntity);
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Order findById(Long id) {
        OrderEntity orderEntity = jpaOrderRepository.findById(id).orElse(null);
        return OrderEntity.toOrder(orderEntity);
    }

    @Override
    public List<Order> findByStatus(String status) {
        return jpaOrderRepository.findByStatus(status).stream()
            .map(OrderEntity::toOrder)
                .toList();
    }
}
