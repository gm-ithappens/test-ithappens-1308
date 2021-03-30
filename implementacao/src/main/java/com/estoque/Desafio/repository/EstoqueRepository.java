package com.estoque.Desafio.repository;

import com.estoque.Desafio.models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    @Query(value = "SELECT * from estoques where filial_id =:filial ", nativeQuery = true)
    List<Estoque> findByFilial(@Param("filial") Integer filial);

    @Query(value = "SELECT * from estoques where filial_id =:filial and produto_id=:produto LIMIT 1", nativeQuery = true)
    Optional<Estoque> findEstoqueByFilialAndProduto(@Param("filial") Integer filial, @Param("produto") Integer produto);
}
