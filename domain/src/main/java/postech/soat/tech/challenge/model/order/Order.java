package postech.soat.tech.challenge.model.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private List<Combo> combos;
    private BigDecimal value;
    private Long clientId;
    private OrderStatus status;
    private float timeEstimate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
