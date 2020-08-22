package br.com.pwneo.estoque_back_end.repositories.supports;

import br.com.pwneo.estoque_back_end.models.supports.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    Operation findByDescription(String description);
}
