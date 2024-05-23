package postech.soat.tech.challenge.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.request.ProductDTO;
import postech.soat.tech.challenge.api.request.ProdutoMapper;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.pedido.model.Product;
import postech.soat.tech.challenge.produto.port.input.CreateProductUseCase;
import postech.soat.tech.challenge.produto.port.input.FindProductUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final FindProductUseCase findProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, FindProductUseCase findProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.findProductUseCase = findProductUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Product> createProduto(@RequestBody ProductDTO productDTO) {
        Product createdProduct = createProductUseCase.createProduct(ProdutoMapper.INSTANCE.toNewProduto(productDTO));
        return new ApiResponse<>(createdProduct);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<Product>> getProdutos() {
        List<Product> products = findProductUseCase.findProdutos();
        return new ApiResponse<>(products);
    }
}