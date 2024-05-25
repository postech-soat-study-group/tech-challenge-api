package postech.soat.tech.challenge.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.request.ProductDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.model.Product;
import postech.soat.tech.challenge.port.input.CreateProductUseCase;
import postech.soat.tech.challenge.port.input.DeleteProductUseCase;
import postech.soat.tech.challenge.port.input.FindProductUseCase;
import postech.soat.tech.challenge.port.input.UpdateProductUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindProductUseCase findProductUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product createdProduct = createProductUseCase.createProduct(ProductDTO.toNewProduct(productDTO));
        return new ApiResponse<>(ProductDTO.toProductDTO(createdProduct));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        Product updatedProduct = updateProductUseCase.updateProduct(ProductDTO.toProduct(productDTO));
        return new ApiResponse<>(ProductDTO.toProductDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<String> deleteProduct(@PathVariable Long id) {
        deleteProductUseCase.deleteProduct(id);
        return new ApiResponse<>();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ProductDTO>> getProducts() {
        List<Product> products = findProductUseCase.findProducts();
        return new ApiResponse<>(ProductDTO.toProductDTOList(products));
    }
}