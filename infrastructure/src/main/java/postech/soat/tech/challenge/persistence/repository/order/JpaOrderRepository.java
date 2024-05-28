package postech.soat.tech.challenge.persistence.repository.order;

import org.springframework.data.repository.CrudRepository;
import postech.soat.tech.challenge.persistence.entity.order.OrderEntity;

public interface JpaOrderRepository extends CrudRepository<OrderEntity, Long> {
}
