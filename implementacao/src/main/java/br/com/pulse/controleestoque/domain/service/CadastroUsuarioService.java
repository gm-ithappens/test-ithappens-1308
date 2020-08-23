package br.com.pulse.controleestoque.domain.service;

import br.com.pulse.controleestoque.domain.exception.UsuarioNaoEncontradoException;
import br.com.pulse.controleestoque.domain.model.Usuario;
import br.com.pulse.controleestoque.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroUsuarioService {

    private final UsuarioRepository usuarioRepository;

    //TODO: Implementear a busca pelo usuário logado no sistema
    public Usuario buscarUsuarioLogado() {
        return buscarOuFalhar(1L);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
