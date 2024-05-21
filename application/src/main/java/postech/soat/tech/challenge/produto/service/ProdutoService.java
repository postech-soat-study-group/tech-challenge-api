package postech.soat.tech.challenge.produto.service;

import postech.soat.tech.challenge.pedido.model.Produto;
import postech.soat.tech.challenge.produto.port.input.CreateProdutoUseCase;
import postech.soat.tech.challenge.produto.port.input.FindProdutoUseCase;
import postech.soat.tech.challenge.produto.port.output.ProdutoRepository;

import java.util.List;

public class ProdutoService implements CreateProdutoUseCase, FindProdutoUseCase {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> findProdutos() {
        return produtoRepository.findAll();
    }
}