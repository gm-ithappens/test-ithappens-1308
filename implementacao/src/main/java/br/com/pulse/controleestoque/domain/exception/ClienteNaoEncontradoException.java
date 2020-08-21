package br.com.pulse.controleestoque.domain.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontradoException(Long clienteId) {
        this(String.format("Não existe cadastro de cliente com o código %d", clienteId));
    }
}
