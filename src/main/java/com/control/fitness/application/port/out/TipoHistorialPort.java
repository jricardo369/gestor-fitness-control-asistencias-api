package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.TipoHistorialEntity;

public interface TipoHistorialPort {
	
	public List<TipoHistorialEntity> obtenerTiposHistorials(); 
	public TipoHistorialEntity obtenerTipoHistorial(int idTipoHistorial);
	public void crearTipoHistorial(TipoHistorialEntity mov);
	public void actualizarTipoHistorial(TipoHistorialEntity mov);
	public void eliminarTipoHistorial(TipoHistorialEntity mov);

}
