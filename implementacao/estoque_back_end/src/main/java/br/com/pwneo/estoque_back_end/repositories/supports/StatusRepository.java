package br.com.pwneo.estoque_back_end.repositories.supports;

import br.com.pwneo.estoque_back_end.models.supports.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByDescription(String description);
}
