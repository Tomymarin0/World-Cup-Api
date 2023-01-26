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
import com.example.demo.models.SeleccionModel;
import com.example.demo.services.SeleccionService;

@RestController
@RequestMapping("/Seleccion")
public class SeleccionController {
	@Autowired
	SeleccionService seleccionService;
	
	@GetMapping("/all")
	public ArrayList<SeleccionModel> ObtenerSelecciones(){
		return seleccionService.ObtenerSelecciones();
	}
	
	@PostMapping()
	public SeleccionModel RegistrarSeleccion(@RequestBody SeleccionModel seleccion){
		return this.seleccionService.RegistrarSeleccion(seleccion);
		
	}
	
	@GetMapping( path = "/{Pais}")
	public SeleccionModel ObtenerPorPais(@PathVariable("Pais") String pais){
		return this.seleccionService.ObtenerPorPais(pais);
	}
	
	@GetMapping( path = "/convocados/{Pais}")
	public List<JugadorModel> ObtenerConvocados(@PathVariable("Pais") String pais){
		return this.seleccionService.ObtenerConvocado(pais);
	}
	
	@DeleteMapping( path = "/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.seleccionService.EliminarSeleccion(id);
		if (ok) {
			return "Se elimino la seleccion con id " + id;
		} else {
			return "No se pudo eliminar la seleccion con id " + id;
		}
		
	}

}
