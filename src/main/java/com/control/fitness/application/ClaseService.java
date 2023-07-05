package com.control.fitness.application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.in.ClaseUseCase;
import com.control.fitness.application.port.out.ClasePort;
import com.control.fitness.domain.ClaseEntity;

@Service
public class ClaseService implements ClaseUseCase {

	Logger log = LoggerFactory.getLogger(ClaseService.class);

	@Autowired
	private ClasePort clasePort;
	
	@Override
	public List<ClaseEntity> obtenerClases(){
		return clasePort.obtenerClases();
	}

	@Override
	public void crearClase(ClaseEntity clase) {
		clasePort.crearClase(clase);
	}

	@Override
	public void actualizarClase(ClaseEntity clase) {
		clasePort.actualizarClase(clase);
	}

	@Override
	public void eliminarClase(ClaseEntity clase) {
		clasePort.eliminarClase(clase);
	}

}
