package postech.soat.tech.challenge.produto.port.input;

import postech.soat.tech.challenge.pedido.model.Produto;

import java.util.List;

public interface FindProdutoUseCase {
    List<Produto> findProdutos();
}