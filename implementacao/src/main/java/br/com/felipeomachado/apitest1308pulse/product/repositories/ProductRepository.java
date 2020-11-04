package br.com.felipeomachado.apitest1308pulse.product.repositories;

import br.com.felipeomachado.apitest1308pulse.product.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByBarcode(String barcode);
    List<Product> findByDescription(String description);

}
