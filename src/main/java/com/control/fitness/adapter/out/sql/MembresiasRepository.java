package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.MembresiaPort;
import com.control.fitness.application.port.out.jpa.MembresiaJpa;
import com.control.fitness.domain.MembresiaEntity;

@Service
public class MembresiasRepository implements MembresiaPort {

	@Autowired
	private MembresiaJpa jpa;

	@Override
	public List<MembresiaEntity> obtenerMembresias() {
		return jpa.findAll();
	}

	@Override
	public void crearMembresia(MembresiaEntity membresia) {
		jpa.save(membresia);
	}

	@Override
	public void actualizarMembresia(MembresiaEntity membresia) {
		jpa.save(membresia);
	}

	@Override
	public void eliminarMembresia(MembresiaEntity membresia) {
		jpa.delete(membresia);
	}

	@Override
	public MembresiaEntity obtenerMembresiaPorId(int idMembresia) {
		return jpa.findByIdMembresia(idMembresia);
	}

}
