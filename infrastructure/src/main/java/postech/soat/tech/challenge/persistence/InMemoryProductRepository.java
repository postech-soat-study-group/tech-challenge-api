package postech.soat.tech.challenge.persistence;

import postech.soat.tech.challenge.pedido.model.Product;
import postech.soat.tech.challenge.produto.port.output.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> database = new HashMap<>();

    @Override
    public Product save(Product product) {
        database.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return database.values().stream().toList();
    }
}