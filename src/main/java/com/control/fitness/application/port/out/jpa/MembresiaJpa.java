package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.MembresiaEntity;

@Repository
public interface MembresiaJpa extends CrudRepository<MembresiaEntity, Serializable> {

	public List<MembresiaEntity> findAll();
	
	public MembresiaEntity findByIdMembresia(int idMembresia);

}
