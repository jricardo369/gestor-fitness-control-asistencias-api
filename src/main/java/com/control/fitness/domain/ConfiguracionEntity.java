package com.control.fitness.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "configuracion")
public class ConfiguracionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_configuracion")
	private int idConfiguracion;

	@Column(name = "correos", length = 200)
	private String correos;
	
	@Column(name = "dias_permitidos")
	private String diasPermitidos;
	
	@Column(name = "color_base")
	private String color_base;
	
	@Column(name = "logo")
	private String logo;
	
	@JoinColumn(name = "id_sociedad", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private SociedadEntity sociedad;

	public int getIdConfiguracion() {
		return idConfiguracion;
	}

	public void setIdConfiguracion(int idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}

	public String getCorreos() {
		return correos;
	}

	public void setCorreos(String correos) {
		this.correos = correos;
	}

	public String getDiasPermitidos() {
		return diasPermitidos;
	}

	public void setDiasPermitidos(String diasPermitidos) {
		this.diasPermitidos = diasPermitidos;
	}

	public SociedadEntity getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadEntity sociedad) {
		this.sociedad = sociedad;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getColor_base() {
		return color_base;
	}

	public void setColor_base(String color_base) {
		this.color_base = color_base;
	}

}
