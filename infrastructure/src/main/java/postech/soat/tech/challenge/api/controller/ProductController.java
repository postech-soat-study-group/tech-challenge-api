package postech.soat.tech.challenge.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.request.ProductDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.port.input.CreateProductUseCase;
import postech.soat.tech.challenge.port.input.FindProductUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final FindProductUseCase findProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, FindProductUseCase findProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.findProductUseCase = findProductUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductDTO> createProduto(@RequestBody ProductDTO productDTO) {
        Product createdProduct = createProductUseCase.createProduct(ProductDTO.toNewProduct(productDTO));
        return new ApiResponse<>(ProductDTO.toProductDTO(createdProduct));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ProductDTO>> getProdutos() {
        List<Product> products = findProductUseCase.findProdutos();
        return new ApiResponse<>(ProductDTO.toProductDTOList(products));
    }
}