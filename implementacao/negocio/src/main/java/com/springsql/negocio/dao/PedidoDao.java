package com.springsql.negocio.dao;

import com.springsql.negocio.model.PedidoEstoque;
import org.springframework.data.repository.CrudRepository;

public interface PedidoDao extends CrudRepository<PedidoEstoque,Integer>{
}
