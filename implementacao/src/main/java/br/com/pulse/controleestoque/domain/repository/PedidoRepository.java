package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("from Pedido p join fetch p.itensPedido join fetch p.cliente where p.id = :pedidoId")
    Optional<Pedido> findByIdWithItens(Long pedidoId);
}
