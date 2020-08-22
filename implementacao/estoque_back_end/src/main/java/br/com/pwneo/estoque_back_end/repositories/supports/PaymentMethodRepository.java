package br.com.pwneo.estoque_back_end.repositories.supports;

import br.com.pwneo.estoque_back_end.models.supports.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
