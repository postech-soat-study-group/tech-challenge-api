package postech.soat.tech.challenge.api.request.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComboItemDTO {
    private Long productId;
    private Integer quantity;
}
