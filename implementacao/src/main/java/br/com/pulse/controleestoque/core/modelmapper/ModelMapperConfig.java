package br.com.pulse.controleestoque.core.modelmapper;

import br.com.pulse.controleestoque.api.model.ItensPedidoModel;
import br.com.pulse.controleestoque.api.model.ProdutoModel;
import br.com.pulse.controleestoque.domain.model.ItensPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var itensPedidoToItensPedidoModelTypeMap = modelMapper
                .createTypeMap(ItensPedido.class, ItensPedidoModel.class);

        itensPedidoToItensPedidoModelTypeMap.<String>addMapping(
                itensPedidoSrc -> itensPedidoSrc.getId().getProduto().getDescricao(),
                (itensPedidoModelDest, value) -> itensPedidoModelDest.getProduto().setDescricao(value));
        itensPedidoToItensPedidoModelTypeMap.<BigDecimal>addMapping(
                itensPedidoSrc -> itensPedidoSrc.getId().getProduto().getValor(),
                (itensPedidoModelDest, value) -> itensPedidoModelDest.getProduto().setValorUnitario(value));

        return modelMapper;
    }
}
