package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;



public class jornadaDelUsuario implements Serializable{
	

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	private Long jornId;
	private String  jornNombre;
	private Double jornMinDispo;
	private Long jornUsuId;
	private String  jornUsuIdca;
	private Integer jornUsuNroActuaciones;


	public Long getJornId() {
		return jornId;
	}
	
	public String getJornNombre() {
		return jornNombre;
	}
		
	public Double getJornMinDispo() {
		return jornMinDispo;
	}	

	public Long getJornUsuId() {
		return jornUsuId;
	}
	
	public String getJornUsuIdca() {
		return jornUsuIdca;
	}
	
	public Integer getJornUsuNroActuaciones() {
		return jornUsuNroActuaciones;
	}
	

	public void setJornId(Long jornId) {
		this.jornId = jornId;
	}
	
	public void setJornUsuId(Long jornUsuId) {
		this.jornUsuId = jornUsuId;
	}
	
	public void setJornUsuIdca(String jornUsuIdca) {
		this.jornUsuIdca = jornUsuIdca;
	}
	
	public void setJornNombre(String jornNombre) {
		this.jornNombre = jornNombre;
	}
	
	public void setJornMinDispo(Double jornMinDispo) {
		this.jornMinDispo = jornMinDispo;
	}
	
	public void setJornUsuNroActuaciones(Integer jornUsuNroActuaciones) {
		this.jornUsuNroActuaciones = jornUsuNroActuaciones;
	}
	

	/**
	 * OBJETO TIPO JORNADAS PARA UNA FECHA POR USUARIO
	 * @param jornUsuId
	 * @param jornUsuIdca
	 * @param jornId
	 * @param jornNombre
	 * @param jornMinDispo
	 * @param jornUsuNroActuaciones
	 */
	public jornadaDelUsuario(Long jornUsuId, String jornUsuIdca, Long jornId, String jornNombre, 
		Double jornMinDispo, Integer jornUsuNroActuaciones) {

		this.jornId = jornId;
		this.jornUsuId = jornUsuId;
		this.jornUsuIdca = jornUsuIdca;
		this.jornNombre = jornNombre;
		this.jornMinDispo = jornMinDispo;
		this.jornUsuNroActuaciones = jornUsuNroActuaciones;
	}

	/**
	 * ORDENA DE MAYOR A MENOR TIEMPO LIBRE
	 * RETORNA UNA LISTA ORDENADA
	 * @param ArrayList jornadaTecnicos
	 * @return ArrayList jornadaOrdenadaTecnicos
	 */
	
	public static ArrayList ordenaJornadaUsuario(ArrayList jornadaUsuario) {
		 
		int elementos 				= jornadaUsuario.size();
		jornadaDelUsuario temp 		= null;
		/*
		 * 	ALGORITMO
		 * 	for (i=0;i < (elementos-1);i++) {
		 *		for (j= (i + 1); j < = (elementos-1) ; j++) {
		 *			if numeros[j] > numeros[i] {
		 *  			tmp = numeros[i];
         *  			numeros[i] = numeros[j];
         *  			numeros[j] = tmp;
		 *			}
		 *  	}
		 * }
		 * 
		 */
		for(int i=0;i <  (elementos-1) ; i++) {
			
			for(int j = (i +1) ;j <= (elementos - 1) ; j ++) {
				jornadaDelUsuario usuarioJorn = (jornadaDelUsuario) jornadaUsuario.get(i);
				jornadaDelUsuario usuarioJorn2 = (jornadaDelUsuario) jornadaUsuario.get(j);
				if (usuarioJorn2.getJornMinDispo().doubleValue() > usuarioJorn.getJornMinDispo().doubleValue()) {
					temp = usuarioJorn;
					jornadaUsuario.set(i,usuarioJorn2);
					jornadaUsuario.set(j,temp);
				}
			}
		}
		return jornadaUsuario;
	}
	
	/**
	 * ORDENA JORNADA DE USUARIOS DE MENOR A MAYOR POR CANTIDAD DE ACTUACIONES ASIGNADAS
	 * @param jornadaUsuarios
	 * @return jornadaUsuarios
	 */
	public static ArrayList ordenaJornadaPorNroActuaciones(ArrayList jornadaUsuarios) {
		int elementos 	= jornadaUsuarios.size();
		jornadaDelUsuario temp 		= null;
		/*
		 * 	ALGORITMO
		 * 	for (i=0;i < (elementos-1);i++) {
		 *		for (j= (i + 1); j < = (elementos-1) ; j++) {
		 *			if numeros[j] > numeros[i] {
		 *  			tmp = numeros[i];
		 *  			numeros[i] = numeros[j];
		 *  			numeros[j] = tmp;
		 *			}
		 *  	}
		 * }
		 * 
		 */
		for(int i=0;i <  (elementos-1) ; i ++) {
			
			for(int j = (i +1) ;j <= (elementos - 1) ; j ++) {
				jornadaDelUsuario usuarioJorn = (jornadaDelUsuario) jornadaUsuarios.get(i);
				jornadaDelUsuario usuarioJorn2 = (jornadaDelUsuario) jornadaUsuarios.get(j);
				if (usuarioJorn2.getJornUsuNroActuaciones().intValue() < usuarioJorn.getJornUsuNroActuaciones().intValue()) {
					temp = usuarioJorn;
					jornadaUsuarios.set(i,usuarioJorn2);
					jornadaUsuarios.set(j,temp);
				}
			}
		}
		return jornadaUsuarios;
	}
	
	/**
	 * VERIFICA SI EXISTE EL NRO DENTRO DE LA LISTA
	 * @param lista
	 * @param nroActuaciones
	 * @return existe
	 */	
	public static boolean existeJornadaUsuario(ArrayList lista, Integer nroActuaciones) {
		boolean existe = false; 
			
		for(int i=0;i <  lista.size(); i++) {
			Integer nro = (Integer) lista.get(i);
			if (nro.intValue() == nroActuaciones.intValue() ){
				existe = true;
			}
		}
		return existe;
	}

	/**
	 * RETORNA UNA LISTA DE NROS DE CANTIDADES DISTINTAS
	 * @param jornadaUsuario
	 * @return jornadaUsuario
	 */
	public static ArrayList retornaActuacionesDiferentes(ArrayList jornadaUsuario) {
		ArrayList nroActDiferentes = new ArrayList();
		
		for(int i=0;i <  jornadaUsuario.size() ; i++) {
			jornadaDelUsuario tecnicoJorn = (jornadaDelUsuario) jornadaUsuario.get(i);
			if (!existeJornadaUsuario(nroActDiferentes,tecnicoJorn.getJornUsuNroActuaciones()))	{
				nroActDiferentes.add(tecnicoJorn.getJornUsuNroActuaciones());	
			}

		}
		return nroActDiferentes;
	}
	
	/**
	 * RETORNA UN SUBGRUPO DE JORNADA POR USUARIO, DONDE NRO ACTUACIONES SEA IGUAL AL PARAMETRO SOLICITADO
	 * @param jornadaUsuario
	 * @param nroActuacion
	 * @return grupoJornada
	 */
	public static ArrayList retornaGrupoJornadaUsuario(ArrayList jornadaUsuario, Integer nroActuacion) {
		ArrayList grupoJornada = new ArrayList();
		
		for(int i=0;i <  jornadaUsuario.size() ; i++) {
			jornadaDelUsuario jornUsu = (jornadaDelUsuario) jornadaUsuario.get(i);
			if (jornUsu.getJornUsuNroActuaciones().intValue() == nroActuacion.intValue())	{
		
				grupoJornada.add(new jornadaDelUsuario(
				jornUsu.getJornUsuId(),
				jornUsu.getJornUsuIdca(),
				jornUsu.getJornId(),
				jornUsu.getJornNombre(),
				jornUsu.getJornMinDispo(),
				jornUsu.getJornUsuNroActuaciones()
				));	
			}

		}
		return grupoJornada;
	}
	
	/**
	 * DE DOS LISTAS; LAS JUNTA Y RESTONA EL RESULTADO
	 * @param resultado
	 * @param grupoJornadaTecnicos
	 * @return resultado
	 */
	public static ArrayList addGrupoJornadaUsuarios(ArrayList resultado, ArrayList grupoJornadaUsuarios) {
			
		for(int i=0;i <  grupoJornadaUsuarios.size() ; i++) {
			jornadaDelUsuario jornUsu = (jornadaDelUsuario) grupoJornadaUsuarios.get(i);
			
			resultado.add(new jornadaDelUsuario(
					jornUsu.getJornUsuId(),
					jornUsu.getJornUsuIdca(),
					jornUsu.getJornId(),
					jornUsu.getJornNombre(),
					jornUsu.getJornMinDispo(),
					jornUsu.getJornUsuNroActuaciones()
				));	
		}
		return resultado;
	}

	/**
	 * ORDENA LAS JORNADAS POR CANTIDAD DE ACTUACIONES Y TIEMPO DISPONIBLE
	 * @param jornadaUsuarios
	 * @return resultado
	 */
	public static ArrayList jornadasOrdenadasAgupadas(ArrayList jornadaUsuarios) {
		ArrayList nroActDiferentes 		= new ArrayList();
		ArrayList grupoJornadaUsuarios	= new ArrayList();
		ArrayList resultado				= new ArrayList();
		int elementos 					= jornadaUsuarios.size();
		jornadaDelUsuario temp 			= null;
		
		jornadaUsuarios = ordenaJornadaPorNroActuaciones(jornadaUsuarios);
		nroActDiferentes = retornaActuacionesDiferentes(jornadaUsuarios);
		
		for(int i=0;i <  nroActDiferentes.size() ; i++) {
			/* Obtiene Nro. cantidad Actuaciones */
			Integer nroAct = (Integer) nroActDiferentes.get(i);
			/* Retorna un subgrupo de JornadasUsuario */
			grupoJornadaUsuarios = retornaGrupoJornadaUsuario(jornadaUsuarios, nroAct);
			/* Ordena por Tiempo Disponible la subjornada */
			grupoJornadaUsuarios = ordenaJornadaUsuario(grupoJornadaUsuarios);
			/* junta los trozos de subjornadas */
			resultado			 = addGrupoJornadaUsuarios(resultado,grupoJornadaUsuarios);
		}
		return resultado;
	}
}