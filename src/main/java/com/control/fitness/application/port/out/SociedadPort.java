package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.SociedadEntity;

public interface SociedadPort {
	
	public List<SociedadEntity> obtenerSociedades(); 
	public SociedadEntity buscarSociedadPorId(int idSociedad);
	public SociedadEntity crearSociedad(SociedadEntity sociedad);
	public void actualizarSociedad(SociedadEntity sociedad);
	public void eliminarSociedad(SociedadEntity sociedad);

}
