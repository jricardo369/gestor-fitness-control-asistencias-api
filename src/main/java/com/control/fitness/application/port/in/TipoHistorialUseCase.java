package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.TipoHistorialEntity;

public interface TipoHistorialUseCase {

	public List<TipoHistorialEntity> obtenerTiposHistorials(); 
	public TipoHistorialEntity obtenerHistorial(int idTipo);
	public void crearTipoHistorial(TipoHistorialEntity mov);
	public void actualizarTipoHistorial(TipoHistorialEntity mov);
	public void eliminarTipoHistorial(TipoHistorialEntity mov);
	
}
