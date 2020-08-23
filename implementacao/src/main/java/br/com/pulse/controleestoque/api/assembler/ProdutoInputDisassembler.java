package br.com.pulse.controleestoque.api.assembler;

import br.com.pulse.controleestoque.api.model.input.ProdutoInput;
import br.com.pulse.controleestoque.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProdutoInputDisassembler {

    private final ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }
}
