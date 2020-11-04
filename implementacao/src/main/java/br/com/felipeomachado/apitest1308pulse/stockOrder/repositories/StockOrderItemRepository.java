package br.com.felipeomachado.apitest1308pulse.stockOrder.repositories;

import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockOrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockOrderItemRepository extends CrudRepository<StockOrderItem, Long> {

    @Query("SELECT i FROM StockOrderItem i where i.stockOrder.id = :stockOrderId and i.product.id = :productId and i.status in (1,3)")
    Optional<StockOrderItem> findByStockOrderAndProductAndStatusActiveOrProcessed(Long stockOrderId, Long productId);
}
