package com.control.fitness.adapter.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.application.port.out.EnviarCorreoPort;
import com.control.fitness.domain.SociedadEntity;

@RequestMapping("/util")
@RestController
public class UtilController {

	Logger log = LoggerFactory.getLogger(UtilController.class);

	@Autowired
	private EnviarCorreoPort envCorrPort;

	@GetMapping("envio-correo/{email}")
	public void obtenerUsuarios(@PathVariable("email") String email) {
		Map<String, Object> params = new HashMap<>();
		params.put("${usuario}", email);
		params.put("${sociedad}", "DENTI");
		params.put("${descripcion}", "PRUEBA");
		SociedadEntity s = new SociedadEntity();
		s.setSociedad(1);
		s.setEstatus(true);
		//envCorrPort.enviarCorreoNuevaCita(email, params, s);
		envCorrPort.enviarCorreoCalendario(email, params, s);
	}
	
	@GetMapping("envio-correo-new/{email}")
	public void obtenerUsuariosNew(@PathVariable("email") String email) {
		envCorrPort.testMail();
	}

}
