package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.MembresiasSociedadPort;
import com.control.fitness.application.port.out.jpa.MembresiasSociedadJpa;
import com.control.fitness.domain.MembresiaSociedadEntity;

@Service
public class MembresiasSociedadRepository implements MembresiasSociedadPort {

	@Autowired
	private MembresiasSociedadJpa jpa;

	@Override
	public List<MembresiaSociedadEntity> obtenerMembresiasSociedades() {
		return jpa.findAll();
	}

	@Override
	public List<MembresiaSociedadEntity> obtenerMembresiasSociedadesDe(int idSociedad) {
		return jpa.buscarPorIdSoc(idSociedad);
	}

	@Override
	public void crearMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad) {
		jpa.save(membresiaSociedad);
	}

	@Override
	public void actualizarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad) {
		jpa.save(membresiaSociedad);
	}

	@Override
	public void eliminarMembresiaSociedad(MembresiaSociedadEntity membresiaSociedad) {
		jpa.delete(membresiaSociedad);
	}

}
