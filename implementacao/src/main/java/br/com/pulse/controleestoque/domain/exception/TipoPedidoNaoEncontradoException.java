package br.com.pulse.controleestoque.domain.exception;

public class TipoPedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public TipoPedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public TipoPedidoNaoEncontradoException(Long tipoPedidoId) {
        this(String.format("Não foi encontrado nenhum tipo de pedido com código %d", tipoPedidoId));
    }
}
