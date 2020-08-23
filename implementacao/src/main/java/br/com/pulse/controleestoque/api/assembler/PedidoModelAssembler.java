package br.com.pulse.controleestoque.api.assembler;

import br.com.pulse.controleestoque.api.model.PedidoModel;
import br.com.pulse.controleestoque.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PedidoModelAssembler {

    private final ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModel.class);
    }
}
