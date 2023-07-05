package com.control.fitness.adapter.out.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.application.port.out.UsuariosPort;
import com.control.fitness.application.port.out.jpa.UsuarioJpa;
import com.control.fitness.domain.UsuarioEntity;

@Service
public class UsuarioRepository implements UsuariosPort {

	@Autowired
	UsuarioJpa usJpa;

	@Override
	public List<UsuarioEntity> obtenerUsuarios() {
		return usJpa.findAll();
	}

	@Override
	public UsuarioEntity buscarPorUsuario(String usuario) {
		return usJpa.findByUsuario(usuario);
	}
	
	@Override
	public UsuarioEntity buscarPorCorreo(String correo) {
		return usJpa.findBycorreoElectronico(correo);
	}

	@Override
	public UsuarioEntity crearUsuario(UsuarioEntity usuario) {
		UsuarioEntity u = usJpa.save(usuario);
		return u;
	}

	@Override
	public void actualizarUsuario(UsuarioEntity usuario) {
		usJpa.save(usuario);
	}

	@Override
	public void eliminarUsuario(UsuarioEntity usuario) {
		usJpa.delete(usuario);
	}

	@Override
	public UsuarioEntity buscarSoloPorUsuario(String usuario) {
		return usJpa.encontrarUsuario(usuario);
	}

	@Override
	public UsuarioEntity buscarPorId(int idUsuario) {
		return usJpa.findByIdUsuario(idUsuario);
	}
	
	@Override
	public List<UsuarioEntity> obtenerPorUsuarioPorSociedad(int idSociedad) {
		return usJpa.obtenerUsuariosPorSociedad(idSociedad);
	}

	@Override
	public List<UsuarioEntity> obtenerUsuariosDeClase(int idClase, String fecha) {
		return usJpa.obtenerUsuariosDeClase(idClase, fecha);
	}

}
