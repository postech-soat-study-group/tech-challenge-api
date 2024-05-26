package postech.soat.tech.challenge.model.order.combo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.InvalidModelException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Combo {
    private Long id;
    private List<ComboItem> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Combo(List<ComboItem> items) {
        this.items = items;

        validate();
    }

    public BigDecimal calculateValue() {
        return this.items.stream().reduce(BigDecimal.ZERO, (total, item) -> total.add(item.calculateValue()), BigDecimal::add);
    }

    private void validate() {
        var className = this.getClass().getSimpleName();

        if (items == null || items.isEmpty()) {
            throw new InvalidModelException(className, "A Combo cannot exist without items");
        }

        validateProductsByCategory();
    }

    private void validateProductsByCategory() {
        Map<Category, Long> itemByCategoryMap = this.items.stream()
                .map(item -> item.product().getCategory())
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()));

        var duplicatedCategories = itemByCategoryMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        if (duplicatedCategories.isEmpty()) {
            return;
        }

        var className = this.getClass().getSimpleName();
        throw new InvalidModelException(className, "A Combo cannot have duplicated categories: " + duplicatedCategories);
    }
}
