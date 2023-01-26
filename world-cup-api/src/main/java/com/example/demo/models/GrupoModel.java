package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "Grupos")
public class GrupoModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@NotNull(message = "grupo no puede ser null")
	private String grupo;
	
	@NotNull(message = "seleccion1 no puede ser null")
	private String seleccion1;
	
	private int puntos1;
	
	@NotNull(message = "seleccion2 no puede ser null")
	private String seleccion2;
	
	private int puntos2;
	
	@NotNull(message = "seleccion3 no puede ser null")
	private String seleccion3;
	
	private int puntos3;
	
	@NotNull(message = "seleccion4 no puede ser null")
	private String seleccion4;
	
	private int puntos4;
	
	private String primero;
	
	private String segundo;
	
	
	public String getPrimero() {
		return primero;
	}
	public void setPrimero(String primero) {
		this.primero = primero;
	}
	public String getSegundo() {
		return segundo;
	}
	public void setSegundo(String segundo) {
		this.segundo = segundo;
	}
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
	public String getSeleccion3() {
		return seleccion3;
	}
	public void setSeleccion3(String seleccion3) {
		this.seleccion3 = seleccion3;
	}
	public String getSeleccion4() {
		return seleccion4;
	}
	public void setSeleccion4(String seleccion4) {
		this.seleccion4 = seleccion4;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public int getPuntos1() {
		return puntos1;
	}
	public void setPuntos1(int puntos1) {
		this.puntos1 = puntos1;
	}
	public int getPuntos2() {
		return puntos2;
	}
	public void setPuntos2(int puntos2) {
		this.puntos2 = puntos2;
	}
	public int getPuntos3() {
		return puntos3;
	}
	public void setPuntos3(int puntos3) {
		this.puntos3 = puntos3;
	}
	public int getPuntos4() {
		return puntos4;
	}
	public void setPuntos4(int puntos4) {
		this.puntos4 = puntos4;
	}
	
	
}
