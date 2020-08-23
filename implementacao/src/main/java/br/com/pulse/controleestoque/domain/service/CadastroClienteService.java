package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.ClienteNaoEncontradoException;
import br.com.pulse.controleestoque.domain.model.Cliente;
import br.com.pulse.controleestoque.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }
}
