package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilialRepository extends JpaRepository<Filial, Long> {
}
