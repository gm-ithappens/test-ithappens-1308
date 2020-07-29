package br.com.pwneo.estoque_back_end.repositories;

import br.com.pwneo.estoque_back_end.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
