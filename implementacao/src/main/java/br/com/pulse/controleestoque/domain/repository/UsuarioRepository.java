package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
