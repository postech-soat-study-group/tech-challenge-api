package postech.soat.tech.challenge.external.api.checkout.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FakePayOrderResponse {
    private final BigDecimal amount;
    private final String message;
    private final LocalDateTime paymentDate;
    private final boolean success;
}
