package com.control.fitness.domain;

import java.io.Serializable;

public class AsistenciaAClaseIds implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idClase;

	private int idUsuario;

	public AsistenciaAClaseIds() {

	}

	public AsistenciaAClaseIds(int idClase, int idUsuario) {
		this.idUsuario = idUsuario;
		this.idClase = idClase;
	}

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
