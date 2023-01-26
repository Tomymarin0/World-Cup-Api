package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.JugadorModel;
import com.example.demo.models.SeleccionModel;
import com.example.demo.repositories.JugadorRepository;
import com.example.demo.repositories.SeleccionRepository;



@Service
public class JugadorService {
	@Autowired
	JugadorRepository jugadorRepository;
	
	@Autowired
	SeleccionRepository seleccionRepository;

	public ArrayList<JugadorModel> ObtenerJugadores() {
		return (ArrayList<JugadorModel>) jugadorRepository.findAll();
	}
	
	public JugadorModel RegistrarJugador(JugadorModel jugador) {
		return jugadorRepository.save(jugador);
	}
	
	
	public ArrayList<JugadorModel> ObtenerPorNacionalidad(String nacionalidad){
		return (ArrayList<JugadorModel>) jugadorRepository.findByNacionalidad(nacionalidad);
		
	}

	public boolean EliminarJugador(Long id) {
		try {
			jugadorRepository.deleteById(id);
			return true;
		} catch (Exception err){
			return false;
		}
	}

	public JugadorModel ObtenerPorId(Long id) {
		return jugadorRepository.findPlayerById(id);
		
	}
	
	public String ConvocarJugador(Long id, String pais) {
		SeleccionModel seleccion = seleccionRepository.findByPais(pais);
		JugadorModel jugador = ObtenerPorId(id);
		if(jugador!=null && seleccion.getJugadores().size()<26) {
			jugador.setSeleccion(seleccion);
			seleccion.getJugadores().add(jugador);
			seleccionRepository.save(seleccion);
			jugadorRepository.save(jugador);
			return ("Jugador " + id + " ha sido convocado por " + pais);
		} else {
			return ("Jugador " + id + " no existe o ya hay 26 jugadores");
		}

	}

	public String EliminarConvocado(Long id, String pais) {
		SeleccionModel seleccion = seleccionRepository.findByPais(pais);
		JugadorModel jugador = ObtenerPorId(id);
		List<JugadorModel> convocados = seleccion.getJugadores();
		if(jugador.getSeleccion()!=null && seleccion.getJugadores().size()>11) {
			jugador.setSeleccion(null);
			int i = 0;
			boolean encontrado = false;
			while (i<convocados.size() || encontrado != true) {
				if (convocados.get(i).getId()== id) {
					encontrado = true;
					convocados.remove(i);
				} else {
					i++;

				}
			}
			seleccionRepository.save(seleccion);
			jugadorRepository.save(jugador);
			return ("Jugador " + id + " ha sido eliminado de la convocatoria de " + pais);
		} else {
			return ("Jugador " + id + " no ha sido eliminado de la convocatoria porque no estaba convocado por "+ pais + " o porque no puede haber menos de 11 convocados");
		}

	}
}