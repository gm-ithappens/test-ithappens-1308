package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.Product;
import br.com.pwneo.estoque_back_end.repositories.ProductRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public List<Product> findAll() {
        return this.repository.findAll();
    }

    public Product findById(Integer id) {
        return this.repository.findById(id).get();
    }

    public Product findBybarCode(String number){
        return this.repository.findByBarCode(number);
    }

    public Product create(Product product){
        return this.repository.save(product);
    }

    public boolean delete(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        }

        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }

        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Integer id, Product product) {
        try {
            Product productTemp = repository.getOne(id);
            productTemp.setBarCode(product.getBarCode());
            productTemp.setDescription(product.getDescription());
            return repository.save(productTemp);
        }

        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
