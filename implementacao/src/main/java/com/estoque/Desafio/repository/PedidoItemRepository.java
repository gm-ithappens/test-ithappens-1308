package com.estoque.Desafio.repository;

import com.estoque.Desafio.models.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
    @Query(value = "SELECT pi.* from pedido_itens pi " +
            "inner join pedidos p on p.id = pi.pedido_id " +
            "where p.filial_id =:filial ", nativeQuery = true)
    List<PedidoItem> findByFilial(@Param("filial") Integer filial);
}
