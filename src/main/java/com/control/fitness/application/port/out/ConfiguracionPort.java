package com.control.fitness.application.port.out;

import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.domain.SociedadEntity;

public interface ConfiguracionPort {
	
	public ConfiguracionEntity obtenerConfiguracion(SociedadEntity sociedad); 
	public void crearConf(ConfiguracionEntity conf);
	public void actualizarConf(ConfiguracionEntity conf);
	public void eliminarConf(ConfiguracionEntity conf);
	public String obtenerRutaLogoSociedad(SociedadEntity sociedad);

}
