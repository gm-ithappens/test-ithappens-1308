package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.model.Pedido;
import br.com.pulse.controleestoque.domain.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CadastroPedidoService {

    private final PedidoRepository pedidoRepository;

    public void salvar(Pedido pedido) {
        pedidoRepository.save(pedido);
    }
}
