package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.OrderItem;
import br.com.pwneo.estoque_back_end.repositories.OrderItemRepository;
import br.com.pwneo.estoque_back_end.services.exceptions.DatabaseException;
import br.com.pwneo.estoque_back_end.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> findAll() {
        return this.repository.findAll();
    }

    public OrderItem findById(Long id) {
        return this.repository.findById(id).get();
    }

    public OrderItem findByItemId(Long id){
        return this.repository.findByStockItem_Id(id);
    }

    public OrderItem create(OrderItem orderItem) {
        return this.repository.save(orderItem);
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

    public OrderItem update(Long id, OrderItem orderItem) {
        try {
            OrderItem OrderItemTemp = repository.getOne(id);
            OrderItemTemp.setQuantity(orderItem.getQuantity());
            OrderItemTemp.setStatus(orderItem.getStatus());
            orderItem.setStockItem(orderItem.getStockItem());
            return repository.save(OrderItemTemp);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}