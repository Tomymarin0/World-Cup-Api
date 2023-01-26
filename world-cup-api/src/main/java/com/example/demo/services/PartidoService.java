package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.GrupoModel;
import com.example.demo.models.PartidoModel;
import com.example.demo.repositories.GrupoRepository;
import com.example.demo.repositories.PartidoRepository;
import com.example.demo.repositories.SeleccionRepository;
import java.util.Random;
@Service
public class PartidoService {
	@Autowired
	SeleccionRepository seleccionRepository;
	
	@Autowired
	PartidoRepository partidoRepository;
	
	@Autowired
	GrupoRepository grupoRepository;
	
	public ArrayList<PartidoModel> ObtenerPartidos() {
		return (ArrayList<PartidoModel>) partidoRepository.findAll();
	}

	public PartidoModel GenerarPartido(PartidoModel partido) {
		if (seleccionRepository.findByPais(partido.getSeleccion1())!=null && seleccionRepository.findByPais(partido.getSeleccion2())!=null) {
			Random random1 = new Random();
			Random random2 = new Random();
			int upperbound = 5;
	        //generate random values from 0-7
			int goles1 = random1.nextInt(upperbound);
			int goles2 = random2.nextInt(upperbound);
			partido.setGoles(goles1 + " - " + goles2);
			if (goles1>goles2) {
				partido.setGanador(partido.getSeleccion1());
			} else if (goles2>goles1) {
				partido.setGanador(partido.getSeleccion2());
			} else {
				partido.setGanador("Empate");
			}
			return partidoRepository.save(partido);
		} else {
			return null;
		}

	}
	
	public PartidoModel GenerarPartidoString(String equipo1, String equipo2, String instancia) {
		PartidoModel partido = new PartidoModel();
		partido.setSeleccion1(equipo1);
		partido.setSeleccion2(equipo2);
		partido.setInstancia(instancia);
		if (seleccionRepository.findByPais(equipo1)!=null && seleccionRepository.findByPais(equipo2)!=null) {
			Random random1 = new Random();
			Random random2 = new Random();
			int upperbound = 5;
	        //generate random values from 0-7
			int goles1 = random1.nextInt(upperbound);
			int goles2 = random2.nextInt(upperbound);
			partido.setGolesSel1(goles1);
			partido.setGolesSel2(goles2);
			partido.setGoles(goles1 + " - " + goles2);
			if (goles1>goles2) {
				partido.setGanador(partido.getSeleccion1());
			} else if (goles2>goles1) {
				partido.setGanador(partido.getSeleccion2());
			} else {
				partido.setGanador("Empate");
			}
			return partidoRepository.save(partido);
		} else {
			return null;
		}

	}
	
	public ArrayList<PartidoModel> findByInstancia(String instancia){
		return partidoRepository.findByInstancia(instancia);
	}

	public String GenerarOctavos() {
		ArrayList<GrupoModel> grupos = (ArrayList<GrupoModel>) grupoRepository.findAll();
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		System.out.println(grupos.size());
		System.out.println(findByInstancia("Octavos").size());
		if (grupos.size()==8 && findByInstancia("Octavos").size()==0) {
			//lado A
			//llave 1
			PartidoModel a1b2 = GenerarPartidoString(grupos.get(0).getPrimero(),grupos.get(1).getSegundo(),"Octavos");
			PartidoModel c1d2 = GenerarPartidoString(grupos.get(2).getPrimero(),grupos.get(3).getSegundo(),"Octavos");
			partidos.add(a1b2);
			partidos.add(c1d2);
			//llave 2
			PartidoModel e1f2 = GenerarPartidoString(grupos.get(4).getPrimero(),grupos.get(5).getSegundo(),"Octavos");
			PartidoModel g1h2 = GenerarPartidoString(grupos.get(6).getPrimero(),grupos.get(7).getSegundo(),"Octavos");
			partidos.add(e1f2);
			partidos.add(g1h2);
			
			//lado B
			//llave 3
			PartidoModel b1a2 = GenerarPartidoString(grupos.get(1).getPrimero(),grupos.get(0).getSegundo(),"Octavos");
			PartidoModel d1c2 = GenerarPartidoString(grupos.get(3).getPrimero(),grupos.get(2).getSegundo(),"Octavos");
			partidos.add(b1a2);
			partidos.add(d1c2);
			//llave 4
			PartidoModel f1e2 = GenerarPartidoString(grupos.get(5).getPrimero(),grupos.get(4).getSegundo(),"Octavos");
			PartidoModel h1g2 = GenerarPartidoString(grupos.get(7).getPrimero(),grupos.get(6).getSegundo(),"Octavos");
			partidos.add(f1e2);
			partidos.add(h1g2);
			
			desempate(partidos);
			return "Los Octavos de final han sido simulados correctamente";
		} else {
			return "Los octavos de final NO se han simulado porque ya se simularon o porque aun no ha concluido la fase de grupos";
		}
		
	}

	private void desempate(ArrayList<PartidoModel> partidos) {
		int i = 0;
		
		while(i<partidos.size()) {
			PartidoModel partido = partidos.get(i);
			if(partido.getGanador()=="Empate") {
				Random random1 = new Random();
				Random random2 = new Random();
				Random random3 = new Random();
				int upperbound = 2;
				int TEoPEN = random1.nextInt(upperbound);
				int ganador = random2.nextInt(upperbound);
				if(TEoPEN==0) {
					//Tiempo Extra
					if(ganador==0) {
						partido.setGanador(partido.getSeleccion1());
						int golesTE=random3.nextInt(1,4);
						partido.setGolesSel1(golesTE+partido.getGolesSel1());
						partido.setGoles(partido.getGolesSel1()+ " - "+partido.getGolesSel2()+" T.E.");
					} else if (ganador==1) {
						partido.setGanador(partido.getSeleccion2());
						int golesTE=random3.nextInt(1,4);
						partido.setGolesSel2(golesTE+partido.getGolesSel2());
						partido.setGoles(partido.getGolesSel1()+ " - "+partido.getGolesSel2()+" T.E.");
					}
					
				} else {
					Random random5 = new Random();
					Random random6 = new Random();
					int upperboun = 6;
					int pen1 = random5.nextInt(upperboun);
					int pen2 = random6.nextInt(upperboun);
					if(ganador==0) {
						partido.setGanador(partido.getSeleccion1());
						if(pen1>pen2) {
							partido.setGoles(partido.getGolesSel1()+"("+pen1+")"+" - "+partido.getSeleccion2()+"("+pen2+")");
						} else if (pen2>pen1) {
							partido.setGoles(partido.getGolesSel1()+"("+pen2+")"+" - "+partido.getGolesSel2()+"("+pen1+")");
						} else {
							partido.setGoles(partido.getGolesSel1()+"("+(pen1+1)+")"+" - "+partido.getGolesSel2()+"("+pen2+")");
						}
					} else {
						partido.setGanador(partido.getSeleccion2());
						if(pen1<pen2) {
							partido.setGoles(partido.getGolesSel1()+"("+pen1+")"+" - "+partido.getGolesSel2()+"("+pen2+")");
						} else if (pen2<pen1) {
							partido.setGoles(partido.getGolesSel1()+"("+pen2+")"+" - "+partido.getGolesSel2()+"("+pen1+")");
						} else {
							partido.setGoles(partido.getGolesSel1()+"("+(pen1+1)+")"+" - "+partido.getGolesSel2()+"("+pen2+")");
						}
						
					}
					
				}
			}
			
			partidoRepository.save(partido);
			i++;
			
		}
	}

	public String GenerarCuartos() {
		ArrayList<PartidoModel> octavos = findByInstancia("Octavos");
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		if (octavos.size()!=0 && findByInstancia("Cuartos").size()==0) {
			//ladoA
			//llave 1
			PartidoModel gan1gan2 = GenerarPartidoString(octavos.get(0).getGanador(),octavos.get(1).getGanador(),"Cuartos");
			PartidoModel gan3gan4 = GenerarPartidoString(octavos.get(2).getGanador(),octavos.get(3).getGanador(),"Cuartos");
			partidos.add(gan1gan2);
			partidos.add(gan3gan4);
			//llave 2
			PartidoModel gan5gan6 = GenerarPartidoString(octavos.get(4).getGanador(),octavos.get(5).getGanador(),"Cuartos");
			PartidoModel gan7gan8 = GenerarPartidoString(octavos.get(6).getGanador(),octavos.get(7).getGanador(),"Cuartos");
			partidos.add(gan5gan6);
			partidos.add(gan7gan8);
			
			desempate(partidos);
			return "Los cuartos de final se han simulado correctamente";
		} else {
			return "Los cuartos de final NO se han simulado porque ya se simularon o porque aun no han concluido las fases anteriores";
		}
		
	}

	public String GenerarSemifinales() {
		ArrayList<PartidoModel> cuartos = findByInstancia("Cuartos");
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		
		if (cuartos.size()!=0 && findByInstancia("Semifinales").size()==0) {
			PartidoModel gan1gan2 = GenerarPartidoString(cuartos.get(0).getGanador(),cuartos.get(1).getGanador(),"Semifinales");
			PartidoModel gan3gan4 = GenerarPartidoString(cuartos.get(2).getGanador(),cuartos.get(3).getGanador(),"Semifinales");
			partidos.add(gan1gan2);
			partidos.add(gan3gan4);
			desempate(partidos);
			return "Las Semifinales se han simulado correctamente";
		} else {
			return "Las Semifinales NO se han simulado porque ya se simularon o porque aun no han concluido las fases anteriores";
		}
		
	}

	public String GenerarFinalYtercero() {
		ArrayList<PartidoModel> semis = findByInstancia("Semifinales");
		ArrayList<PartidoModel> partidos = new ArrayList<PartidoModel>();
		String perdedor1;
		String perdedor2;
		if (semis.size()!=0 && findByInstancia("Final").size()==0) {
			PartidoModel Final = GenerarPartidoString(semis.get(0).getGanador(),semis.get(1).getGanador(),"Final");
			if(semis.get(0).getGanador()!=semis.get(0).getSeleccion1()) {
				perdedor1=semis.get(0).getSeleccion1();
			} else {
				perdedor1=semis.get(0).getSeleccion2();
			}
			if(semis.get(1).getGanador()!=semis.get(1).getSeleccion1()) {
				perdedor2=semis.get(1).getSeleccion1();
			} else {
				perdedor2=semis.get(1).getSeleccion2();
			}
			
			PartidoModel Tercero = GenerarPartidoString(perdedor1,perdedor2,"Tercero");
			partidos.add(Final);
			partidos.add(Tercero);
			desempate(partidos);
			return "La Final y El Tercer Puesto se han simulado correctamente";
		} else {
			return "La Final y El Tercer Puesto NO se han simulado porque ya se simularon o porque aun no han concluido las fases anteriores";
		}
	}
}
