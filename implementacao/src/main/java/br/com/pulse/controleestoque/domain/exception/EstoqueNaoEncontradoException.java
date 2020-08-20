package br.com.pulse.controleestoque.domain.exception;

import br.com.pulse.controleestoque.domain.model.EstoquePk;

public class EstoqueNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public EstoqueNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstoqueNaoEncontradoException(EstoquePk id) {
        this(String.format("Não existe um cadastro de estoque com código do produto %d e código da filial %d",
                id.getProduto().getId(),
                id.getFilial().getId()));
    }
}
