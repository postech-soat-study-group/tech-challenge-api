package postech.soat.tech.challenge.external.api.checkout.services;

import postech.soat.tech.challenge.external.api.checkout.request.FakePayOrderRequest;
import postech.soat.tech.challenge.external.api.checkout.response.FakePayOrderResponse;

import java.time.LocalDateTime;

public class FakeCheckoutOrderAdapter implements FakeCheckoutOrder {
    public FakePayOrderResponse PayOrder(FakePayOrderRequest request) {
        return new FakePayOrderResponse(
                request.orderAmount(),
                "Payment approved",
                LocalDateTime.now(),
                true
        );
    }
}
