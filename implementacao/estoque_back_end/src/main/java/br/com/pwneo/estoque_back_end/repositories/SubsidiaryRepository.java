package br.com.pwneo.estoque_back_end.repositories;

import br.com.pwneo.estoque_back_end.models.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Integer> {
    Subsidiary getByCnpj(String code);
}
