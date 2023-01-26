package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.JugadorModel;
import com.example.demo.services.JugadorService;

@RestController
@RequestMapping("/Jugador")
public class JugadorController {
	@Autowired
	JugadorService jugadorService;
	
	@GetMapping("/all")
	public ArrayList<JugadorModel> ObtenerJugadores(){
		return jugadorService.ObtenerJugadores();
	}
	
	@GetMapping( path = "/{Nacionalidad}")
	public ArrayList<JugadorModel> ObtenerPorNacionalidad(@PathVariable("Nacionalidad") String nacionalidad){
		return this.jugadorService.ObtenerPorNacionalidad(nacionalidad);
	}
	
	@PostMapping()
	public JugadorModel RegistrarJugador(@RequestBody JugadorModel jugador){
		return this.jugadorService.RegistrarJugador(jugador);
		
	}
	
	@PostMapping("/{id}/{pais}")
	public String ConvocarJugador(@PathVariable("id") Long id, @PathVariable("pais") String pais) {
		return this.jugadorService.ConvocarJugador(id,pais);
		
	}
	@DeleteMapping("/{id}/{pais}")
	public String EliminarConvocado (@PathVariable("id") Long id, @PathVariable("pais") String pais) {
		return this.jugadorService.EliminarConvocado(id,pais);
	}
	
	@DeleteMapping( path = "/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.jugadorService.EliminarJugador(id);
		if (ok) {
			return "Se elimino el jugador con id " + id;
		} else {
			return "No se pudo eliminar el jugador con id " + id;
		}
		
	}
}
