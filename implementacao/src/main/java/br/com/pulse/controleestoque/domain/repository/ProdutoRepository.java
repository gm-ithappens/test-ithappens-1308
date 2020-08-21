package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
