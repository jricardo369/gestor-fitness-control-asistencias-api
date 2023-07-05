package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.ClaseEntity;

public interface ClaseUseCase {
     
	public List<ClaseEntity> obtenerClases();
	public void crearClase(ClaseEntity clase);
	public void actualizarClase(ClaseEntity clase);
	public void eliminarClase(ClaseEntity clasae);
	
}
