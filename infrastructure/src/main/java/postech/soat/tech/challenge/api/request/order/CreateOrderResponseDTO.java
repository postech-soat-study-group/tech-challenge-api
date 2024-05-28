package postech.soat.tech.challenge.api.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.OrderStatus;
import postech.soat.tech.challenge.model.order.combo.Combo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateOrderResponseDTO {
    private Long id;
    private List<Map<Long, Integer>> combos;
    private BigDecimal value;
    private Long customerId;
    private OrderStatus status;
    private int timeEstimate;

    public static CreateOrderResponseDTO fromOrder(Order order) {
        var mappedCombos = mapCombos(order.getCombos());
        return CreateOrderResponseDTO.builder()
                .id(order.getId())
                .combos(mappedCombos)
                .value(order.getValue())
                .customerId(order.getCustomer().getId())
                .status(order.getStatus())
                .timeEstimate(order.getTimeEstimate())
                .build();
    }

    private static List<Map<Long, Integer>> mapCombos(List<Combo> combos) {
        var mappedCombos = new ArrayList<Map<Long, Integer>>();
        for (var combo : combos) {
            var map = new HashMap<Long, Integer>();
            combo.getItems().forEach(item -> map.put(item.product().getId(), item.quantity()));
            mappedCombos.add(map);
        }
        return mappedCombos;
    }
}
