/*
 * Created on Feb 24, 2005
 */
package com.telefonica_chile.comun.asigna.dto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;


public class peticion  implements Serializable{
	
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(peticion.class);

	private Long 		peticionId;
	private Integer 	peticionIdJornada;
	private ArrayList 	peticionUsuarios;
	private ArrayList 	peticionSupervisor;
	private Long	 	peticionIdBloque;
	private String		peticionFecha;
	private Character 	peticionTipo;
	private boolean		peticionResultado;
	private Integer 	peticionIdActuacion;
	private Double 		peticionTimeActuacion;
	private Long 		peticionIdRolAsignado;
	private Date 	 	peticionTimeAsignacion;
	private Long		peticionIdBloqueJornada;
	private String 		peticionNombreUsuario;
	private Date 	 	peticionTimeTermino;
			

	
		
	/**
	 * @return
	 */
	public Long getPeticionIdBloqueJornada() {
		return peticionIdBloqueJornada;
	}

	/**
	 * @param long1
	 */
	public void setPeticionIdBloqueJornada(Long long1) {
		peticionIdBloqueJornada = long1;
	}
		
	public Long getPeticionId() {
		return peticionId;
	}

	public Integer getPeticionIdJornada() {
			return peticionIdJornada;
		}
		
	public ArrayList getPeticionUsuarios() {
		return peticionUsuarios;
	}
	
	public ArrayList getPeticionSupervisor() {
		return peticionSupervisor;
	}
		
	public Long getPeticionIdBloque() {
		return peticionIdBloque;
	}
	
	public String getPeticionFecha() {
		return peticionFecha;
	}
	
	public Character getPeticionTipo() {
		return peticionTipo;
	}
	
	public boolean getPeticionResultado() {
		return peticionResultado;
	}

	public Integer getPeticionIdActuacion() {
		return peticionIdActuacion;
	}

	public Double getPeticionTimeActuacion() {
		return peticionTimeActuacion;
	}
	
	public Long getPeticionIdRolAsignado() {
		return peticionIdRolAsignado;
	}
	
	public Date getPeticionTimeAsignacion() {
		return peticionTimeAsignacion;
	}
		
	public void setPeticionId(Long idPeticion) {
		this.peticionId = idPeticion;
	}

	public void setPeticionIdJornada(Integer idJornada) {
		this.peticionIdJornada = idJornada;
	}
	
	public void setPeticionTecnicos(ArrayList usuarios) {
		this.peticionUsuarios = usuarios;
	}

	public void setPeticionSupervisor(ArrayList supervisor) {
		this.peticionSupervisor = supervisor;
	}

	public void setPeticionIdBloque(Long idBloque) {
		this.peticionIdBloque = idBloque;
	}
	
	public void setPeticionFecha(String fecha) {
		this.peticionFecha = fecha;
	}
	
	public void setPeticionTipo(Character tipo) {
		this.peticionTipo = tipo;
	}

	public void setPeticionResultado(boolean resultado) {
		this.peticionResultado = resultado;
	}
	
	public void setPeticionIdActuacion(Integer idActuacion) {
		this.peticionIdActuacion = idActuacion;
	}
	
	public void setPeticionTimeActuacion(Double timeActuacion) {
		this.peticionTimeActuacion = timeActuacion;
	}
	
	public void setPeticionIdRolAsignado(Long idRolAsignado) {
		this.peticionIdRolAsignado = idRolAsignado;
	}
	
	public void setPeticionTimeAsignacion(Date timeAsignacion) {
		this.peticionTimeAsignacion = timeAsignacion;
	}
	

	
	/**Constructor Sin Argumentos...*/
	public peticion() {		
	}

	/**
	 * 
	 * @param idPeticion
	 * @param idJornada
	 * @param usuarios
	 * @param supervisor
	 * @param idBloque
	 * @param fecha
	 * @param tipo
	 * @param resultado
	 * @param idActuacion
	 * @param timeActuacion
	 * @param idRolAsignado
	 * @param timeAsignacion
	 * @param idBloqueJornada
	 */
	public peticion(Long idPeticion, Integer idJornada, ArrayList usuarios, ArrayList supervisor,
		Long idBloque, String fecha, Character tipo, boolean resultado, Integer idActuacion, 
		Double timeActuacion, Long idRolAsignado, Date timeAsignacion, Long idBloqueJornada) {
		this.peticionId = idPeticion;
		this.peticionIdJornada = idJornada;
		this.peticionUsuarios   = usuarios;
		this.peticionSupervisor = supervisor;		
		this.peticionIdBloque = idBloque;
		this.peticionFecha = fecha;
		this.peticionTipo = tipo;
		this.peticionResultado = resultado;
		this.peticionIdActuacion = idActuacion;
		this.peticionTimeActuacion = timeActuacion;
		this.peticionIdRolAsignado = idRolAsignado;
		this.peticionTimeAsignacion = timeAsignacion;
		this.peticionIdBloqueJornada = idBloqueJornada;
	}
	
	/**
	 *  ASIGNA PETICION A ROL TECNICO O EN SU DEFECTO PUBLICA AL SUPERVISOR
	 * @param peticionOk
	 * @return resultado
	 * @throws AsignacionException
	 */
	
	public static peticion asignarPeticion(peticion peticionOk) {
		peticion resultado = null;
		log.debug("Tamaño tecnicos: peticionOk.getPeticionUsuarios().size() ==>"+peticionOk.getPeticionUsuarios().size());
		int tecnicos 	= peticionOk.getPeticionUsuarios().size();
		log.debug("Tamaño supervisor: peticionOk.getPeticionSupervisor().size() ==>"+peticionOk.getPeticionSupervisor().size());
		int supervisor 	= peticionOk.getPeticionSupervisor().size();

		/* verifica que existan usuarios disponibles */
		if (tecnicos > 0) {
			/* verifica si existe un cupo disponible para la peticion a asignar en los usuarios disponibles */
			resultado = Bloque.buscaEspacioBloque(peticionOk);
			if (resultado.getPeticionIdRolAsignado() != null) {
				/* EL USUARIO SI DISPONE DE TIEMPO --> ASIGNACION */
				resultado = grabaPeticion(resultado);
				
			} else {
				/* EL USUARIO NO DISPONE DE TIEMPO --> ENVIA SUPERVISOR */
				resultado = peticionOk ;
				resultado.setPeticionIdBloque(null);
				resultado.setPeticionIdRolAsignado(recuperaIdSupervisor(peticionOk.getPeticionSupervisor()));
				resultado.setPeticionResultado(true);
			}
		} else {
			/* EL USUARIO NO DISPONE DE TIEMPO --> ENVIA SUPERVISOR */
			resultado = peticionOk ;
			resultado.setPeticionIdBloque(null);
			resultado.setPeticionIdRolAsignado(recuperaIdSupervisor(peticionOk.getPeticionSupervisor()));	
			resultado.setPeticionResultado(true);		
		}
		return resultado;
	}
	
	/**
	 *  GRABA LA PETICION AL ROL SELECCIONADO
	 * @param peticionOk
	 * @return
	 * @throws AsignacionException
	 */


	public static peticion grabaPeticion(peticion peticionOk)  {
			
//		boolean resultado = false;			
//		BloqueLocalHome home = null;
//		Long idBloque = null; 
//		Date fechaTermino = new Date();
//		Date fechaInicio = new Date();
//		int tmpMinutos = 0;
//		int tmp2Segundos = 0;
//		double tmpSegundos = 0;
//			
//		//Busco sgte secuencia para Bloque
//		try{
//			idBloque = Boletero.getInstance().obtenerTicket("Bloque");	
//		}catch (Exception e) {
//			log.error("Problemas recuperando Secuencia para Tabla BLOQUE  ", e);
//		}
//			
//		/**Se crea Bloque para restar a la Jornada*/
//		tmpMinutos 		= peticionOk.getPeticionTimeActuacion().intValue();
//		tmpSegundos 	=  (peticionOk.getPeticionTimeActuacion().doubleValue() - tmpMinutos )*60;
//				
//				
//		fechaInicio 	= 	FechaUtil.agregaMinutosFecha(peticionOk.getPeticionTimeAsignacion(),tmpMinutos);
//		fechaInicio		= 	FechaUtil.agregaSegundosFecha(fechaInicio,(int)tmpSegundos);
//		Bloque bloqueResta = new Bloque(
//										idBloque,
//										peticionOk.getPeticionIdJornada(),
//										peticionOk.getPeticionIdRolAsignado(),
//										peticionOk.getPeticionTimeAsignacion(),
//										fechaInicio,
//										TipoBloque.AGENDADO											
//								);
//			
//		/**Para particionar Bloque necesito
//		 * idBloqueOriginal
//		 * Bloque que queremos agendar (para restarselo a la Jornada)
//		 */						
//		boolean resultadoParticion = grabaNuevaJornada(peticionOk.getPeticionIdBloqueJornada(),bloqueResta);
//			
//		if(resultadoParticion){
//				
//			try {
//				home = (BloqueLocalHome) HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//			} catch (NamingException e) {
//				log.error("Problemas recuperando jndi " + BloqueLocalHome.JNDI_NAME, e);
//			}
//			
//			try{
//				tmpMinutos 		= peticionOk.getPeticionTimeActuacion().intValue();
//				tmpSegundos 	=  (peticionOk.getPeticionTimeActuacion().doubleValue() - tmpMinutos )*60;
//				
//				
//				fechaTermino 	= 	FechaUtil.agregaMinutosFecha(peticionOk.getPeticionTimeAsignacion(),tmpMinutos);
//				fechaTermino	= 	FechaUtil.agregaSegundosFecha(fechaTermino,(int)tmpSegundos);
//				home.create(
//						idBloque,
//						peticion.getUsuario(peticionOk.getPeticionIdRolAsignado()),
//						peticion.getJornada(peticionOk.getPeticionIdJornada()),
//						peticionOk.getPeticionTimeAsignacion(),
//						fechaTermino,
//						TipoBloque.AGENDADO,
//						(Long) peticionOk.getPeticionId()			
//				);
//			
//				
//				resultado = eliminaBloqueJornada( peticionOk.getPeticionIdBloqueJornada());
//				peticionOk.setPeticionIdBloque(idBloque);
//				if (log.isDebugEnabled())
//					log.debug("BLOQUE GRABADO EN DB : " + peticionOk.getPeticionIdBloque() + " - PETICION :" +peticionOk.getPeticionId());						
//
//			}catch (Exception e) {
//				log.debug("No se pudo Grabar BLOQUE "+peticionOk.getPeticionIdBloque()+" en B.D...");
//				resultado = false;				
//			}
//				
//		}
//		else{
//			log.error("Error , no se pudo particionar Bloque : "+peticionOk.getPeticionIdBloqueJornada().toString());
//			resultado = false;
//		}
//
//		peticionOk.setPeticionResultado(resultado);
//		return peticionOk;
		return null;	
												
	}


	/**
	 * RETORNA EL ID DEL SUPERVISOR
	 * @param supervisor
	 * @return Long idSupervisor
	 */
	
	public static Long recuperaIdSupervisor(ArrayList supervisor) {
		
		usuario idSupervisor = null;
		idSupervisor = (usuario) supervisor.get(0);
		
		if (idSupervisor.getUsuarioIdSupervisor() == null) {
			/* BUSCAR USUARIO POR DEFCTO PARA LA ACTIVIDAD Y ROL */
			/* RETORNAR ID DEL USUARIO POR DEFECTO */
		}
		
		
		return idSupervisor.getUsuarioIdSupervisor();
	}
	
	/**
	 * GRABA LOS NUEVOS BLOQUES JORNADA
	 * @param idBloqueJornada
	 * @param bloqueCompromiso
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws FinderException
	 */
	private static boolean grabaNuevaJornada(Long idBloqueJornada,Bloque bloqueCompromiso){
			
		boolean resultado = false;
//		Long idBloque = null; 	
//
//			
//		try{
//			BloqueLocalHome home = (BloqueLocalHome)HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//			BloqueLocal bloqueLocal = getBloque(idBloqueJornada);
//			JornadaLocal jornadaLocal = getJornada(bloqueCompromiso.getBloqueJornadaId());
//			UsuarioLocal usuarioLocal = getUsuario(bloqueCompromiso.getBloqueUsuarioId());
//			Bloque bloqueJornada = new Bloque(
//										(Long)bloqueLocal.getPrimaryKey(),
//										bloqueLocal.getIdJornada(),
//										bloqueLocal.getIdUsuario(),
//										bloqueLocal.getFechaInicio(),
//										bloqueLocal.getFechaTermino(),
//										TipoBloque.JORNADA
//										);
//			
//			List listadoBloquesParticion = Bloque.retornaParticionBloque(bloqueJornada,bloqueCompromiso);							
//
//			for (Iterator iter = listadoBloquesParticion.iterator();iter.hasNext();	) {
//				Bloque bloqueChico = (Bloque) iter.next();
//				
//				//Busco sgte secuencia para Bloque
//				try{
//					idBloque = Boletero.getInstance().obtenerTicket("Bloque");	
//				}catch (Exception e) {
//					log.error("Problemas recuperando Secuencia para Tabla BLOQUE  ", e);
//				}
//
//				
//				home.create(
//				idBloque,
//				usuarioLocal,
//				jornadaLocal,
//				bloqueChico.getBloqueHoraInicio(),
//				bloqueChico.getBloqueHoraTermino(),
//				TipoBloque.JORNADA,
//				null);
//				
//			
//			}
//			resultado = true;
//				
//				
//		}catch (Exception e) {
//			log.error("Error al intentar particionar Jornada", e);
//			return false;			
//		}
		return resultado;
			
	}
			
	/**
	 * ELIMINA JORNADAD
	 * @param idBloqueJornada
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws FinderException
	 */	
	public static boolean eliminaBloqueJornada(Long idBloqueJornada) {
			
		boolean resultado = false;
			
//		try{
//			BloqueLocalHome home = (BloqueLocalHome)HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);
//			BloqueLocal bloqueLocal = getBloque(idBloqueJornada);
//						
//			bloqueLocal.remove();
//			resultado = true;
//							
//				
//		} catch (Exception e) {
//			log.error("Error al eliminar jornada", e);
//			return false;			
//		}
		return resultado;
			
	}	
		
//	public static BloqueLocal getBloque(Long idBloque) throws NamingException, RemoteException, CreateException, FinderException {
//		BloqueLocalHome home = (BloqueLocalHome)HomeFactory.getHome(BloqueLocalHome.JNDI_NAME);									
//		BloqueLocal bloqueEntity = home.findByPrimaryKey(idBloque);
//		return bloqueEntity;	
//	}
		

	public static UsuarioLocal getUsuario(Long idUsuario) throws NamingException, RemoteException, CreateException, FinderException {
		UsuarioLocalHome home = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);									
		UsuarioLocal usuarioEntity = home.findByPrimaryKey(new UsuarioKey(idUsuario));
		return usuarioEntity;	
	}
		
//	public static JornadaLocal getJornada(Integer idJornada) throws NamingException, RemoteException, CreateException, FinderException {
//			JornadaLocalHome home = (JornadaLocalHome)HomeFactory.getHome(JornadaLocalHome.JNDI_NAME);									
//			JornadaLocal jornadaEntity = home.findByPrimaryKey(idJornada);
//			return jornadaEntity;	
//	}

	/**
	 * @return
	 */
	public String getPeticionNombreUsuario() {
		return peticionNombreUsuario;
	}

	/**
	 * @param string
	 */
	public void setPeticionNombreUsuario(String string) {
		peticionNombreUsuario = string;
	}

	/**
	 * @return
	 */
	public Date getPeticionTimeTermino() {
		return peticionTimeTermino;
	}

	/**
	 * @param date
	 */
	public void setPeticionTimeTermino(Date date) {
		peticionTimeTermino = date;
	}

}
