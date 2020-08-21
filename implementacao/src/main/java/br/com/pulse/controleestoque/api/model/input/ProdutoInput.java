package br.com.pulse.controleestoque.api.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @JsonProperty("codigo_barras")
    @ApiModelProperty(example = "123456789", required = true)
    @NotBlank
    private String codigoBarras;

    @ApiModelProperty(example = "Extrato de Tomate", required = true)
    @NotBlank
    private String descricao;

    @ApiModelProperty(example = "10.20", required = true)
    @DecimalMin(value = "0.1", inclusive = false)
    @Digits(integer = 9,fraction = 2)
    private BigDecimal valor;
}
