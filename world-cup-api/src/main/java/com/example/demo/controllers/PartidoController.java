package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.JugadorModel;
import com.example.demo.models.PartidoModel;
import com.example.demo.services.PartidoService;
import com.example.demo.services.SeleccionService;

@RestController
@RequestMapping("/Partido")
public class PartidoController {
	@Autowired
	SeleccionService seleccionService;
	
	@Autowired
	PartidoService partidoService;

	@GetMapping("/all")
	public ArrayList<PartidoModel> ObtenerPartidos(){
		return partidoService.ObtenerPartidos();
	}
	
	@PostMapping()
	public PartidoModel GenerarPartido(@RequestBody PartidoModel partido){
		return this.partidoService.GenerarPartido(partido);
	}
	
	@PostMapping("/octavos")
	public String GenerarOctavos(){
		return this.partidoService.GenerarOctavos();
	}
	
	@PostMapping("/cuartos")
	public String GenerarCuartos(){
		return this.partidoService.GenerarCuartos();
	}
	
	@PostMapping("/semifinales")
	public String GenerarSemifinales(){
		return this.partidoService.GenerarSemifinales();
	}
	
	@PostMapping("/final")
	public String GenerarFinalYtercero(){
		return this.partidoService.GenerarFinalYtercero();
	}
	
	@GetMapping( path = "/{Instancia}")
	public ArrayList<PartidoModel> findByInstancia(@PathVariable("Instancia") String instancia){
		return this.partidoService.findByInstancia(instancia);
	}
	
}
