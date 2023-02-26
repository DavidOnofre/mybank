package com.kodigo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersona;

	@NotNull
	@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;

	@NotNull
	@Size(min = 1, max = 1, message = "Valores aceptados M: masculino, F: femenino")
	@Column(name = "genero", nullable = false, length = 1)
	private String genero;

	@NotNull
	@Column(name = "edad", nullable = false)
	private Integer edad;

	@NotNull
	@Size(min = 10, max = 10, message = "Identificacion debe tener 10 caracteres")
	@Column(name = "identificacion", nullable = false, length = 10)
	private String identificacion;

	@NotNull
	@Size(max = 150, message = "Direccion acepta hasta 150 caracteres")
	@Column(name = "direccion", nullable = false, length = 150)
	private String direccion;

	@NotNull
	@Size(min = 10, max = 10, message = "Identificacion debe tener 10 caracteres")
	@Column(name = "telefono", nullable = false, length = 10)
	private String telefono;

	@OneToOne(mappedBy = "persona")
	private Cliente cliente;

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
