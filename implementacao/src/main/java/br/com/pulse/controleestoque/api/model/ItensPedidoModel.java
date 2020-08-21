package br.com.pulse.controleestoque.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItensPedidoModel {

    private ProdutoModel produto;

    @ApiModelProperty(example = "2")
    private int quantidade;

    @ApiModelProperty(example = "ATIVO")
    private String status;
}
