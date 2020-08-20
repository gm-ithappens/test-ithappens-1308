package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
