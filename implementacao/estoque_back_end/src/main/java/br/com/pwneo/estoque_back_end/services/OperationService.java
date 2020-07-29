package br.com.pwneo.estoque_back_end.services;

import br.com.pwneo.estoque_back_end.models.Operation;
import br.com.pwneo.estoque_back_end.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository repository;

    public List<Operation> findAll() {
        return this.repository.findAll();
    }

    public Operation findById(Long id) {
        return this.repository.findById(id).get();
    }
}
