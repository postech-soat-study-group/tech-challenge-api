package postech.soat.tech.challenge.pedido.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    private long id;
    private String name;
    private String description;
    private double price;
    private int quantity;

}
