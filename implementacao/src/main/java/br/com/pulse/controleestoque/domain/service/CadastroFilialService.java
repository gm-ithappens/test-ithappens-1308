package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.FilialNaoEncontradaException;
import br.com.pulse.controleestoque.domain.model.Filial;
import br.com.pulse.controleestoque.domain.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroFilialService {

    private final FilialRepository filialRepository;

    public Filial buscarOuFalhar(Long filialId) {
        return filialRepository.findById(filialId)
                .orElseThrow(() -> new FilialNaoEncontradaException(filialId));
    }
}
