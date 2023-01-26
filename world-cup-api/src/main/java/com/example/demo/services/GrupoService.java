package com.example.demo.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.GrupoModel;
import com.example.demo.models.PartidoModel;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.SeleccionRepository;

@Service
public class GrupoService {
	@Autowired
	GrupoRepository grupoRepository;
	
	@Autowired
	SeleccionRepository seleccionRepository;
	
	@Autowired
	PartidoService partidoService;
	
	public ArrayList<GrupoModel> ObtenerGrupos(){
		return (ArrayList<GrupoModel>) grupoRepository.findAll();
	}
	
	public ArrayList<Integer> calcularPrimeroSegundo(ArrayList<Integer> puntos,ArrayList<Integer> dif){
		int i = 0;
		int j = 0;
		int primero = 0;
		int pos1 = 0;
		int pos2 = 0;
		int segundo = 0;
		while (i<puntos.size()) {
			if (primero<=puntos.get(i)){
				if (primero==puntos.get(i)) {
					if(dif.get(pos1)<dif.get(i)) {
						primero=puntos.get(i);
						pos1=i;
					} 
				} else {
					primero=puntos.get(i);
					pos1=i;
				}
				
				i++;
			} else {
				i++;
			}
		}
		
		while (j<puntos.size()) {
			if (segundo<=puntos.get(j) && j!=pos1){
				if (segundo==puntos.get(j)) {
					if(dif.get(pos2)<dif.get(j)) {
						segundo=puntos.get(j);
						pos2=j;
					} 
				} else {
					segundo=puntos.get(j);
					pos2=j;
				}
				
				j++;
			} else {
				j++;
			}
		}
		

		ArrayList<Integer> PriSeg = new ArrayList<Integer>();
		PriSeg.add(pos1);
		PriSeg.add(pos2);
		return PriSeg;
	}
	
	public ArrayList<Integer> simularPartidos(String sel1, String sel2, String sel3, String sel4, String grupo){
		//Fecha 1
		PartidoModel partido1 = partidoService.GenerarPartidoString(sel1,sel2,grupo);
		PartidoModel partido2 = partidoService.GenerarPartidoString(sel3,sel4,grupo);
		
		//Fecha 2
		PartidoModel partido3 = partidoService.GenerarPartidoString(sel1,sel3,grupo);
		PartidoModel partido4 = partidoService.GenerarPartidoString(sel2,sel4,grupo);
		
		//Fecha 3
		PartidoModel partido5 = partidoService.GenerarPartidoString(sel1,sel4,grupo);
		PartidoModel partido6 = partidoService.GenerarPartidoString(sel2,sel3,grupo);
		
		
		int qatar = 0;
		int ecuador = 0;
		int paisesBajos = 0;
		int senegal = 0;
		
		if(partido1.getGanador()==sel1) {
			qatar = qatar + 3;
		} else if (partido1.getGanador()==sel2) {
			ecuador = ecuador + 3;
		} else {
			qatar = qatar + 1;
			ecuador = ecuador + 1;
		}
		
		if(partido2.getGanador()==sel3) {
			paisesBajos = paisesBajos + 3;
		} else if (partido2.getGanador()==sel4) {
			senegal = senegal + 3;
		} else {
			paisesBajos = paisesBajos + 1;
			senegal = senegal + 1;
		}
		
		if(partido3.getGanador()==sel1) {
			qatar = qatar + 3;
		} else if (partido3.getGanador()==sel3) {
			paisesBajos = paisesBajos + 3;
		} else {
			qatar = qatar + 1;
			paisesBajos = paisesBajos + 1;
		}
		
		if(partido4.getGanador()==sel4) {
			senegal = senegal + 3;
		} else if (partido4.getGanador()==sel2) {
			ecuador = ecuador + 3;
		} else {
			ecuador = ecuador + 1;
			senegal = senegal + 1;
		}
		
		if(partido5.getGanador()==sel1) {
			qatar = qatar + 3;
		} else if (partido5.getGanador()==sel4) {
			senegal = senegal + 3;
		} else {
			qatar = qatar + 1;
			senegal = senegal + 1;
		}
		
		if(partido6.getGanador()==sel3) {
			paisesBajos = paisesBajos + 3;
		} else if (partido6.getGanador()==sel2) {
			ecuador = ecuador + 3;
		} else {
			paisesBajos = paisesBajos + 1;
			ecuador = ecuador + 1;
		}
		ArrayList<Integer> puntos = new ArrayList<Integer>();
		puntos.add(qatar);
		puntos.add(ecuador);
		puntos.add(paisesBajos);
		puntos.add(senegal);
				
		return puntos;
	}
	
	public ArrayList<Integer> diferenciaDeGol(String sel1, String sel2, String sel3, String sel4, String grupo){
		ArrayList<PartidoModel> partidos = partidoService.findByInstancia(grupo);
		ArrayList<String> selecciones = new ArrayList<String>();
		ArrayList<Integer> diferencias = new ArrayList<Integer>();
		selecciones.add(sel1);
		selecciones.add(sel2);
		selecciones.add(sel3);
		selecciones.add(sel4);
		int i = 0;
		int dif1 = 0;
		int dif2 = 0;
		int dif3 = 0;
		int dif4 = 0;
		int calculo = 0;
		
		while(i<partidos.size()) {
			if(partidos.get(i).getGanador()==sel1) {
				calculo= partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
				if(calculo<0) {
					calculo=calculo*-1;
				}
				dif1=dif1+calculo;
			}
			if(partidos.get(i).getGanador()==sel2) {
				calculo= partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
				if(calculo<0) {
					calculo=calculo*-1;
				}
				dif2=dif2+calculo;
			}
			if(partidos.get(i).getGanador()==sel3) {
				calculo= partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
				if(calculo<0) {
					calculo=calculo*-1;
				}
				dif3=dif3+calculo;
			}
			if(partidos.get(i).getGanador()==sel4) {
				calculo= partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
				if(calculo<0) {
					calculo=calculo*-1;
				}
				dif4=dif4+calculo;
			}
			if((partidos.get(i).getSeleccion1()==sel1 ||  partidos.get(i).getSeleccion2()==sel1)
				&&	partidos.get(i).getGanador()!=sel1) {
				calculo = partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
				if(calculo==0) {
				} else if (calculo>0){
					calculo=calculo*-1;
				}
				dif1=dif1+calculo;
			}
			if((partidos.get(i).getSeleccion1()==sel2 ||  partidos.get(i).getSeleccion2()==sel2)
					&&	partidos.get(i).getGanador()!=sel2) {
					calculo = partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
					if(calculo==0) {
					} else if (calculo>0){
						calculo=calculo*-1;
					}
					dif2=dif2+calculo;
			}
			if((partidos.get(i).getSeleccion1()==sel3 ||  partidos.get(i).getSeleccion2()==sel3)
					&&	partidos.get(i).getGanador()!=sel3) {
					calculo = partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
					if(calculo==0) {
					} else if (calculo>0){
						calculo=calculo*-1;
					}
					dif3=dif3+calculo;
			}
			if((partidos.get(i).getSeleccion1()==sel4 ||  partidos.get(i).getSeleccion2()==sel4)
					&&	partidos.get(i).getGanador()!=sel4) {
					calculo = partidos.get(i).getGolesSel1()-partidos.get(i).getGolesSel2();
					if(calculo==0) {
					} else if (calculo>0){
						calculo=calculo*-1;
					}
					dif4=dif4+calculo;
			}
			i++;
			
		}
		diferencias.add(dif1);
		diferencias.add(dif2);
		diferencias.add(dif3);
		diferencias.add(dif4);

		return diferencias;
	}
	
	public String CrearGrupo(GrupoModel grupo,String sel1,String sel2,String sel3,String sel4,String group) {
		if(seleccionRepository.findByPais(sel1)!=null && seleccionRepository.findByPais(sel2)!=null
		&& seleccionRepository.findByPais(sel3)!=null && seleccionRepository.findByPais(sel4)!=null  && ObtenerPorGrupo(group)==null) {
			ArrayList<Integer> puntos = simularPartidos(sel1,sel2,sel3,sel4,group);
			grupo.setSeleccion1(sel1);
			grupo.setPuntos1(puntos.get(0));
			grupo.setSeleccion2(sel2);
			grupo.setPuntos2(puntos.get(1));
			grupo.setSeleccion3(sel3);
			grupo.setPuntos3(puntos.get(2));
			grupo.setSeleccion4(sel4);
			grupo.setPuntos4(puntos.get(3));
			ArrayList<Integer> dif = diferenciaDeGol(sel1,sel2,sel3,sel4,group);
			ArrayList<Integer> PriSeg = calcularPrimeroSegundo(puntos,dif);
			
			if(PriSeg.get(0)==0) {
				grupo.setPrimero(sel1);
			} else if (PriSeg.get(0)==1) {
				grupo.setPrimero(sel2);
			} else if (PriSeg.get(0)==2) {
				grupo.setPrimero(sel3);
			} else if (PriSeg.get(0)==3) {
				grupo.setPrimero(sel4);
			}
			
			if(PriSeg.get(1)==0) {
				grupo.setSegundo(sel1);
			} else if (PriSeg.get(1)==1) {
				grupo.setSegundo(sel2);
			} else if (PriSeg.get(1)==2) {
				grupo.setSegundo(sel3);
			} else if (PriSeg.get(1)==3) {
				grupo.setSegundo(sel4);
			}

			seleccionRepository.findByPais(sel1).setGrupo(group);
			seleccionRepository.findByPais(sel2).setGrupo(group);
			seleccionRepository.findByPais(sel3).setGrupo(group);
			seleccionRepository.findByPais(sel4).setGrupo(group);
			grupo.setGrupo(group);
			grupoRepository.save(grupo);
			return "El grupo " + group + " fue creado exitosamente";
		} else {
			return "El grupo " + group + " no fue creado porque hay selecciones de este grupo aun no generadas o porque el grupo ya existe";
		}
	}
		
	public GrupoModel ObtenerPorGrupo(String grupo) {
		return grupoRepository.findByGrupo(grupo);
	}
}
