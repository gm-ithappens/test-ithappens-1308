package com.springsql.negocio.dao;

import com.springsql.negocio.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario,Integer>{
}
