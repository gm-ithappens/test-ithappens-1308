package br.com.felipeomachado.apitest1308pulse.stock.repositories;

import br.com.felipeomachado.apitest1308pulse.stock.entities.Stock;
import br.com.felipeomachado.apitest1308pulse.stock.entities.StockPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, StockPK> {
}
