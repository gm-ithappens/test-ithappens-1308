package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.PedidoNaoEncontradoException;
import br.com.pulse.controleestoque.domain.model.*;
import br.com.pulse.controleestoque.domain.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CadastroPedidoService {

    private final PedidoRepository pedidoRepository;
    private final CadastroUsuarioService cadastroUsuario;
    private final CadastroClienteService cadastroCliente;
    private final CadastroFormaPagamentoService cadastroFormaPagamento;
    private final CadastroTipoPedidoService cadastroTipoPedido;
    private final CadastroFilialService cadastroFilial;
    private final CadastroProdutoService cadastroProduto;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        Long clienteId = pedido.getCliente().getId();
        Long formaPagamentoId = pedido.getFormaPagamento().getId();
        Long tipoPedidoId = pedido.getTipoPedido().getId();
        Long filialId = pedido.getFilial().getId();

        Usuario usuario = cadastroUsuario.buscarUsuarioLogado();
        Cliente cliente = cadastroCliente.buscarOuFalhar(clienteId);
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOrFalhar(formaPagamentoId);
        TipoPedido tipoPedido = cadastroTipoPedido.buscarOuFalhar(tipoPedidoId);
        Filial filial = cadastroFilial.buscarOuFalhar(filialId);

        List<ItensPedido> itensPedidos = pedido.getItensPedido().stream()
                .map(itensPedido -> createProduto(pedido, itensPedido))
                .collect(Collectors.toList());

        pedido.setCliente(cliente);
        pedido.setUsuario(usuario);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setTipoPedido(tipoPedido);
        pedido.setFilial(filial);
        pedido.setItensPedido(itensPedidos);

        return pedidoRepository.save(pedido);
    }

    private ItensPedido createProduto(Pedido pedido, ItensPedido itensPedido) {
        var novoItensPedido = new ItensPedido();
        var itensPedidoPk = new ItensPedidoPk();
        itensPedidoPk.setPedido(pedido);
        itensPedidoPk.setProduto(cadastroProduto.buscarOuFalhar(itensPedido.getId().getProduto().getId()));
        novoItensPedido.setId(itensPedidoPk);
        novoItensPedido.setValorTotal(itensPedido.getValorTotal());
        novoItensPedido.setStatus(itensPedido.getStatus());
        novoItensPedido.setQuantidade(itensPedido.getQuantidade());

        return novoItensPedido;
    }

    public Pedido buscarOuFalhar(Long id) {
        return pedidoRepository.findByIdWithItens(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }
}
