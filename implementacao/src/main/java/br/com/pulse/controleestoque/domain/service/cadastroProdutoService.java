package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.ProdutoNaoEncontradoException;
import br.com.pulse.controleestoque.domain.model.Produto;
import br.com.pulse.controleestoque.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class cadastroProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto buscarOuFalhar(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }
}
