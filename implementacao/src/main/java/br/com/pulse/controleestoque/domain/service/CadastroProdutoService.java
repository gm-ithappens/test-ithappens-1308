package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.ProdutoNaoEncontradoException;
import br.com.pulse.controleestoque.domain.model.Produto;
import br.com.pulse.controleestoque.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }

    public Produto buscarPorCodigoBarras(String codigoBarras) {
        return produtoRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Não existe cadastro de produto com o código de barras " + codigoBarras));
    }
}
