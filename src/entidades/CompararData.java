package entidades;

import java.util.List;
import java.util.ArrayList;

public class CompararData {
	List<String> listaTemp = new ArrayList<>();

	public CompararData(List<String> listaTempEntrada) {
		this.listaTemp = listaTempEntrada;
	}

	
	
	public static List<String> listaRetorno(List<String> listaOrigem, List<String> listaDestino, String[] DataOString, String[] DataDString){
		
		List<String> listPastaDiferenteNome = new ArrayList<>();
		
		for (String elemento : listaOrigem) {			
			if (listaDestino.contains(elemento)) {
				int tempO = listaOrigem.indexOf(elemento);
				int tempD = listaDestino.indexOf(elemento);
				
				if(DataOString[tempO] != DataDString[tempD] ) {
					String tO = DataOString[tempO].toString();
					int anoO = Integer.parseInt(tO.substring(24,28));
					int mesO = ConversorDataMes.toIntMes(tO.substring(4,7));
					int diaO = Integer.parseInt(tO.substring(8, 10));
					int horaO = Integer.parseInt(tO.substring(11, 13));
					int minO = Integer.parseInt(tO.substring(14,16));
					int secO = Integer.parseInt(tO.substring(17, 19));
					//System.out.println(anoO +" "+mesO+" "+diaO+" "+horaO+" "+minO+" "+secO);
					
					String tD = DataDString[tempD].toString();
					int anoD = Integer.parseInt(tD.substring(24,28));
					int mesD = ConversorDataMes.toIntMes(tD.substring(4,7));
					int diaD = Integer.parseInt(tD.substring(8, 10));
					int horaD = Integer.parseInt(tD.substring(11, 13));
					int minD = Integer.parseInt(tD.substring(14,16));
					int secD = Integer.parseInt(tD.substring(17, 19));
					//System.out.println(anoD +" "+mesD+" "+diaD+" "+horaD+" "+minD+" "+secD);
					
					
					if(anoO == anoD) {
						if(mesO == mesD) {
							if(diaO == diaD) {
								if(horaO == horaD) {
									if(minO == minD) {
										if(secO == secD) {
											//System.out.println("As datas coincidem. Nada a ser feito\n");
										}else {
											if(secO > secD && horaO >= horaD ) {
											listPastaDiferenteNome.add(elemento);
											}else {
												//System.out.println("As datas coincidem. Nada a ser feito\n");
											}
										}													
									}else {
										if(minO > minD && horaO >= horaD ) {
											listPastaDiferenteNome.add(elemento);
										}
									}
								}else {
									if(horaO > horaD && diaO >= diaD ) {
										listPastaDiferenteNome.add(elemento);
									}
								}
							}else {
								if(diaO > diaD && mesO >= mesD) {
									listPastaDiferenteNome.add(elemento);
								}
							}
							
						}else {
							if(mesO > mesD && anoO >= anoD) {
								listPastaDiferenteNome.add(elemento);
							}
						}
					}else {
						if(anoO > anoD) {
							listPastaDiferenteNome.add(elemento);
						}
					}
					
					//System.out.println("Ano: "+ anoO + " Mes: " + tO.substring(4, 7) + " Dia: " + diaO +
					//		 " Hora: " + horaO + " Min: " + minO + " Segundos: " + secO);
					//System.out.println("Ano: "+ anoD + " Mes: " + tD.substring(4, 7) + " Dia: " + diaD +
					//		 " Hora: " + horaD + " Min: " + minD + " Segundos: " + secD);
				}																		
			}
		}
		return  listPastaDiferenteNome;
		
	}
		
}
