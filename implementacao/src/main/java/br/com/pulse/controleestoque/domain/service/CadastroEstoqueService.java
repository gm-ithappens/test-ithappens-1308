package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.EstoqueNaoEncontradoException;
import br.com.pulse.controleestoque.domain.model.Estoque;
import br.com.pulse.controleestoque.domain.model.EstoquePk;
import br.com.pulse.controleestoque.domain.model.Filial;
import br.com.pulse.controleestoque.domain.model.Produto;
import br.com.pulse.controleestoque.domain.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CadastroEstoqueService {

    private final EstoqueRepository estoqueRepository;

    public Estoque buscarOuFalhar(Produto produto, Filial filial) {
        EstoquePk id = new EstoquePk(produto, filial);
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new EstoqueNaoEncontradoException(id));
    }
}
