package postech.soat.tech.challenge.external.api.checkout.request;

import java.math.BigDecimal;

public record FakePayOrderRequest(BigDecimal orderAmount) {
}
