package postech.soat.tech.challenge.api.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.OrderStatus;

@AllArgsConstructor
@Data
public class UpdateOrderDTO {
    private Long id;
    private String status;

    public static Order toOrder(UpdateOrderDTO dto) {
        return new Order(
                dto.getId(),
                OrderStatus.valueOf(dto.getStatus())
        );
    }
}
