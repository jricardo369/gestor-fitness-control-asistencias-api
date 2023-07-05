package com.control.fitness.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "membresias_de_sociedad")
@IdClass(MembresiaSociedadEntity.class)
public class MembresiaSociedadEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name = "id_membresia")
	private int id_membresia;
	
	@Id
	@JoinColumn(name = "id_sociedad")
	private int id_sociedad;
	
	@Column(name = "fecha_caducidad", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fechaCaducidad;

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public int getId_membresia() {
		return id_membresia;
	}

	public void setId_membresia(int id_membresia) {
		this.id_membresia = id_membresia;
	}

	public int getId_sociedad() {
		return id_sociedad;
	}

	public void setId_sociedad(int id_sociedad) {
		this.id_sociedad = id_sociedad;
	}
	
	
	
}
