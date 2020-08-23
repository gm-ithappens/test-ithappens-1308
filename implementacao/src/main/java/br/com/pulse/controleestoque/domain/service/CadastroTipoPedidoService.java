package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.TipoPedidoNaoEncontradoException;
import br.com.pulse.controleestoque.domain.model.TipoPedido;
import br.com.pulse.controleestoque.domain.repository.TipoPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CadastroTipoPedidoService {

    private final TipoPedidoRepository tipoPedidoRepository;

    public TipoPedido buscarOuFalhar(Long tipoPedidoId) {
        return tipoPedidoRepository.findById(tipoPedidoId)
                .orElseThrow(() -> new TipoPedidoNaoEncontradoException(tipoPedidoId));
    }
}
