package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.SociedadEntity;

public interface SociedadUseCase {
	
	public List<SociedadEntity> obtenerSociedades(); 
	public SociedadEntity buscarSociedadPorId(int idSociedad);
	public void crearSociedad(SociedadEntity sociedad);
	public void actualizarSociedad(SociedadEntity sociedad);
	public void eliminarSociedad(SociedadEntity sociedad);


}
