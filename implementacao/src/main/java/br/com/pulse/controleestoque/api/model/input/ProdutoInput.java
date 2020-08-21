package br.com.pulse.controleestoque.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(example = "2.5", required = true)
    @NotNull
    private BigDecimal valor;
}
