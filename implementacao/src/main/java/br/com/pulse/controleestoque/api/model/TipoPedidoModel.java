package br.com.pulse.controleestoque.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoPedidoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "SAIDA")
    private String descricao;
}
