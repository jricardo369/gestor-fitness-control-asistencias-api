package com.control.fitness.application.port.out;

import java.util.List;

import com.control.fitness.domain.UsuarioEntity;

public interface UsuariosPort {
	
	public List<UsuarioEntity> obtenerUsuarios(); 
	public UsuarioEntity buscarPorUsuario(String usuario);
	public List<UsuarioEntity> obtenerPorUsuarioPorSociedad(int idSociedad);
	public UsuarioEntity buscarSoloPorUsuario(String usuario);
	public UsuarioEntity buscarPorId(int idUsuario);
	public UsuarioEntity crearUsuario(UsuarioEntity usuario);
	public void actualizarUsuario(UsuarioEntity usuario);
	public void eliminarUsuario(UsuarioEntity usuario);
	public UsuarioEntity buscarPorCorreo(String correo);
	public List<UsuarioEntity> obtenerUsuariosDeClase(int idClase,String fecha);

}
