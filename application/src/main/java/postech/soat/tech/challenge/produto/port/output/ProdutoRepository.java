package postech.soat.tech.challenge.produto.port.output;

import postech.soat.tech.challenge.pedido.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    Produto save(Produto produto);
    List<Produto> findAll();
}