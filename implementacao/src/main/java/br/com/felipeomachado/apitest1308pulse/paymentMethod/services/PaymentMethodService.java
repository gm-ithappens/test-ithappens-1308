package br.com.felipeomachado.apitest1308pulse.paymentMethod.services;

import br.com.felipeomachado.apitest1308pulse.paymentMethod.entities.PaymentMethod;
import br.com.felipeomachado.apitest1308pulse.paymentMethod.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public Optional<PaymentMethod> findById(Long id) {
        return paymentMethodRepository.findById(id);
    }
}
