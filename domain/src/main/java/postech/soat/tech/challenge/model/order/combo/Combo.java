package postech.soat.tech.challenge.model.order.combo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Combo {
    private Long id;
    private List<ComboItem> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BigDecimal calculateValue() {
        return this.items.stream().reduce(BigDecimal.ZERO, (total, item) -> total.add(item.calculateValue()), BigDecimal::add);
    }
    // TODO: implement validation for the number of products in each category once the products PR is merged
}
