package com.control.fitness.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clase")
public class ClaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_clase")
	private int idClase;

	@Column(name = "nombre", length = 45)
	private String nombre;
	
	@Column(name = "hora_inicio", length = 45)
	private String horaInicio;
	
	@Column(name = "hora_fin", length = 45)
	private String horaFin;
	
	@Column(name = "horario", length = 45)
	private String horario;
	
	@Column(name = "personas")
	private int personas;
	
	@Column(name = "profesor", length = 45)
	private String profesor;
	
	@Column(name = "estatus", length = 1)
	private boolean estatus;
	
	@Column(name = "id_sociedad")
	private int idSociedad;

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public int getPersonas() {
		return personas;
	}

	public void setPersonas(int personas) {
		this.personas = personas;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public int getIdSociedad() {
		return idSociedad;
	}

	public void setIdSociedad(int idSociedad) {
		this.idSociedad = idSociedad;
	}
	
}
