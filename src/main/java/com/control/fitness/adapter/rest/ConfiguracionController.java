package com.control.fitness.adapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.in.ConfiguracionesUseCase;
import com.control.fitness.domain.ConfiguracionEntity;
import com.control.fitness.domain.SociedadEntity;

@RequestMapping("/configuraciones")
@RestController
public class ConfiguracionController {

	Logger log = LoggerFactory.getLogger(ConfiguracionController.class);

	@Autowired
	ConfiguracionesUseCase confUseCase;

	@GetMapping("{idSociedad}")
	public ConfiguracionEntity obtenerConfig(@PathVariable("idSociedad") int idSociedad) {
		SociedadEntity s = new SociedadEntity();
		s.setSociedad(idSociedad);
		return confUseCase.obtenerConfiguracion(s);
	}

	@PostMapping
	public void crearConf(@RequestBody ConfiguracionEntity conf) {
		confUseCase.crearConf(conf);
	}
	
	@PutMapping
	public void actulizarConf(@RequestBody ConfiguracionEntity conf) {
		confUseCase.actualizarConf(conf);
	}
	
	@DeleteMapping
	public void eliminarConf(@RequestBody ConfiguracionEntity conf) {
		confUseCase.eliminarConf(conf);
	}

}
