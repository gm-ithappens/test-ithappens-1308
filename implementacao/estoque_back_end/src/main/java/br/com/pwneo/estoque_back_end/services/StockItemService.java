package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.StockItem;
import br.com.pwneo.estoque_back_end.repositories.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockItemService {

    @Autowired
    private StockItemRepository repository;

    public List<StockItem> findAll() {
        return this.repository.findAll();
    }

    public StockItem findById(Long id) {
        return this.repository.findById(id).get();
    }
}
