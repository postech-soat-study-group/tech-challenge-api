package postech.soat.tech.challenge.port.input.order;

import postech.soat.tech.challenge.model.order.Order;
import postech.soat.tech.challenge.model.order.combo.Combo;
import postech.soat.tech.challenge.port.input.CreateCustomerUseCase;

import java.util.List;
import java.util.logging.Logger;

public class CreateOrderUseCase {
    Logger logger = Logger.getLogger(CreateCustomerUseCase.class.getName());

    public Order create(List<Combo> combos) {
        return null;
    }
}
