package postech.soat.tech.challenge.api.request;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import postech.soat.tech.challenge.pedido.model.Product;

import java.util.Random;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    default ProductDTO toProdutoDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
    }

    default Product toProduto(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getQuantity());
    }

    default Product toNewProduto(ProductDTO productDTO) {
        return new Product(new Random(0).nextLong(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getQuantity());
    }
}
