package postech.soat.tech.challenge.api.request.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class CreateOrderRequestDTO {
    private Long customerId;
    private List<ComboDTO> combos;

    public List<Map<Long, Integer>> toDomain() {
        var mappedCombos = new ArrayList<Map<Long, Integer>>();
        combos
                .forEach(combo -> {
                    var map = new HashMap<Long, Integer>();
                    combo.getItems().forEach(item -> map.put(item.getProductId(), item.getQuantity()));
                    mappedCombos.add(map);
                });
        return mappedCombos;
    }
}
