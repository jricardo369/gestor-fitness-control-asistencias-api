package com.control.fitness.adapter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.control.fitness.ApiException;
import com.control.fitness.application.port.in.GenerarTokenDeAutenticacionUseCase;
import com.control.fitness.domain.Usuario;


@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST,RequestMethod.OPTIONS})
@RequestMapping("/autenticaciones")
@RestController
public class AutenticacionController {
	
	Logger log = LoggerFactory.getLogger(AutenticacionController.class);
	
	@Autowired
	private GenerarTokenDeAutenticacionUseCase useCase;
	
	@PostMapping
	public String login(@RequestParam("username") String username, @RequestParam("password") String pwd) throws ApiException { 
		
		return useCase.autenticarUsuario(username, pwd);
		
	}
	
	@PostMapping("inicio-sesion")
	public String inicioSesion(@RequestBody Usuario usuario) throws ApiException { 
		System.out.println("us:"+usuario.getUsuario());
		return useCase.autenticarUsuario(usuario.getUsuario(), usuario.getContrasenia());
		
	}

}
