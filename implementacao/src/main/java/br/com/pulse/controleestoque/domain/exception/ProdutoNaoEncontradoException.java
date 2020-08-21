package br.com.pulse.controleestoque.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long produtoId) {
        this(String.format("Nao existe cadastro de produto com código %d", produtoId));
    }
}
