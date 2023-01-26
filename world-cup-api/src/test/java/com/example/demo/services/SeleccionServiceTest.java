package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.controllers.JugadorController;
import com.example.demo.models.*;
import com.example.demo.repositories.SeleccionRepository;

public class SeleccionServiceTest {
	
	@InjectMocks
	SeleccionService seleccionService;
	
	@Mock
	SeleccionRepository seleccionRepository;
	
	@Mock
	JugadorController jugadorController;
	
	@BeforeEach
	public void Start() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void ObtenerSelecciones() {
		assertNotNull(seleccionService.ObtenerSelecciones());
	}
	
	@Test
	public void ObtenerPorPais() {
		Mockito.when(seleccionRepository.findByPais(Mockito.anyString())).thenReturn(new SeleccionModel());
		assertNotNull(seleccionService.ObtenerPorPais(Mockito.anyString()));
	}
	
	@Test
	public void RegistrarSeleccion() {
		ArrayList<JugadorModel> jugadores = new ArrayList<JugadorModel>();
		int i = 0;
		SeleccionModel seleccion = new SeleccionModel();
		seleccion.setPais("Argentina");
		seleccion.setColorCamisetaTitular("Celeste y Blanca");
		seleccion.setColorCamisetaSuplente("Violeta");
		while(i<11) {
			JugadorModel jugador = new JugadorModel();
			jugador.setNombre("Lionel");
			jugador.setApellido("Messi");
			jugador.setNacionalidad("Argentina");
			jugador.setDorsal(10);
			jugador.setEdad(35);
			jugador.setPosicion("Delantero");
			jugador.setPeso(72);
			jugadores.add(jugador);
			i++;
		}
		Mockito.when(jugadorController.ObtenerPorNacionalidad(Mockito.anyString())).thenReturn(jugadores);
		
		Mockito.when(seleccionRepository.save(Mockito.any(SeleccionModel.class))).thenReturn(seleccion);
		
		seleccionService.RegistrarSeleccion(seleccion);
		
		assertNotNull(seleccion.getJugadores().get(0).getNombre());
		
		Mockito.verify(seleccionRepository,Mockito.times(1)).save(Mockito.any(SeleccionModel.class));

	}
	
	@Test
	public void RegistrarSeleccionJugadoresInsuficientes() {
		ArrayList<JugadorModel> jugadores = new ArrayList<JugadorModel>();
		int i = 0;
		SeleccionModel seleccion = new SeleccionModel();
		seleccion.setPais("Argentina");
		seleccion.setColorCamisetaTitular("Celeste y Blanca");
		seleccion.setColorCamisetaSuplente("Violeta");
		while(i<5) {
			JugadorModel jugador = new JugadorModel();
			jugador.setNombre("Lionel");
			jugador.setApellido("Messi");
			jugador.setNacionalidad("Argentina");
			jugador.setDorsal(10);
			jugador.setEdad(35);
			jugador.setPosicion("Delantero");
			jugador.setPeso(72);
			jugadores.add(jugador);
			i++;
		}
		Mockito.when(jugadorController.ObtenerPorNacionalidad(Mockito.anyString())).thenReturn(jugadores);
		
		Mockito.when(seleccionRepository.save(Mockito.any(SeleccionModel.class))).thenReturn(seleccion);
		
		assertNull(seleccionService.RegistrarSeleccion(seleccion));
		
		
	}
	
	@Test
	public void RegistrarSeleccionYaExistente() {
		ArrayList<JugadorModel> jugadores = new ArrayList<JugadorModel>();
		int i = 0;
		SeleccionModel seleccion = new SeleccionModel();
		seleccion.setPais("Argentina");
		seleccion.setColorCamisetaTitular("Celeste y Blanca");
		seleccion.setColorCamisetaSuplente("Violeta");
		while(i<11) {
			JugadorModel jugador = new JugadorModel();
			jugador.setNombre("Lionel");
			jugador.setApellido("Messi");
			jugador.setNacionalidad("Argentina");
			jugador.setDorsal(10);
			jugador.setEdad(35);
			jugador.setPosicion("Delantero");
			jugador.setPeso(72);
			jugadores.add(jugador);
			i++;
		}
		Mockito.when(jugadorController.ObtenerPorNacionalidad(Mockito.anyString())).thenReturn(jugadores);
		
		Mockito.when(seleccionRepository.save(Mockito.any(SeleccionModel.class))).thenReturn(seleccion);
		
		Mockito.when(seleccionService.ObtenerPorPais(Mockito.anyString())).thenReturn(seleccion);
		
		assertNull(seleccionService.RegistrarSeleccion(seleccion));
		
	}
	
	
}
