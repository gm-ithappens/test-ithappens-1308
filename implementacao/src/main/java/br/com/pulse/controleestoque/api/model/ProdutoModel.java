package br.com.pulse.controleestoque.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {

    private Long id;

    private String descricao;

    @JsonProperty("valor_unitario")
    private BigDecimal valorUnitario;
}
