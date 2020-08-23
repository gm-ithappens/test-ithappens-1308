package br.com.pulse.controleestoque.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemInput {

    @Valid
    @NotNull
    private ProdutoResumoInput produto;

    @ApiModelProperty(example = "1", required = true)
    @Min(1)
    private int quantidade;

    @ApiModelProperty(example = "ATIVO", required = true)
    @Valid
    @NotNull
    private StatusPedidoInput status;
}
