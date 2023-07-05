package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.TipoMovimientoEntity;

@Repository
public interface TipoMovimientoJpa extends CrudRepository<TipoMovimientoEntity, Serializable> {

	public List<TipoMovimientoEntity> findAll();
	
	public TipoMovimientoEntity findByIdTipo(int idTipo);

}
