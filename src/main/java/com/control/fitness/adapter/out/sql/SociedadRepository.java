package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.SociedadPort;
import com.control.fitness.application.port.out.jpa.SociedadJpa;
import com.control.fitness.domain.SociedadEntity;

@Service
public class SociedadRepository implements SociedadPort {

	@Autowired
	SociedadJpa jpa;

	@Override
	public List<SociedadEntity> obtenerSociedades() {
		return jpa.findAll();
	}
	
	@Override
	public SociedadEntity buscarSociedadPorId(int idSociedad) {
		return jpa.findBySociedad(idSociedad);
	}

	@Override
	public SociedadEntity crearSociedad(SociedadEntity sociedad) {
		return jpa.save(sociedad);
	}

	@Override
	public void actualizarSociedad(SociedadEntity sociedad) {
		jpa.save(sociedad);
	}

	@Override
	public void eliminarSociedad(SociedadEntity sociedad) {
		jpa.delete(sociedad);
	}

}
