package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.Stock;
import br.com.pwneo.estoque_back_end.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    public List<Stock> findAll() {
        return this.repository.findAll();
    }

    public Stock findById(Integer id) {
        return this.repository.findById(id).get();
    }
}