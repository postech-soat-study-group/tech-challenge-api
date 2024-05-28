package postech.soat.tech.challenge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import postech.soat.tech.challenge.api.request.order.CreateOrderRequestDTO;
import postech.soat.tech.challenge.api.request.order.CreateOrderResponseDTO;
import postech.soat.tech.challenge.api.request.order.OrderDTO;
import postech.soat.tech.challenge.api.request.order.UpdateOrderDTO;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.OrderStatus;
import postech.soat.tech.challenge.port.input.order.CreateOrderUseCase;
import postech.soat.tech.challenge.port.input.order.FindOrderUseCase;
import postech.soat.tech.challenge.port.input.order.UpdateOrderUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateOrderResponseDTO> createOrder(@RequestBody CreateOrderRequestDTO createOrderDTO) {
        var createdOrder = createOrderUseCase.create(createOrderDTO.toDomain(), createOrderDTO.getCustomerId());
        return new ApiResponse<>(CreateOrderResponseDTO.fromOrder(createdOrder));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<OrderDTO> getOrder(@PathVariable long id) {
        var order = findOrderUseCase.findOrder(id);
        return new ApiResponse<>(OrderDTO.fromOrder(order));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderDTO orderDTO) {
        orderDTO.setId(id);
        Order updateOrder = updateOrderUseCase.updateOrder(UpdateOrderDTO.toOrder(orderDTO));
        return new ApiResponse<>(OrderDTO.fromOrder(updateOrder));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<OrderDTO>> getAllOrders(@RequestParam(required = false) OrderStatus orderStatus ) {
        var orders = findOrderUseCase.findOrderByStatus(orderStatus);
        return new ApiResponse<>(OrderDTO.toOrderDTOList(orders));
    }
}
