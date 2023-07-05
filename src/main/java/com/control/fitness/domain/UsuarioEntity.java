package com.control.fitness.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "usuario", length = 45)
	private String usuario;

	@Column(name = "contrasenia", length = 8)
	private String contrasenia;

	@Column(name = "nombre", length = 8)
	private String nombre;

	@Column(name = "telefono", length = 8)
	private String telefono;

	@Column(name = "ciudad", length = 8)
	private String ciudad;

	@Column(name = "correo_electronico", length = 8)
	private String correoElectronico;

	@Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fechaCreacion;

	@JoinColumn(name = "id_sociedad", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private SociedadEntity sociedad;

	@Column(name = "id_rol", length = 8)
	private String rol;

	@Column(name = "estatus", length = 1)
	private String estatus;
	
	@Column(name = "edad", length = 1)
	private int edad;
	
	@Column(name = "sexo", length = 10)
	private String sexo;
	
	@Column(name = "peso")
	private double peso;
	
	@Column(name = "altura")
	private double altura;
	
	@Column(name = "nivel", length = 45)
	private String nivel;
	
	@Column(name = "intentos", length = 1)
	private int intentos;
	
	@Column(name = "imc", length = 2)
	private int imc;
	
	@Transient
	private List<PermisoUsuario> permisos;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public SociedadEntity getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadEntity sociedad) {
		this.sociedad = sociedad;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public int getImc() {
		return imc;
	}

	public void setImc(int imc) {
		this.imc = imc;
	}

	public List<PermisoUsuario> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<PermisoUsuario> permisos) {
		this.permisos = permisos;
	}

}
