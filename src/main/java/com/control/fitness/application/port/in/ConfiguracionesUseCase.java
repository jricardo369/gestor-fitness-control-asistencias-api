package com.control.fitness.application.port.in;

import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.domain.SociedadEntity;

public interface ConfiguracionesUseCase {
     
	public ConfiguracionEntity obtenerConfiguracion(SociedadEntity sociedad); 
	public void crearConf(ConfiguracionEntity conf);
	public void actualizarConf(ConfiguracionEntity conf);
	public void eliminarConf(ConfiguracionEntity conf);
	
}
