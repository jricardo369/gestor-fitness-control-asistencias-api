package com.control.fitness.adapter.out.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.ConfiguracionPort;
import com.control.fitness.application.port.out.jpa.ConfiguracionJpa;
import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.domain.SociedadEntity;

@Service
public class ConfiguracionRepository implements ConfiguracionPort {

	@Autowired
	ConfiguracionJpa confJpa;

	@Override
	public ConfiguracionEntity obtenerConfiguracion(SociedadEntity sociedad) {
		return confJpa.findBySociedad(sociedad);
	}

	@Override
	public void crearConf(ConfiguracionEntity conf) {
		confJpa.save(conf);
	}

	@Override
	public void actualizarConf(ConfiguracionEntity conf) {
		confJpa.save(conf);
	}

	@Override
	public void eliminarConf(ConfiguracionEntity conf) {
		confJpa.delete(conf);
	}

	@Override
	public String obtenerRutaLogoSociedad(SociedadEntity sociedad){
		String rutaLogo = "";
		ConfiguracionEntity conf = obtenerConfiguracion(sociedad);
		if (conf != null) {
			rutaLogo = conf.getLogo();
		}
		return rutaLogo;
	}
	
}
