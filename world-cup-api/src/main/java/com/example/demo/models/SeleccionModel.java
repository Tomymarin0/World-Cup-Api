package com.example.demo.models;

import java.util.List;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table (name = "Selecciones")
public class SeleccionModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@NotNull(message = "Pais no puede ser null")
	private String pais;
	
	@NotNull(message = "colorCamisetaTitular no puede ser null")
	private String colorCamisetaTitular;
	
	@NotNull(message = "colorCamisetaSuplente no puede ser null")
	private String colorCamisetaSuplente;
	
	@OneToMany(mappedBy = "seleccion")
	@JsonIgnore
	private List<JugadorModel> jugadores;
	
	private String grupo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getColorCamisetaTitular() {
		return colorCamisetaTitular;
	}
	public void setColorCamisetaTitular(String colorCamisetaTitular) {
		this.colorCamisetaTitular = colorCamisetaTitular;
	}
	public String getColorCamisetaSuplente() {
		return colorCamisetaSuplente;
	}
	public void setColorCamisetaSuplente(String colorCamisetaSuplente) {
		this.colorCamisetaSuplente = colorCamisetaSuplente;
	}
	public List<JugadorModel> getJugadores() {
		return jugadores;
	}
	public void setJugadores(List<JugadorModel> jugadores) {
		this.jugadores = jugadores;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

}
