package postech.soat.tech.challenge.pedido.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Produto {

    private UUID id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

}
