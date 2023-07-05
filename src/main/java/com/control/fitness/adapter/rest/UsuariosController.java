package com.control.fitness.adapter.rest;

import java.util.List;

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

import com.control.fitness.application.port.in.UsuariosUseCase;
import com.control.fitness.domain.UsuarioEntity;

@RequestMapping("/usuarios")
@RestController
public class UsuariosController {

	Logger log = LoggerFactory.getLogger(UsuariosController.class);

	@Autowired
	UsuariosUseCase usrUseCase;

	@GetMapping
	public List<UsuarioEntity> obtenerUsuarios() {
		return usrUseCase.obtenerUsuarios();
	}

	@GetMapping("por-usuario")
	public UsuarioEntity buscarPorUsuario(String usuario) {
		return usrUseCase.buscarPorUsuario(usuario);
	}
	
	@GetMapping("{idUsuario}")
	public UsuarioEntity buscarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
		return usrUseCase.obtenerPorIdUsuario(idUsuario);
	}
	
	@GetMapping("sociedad/{idSociedad}")
	public List<UsuarioEntity> obtenerUsuariosDeSociedad(@PathVariable("idSociedad") int idSociedad) {
		return usrUseCase.obtenerPorUsuarioPorSociedad(idSociedad);
	}

	@PostMapping
	public void crearUsuario(@RequestBody UsuarioEntity usuario) {
		usrUseCase.crearUsuario(usuario);
	}

	@PutMapping
	public void actulizarUsuario(@RequestBody UsuarioEntity usuario) {
		usrUseCase.actualizarUsuario(usuario);
	}

	@DeleteMapping("{idUsuario}")
	public void eliminarUsuario(@PathVariable("idUsuario") int idUsuario) {
		System.out.println("··USUARIO:" + idUsuario);
		usrUseCase.eliminarUsuario(idUsuario);
	}

}
