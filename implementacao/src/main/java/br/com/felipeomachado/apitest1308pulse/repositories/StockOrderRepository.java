package br.com.felipeomachado.apitest1308pulse.repositories;

import br.com.felipeomachado.apitest1308pulse.entities.StockOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockOrderRepository extends CrudRepository<StockOrder, Long> {
}
