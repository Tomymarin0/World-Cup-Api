package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controllers.JugadorController;
import com.example.demo.models.JugadorModel;
import com.example.demo.models.SeleccionModel;
import com.example.demo.repositories.SeleccionRepository;

@Service
public class SeleccionService {
	@Autowired
	SeleccionRepository seleccionRepository;
	@Autowired
	JugadorController jugadorController;
	
	public ArrayList<SeleccionModel> ObtenerSelecciones() {
		return (ArrayList<SeleccionModel>) seleccionRepository.findAll();
	}
	
	public SeleccionModel RegistrarSeleccion(SeleccionModel seleccion) {
		ArrayList<JugadorModel> jugadores = jugadorController.ObtenerPorNacionalidad(seleccion.getPais());
		if (jugadores.size()<11) {
			System.out.print("No se pudo crear la seleccion porque no se cumple el minimo de 11 jugadores");
			return null;
		} else {
			if(ObtenerPorPais(seleccion.getPais())==null) {
				int i = 0;
				while (i<jugadores.size() && i<26) {
					jugadores.get(i).setSeleccion(seleccion);;
					i++;
				}
				seleccion.setJugadores(jugadores);
				return seleccionRepository.save(seleccion);
			} else {
				return null;
			}

		}
	}
	
	public SeleccionModel ObtenerPorPais(String pais){
		return seleccionRepository.findByPais(pais);
		
	}
	
	public boolean EliminarSeleccion(Long id) {
		try {
			seleccionRepository.deleteById(id);
			return true;
		} catch (Exception err){
			return false;
		}
	}

	public List<JugadorModel> ObtenerConvocado(String pais) {
		return seleccionRepository.findByPais(pais).getJugadores();
	}

}
