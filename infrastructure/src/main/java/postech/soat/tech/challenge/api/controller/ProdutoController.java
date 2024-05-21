package postech.soat.tech.challenge.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.request.ProdutoDTO;
import postech.soat.tech.challenge.api.request.ProdutoMapper;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.pedido.model.Produto;
import postech.soat.tech.challenge.produto.port.input.CreateProdutoUseCase;
import postech.soat.tech.challenge.produto.port.input.FindProdutoUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private final CreateProdutoUseCase createProdutoUseCase;
    @Autowired
    private final FindProdutoUseCase findProdutoUseCase;


    public ProdutoController(CreateProdutoUseCase createProdutoUseCase, FindProdutoUseCase findProdutoUseCase) {
        this.createProdutoUseCase = createProdutoUseCase;
        this.findProdutoUseCase = findProdutoUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Produto> createProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto createdProduto = createProdutoUseCase.createProduto(ProdutoMapper.INSTANCE.toNewProduto(produtoDTO));
        return new ApiResponse<>(createdProduto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<Produto>> getProdutos() {
        List<Produto> produtos = findProdutoUseCase.findProdutos();
        return new ApiResponse<>(produtos);
    }
}