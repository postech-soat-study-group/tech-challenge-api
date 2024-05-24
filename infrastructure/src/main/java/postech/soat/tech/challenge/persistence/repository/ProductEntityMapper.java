package postech.soat.tech.challenge.persistence.repository;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.persistence.entity.ProductEntity;

@Mapper
public interface ProductEntityMapper {

    ProductEntityMapper INSTANCE = Mappers.getMapper(ProductEntityMapper.class);

    default ProductEntity toProductEntity(Product product) {
        return new ProductEntity(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }

    default Product toProduct(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getDescription(), productEntity.getPrice(), productEntity.getQuantity());
    }

}
