package com.control.fitness.application;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.in.SociedadUseCase;
import com.control.fitness.application.port.out.MembresiaPort;
import com.control.fitness.application.port.out.MembresiasSociedadPort;
import com.control.fitness.application.port.out.SociedadPort;
import com.control.fitness.domain.MembresiaEntity;
import com.control.fitness.domain.MembresiaSociedadEntity;
import com.control.fitness.domain.SociedadEntity;

@Service
public class SociedadesService implements SociedadUseCase {
	
	Logger log = LoggerFactory.getLogger(SociedadesService.class);
	
	@Autowired
	private SociedadPort socPort;
	
	@Autowired
	private MembresiasSociedadPort memSocPort;
	
	@Autowired
	private MembresiaPort memPort;

	@Override
	public List<SociedadEntity> obtenerSociedades() {
		return socPort.obtenerSociedades();
	}

	@Override
	public SociedadEntity buscarSociedadPorId(int idSociedad) {
		return socPort.buscarSociedadPorId(idSociedad);
	}

	@Override
	public void crearSociedad(SociedadEntity sociedad) {
		
		sociedad.setFechaCreacion(new Date());
		sociedad.setEstatus(true);
		
		SociedadEntity soc = socPort.crearSociedad(sociedad);
		log.info("soc:"+soc.getSociedad());
		
		MembresiaEntity mem = memPort.obtenerMembresiaPorId(1);
		log.info("mem:"+mem.getIdMembresia());
		
		MembresiaSociedadEntity msoc = new MembresiaSociedadEntity();
		msoc.setId_membresia(mem.getIdMembresia());
		msoc.setId_sociedad(soc.getSociedad());
		msoc.setFechaCaducidad(new Date());
		
		log.info("crear membresia soc");
		memSocPort.crearMembresiaSociedad(msoc);
	}

	@Override
	public void actualizarSociedad(SociedadEntity sociedad) {
		socPort.actualizarSociedad(sociedad);
	}

	@Override
	public void eliminarSociedad(SociedadEntity sociedad) {
		socPort.eliminarSociedad(sociedad);
	}



}
