package com.control.fitness.domain;

import java.util.List;

public class AsistenciaAClaseUsuarios {

	private ClaseEntity clase;
	private List<UsuarioEntity> usuarios;
	private String fecha;
	
	public ClaseEntity getClase() {
		return clase;
	}
	public void setClase(ClaseEntity clase) {
		this.clase = clase;
	}
	public List<UsuarioEntity> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioEntity> usuarios) {
		this.usuarios = usuarios;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
