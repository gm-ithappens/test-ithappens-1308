package br.com.felipeomachado.apitest1308pulse.repositories;

import br.com.felipeomachado.apitest1308pulse.entities.Stock;
import br.com.felipeomachado.apitest1308pulse.entities.StockPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, StockPK> {
}
