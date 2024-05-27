package postech.soat.tech.challenge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.request.order.CreateOrderRequestDTO;
import postech.soat.tech.challenge.api.request.order.CreateOrderResponseDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.port.input.order.CreateOrderUseCase;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateOrderResponseDTO> createOrder(@RequestBody CreateOrderRequestDTO createOrderDTO) {
        var createdOrder = createOrderUseCase.create(createOrderDTO.toDomain());
        return new ApiResponse<>(CreateOrderResponseDTO.fromOrder(createdOrder));
    }
}
