package postech.soat.tech.challenge.api.request;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import postech.soat.tech.challenge.model.Product;

@Mapper
public interface ProductRequestMapper {

    ProductRequestMapper INSTANCE = Mappers.getMapper(ProductRequestMapper.class);

    default ProductDTO toProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }

    default Product toProduct(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getQuantity());
    }

    default Product toNewProduct(ProductDTO productDTO) {
        return new Product(null, productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getQuantity());
    }
}
