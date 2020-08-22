package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.StockProduct;
import br.com.pwneo.estoque_back_end.models.dtos.StockProductNewDTO;
import br.com.pwneo.estoque_back_end.models.dtos.StockProductPriceDTO;
import br.com.pwneo.estoque_back_end.models.dtos.StockProductQuantityDTO;
import br.com.pwneo.estoque_back_end.repositories.ProductRepository;
import br.com.pwneo.estoque_back_end.repositories.StockProductRepository;
import br.com.pwneo.estoque_back_end.repositories.StockRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StockProductService {

    @Autowired
    private StockProductRepository stockProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;


    public List<StockProduct> findAll() {
        return this.stockProductRepository.findAll();
    }

    public StockProduct findById(Integer id) {
        return this.stockProductRepository.findById(id).get();
    }

    public Set<StockProduct> findByStock(Integer id){
        return this.stockProductRepository.findByStock_Id(id);
    }

    /*
     * O método é responsável por receber os id's de um produto e de um estoque,
     * além de quantidade e valor unitario.
     * Com isso, deve criar um novo produto no estoque escolhido.
     * Caso o produto já exista retornará um erro.
     * */
    public StockProduct create(StockProductNewDTO stockProductNewDTO) {
        StockProduct stockProduct = null;
        AtomicInteger index = new AtomicInteger(-1);
        List<StockProduct> stockProducts = this.stockProductRepository.findAll();

        stockProducts.forEach(stockProductCurrent -> {
            Integer productId = stockProductCurrent.getProduct().getId();
            Integer stockId = stockProductCurrent.getStock().getId();

            if (productId.equals(stockProductNewDTO.getProduct()) && stockId.equals(stockProductNewDTO.getStock())) {
                index.set(stockProductCurrent.getId());
            }
        });

        if (index.get() > -1) {
            throw new DatabaseException("Produto já existe no estoque!");
        } else {
            stockProduct = new StockProduct();
            stockProduct.setQuantity(stockProductNewDTO.getQuantity());
            stockProduct.setPrice(stockProductNewDTO.getPrice());
            stockProduct.setProduct(this.productRepository.findById(stockProductNewDTO.getProduct()).get());
            stockProduct.setStock(this.stockRepository.findById(stockProductNewDTO.getStock()).get());
        }
        return this.stockProductRepository.save(stockProduct);
    }

    public boolean delete(Integer id) {
        try {
            stockProductRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /*
     * O Método é responsável por receber o id do item no estoque, além da informação da quantidade que
     * deseja acrescentar a atual no banco.
     * Caso não seja encontrado o item, será gerado um erro.
     * */
    public StockProduct updateQuantity(Integer id, StockProductQuantityDTO stockProductQuantityDTO) {
        try {
            StockProduct stockProduct = this.stockProductRepository.getOne(id);
            stockProduct.setQuantity(stockProduct.getQuantity() + stockProductQuantityDTO.getQuantity());
            return this.stockProductRepository.save(stockProduct);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    /*
     * O Método é responsável por receber o id do item no estoque, além da informação da novo valor unitário que
     * deseja atualizar o atual no banco.
     * Caso não seja encontrado o item, será gerado um erro.
     * */
    public StockProduct updatePrice(Integer id, StockProductPriceDTO stockProductPriceDTO) {
        try {
            StockProduct stockProduct = this.stockProductRepository.getOne(id);
            stockProduct.setPrice(stockProductPriceDTO.getPrice());
            return this.stockProductRepository.save(stockProduct);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
