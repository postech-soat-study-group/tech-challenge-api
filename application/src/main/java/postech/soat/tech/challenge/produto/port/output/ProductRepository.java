package postech.soat.tech.challenge.produto.port.output;

import postech.soat.tech.challenge.pedido.model.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);

    List<Product> findAll();
}