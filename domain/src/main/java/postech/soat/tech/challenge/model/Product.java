package postech.soat.tech.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Category category;
    private int timeToPrepareMinutes;
}
