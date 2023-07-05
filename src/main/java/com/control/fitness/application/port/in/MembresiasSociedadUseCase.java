package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.MembresiaSociedad;
import com.control.fitness.domain.MembresiaSociedadEntity;

public interface MembresiasSociedadUseCase {
	
	public List<MembresiaSociedad> obtenerMembresiasSociedades();
	public List<MembresiaSociedad> obtenerMembresiasSociedadesDe(int idSociedad);
	public void crearMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad);
	public void actualizarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad);
	public void eliminarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad);

}
