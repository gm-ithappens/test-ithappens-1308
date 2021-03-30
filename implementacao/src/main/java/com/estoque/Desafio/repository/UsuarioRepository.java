package com.estoque.Desafio.repository;

import com.estoque.Desafio.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * from usuarios where filial_id =:filial ", nativeQuery = true)
    List<Usuario> findByFilial(@Param("filial") Integer filial);
}
