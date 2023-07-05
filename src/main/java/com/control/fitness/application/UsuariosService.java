package com.control.fitness.application;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.fitness.ApiException;
import com.control.fitness.UtilidadesAdapter;
import com.control.fitness.application.port.in.UsuariosUseCase;
import com.control.fitness.application.port.out.PermisosUsuariosPort;
import com.control.fitness.application.port.out.UsuariosPort;
import com.control.fitness.domain.PermisoUsuario;
import com.control.fitness.domain.PermisoUsuarioEntity;
import com.control.fitness.domain.UsuarioEntity;

@Service
public class UsuariosService implements UsuariosUseCase {

	Logger log = LoggerFactory.getLogger(UsuariosService.class);

	@Autowired
	private UsuariosPort usrPort;

	@Autowired
	private PermisosUsuariosPort puPort;

	@Override
	public List<UsuarioEntity> obtenerUsuarios() {
		return usrPort.obtenerUsuarios();
	}

	@Override
	public UsuarioEntity buscarPorUsuario(String usuario) {
		return usrPort.buscarPorUsuario(usuario);
	}
	
	@Override
	public UsuarioEntity buscarPorCorreo(String correo) {
		return usrPort.buscarPorCorreo(correo);
	}

	@Override
	public UsuarioEntity obtenerPorIdUsuario(int idUsuario) {
		List<PermisoUsuario> permisos = new ArrayList<>();
		PermisoUsuario p = null;
		List<PermisoUsuarioEntity> p1 = puPort.obtenerPermisosDeUsuario(idUsuario);
		for (PermisoUsuarioEntity pe : p1) {
			p = new PermisoUsuario();
			p.setId(pe.getId());
			p.setNombre(pe.getNombre());
			permisos.add(p);
		}
		UsuarioEntity u = usrPort.buscarPorId(idUsuario);
		u.setPermisos(permisos);
		return u;
	}

	@Override
	public void crearUsuario(UsuarioEntity usuario) {

		UsuarioEntity usE = usrPort.buscarPorUsuario(usuario.getUsuario());
		if (usE == null) {
			System.out.println(UtilidadesAdapter.sha256(usuario.getUsuario(), usuario.getContrasenia()));
			usuario.setContrasenia(UtilidadesAdapter.sha256(usuario.getUsuario(), usuario.getContrasenia()));
			usuario.setEstatus("1");
			List<PermisoUsuario> permisos = usuario.getPermisos();

			UsuarioEntity us = usrPort.crearUsuario(usuario);

			if (permisos != null) {
				if (!permisos.isEmpty()) {
					for (PermisoUsuario p : permisos) {
						puPort.insertarPermisoUsuario(p.getId(), us.getIdUsuario());
					}
				}
			}
		} else {
			// Error de duplicidad
			throw new ApiException(409, "Nombre de usuario ya existente");

		}

	}

	@Override
	public void actualizarUsuario(UsuarioEntity usuario) {
		UsuarioEntity u = usrPort.buscarPorId(usuario.getIdUsuario());
		String contEntrante = usuario.getContrasenia();
		String contExistente = u.getContrasenia();
		String contNueva = UtilidadesAdapter.sha256(usuario.getUsuario(), usuario.getContrasenia()); 
		System.out.println("Contraseña entrante:"+contEntrante);
		System.out.println("Contraseña existente:"+usuario.getContrasenia());
		//Validar si cambiaron contrasenia
		if(!contExistente.equals(contEntrante)){
			usuario.setContrasenia(contNueva);
		}
		usrPort.actualizarUsuario(usuario);
		List<PermisoUsuario> permisos = usuario.getPermisos();
		puPort.eliminarPermisosDeUsuario(usuario.getIdUsuario());
		if (permisos != null) {
			if (!permisos.isEmpty()) { 
				for (PermisoUsuario p : permisos) {
					puPort.insertarPermisoUsuario(p.getId(), usuario.getIdUsuario());
				}
			}
		}
	}

	@Override
	public void eliminarUsuario(int idUsuario) {
		UsuarioEntity u = usrPort.buscarPorId(idUsuario);
		System.out.println("usuario:" + u.getUsuario());
		usrPort.eliminarUsuario(u);
	}

	@Override
	public List<UsuarioEntity> obtenerPorUsuarioPorSociedad(int idSociedad) {
		return usrPort.obtenerPorUsuarioPorSociedad(idSociedad);
	}

}
