package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.models.GrupoModel;
import com.example.demo.models.PartidoModel;
import com.example.demo.models.SeleccionModel;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.PartidoRepository;
import com.example.demo.repositories.SeleccionRepository;

public class PartidoServiceTest {
	
	@InjectMocks
	private PartidoService partidoService;
	
	@Mock
	private SeleccionRepository seleccionRepository;
	
	@Mock
	private GrupoRepository grupoRepository;
	
	@Mock
	private PartidoRepository partidoRepository;
	
	@BeforeEach
	public void Start() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void TestGenerarPartidoString() {
		Mockito.when(seleccionRepository.findByPais(Mockito.anyString())).thenReturn(new SeleccionModel());
		
		Mockito.when(partidoRepository.save(Mockito.any(PartidoModel.class))).thenReturn(new PartidoModel());

		partidoService.GenerarPartidoString("Argentina","Mexico","Octavos");
				
		Mockito.verify(partidoRepository,Mockito.times(1)).save(Mockito.any(PartidoModel.class));

	}
	
	@Test
	public void TestGenerarPartido() {
		//creacion de objeto partidoModel
		PartidoModel partidoModel = new PartidoModel();
		partidoModel.setSeleccion1("Argentina");
		partidoModel.setSeleccion2("Mexico");
		
		//Aca dice que cuando se utilice el metodo findByPais que envie cualquier string y que devuelva un SeleccionModel
		Mockito.when(seleccionRepository.findByPais(Mockito.anyString())).thenReturn(new SeleccionModel());
		
		//Aca dice que cuando se utilice partidoRepository.save() que guarde cualquier objeto tipo PartidoModel, y que devuelva un PartidoModel
		Mockito.when(partidoRepository.save(Mockito.any(PartidoModel.class))).thenReturn(partidoModel);
		
		//Aca se envia el objeto partidoModel recien Mockeado al metodo para probarlo
		partidoService.GenerarPartido(partidoModel);
		
		//Aca para verificar que el metodo creo el partido exitosamente verificamos que ganador sea distinto de null
		assertNotNull(partidoModel.getGanador());
		
		//Verifica que haya pasado por lo menos una vez por el medoto .save
		Mockito.verify(partidoRepository,Mockito.times(1)).save(Mockito.any(PartidoModel.class));
	}

	@Test
	public void TestGenerarPartidoElse() {
		//creacion de objeto partidoModel
		PartidoModel partidoModel = new PartidoModel();
		partidoModel.setSeleccion1("Argentina");
		partidoModel.setSeleccion2("Mexico");
		
		//Aca dice que cuando se utilice el metodo findByPais que envie cualquier string y que devuelva null para entrar en el else
		Mockito.when(seleccionRepository.findByPais(Mockito.anyString())).thenReturn(null);
		
		//Aca se envia el objeto partidoModel recien Mockeado al meotod para probarlo
		partidoService.GenerarPartido(partidoModel);
		
		//Aca para verificar que el metodo creo el partido exitosamente verificamos que ganador sea igual a null
		assertNull(partidoModel.getGanador());
	}
	
	@Test
	public void TestGenerarOctavos() {
		GrupoModel grupoA = new GrupoModel();
		GrupoModel grupoB = new GrupoModel();
		GrupoModel grupoC = new GrupoModel();
		GrupoModel grupoD = new GrupoModel();
		GrupoModel grupoE = new GrupoModel();
		GrupoModel grupoF = new GrupoModel();
		GrupoModel grupoG = new GrupoModel();
		GrupoModel grupoH = new GrupoModel();
		grupoA.setPrimero("Paises Bajos");
		grupoA.setSegundo("Senegal");
		grupoB.setPrimero("Inglaterra");
		grupoB.setSegundo("Estados Unidos");
		grupoC.setPrimero("Argentina");
		grupoC.setSegundo("Polonia");
		grupoD.setPrimero("Francia");
		grupoD.setSegundo("Australia");
		grupoF.setPrimero("Japon");
		grupoF.setSegundo("España");
		grupoG.setPrimero("Marruecos");
		grupoG.setSegundo("Croacia");
		grupoH.setPrimero("Brasil");
		grupoH.setSegundo("Suiza");
		ArrayList<GrupoModel> grupos = new ArrayList<GrupoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		grupos.add(grupoA);
		grupos.add(grupoB);
		grupos.add(grupoC);
		grupos.add(grupoD);
		grupos.add(grupoE);
		grupos.add(grupoF);
		grupos.add(grupoG);
		grupos.add(grupoH);
		PartidoModel game = new PartidoModel();
		game.setSeleccion1("Argentina");
		game.setSeleccion2("Mexico");
		game.setGanador("Argentina");
		Mockito.when(grupoRepository.findAll()).thenReturn(grupos);
		
		Mockito.when(partidoService.findByInstancia(Mockito.anyString())).thenReturn(partidos);
		
		Mockito.when(seleccionRepository.findByPais(Mockito.anyString())).thenReturn(new SeleccionModel());
		
		Mockito.when(partidoService.GenerarPartidoString(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(game);
		
		partidoService.GenerarOctavos();
		
		assertNotNull(partidoService.findByInstancia("Octavos"));
	}
	
	@Test
	public void TestGenerarOctavosElse(){
		//Creamos los array para los returns
		ArrayList<GrupoModel> grupos = new ArrayList<GrupoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		
		//Cuando se use findAll devuelve grupos
		Mockito.when(grupoRepository.findAll()).thenReturn(grupos);
		
		//Cuando se use findByInstancia, envia cualquier string y devuelve partidos
		Mockito.when(partidoRepository.findByInstancia(Mockito.anyString())).thenReturn(partidos);
		
		assertNotNull(partidoService.GenerarOctavos());
		
	}
	
	@Test
	public void TestGenerarCuartos() {
		ArrayList<PartidoModel> octavos = new ArrayList<PartidoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		PartidoModel partido1 = new PartidoModel();
		PartidoModel partido2 = new PartidoModel();
		PartidoModel partido3 = new PartidoModel();
		PartidoModel partido4 = new PartidoModel();
		PartidoModel partido5 = new PartidoModel();
		PartidoModel partido6 = new PartidoModel();
		PartidoModel partido7 = new PartidoModel();
		PartidoModel partido8 = new PartidoModel();
		octavos.add(partido1);
		octavos.add(partido2);
		octavos.add(partido3);
		octavos.add(partido4);
		octavos.add(partido5);
		octavos.add(partido6);
		octavos.add(partido7);
		octavos.add(partido8);
		
		Mockito.when(partidoRepository.findByInstancia(Mockito.anyString())).thenReturn(octavos).thenReturn(partidos);
		
		partidoService.GenerarCuartos();
	}
	
	@Test
	public void TestGenerarCuartosElse() {
		ArrayList<PartidoModel> octavos = new ArrayList<PartidoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		
		Mockito.when(partidoRepository.findByInstancia(Mockito.anyString())).thenReturn(octavos).thenReturn(partidos);
		
		partidoService.GenerarCuartos();
	}
	
	@Test
	public void TestGenerarSemis() {
		ArrayList<PartidoModel> octavos = new ArrayList<PartidoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		PartidoModel partido1 = new PartidoModel();
		PartidoModel partido2 = new PartidoModel();
		PartidoModel partido3 = new PartidoModel();
		PartidoModel partido4 = new PartidoModel();

		octavos.add(partido1);
		octavos.add(partido2);
		octavos.add(partido3);
		octavos.add(partido4);

		
		Mockito.when(partidoRepository.findByInstancia(Mockito.anyString())).thenReturn(octavos).thenReturn(partidos);
		
		partidoService.GenerarSemifinales();
	}
	
	@Test
	public void TestGenerarSemisElse() {
		ArrayList<PartidoModel> octavos = new ArrayList<PartidoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		
		Mockito.when(partidoRepository.findByInstancia(Mockito.anyString())).thenReturn(octavos).thenReturn(partidos);
		
		partidoService.GenerarSemifinales();
	}
	
	@Test
	public void TestGenerarFinalYtercero() {
		ArrayList<PartidoModel> octavos = new ArrayList<PartidoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		PartidoModel partido1 = new PartidoModel();
		PartidoModel partido2 = new PartidoModel();

		octavos.add(partido1);
		octavos.add(partido2);
		
		Mockito.when(partidoRepository.findByInstancia(Mockito.anyString())).thenReturn(octavos).thenReturn(partidos);
		
		partidoService.GenerarFinalYtercero();
	}
	
	@Test
	public void TestGenerarFinalYterceroElse() {
		ArrayList<PartidoModel> octavos = new ArrayList<PartidoModel>();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		
		Mockito.when(partidoRepository.findByInstancia(Mockito.anyString())).thenReturn(octavos).thenReturn(partidos);
		
		partidoService.GenerarFinalYtercero();
	}
	

}
