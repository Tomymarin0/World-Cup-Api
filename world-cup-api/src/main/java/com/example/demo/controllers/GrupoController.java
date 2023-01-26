package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.GrupoModel;
import com.example.demo.services.GrupoService;

@RestController
@RequestMapping("/Grupos")
public class GrupoController {
	@Autowired
	GrupoService grupoService;
	
	@GetMapping("/all")
	public ArrayList<GrupoModel> ObtenerGrupos() {
		return grupoService.ObtenerGrupos();
	}
	
	@GetMapping( path = "/{grupo}")
	public GrupoModel ObtenerPorGrupo(@PathVariable("grupo") String grupo){
		return this.grupoService.ObtenerPorGrupo(grupo);
	}
	
	@PostMapping("/CrearGrupoA")
	public String CrearGrupoA(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"Qatar","Ecuador","Paises Bajos","Senegal","A");
	}
	
	@PostMapping("/CrearGrupoB")
	public String CrearGrupoB(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"Inglaterra","Iran","Estados Unidos","Gales","B");
	}
	
	@PostMapping("/CrearGrupoC")
	public String CrearGrupoC(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"Argentina","Arabia Saudita","Mexico","Polonia","C");
	}
	
	@PostMapping("/CrearGrupoD")
	public String CrearGrupoD(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"Francia","Australia","Dinamarca","Tunez","D");
	}
	
	@PostMapping("/CrearGrupoE")
	public String CrearGrupoE(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"España","Costa Rica","Alemania","Japon","E");
	}
	
	@PostMapping("/CrearGrupoF")
	public String CrearGrupoF(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"Belgica","Canada","Croacia","Marruecos","F");
	}
	
	@PostMapping("/CrearGrupoG")
	public String CrearGrupoG(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"Brasil","Serbia","Suiza","Camerun","G");
	}
	
	@PostMapping("/CrearGrupoH")
	public String CrearGrupoH(GrupoModel grupo) {
		return this.grupoService.CrearGrupo(grupo,"Uruguay","Corea Del Sur","Ghana","Portugal","H");
	}
	

}
