package postech.soat.tech.challenge.external.api.checkout.services;

import postech.soat.tech.challenge.external.api.checkout.request.FakePayOrderRequest;
import postech.soat.tech.challenge.external.api.checkout.response.FakePayOrderResponse;

public interface FakeCheckoutOrder {
    FakePayOrderResponse PayOrder(FakePayOrderRequest request);
}
