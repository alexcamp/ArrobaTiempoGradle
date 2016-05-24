
package com.telefonica_chile.comun.asigna.dto;

/**
 * @author crferna

 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.basicos.FechaUtil;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;


public class jornada implements Serializable{
	
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(jornada.class);
	
	private Long jornadaId;
	private Long jornadaIdUsuario;
	private String  jornadaNombre;
	private Integer jornadaMinTotal;
	private Integer jornadaMinLibre;
	private Integer jornadaNroActuaciones;
	
	public Long getJornadaId() {
		return jornadaId;
	}
	
	public Long getJornadaIdUsuario() {
		return jornadaIdUsuario;
	}
	
	public String getJornadaNombre() {
		return jornadaNombre;
	}
		
	public Integer getJornadaMinTotal() {
		return jornadaMinTotal;
	}	

	public Integer getJornadaMinLibre() {
		return jornadaMinLibre;
	}

	public Integer getJornadaNroActuaciones() {
		return jornadaNroActuaciones;
	}
			
	public void setJornadaId(Long jornadaId) {
		this.jornadaId = jornadaId;
	}
	
	public void setJornadaIdUsuario(Long jornadaIdUsuario) {
		this.jornadaIdUsuario = jornadaIdUsuario;
	}
	
	public void setJornNombre(String jornadaNombre) {
		this.jornadaNombre = jornadaNombre;
	}
	
	public void setJornadaMinTotal(Integer jornadaMinTotal) {
		this.jornadaMinTotal = jornadaMinTotal;
	}
	
	public void setJornadaNroActuaciones(Integer jornadaNroActuaciones) {
		this.jornadaNroActuaciones = jornadaNroActuaciones;
	}
	
	public void setJornadaMinLibre(Integer jornadaMinLibre) {
		this.jornadaMinLibre = jornadaMinLibre;
	}
	
	/**
	 * OBJETO QUE ALMACENA LAS JORNADAS DE LOS USUARIOS
	 * @param jornadaId
	 * @param jornadaNombre
	 * @param jornadaMinTotal
	 * @param jornadaNroActuaciones
	 * @param jornadaMinLibre
	 */
	public jornada(Long jornadaId,Long jornadaIdUsuario, String jornadaNombre, Integer jornadaMinTotal, Integer jornadaNroActuaciones, Integer jornadaMinLibre) {
		this.jornadaId = jornadaId;
		this.jornadaIdUsuario = jornadaIdUsuario;
		this.jornadaNombre = jornadaNombre;
		this.jornadaMinTotal = jornadaMinTotal;
		this.jornadaNroActuaciones = jornadaNroActuaciones;
		this.jornadaMinLibre = jornadaMinLibre;
	}

	
	/**
	 * PARA UN USUARIO, UNA FECHA --> RETORNA TODOS LOS BLOQUES JORNADAS DISPONIBLES DEL USUARIO
	 * RETORNANDO UNA LISTA DE BOQUES-JORNADAS LIBRES.
	 */
	
	public static ArrayList recuperaBloquesJornadaLibres(Long idUsuario, String fechaActuacion) {
		
		ArrayList jornadaTrabajo = new ArrayList();
		Character J = new Character ('J');
		
		Date fecha 		= new Date();
		fecha = FechaUtil.stringAFecha(fechaActuacion);
		Date tramoInicio = new Date();
		Date tramoFinal  = new Date();
		int anoFecha = Integer.parseInt(FechaUtil.formatoParaFecha(fecha,"yyyy"));
		int mesFecha = Integer.parseInt(FechaUtil.formatoParaFecha(fecha,"MM"));
		int diaFecha = Integer.parseInt(FechaUtil.formatoParaFecha(fecha,"dd"));
		
		
		tramoFinal = FechaUtil.nuevaFecha(anoFecha,mesFecha,diaFecha,23,59); /* "23.59.59" */
		tramoInicio  = FechaUtil.nuevaFecha(anoFecha,mesFecha,diaFecha,00,00); /* "00.00.00" */

		
//		BloqueLocalHome home;
//		try {
//			home = (BloqueLocalHome)
//					HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			throw new NestedRuntimeException("Problemas recuperando jndi " + BloqueLocalHome.JNDI_NAME, e);
//		}
//		
//		Collection BoqueJornada = null;
//		try {
//			BoqueJornada = home.findBloquesDeJornada(idUsuario, J,tramoInicio,tramoFinal );
//			if (log.isDebugEnabled())
//				log.debug("Pares Bloques-Jornada encontrados: " + BoqueJornada.size());
//		} catch (FinderException e) {
//			log.info("No se encontraron items Bloques-Jornada : " + e.getMessage());
//			return jornadaTrabajo;
//		}
//
//		
//		for (Iterator it = BoqueJornada.iterator(); it.hasNext(); ) {
//			BloqueLocal bloqueEjb 	= (BloqueLocal) it.next();
//			Long bloqueid = (Long) bloqueEjb.getPrimaryKey();
//			
//			
//			if (log.isDebugEnabled())
//				log.debug("Bloque-Jornada Encontrado : BLOQUE_ID <" + bloqueid + "> ");
//	
//			jornadaTrabajo.add(
//				new Bloque(
//				bloqueid,
//				bloqueEjb.getIdJornada(),
//				bloqueEjb.getIdUsuario(),
//				bloqueEjb.getFechaInicio(),
//				bloqueEjb.getFechaTermino(),
//				J) );
//			
//		}
		return jornadaTrabajo;
	}
	
	/**
	 * RETORNA UN OBJETO JORNADA PARA EL ID JORNADA
	 * @param idJornada
	 * @return
	 */
//	public static JornadaLocal recuperaJornada(Integer idJornada) {
//		
//		JornadaLocalHome home;
//		Long jornadaId = null;
//		try {
//			home = (JornadaLocalHome)
//					HomeFactory.getHome(JornadaLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			throw new NestedRuntimeException("Problemas recuperando jndi " + BloqueLocalHome.JNDI_NAME, e);
//		}
//	
//		JornadaLocal jornada = null;
//		try {
//			jornada = home.findByPrimaryKey(idJornada);
//			jornadaId = (Long) jornada.getPrimaryKey();
//			if (log.isDebugEnabled())
//				log.debug("Jornada encontrada: " + jornadaId);
//		} catch (FinderException e) {
//			log.info("No se encontraron items Bloques-Jornada : " + e.getMessage());
//			return jornada;
//		}
//		return jornada;
//	}
	
	/**
	 * RETORNA UNA LISTA DE BLOQUES AGENDADOS O BLOQUEADOS
	 * ASIGNADOS AL USUARIO PARA UNA FECHA ESPECIFICA
	 * @param Integer idTecnico, String fechaActuacion
	 * @return ArrayList bloquesOcupados
	 */
	public static ArrayList recuperaBloquesAsignados(Long idUsuario, String fechaActuacion) {
		ArrayList bloquesOcupados = new ArrayList();


//		Date tramoInicio = new Date();
//		Date tramoFinal  = new Date();
//		int anoFecha = Integer.parseInt(FechaUtil.formatoParaFecha(FechaUtil.stringAFecha(fechaActuacion),"yyyy"));
//		int mesFecha = Integer.parseInt(FechaUtil.formatoParaFecha(FechaUtil.stringAFecha(fechaActuacion),"MM"));
//		int diaFecha = Integer.parseInt(FechaUtil.formatoParaFecha(FechaUtil.stringAFecha(fechaActuacion),"dd"));
//		
//		
//		tramoFinal = FechaUtil.nuevaFecha(anoFecha,mesFecha,diaFecha,23,59); /* "23.59.59" */
//		tramoInicio  = FechaUtil.nuevaFecha(anoFecha,mesFecha,diaFecha,00,00); /* "00.00.00" */
//
//		Character bloqOcup = new Character ('O');
//		
//		BloqueLocalHome home;
//		try {
//			home = (BloqueLocalHome)
//			HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			throw new NestedRuntimeException("Problemas recuperando jndi " + BloqueLocalHome.JNDI_NAME, e);
//		}
//		Collection bloquesOut = null;
//		try {
//		bloquesOut = home.findBuscaBloquesOcupados(idUsuario,tramoInicio,tramoFinal);
//		if (log.isDebugEnabled())
//			log.debug("Bloques Ocupados encontrados: " + bloquesOut.size());
//		} catch (FinderException e) {
//			log.info("No se encontraron items Bloques Ocupados : " + e.getMessage());
//		return bloquesOcupados;
//		}
//		
//		for (Iterator it = bloquesOut.iterator(); it.hasNext(); ) {
//			BloqueLocal bloqueEjb = (BloqueLocal) it.next();
//			Long bloqId = (Long) bloqueEjb.getPrimaryKey();
//			
//	
//			if (log.isDebugEnabled())
//			log.debug("BLOQUE_ID : <" + bloqId + "> USUARIO_ID : <" + idUsuario + "> PETICION_ID : <" + bloqueEjb.getIdPeticion() + ">");
//
//			bloquesOcupados.add(new Bloque(
//					bloqId,
//					bloqueEjb.getIdJornada(),
//					bloqueEjb.getIdUsuario(),
//					bloqueEjb.getFechaInicio(),
//					bloqueEjb.getFechaTermino(),
//					bloqueEjb.getTipo() ));
//		}

		return bloquesOcupados;
	}
	

	/**
	 * DE UN LISTADO DE BLOQUES ASOCIADOS A LA JORNADA
	 * RETORNA LOS MINUTOS TOTALES DE LA JORNADA
	 * @param bloquesJornada
	 * @return minutosTotales
	 */
	
	public static Double recuperaMinutosTotalesJornada(ArrayList listaBloquesJornada) { 
		Double minutosTotales = Double.valueOf("0");
		ArrayList bloquesTecnico = new ArrayList();
		
		double minutos = 0;
		double minutosTmp = 0;
		String  minFin    = "0000";
		String  minInicio = "0000";

		Date tramoInicio = new Date();
		Date tramoFinal  = new Date();
		
		bloquesTecnico = listaBloquesJornada;		
		for(int j=0; j < bloquesTecnico.size(); j++) {
				Bloque tecnicosBloqueJorn = (Bloque) bloquesTecnico.get(j);
				tramoInicio = tecnicosBloqueJorn.getBloqueHoraInicio();
				tramoFinal  = tecnicosBloqueJorn.getBloqueHoraTermino();
			
				minInicio = FechaUtil.formatoFecha(tramoInicio, "Hmmss");
				minFin    = FechaUtil.formatoFecha(tramoFinal , "Hmmss");
				minutos = FechaUtil.convierteHHMMaMM(minFin) - FechaUtil.convierteHHMMaMM(minInicio);
			
				minutosTotales = Double.valueOf(Double.toString(minutos));
			
			if (log.isDebugEnabled())
				log.debug("Bloque: TRAMO: " + minInicio+ " - " + minFin+ " = MINUTOS TOTAL : <" + minutosTotales + ">");
		}
		
		return minutosTotales;
	}


	/**
	 * RECUPERA LA ACTUACIONES ASIGNADAS A UN USUARIO PAR UNA FECHA
	 * @param idUsuario
	 * @param fechaActuacion
	 * @return
	 */
	public static Integer recuperaNroActuacionAsignadas(Long idUsuario, String fechaActuacion) { 
		Integer nroActuacionAsignadas = Integer.valueOf("0");
		ArrayList bloquesAsignados = new ArrayList();
		
		bloquesAsignados = recuperaBloquesAsignados(idUsuario,fechaActuacion);
		nroActuacionAsignadas =  Integer.valueOf(Integer.toString(bloquesAsignados.size()));
		return nroActuacionAsignadas;
	}

	/**
	 * DEPURA EL LISTADO DE USUARIOS Y JORNADAS
	 * RETORNA UN OBJETO 
	 * @param ArrayList usuariosPosibles
	 * @param Long idUsuario
	 * @param String FechaActuacion
	 * @return jornadaDelUsuario jornadaUsuario
	 */

	public static jornadaDelUsuario recuperaJornadaDelUsuario(ArrayList usuariosJornPosibles, usuario usuarioEjb, String fechaActuacion) {
		jornadaDelUsuario jornadaUsuario = null;
		String IdcaUsuario 	= null;
		double tmpMinDispo 	= 0;
		double minutos = 0;
		double minutosInicio = 0;
		double minutosTermino = 0;
		double minutosTmp = 0;
		String  minFin    = "0000";
		String  minInicio = "0000";
		String  segFin    = "0000";
		String  segInicio = "0000";
		
		for(int i=0;i <  usuariosJornPosibles.size(); i++) {
			Bloque usuarioJorn = (Bloque) usuariosJornPosibles.get(i);
			
			if (usuarioJorn.getBloqueUsuarioId().intValue()==usuarioEjb.getUsuarioId().intValue()) { 
				IdcaUsuario = usuarioEjb.getUsuarioIdca();
				
				minInicio = FechaUtil.formatoParaFecha(usuarioJorn.getBloqueHoraInicio() , "Hmmss");
				minFin    = FechaUtil.formatoParaFecha(usuarioJorn.getBloqueHoraTermino() , "Hmmss");

				minutosInicio = FechaUtil.convierteHHMMaMM(minInicio);
				minutosTermino = FechaUtil.convierteHHMMaMM(minFin);
				minutos = minutosTermino - minutosInicio;
			
				
				
				tmpMinDispo = tmpMinDispo 	+ minutos;
				
			}
		
		}
		
		jornadaUsuario = new jornadaDelUsuario(
			usuarioEjb.getUsuarioId(),
			usuarioEjb.getUsuarioIdca(),
			null,
			fechaActuacion,
			Double.valueOf(String.valueOf(tmpMinDispo)),
			Integer.valueOf("0")
			);
		
		return jornadaUsuario;
	}

	/**
	 * VERIFICA SI EXISTE ALGUNA JORNADA PARA EL USUARIO
	 * RETORNA UN BOOLEAN
	 * @param ArrayList listaTecnicos Integer idTecnico
	 * @return boolean existe
	 */
	
	public static boolean existeJornadaUsuario(ArrayList listaUsuario, Long idUsuario) {
		boolean existe = false; 			
			
		for(int i=0;i <  listaUsuario.size(); i++) {
			Bloque usuarioJorn = (Bloque) listaUsuario.get(i);
			if (usuarioJorn.getBloqueUsuarioId()== idUsuario ){
				existe = true;
			}
		}
		
		return existe;
	}

	/**
	 * DEPURA EL LISTADO DE USUARIOS Y JORNADAS
	 * RETORNA UN OBJETO 
	 * @param ArrayList tecnicosPosibles
	 * @param String fechaActuacion
	 * @return jornadaPorTecnico disponibilidadTecnico
	 */
	
	public static ArrayList recuperaDisponibilidadUsuarios(ArrayList usuariosPosibles,String fechaActuacion) {
		ArrayList dispoJornUsuario 					= new ArrayList();
		ArrayList jornadaBloqueUsuario 			    = new ArrayList();
		ArrayList jornadasUsuario					= new ArrayList();
		jornadaDelUsuario jornadasUsuarioTemp		= null;
		int minutosFree = 0;
		Integer minutosOcupados;
		
			
		for(int i=0;i <  usuariosPosibles.size(); i++) {
			usuario usuarioJorn = (usuario) usuariosPosibles.get(i);
			if (!existeJornadaUsuario(dispoJornUsuario,usuarioJorn.getUsuarioId())){
				/* BLOQUES JORNADA USUARIO */
				jornadaBloqueUsuario = recuperaBloquesJornadaLibres(usuarioJorn.getUsuarioId(),  fechaActuacion);
				
				for(int j=0;j <  jornadaBloqueUsuario.size(); j++) {
					Bloque bloqueJorn = (Bloque) jornadaBloqueUsuario.get(j);
				
					dispoJornUsuario.add(new Bloque(
					bloqueJorn.getBloqueId(),
					bloqueJorn.getBloqueJornadaId(),
					bloqueJorn.getBloqueUsuarioId(),
					bloqueJorn.getBloqueHoraInicio(),
					bloqueJorn.getBloqueHoraTermino(),
					bloqueJorn.getBloqueTipo() ));
				}
			}
		}
		/* DEPURAR LAS JORNADAS */
		for(int u=0;u <  usuariosPosibles.size(); u++) {
			usuario usuarioPos = (usuario) usuariosPosibles.get(u);
			jornadasUsuarioTemp = recuperaJornadaDelUsuario( dispoJornUsuario, usuarioPos, fechaActuacion);
		
			minutosFree = recuperaNroActuacionAsignadas(usuarioPos.getUsuarioId(), fechaActuacion).intValue();
			jornadasUsuario.add(new jornadaDelUsuario(
					jornadasUsuarioTemp.getJornUsuId(),
					jornadasUsuarioTemp.getJornUsuIdca(),
					jornadasUsuarioTemp.getJornId(),
					jornadasUsuarioTemp.getJornNombre(),
					jornadasUsuarioTemp.getJornMinDispo(),
					Integer.valueOf(String.valueOf(minutosFree)) ));
		}
		
		return jornadasUsuario;
	}
	
	
	/**
		 * Recupera Jornadas Disponibles de un Usuario en Particular
		 * **/
		public static ArrayList recuperaJornadasParaAgendar(ArrayList usuarios,FiltroDia objFiltroDia) throws NamingException, CreateException, FinderException {
		
		 
			ArrayList bloquesJornada = new ArrayList();
//			BloqueLocal bloqueEjb = null;		
//			usuario objUsuario;
//		
//		
//			for (Iterator iter = usuarios.iterator(); iter.hasNext();) {
//				objUsuario = (usuario) iter.next();
//				Collection listBloquesEjb = getJornadasLibresUsuario(objUsuario.getUsuarioId(),objFiltroDia);
//				for (Iterator iterator = listBloquesEjb.iterator();	iterator.hasNext();	) {
//					bloqueEjb = (BloqueLocal) iterator.next();								
//					Bloque bloqueDTO = new Bloque(	(Long)bloqueEjb.getPrimaryKey(),
//													(Integer)bloqueEjb.getJornadas().getPrimaryKey(),
//													(Long)bloqueEjb.getUsuarios().getPrimaryKey(),
//													bloqueEjb.getFechaInicio(),
//													bloqueEjb.getFechaTermino(),
//													TipoBloque.JORNADA);												
//					bloquesJornada.add(bloqueDTO);								
//				}
//			
//			}
			return bloquesJornada;
		}
	
		public static Collection getJornadasLibresUsuario(Long idUsuario,FiltroDia objFiltroDia) throws NamingException, CreateException, FinderException {
//			BloqueLocalHome home = (BloqueLocalHome)HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);									
//			Collection bloquesJornada = home.findBusquedaJornada(idUsuario,objFiltroDia.getFechaMinima(),objFiltroDia.getFechaMaxima());
//			return bloquesJornada;
			return new ArrayList();	
		}
	
	
}




















