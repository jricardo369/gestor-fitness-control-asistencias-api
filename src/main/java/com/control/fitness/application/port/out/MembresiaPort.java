package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.MembresiaEntity;

public interface MembresiaPort {

	public List<MembresiaEntity> obtenerMembresias();
	public MembresiaEntity obtenerMembresiaPorId(int idMembresia);
	public void crearMembresia(MembresiaEntity membresia);
	public void actualizarMembresia(MembresiaEntity membresia);
	public void eliminarMembresia(MembresiaEntity membresia);
	
}
