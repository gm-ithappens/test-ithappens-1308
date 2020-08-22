package br.com.pwneo.estoque_back_end.services.supports;

import br.com.pwneo.estoque_back_end.models.supports.PaymentMethod;
import br.com.pwneo.estoque_back_end.repositories.supports.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository repository;

    public List<PaymentMethod> findAll() {
        return this.repository.findAll();
    }

    public PaymentMethod findById(Integer id) {
        return this.repository.findById(id).get();
    }
}
