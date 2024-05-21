package postech.soat.tech.challenge.produto.port.input;

import postech.soat.tech.challenge.pedido.model.Produto;

public interface CreateProdutoUseCase {
    Produto createProduto(Produto produto);
}