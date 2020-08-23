package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.TipoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPedidoRepository extends JpaRepository<TipoPedido, Long> {
}
