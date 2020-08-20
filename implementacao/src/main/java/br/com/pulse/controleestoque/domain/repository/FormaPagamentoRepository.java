package br.com.pulse.controleestoque.domain.repository;

import br.com.pulse.controleestoque.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
