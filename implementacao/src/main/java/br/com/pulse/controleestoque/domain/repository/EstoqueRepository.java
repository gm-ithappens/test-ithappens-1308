package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.Estoque;
import br.com.pulse.controleestoque.domain.model.EstoquePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, EstoquePk> {
}
