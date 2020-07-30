package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.StockProduct;
import br.com.pwneo.estoque_back_end.repositories.StockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StockProductService {

    @Autowired
    private StockProductRepository repository;

    public List<StockProduct> findAll() {
        return this.repository.findAll();
    }

    public StockProduct findById(Long id) {
        return this.repository.findById(id).get();
    }

//    public Set<StockProduct> findBySubsidiary(Long id){
//        return this.repository.findBySubsidiary_Id(id);
//    }
}
