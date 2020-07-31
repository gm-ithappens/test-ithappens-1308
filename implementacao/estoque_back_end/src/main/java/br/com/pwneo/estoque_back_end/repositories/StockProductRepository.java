package br.com.pwneo.estoque_back_end.repositories;

import br.com.pwneo.estoque_back_end.models.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StockProductRepository extends JpaRepository<StockProduct, Integer> {

    Set<StockProduct> findByStock_Id(Integer id);
}
