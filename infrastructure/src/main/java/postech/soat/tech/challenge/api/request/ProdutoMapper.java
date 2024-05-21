package postech.soat.tech.challenge.api.request;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import postech.soat.tech.challenge.pedido.model.Produto;

import java.util.UUID;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    default ProdutoDTO toProdutoDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
    }

    default Produto toProduto(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.getId(), produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), produtoDTO.getQuantidade());
    }

    default Produto toNewProduto(ProdutoDTO produtoDTO) {
        return new Produto(UUID.randomUUID(), produtoDTO.getNome(), produtoDTO.getDescricao(), produtoDTO.getPreco(), produtoDTO.getQuantidade());
    }
}
