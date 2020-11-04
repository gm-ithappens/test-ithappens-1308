package br.com.felipeomachado.apitest1308pulse.product.repositories;

import br.com.felipeomachado.apitest1308pulse.product.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByBarcode(String barcode);
    Optional<Product> findByDescription(String description);

}
