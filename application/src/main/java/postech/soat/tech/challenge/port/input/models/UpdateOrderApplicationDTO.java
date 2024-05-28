package postech.soat.tech.challenge.port.input.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import postech.soat.tech.challenge.model.order.OrderStatus;

@AllArgsConstructor
@Getter
public class UpdateOrderApplicationDTO {
    private Long id;
    private OrderStatus status;
}
