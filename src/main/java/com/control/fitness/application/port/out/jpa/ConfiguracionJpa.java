package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.domain.SociedadEntity;

@Repository
public interface ConfiguracionJpa extends CrudRepository<ConfiguracionEntity, Serializable> {

	public List<ConfiguracionEntity> findAll();
	
	public ConfiguracionEntity findBySociedad(SociedadEntity sociedad);

}
