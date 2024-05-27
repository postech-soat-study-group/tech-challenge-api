package postech.soat.tech.challenge.api.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ComboDTO {
    List<ComboItemDTO> items;
}
