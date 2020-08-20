package br.com.pulse.controleestoque.domain.exception;

public class FilialNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FilialNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FilialNaoEncontradaException(Long filialId) {
        this(String.format("Não foi encontrada cadastro de filial filial com código %d", filialId));
    }
}
