package com.control.fitness.application.port.out.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.control.fitness.domain.UsuarioEntity;

@Repository
public interface UsuarioJpa extends CrudRepository<UsuarioEntity, Serializable> {

	public List<UsuarioEntity> findAll();

	public UsuarioEntity findByIdUsuario(int idUsuario);

	public UsuarioEntity findByUsuario(String usuario);

	public UsuarioEntity findBycorreoElectronico(String correo);

	@Query(value = "SELECT * FROM usuario WHERE usuario = ?1", nativeQuery = true)
	public UsuarioEntity encontrarUsuario(String usuario);

	@Query(value = "SELECT * FROM usuario WHERE usuario = ?1 AND contrasenia = ?2", nativeQuery = true)
	public UsuarioEntity autenticar(String usuario, String passwd);

	@Query(value = "SELECT * FROM usuario WHERE id_sociedad = ?1", nativeQuery = true)
	public List<UsuarioEntity> obtenerUsuariosPorSociedad(int idSociedad);

	@Query(value = "SELECT u.id_usuario,u.id_rol,u.usuario,u.contrasenia,u.nombre,u.sexo,u.correo_electronico,u.peso,u.altura,u.imc,"
			+ "u.telefono,u.nivel,u.estatus,u.intentos,u.fecha_creacion,u.edad,u.id_sociedad,u.ciudad "
			+ "from usuario u JOIN asistencia_a_clase a ON u.id_usuario =  a.id_usuario " + "WHERE a.id_clase = ?1 "
			+ "AND a.fecha = ?2 " + "ORDER BY u.nombre", nativeQuery = true)
	public List<UsuarioEntity> obtenerUsuariosDeClase(int idClase, String fecha);

}
