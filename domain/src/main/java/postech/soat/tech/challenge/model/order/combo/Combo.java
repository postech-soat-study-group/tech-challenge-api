package postech.soat.tech.challenge.model.order.combo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.validation.DomainInvalidException;
import postech.soat.tech.challenge.validation.DomainValidationResult;

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
        return this.items.stream()
                .reduce(
                        BigDecimal.ZERO,
                        (total, item) -> total.add(item.calculateValue()), BigDecimal::add
                );
    }

    public int calculateTimeToPrepare() {
        return this.items.stream()
                .map(ComboItem::calculateTimeToPrepare)
                .reduce(0, Integer::sum);
    }

    private void validate() {
        var domainValidationResult = new DomainValidationResult();

        if (items == null || items.isEmpty()) {
            domainValidationResult.addError("A Combo cannot exist without items");
            throw new DomainInvalidException(domainValidationResult.getErrors(), domainValidationResult.getErrorsMessage());
        }

        var duplicatedCategories = getDuplicatedProductCategories();
        if (!duplicatedCategories.isEmpty()) {
            domainValidationResult.addError("A Combo cannot have duplicated categories: " + duplicatedCategories);
        }

        if(!domainValidationResult.isValid()) {
            throw new DomainInvalidException(domainValidationResult.getErrors(), domainValidationResult.getErrorsMessage());
        }
    }

    private List<Category> getDuplicatedProductCategories() {
        Map<Category, Long> itemByCategoryMap = this.items.stream()
                .map(item -> item.product().getCategory())
                .collect(Collectors.groupingBy(category -> category, Collectors.counting()));

        return itemByCategoryMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }
}
