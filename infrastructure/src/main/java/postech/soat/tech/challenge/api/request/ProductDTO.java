package postech.soat.tech.challenge.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import postech.soat.tech.challenge.model.Category;
import postech.soat.tech.challenge.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String category;
    private int timeToPrepareMinutes;

    public static Product toNewProduct(ProductDTO productDTO) {
        return new Product(
                null,
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                Category.valueOf(productDTO.getCategory()),
                productDTO.getTimeToPrepareMinutes()
        );
    }

    public static List<ProductDTO> toProductDTOList(List<Product> products) {
        return products.stream().map(ProductDTO::toProductDTO).toList();
    }

    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().name(),
                product.getTimeToPrepareMinutes()
        );
    }

    public static Product toProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                Category.valueOf(productDTO.getCategory()),
                productDTO.getTimeToPrepareMinutes()
        );
    }
}
