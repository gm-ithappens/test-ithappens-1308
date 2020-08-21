package br.com.pulse.controleestoque.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Arroz")
    private String descricao;

    @JsonProperty("codigo_barras")
    @ApiModelProperty(example = "123456789")
    private String codigoBarras;

    @ApiModelProperty(example = "17.50")
    @JsonProperty("valor_unitario")
    private BigDecimal valor;
}
