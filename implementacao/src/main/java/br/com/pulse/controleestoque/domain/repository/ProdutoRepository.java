package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    Optional<Produto> findByCodigoBarras(String codigoBarras);
}
