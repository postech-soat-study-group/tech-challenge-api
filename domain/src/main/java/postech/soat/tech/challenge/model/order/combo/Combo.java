package postech.soat.tech.challenge.model.order.combo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
}
