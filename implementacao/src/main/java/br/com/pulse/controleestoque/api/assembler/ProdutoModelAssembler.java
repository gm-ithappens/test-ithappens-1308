package br.com.pulse.controleestoque.api.assembler;

import br.com.pulse.controleestoque.api.model.ProdutoModel;
import br.com.pulse.controleestoque.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoModelAssembler {

    private final ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }
}
