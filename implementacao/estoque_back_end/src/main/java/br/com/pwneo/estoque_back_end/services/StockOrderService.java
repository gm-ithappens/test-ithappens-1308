package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.StockOrder;
import br.com.pwneo.estoque_back_end.repositories.StockOrderRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StockOrderService {

    @Autowired
    private StockOrderRepository repository;

    public List<StockOrder> findAll() {
        return this.repository.findAll();
    }

    public StockOrder findById(Long id) {
        return this.repository.findById(id).get();
    }

    public StockOrder create(StockOrder stockOrder) {
        return this.repository.save(stockOrder);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public StockOrder update(Long id, StockOrder stockOrder) {
        try {
            StockOrder stockOrderTemp = repository.getOne(id);
            stockOrderTemp.setNote(stockOrder.getNote());
            stockOrderTemp.setPaymentMethod(stockOrder.getPaymentMethod());
            stockOrder.setSubsidiary(stockOrder.getSubsidiary());
            return repository.save(stockOrderTemp);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
