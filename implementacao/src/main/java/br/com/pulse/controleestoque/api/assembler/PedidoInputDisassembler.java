package br.com.pulse.controleestoque.api.assembler;

import br.com.pulse.controleestoque.api.model.input.PedidoInput;
import br.com.pulse.controleestoque.domain.model.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Component
public class PedidoInputDisassembler {

    public Pedido toDomainObject(PedidoInput pedidoInput) {
        Pedido pedido = new Pedido();

        pedido.setCliente(createCliente(pedidoInput));
        pedido.setTipoPedido(createTipoPedido(pedidoInput));
        pedido.setObsEntrega(pedidoInput.getObsEntrega());
        pedido.setItensPedido(createItensPedidos(pedidoInput, pedido));
        pedido.setValorPedido(getValorTotal(pedido));

        return pedido;
    }

    private BigDecimal getValorTotal(Pedido pedido) {
        return pedido.getItensPedido().stream()
                .filter(itensPedido -> itensPedido.getStatus().equals(StatusPedido.ATIVO))
                .map(ItensPedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ItensPedido> createItensPedidos(PedidoInput pedidoInput, Pedido pedido) {
        return pedidoInput.getItens().stream().map(itemInput -> {
            ItensPedido itensPedido = new ItensPedido();
            ItensPedidoPk itensPedidoPk = new ItensPedidoPk();
            Produto produto = new Produto();
            produto.setId(itemInput.getProduto().getId());

            itensPedidoPk.setProduto(produto);
            itensPedidoPk.setPedido(pedido);

            itensPedido.setId(itensPedidoPk);
            itensPedido.setQuantidade(itemInput.getQuantidade());
            itensPedido.setStatus(StatusPedido.ATIVO);
            itensPedido.setValorTotal(itemInput.getProduto().getValor().multiply(new BigDecimal(itemInput.getQuantidade())));

            return itensPedido;
        }).collect(Collectors.toList());
    }

    private TipoPedido createTipoPedido(PedidoInput pedidoInput) {
        TipoPedido tipoPedido = new TipoPedido();
        tipoPedido.setId(pedidoInput.getTipoPedido().getId());
        return tipoPedido;
    }

    private Cliente createCliente(PedidoInput pedidoInput) {
        Cliente cliente = new Cliente();
        cliente.setId(pedidoInput.getCliente().getId());
        return cliente;
    }
}
