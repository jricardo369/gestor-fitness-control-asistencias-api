package com.control.fitness.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_historial")
public class TipoHistorialEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_historial")
	private int idTipoHistorial;

	@Column(name = "descripcion", length = 45)
	private String descripcion;

	public int getIdTipoHistorial() {
		return idTipoHistorial;
	}

	public void setIdTipoHistorial(int idTipoHistorial) {
		this.idTipoHistorial = idTipoHistorial;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
