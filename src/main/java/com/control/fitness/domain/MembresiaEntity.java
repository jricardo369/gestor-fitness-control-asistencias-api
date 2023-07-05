package com.control.fitness.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membresia")
public class MembresiaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_membresia")
	private int idMembresia;

	@Column(name = "descripcion", length = 45)
	private String descripcion;

	@Column(name = "duracion")
	private int duracion;

	public int getIdMembresia() {
		return idMembresia;
	}

	public void setIdMembresia(int idMembresia) {
		this.idMembresia = idMembresia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
