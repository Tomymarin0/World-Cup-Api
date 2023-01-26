package com.example.demo.models;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table (name = "Jugadores")
public class JugadorModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@NotNull(message = "Nombre no puede ser null")
	private String nombre;
	
	@NotNull(message = "Apellido no puede ser null")
	private String apellido;
	
	@NotNull(message = "Nacionalidad no puede ser null")
	private String nacionalidad;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="jugador_id",referencedColumnName="id")
	private SeleccionModel seleccion;
	
	@NotNull(message = "Posicion no puede ser null")
	private String posicion;
	
	@NotNull(message = "Dorsal no puede ser null")
	@Positive
	@Max(value = 99, message = "El Dorsal no puede ser mayor que 99")
	private int dorsal;
	
	@NotNull(message = "Edad no puede ser null")
	@Positive
	private int edad;
	
	@NotNull(message = "Peso no puede ser null")
	@Positive
	private int peso;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public SeleccionModel getSeleccion() {
		return seleccion;
	}
	public void setSeleccion(SeleccionModel seleccion) {
		this.seleccion = seleccion;
	}
	
	
}
