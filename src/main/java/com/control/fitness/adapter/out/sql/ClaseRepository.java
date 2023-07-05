package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.ClasePort;
import com.control.fitness.application.port.out.jpa.ClaseJpa;
import com.control.fitness.domain.ClaseEntity;

@Service
public class ClaseRepository implements ClasePort {

	@Autowired
	ClaseJpa claseJpa;

	@Override
	public void crearClase(ClaseEntity conf) {
		claseJpa.save(conf);
	}

	@Override
	public void actualizarClase(ClaseEntity conf) {
		claseJpa.save(conf);
	}

	@Override
	public void eliminarClase(ClaseEntity conf) {
		claseJpa.delete(conf);
	}

	@Override
	public List<ClaseEntity> obtenerClases() {
		return claseJpa.findAll();
	}

	@Override
	public ClaseEntity obtenerClasePorId(int idClase) {
		return claseJpa.findByIdClase(idClase);
	}
	
	@Override
	public ClaseEntity estaEnClaseEnFecha(String fecha, int idUsuario){
		return claseJpa.estaEnClaseEnFecha(fecha, idUsuario);
	}

}
