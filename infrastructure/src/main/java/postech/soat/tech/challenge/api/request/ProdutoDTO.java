package postech.soat.tech.challenge.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProdutoDTO {
        private UUID id;
        private String nome;
        private String descricao;
        private double preco;
        private int quantidade;
}
