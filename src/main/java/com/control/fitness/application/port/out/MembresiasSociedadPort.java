package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.MembresiaSociedadEntity;

public interface MembresiasSociedadPort {
	
	public List<MembresiaSociedadEntity> obtenerMembresiasSociedades();
	public List<MembresiaSociedadEntity> obtenerMembresiasSociedadesDe(int idSociedad);
	public void crearMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad);
	public void actualizarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad);
	public void eliminarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad);
	

}
