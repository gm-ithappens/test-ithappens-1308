package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.Subsidiary;
import br.com.pwneo.estoque_back_end.repositories.SubsidiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsidiaryService {

    @Autowired
    private SubsidiaryRepository repository;

    public List<Subsidiary> findAll() {
        return this.repository.findAll();
    }

    public Subsidiary findById(Integer id) {
        return this.repository.findById(id).get();
    }

    public Subsidiary findByCnpj(String code){
        return this.repository.getByCnpj(code);
    }
}
