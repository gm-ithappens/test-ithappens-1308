package br.com.felipeomachado.apitest1308pulse.stockOrder.repositories;

import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.Stock;
import br.com.felipeomachado.apitest1308pulse.stockOrder.entities.StockPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, StockPK> {
}
