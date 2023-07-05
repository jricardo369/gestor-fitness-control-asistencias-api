package com.control.fitness.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.in.ConfiguracionesUseCase;
import com.control.fitness.application.port.out.ConfiguracionPort;
import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.domain.SociedadEntity;

@Service
public class ConfiguracionesService implements ConfiguracionesUseCase {

	Logger log = LoggerFactory.getLogger(ConfiguracionesService.class);

	@Autowired
	private ConfiguracionPort confPort;

	@Override
	public ConfiguracionEntity obtenerConfiguracion(SociedadEntity sociedad) {
		return confPort.obtenerConfiguracion(sociedad);
	}

	@Override
	public void crearConf(ConfiguracionEntity conf) {
		confPort.crearConf(conf);
	}

	@Override
	public void actualizarConf(ConfiguracionEntity conf) {
		confPort.actualizarConf(conf);
	}

	@Override
	public void eliminarConf(ConfiguracionEntity conf) {
		confPort.eliminarConf(conf);
	}

}
