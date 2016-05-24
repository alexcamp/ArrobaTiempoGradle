/*
 * CR - 00027016 - Jul 1, 2009
 *		Clase auxiliar que contiene metodos utiles para la busqueda de peticiones - German P.
 */
package com.telefonica_chile.atiempo.utiles;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import co.com.atiempo.dto.BuscadorPeticionVpiDTO;
import co.com.atiempo.dto.DominioTipoPcDTO;

/**
 * @author 810884
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BuscadorPeticionUtiles {
	
	public static final int COD_PETICION = 1;
	public static final int COD_IDENTIFICADOR_CLIENTE = 2;
	public static final int COD_IDENTIFICADOR_PC_LINEA = 3;
	public static final int COD_IDENTIFICADOR_PC_TV = 4;
	public static final int COD_IDENTIFICADOR_PC_IC = 5;
	
	public static final int COD_BUSCADOR_VPI = 6;
	public static final int COD_BUSCADOR_GES_OS = 7;
	public static final int COD_BUSCADOR_OUTBOUND = 8;
	public static final int COD_BUSCADOR_INBOUND = 9;
	public static final int COD_BUSCADOR_INSTALAR_EQUIPOS = 10;
	public static final int COD_BUSCADOR_RECOGIDA_EQUIPOS = 11;	
	
	public static int obtenerCodigoConsulta(Long idPeticionAtis, String rutCli, String rutDv, String idPc, int typePc){
		int cod = -1;
		if (idPeticionAtis != null){
			cod = COD_PETICION;
		}else if(!rutCli.equals("") || !rutDv.equals("")){
			cod = COD_IDENTIFICADOR_CLIENTE;
		}else if(idPc!=null && !idPc.equals("")){ //cambio validación para corregir full scan - Gustavo
			if (typePc == 1){
				cod = COD_IDENTIFICADOR_PC_LINEA;
			}else if (typePc == 2){
				cod = COD_IDENTIFICADOR_PC_TV;
			}else if (typePc == 3){
				cod = COD_IDENTIFICADOR_PC_IC;
			}
		}
		return cod;
	}
	
	public static String mensajeResultado (BuscadorPeticionVpiDTO buscadorPeticionVpiDTO){
		
		int codigo = buscadorPeticionVpiDTO.getIdBusqueda();
		Long idPeticionAtis = buscadorPeticionVpiDTO.getIdPeticionAtis();
		String rutCli = buscadorPeticionVpiDTO.getRutCli();
		String rutDv = buscadorPeticionVpiDTO.getRutDv();
		String idPc = buscadorPeticionVpiDTO.getIdPc();
		String mens = "";
		switch (codigo){
			case COD_PETICION:
				mens = "Listado de Peticiones encontradas para Petición " + idPeticionAtis + "";
				break;
			case COD_IDENTIFICADOR_CLIENTE:
				mens = "Listado de Peticiones encontradas para Cliente " + rutCli + "-" + rutDv + "";
				break;
			case COD_IDENTIFICADOR_PC_LINEA:
				mens = "Listado de Peticiones encontradas para PC - LINEA " + idPc + "";
				break;				
			case COD_IDENTIFICADOR_PC_TV:
				mens = "Listado de Peticiones encontradas para PC - TV " + idPc + "";
				break;
			case COD_IDENTIFICADOR_PC_IC:
				mens = "Listado de Peticiones encontradas para PC - IC " + idPc + "";
				break;	
		}
		return mens;
	}	
	
	public static String mensajeResultadoVacio (BuscadorPeticionVpiDTO buscadorPeticionVpiDTO){
		
		int codigo = buscadorPeticionVpiDTO.getIdBusqueda();
		Long idPeticionAtis = buscadorPeticionVpiDTO.getIdPeticionAtis();
		String rutCli = buscadorPeticionVpiDTO.getRutCli();
		String rutDv = buscadorPeticionVpiDTO.getRutDv();
		String idPc = buscadorPeticionVpiDTO.getIdPc();
		
		String mens = "";
		switch (codigo){
			case COD_PETICION:
				mens = "No se encontraron Peticiones para Petición [" + idPeticionAtis + "]";
				break;
			case COD_IDENTIFICADOR_CLIENTE:
				mens = "No se encontraron Peticiones para Cliente [" + rutCli + "-" + rutDv + "]";
				break;
			case COD_IDENTIFICADOR_PC_LINEA:
				mens = "No se encontraron Peticiones para PC - LINEA [" + idPc + "]";
				break;				
			case COD_IDENTIFICADOR_PC_TV:
				mens = "No se encontraron Peticiones para PC - TV [" + idPc + "]";
				break;
			case COD_IDENTIFICADOR_PC_IC:
				mens = "No se encontraron Peticiones para PC - IC [" + idPc + "]";
				break;	
		}
		return mens;
	}
	
	public static List mensajeSugerencias (BuscadorPeticionVpiDTO busquedaPeticionDTO){
		
		List listaSugerencias = null;
		
		int codigo = busquedaPeticionDTO.getIdBusqueda();
		boolean limiteAlcanzado = busquedaPeticionDTO.isLimiteAlcanzado();
		
		if (limiteAlcanzado){
			listaSugerencias = new ArrayList();
			String mensLimite = "Se llegó al limite (" + busquedaPeticionDTO.getLimitePeticiones() + ") de visualización, existen mas peticiones coincidentes que no se visualizan";
			listaSugerencias.add(mensLimite);
			
			if (codigo == COD_PETICION || codigo == COD_IDENTIFICADOR_CLIENTE){
				String mens = "Utilice el filtro Identificador PC para reducir la cantidad de peticiones coincidentes";
				listaSugerencias.add(mens);
			}
		}
		
		return listaSugerencias;
	}
	
	public static void cargarComboTipoPc(HttpServletRequest request){
		List ls = new ArrayList();
		ls.add(new DominioTipoPcDTO("1","Linea"));
		ls.add(new DominioTipoPcDTO("2","Tv"));
		ls.add(new DominioTipoPcDTO("3","IC (PCs)"));
		request.setAttribute("comboTipoPc", ls);
	}
}
