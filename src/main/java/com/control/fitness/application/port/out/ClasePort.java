package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.ClaseEntity;

public interface ClasePort {
	
	public List<ClaseEntity> obtenerClases();
	public void crearClase(ClaseEntity clase);
	public void actualizarClase(ClaseEntity clase);
	public void eliminarClase(ClaseEntity clasae);
	public ClaseEntity obtenerClasePorId(int idClase);
	public ClaseEntity estaEnClaseEnFecha(String fecha, int idUsuario);

}
