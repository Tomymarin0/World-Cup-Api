package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.models.JugadorModel;
import com.example.demo.models.SeleccionModel;
import com.example.demo.repositories.JugadorRepository;
import com.example.demo.repositories.SeleccionRepository;

public class JugadorServiceTest {
	
	@InjectMocks
	private JugadorService jugadorService;
	
	@Mock
	private SeleccionRepository seleccionRepository;
	
	@Mock
	private JugadorRepository jugadorRepository;
	
	@BeforeEach
	public void Start() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void RegistrarJugador() {
		JugadorModel jugador = new JugadorModel();
		jugador.setNombre("Lionel");
		jugador.setApellido("Messi");
		jugador.setNacionalidad("Argentina");
		jugador.setDorsal(10);
		jugador.setEdad(35);
		jugador.setPosicion("Delantero");
		jugador.setPeso(72);
		
		Mockito.when(jugadorRepository.save(Mockito.any(JugadorModel.class))).thenReturn(jugador);
		
		jugador = jugadorService.RegistrarJugador(jugador);
			
		assertNotNull(jugador.getNombre());
		
		Mockito.verify(jugadorRepository,Mockito.times(1)).save(Mockito.any(JugadorModel.class));

	}
	
	@Test
	public void ObtenerJugadores() {
		ArrayList<JugadorModel> array = jugadorService.ObtenerJugadores();
		
		assertNotNull(array);
	}

	@Test
	public void ObtenerPorNacionalidad() {
		ArrayList<JugadorModel> array = new ArrayList<JugadorModel>();

		Mockito.when(jugadorRepository.findByNacionalidad(Mockito.anyString())).thenReturn(array);
		
		assertNotNull(jugadorService.ObtenerPorNacionalidad(Mockito.anyString()));

	}
	
	@Test
	public void ObtenerPorId() {
		//Mockito.when(jugadorRepository.findById(Mockito.anyLong())).thenReturn(new JugadorModel());//
	}
	
	@Test
	public void convocarJugador() {

		Mockito.when(seleccionRepository.findByPais(Mockito.anyString())).thenReturn(new SeleccionModel());
		Mockito.when(jugadorRepository.findById(Mockito.anyLong()));
//		Mockito.when(jugadorRepository.save(Mockito.any(JugadorModel.class))).thenReturn(new JugadorModel());
//		Mockito.when(seleccionRepository.save(Mockito.any(SeleccionModel.class))).thenReturn(new SeleccionModel());

		jugadorService.ConvocarJugador(Mockito.anyLong(), Mockito.anyString());
	}



}
