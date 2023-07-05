package com.control.fitness.application.port.in;

import java.util.List;

import com.control.fitness.domain.UsuarioEntity;

public interface UsuariosUseCase {

	public List<UsuarioEntity> obtenerUsuarios(); 
	public UsuarioEntity buscarPorUsuario(String usuario);
	public List<UsuarioEntity> obtenerPorUsuarioPorSociedad(int idSociedad);
	public UsuarioEntity obtenerPorIdUsuario(int idUsuario);
	public void crearUsuario(UsuarioEntity usuario);
	public void actualizarUsuario(UsuarioEntity usuario);
	public void eliminarUsuario(int usuario);
	public UsuarioEntity buscarPorCorreo(String correo);
	
}
