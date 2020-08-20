package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.pulse.controleestoque.domain.model.FormaPagamento;
import br.com.pulse.controleestoque.domain.repository.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroFormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento buscarOrFalhar(Long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }
}
