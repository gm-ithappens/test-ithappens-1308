package br.com.pulse.controleestoque.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long usuarioId) {
        this(String.format("Não existe cadastro de usuário do o código %d", usuarioId));
    }
}
