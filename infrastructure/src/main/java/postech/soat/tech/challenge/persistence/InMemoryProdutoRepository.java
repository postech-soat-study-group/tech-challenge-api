package postech.soat.tech.challenge.persistence;

import postech.soat.tech.challenge.pedido.model.Produto;
import postech.soat.tech.challenge.produto.port.output.ProdutoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryProdutoRepository implements ProdutoRepository {

    private final Map<UUID, Produto> database = new HashMap<>();

    @Override
    public Produto save(Produto produto) {
        database.put(produto.getId(), produto);
        return produto;
    }

    @Override
    public List<Produto> findAll() {
        return database.values().stream().toList();
    }
}