package br.com.felipeomachado.apitest1308pulse.paymentMethod.repositories;

import br.com.felipeomachado.apitest1308pulse.paymentMethod.entities.PaymentMethod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {
}
