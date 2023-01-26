package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Table (name = "Partidos")
public class PartidoModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@NotNull(message = "Seleccion1 no puede ser null")
	private String seleccion1;
	
	@NotNull(message = "Seleccion2 no puede ser null")
	private String seleccion2;
	
	
	private String goles;
	
	
	private String ganador;
	
	
	private int golesSel1;
	
	
	private int golesSel2;
	
	
	private String instancia;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeleccion1() {
		return seleccion1;
	}
	public void setSeleccion1(String seleccion1) {
		this.seleccion1 = seleccion1;
	}
	public String getSeleccion2() {
		return seleccion2;
	}
	public void setSeleccion2(String seleccion2) {
		this.seleccion2 = seleccion2;
	}
	public String getGoles() {
		return goles;
	}
	public void setGoles(String goles) {
		this.goles = goles;
	}
	public String getGanador() {
		return ganador;
	}
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}
	public String getInstancia() {
		return instancia;
	}
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}
	public int getGolesSel1() {
		return golesSel1;
	}
	public void setGolesSel1(int golesSel1) {
		this.golesSel1 = golesSel1;
	}
	public int getGolesSel2() {
		return golesSel2;
	}
	public void setGolesSel2(int golesSel2) {
		this.golesSel2 = golesSel2;
	}

	
	
}
