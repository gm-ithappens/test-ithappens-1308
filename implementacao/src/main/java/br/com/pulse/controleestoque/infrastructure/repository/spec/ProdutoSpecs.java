package br.com.pulse.controleestoque.infrastructure.repository.spec;

import br.com.pulse.controleestoque.domain.filter.ProdutoFilter;
import br.com.pulse.controleestoque.domain.model.Produto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class ProdutoSpecs {

    public static Specification<Produto> usandoFiltro(ProdutoFilter filtro) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if(filtro.getDescricao() != null) {
                predicates.add(builder.equal(root.get("descricao"), filtro.getDescricao()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
