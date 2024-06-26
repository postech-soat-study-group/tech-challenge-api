package postech.soat.tech.challenge.api.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import postech.soat.tech.challenge.model.order.OrderStatus;
import postech.soat.tech.challenge.port.input.models.UpdateOrderApplicationDTO;

@AllArgsConstructor
@Data
public class UpdateOrderDTO {
    private Long id;
    private String status;

    public static UpdateOrderApplicationDTO toOrder(UpdateOrderDTO dto) {
        return new UpdateOrderApplicationDTO(
                dto.getId(),
                OrderStatus.valueOf(dto.getStatus())
        );
    }
}
