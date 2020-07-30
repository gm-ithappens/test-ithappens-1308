package br.com.pwneo.estoque_back_end.repositories;

import br.com.pwneo.estoque_back_end.models.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockProductRepository extends JpaRepository<StockProduct, Long> {
}
