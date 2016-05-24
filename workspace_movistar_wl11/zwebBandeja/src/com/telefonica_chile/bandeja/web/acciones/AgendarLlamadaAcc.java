/*
 * Created on Mar 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;


public class AgendarLlamadaAcc extends Accion {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.tecnonautica.mvc.Accion#ejecutar(javax.servlet.http.HttpServletRequest)
	 */
	protected void ejecutar(HttpServletRequest request) throws Evento {
		
	}
	
//	protected void ejecutar(HttpServletRequest request) throws Evento {
//			
//			HttpSession session = request.getSession(true);		
//			String numeroPeticion = request.getParameter("numeroPeticion");
//			ArrayList usuariosConHabilidad = (ArrayList)session.getAttribute("usuarios");
//			SimpleDateFormat sdfHora = new SimpleDateFormat("ddMMyy HH:mm");
//			
//			try{
//				peticion objPeticion = null;
//				
//				//Fecha Mínina  
//				Date fechaMinima = sdfHora.parse(request.getParameter("fecha_limite"));
//				//Fecha Mínina + 5 Días
//				Date fechaMaxima = UtilesWeb.agregaHorasFecha(sdfHora.parse(request.getParameter("fecha_limite")),1);
//												
//					//Avisar a Supervisor de Técnico que debe resolver petición 
//				if(usuariosConHabilidad.size()>0){
//					objPeticion = agendarPeticionSupervisor(fechaMinima,fechaMaxima,numeroPeticion,usuariosConHabilidad);
//				}
//				else{
//					//Avisar a Admin del Sistema
//					objPeticion = agendarPeticionAdminSistema(fechaMinima,fechaMaxima,numeroPeticion);
//				}
//				
//									
//				if(objPeticion.getPeticionResultado()){
//					request.setAttribute("peticionOk",objPeticion);
//					setResultado("exitoAgendaLlamada");  
//				}else{			
//					request.setAttribute("peticionOk",objPeticion);					
//					setResultado("errorAgendaLlamada");
//				}	
//				
//				
//			}catch (Exception e) {
//				
//				// TODO: handle exception
//			}
//
//	}
//
//	/**
//	 * @param fechaMinima
//	 * @param fechaMaxima
//	 * @param numeroPeticion
//	 * @return
//	 */
//	private peticion agendarPeticionAdminSistema(Date fechaMinima, Date fechaMaxima, String numeroPeticion) throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();		
//			return  asignacion.agendarPeticionAdminSistema(fechaMinima,fechaMaxima,numeroPeticion); 
//		} catch (Exception e) {
//			log.error("ERROR :  < AL ASIGNAR PETICIÓN A SUPER USUARIO >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	/**
//	 * @param fechaMinima
//	 * @param fechaMinima1
//	 * @param numeroPeticion
//	 * @param usuariosConHabilidad
//	 * @return
//	 */
//	private peticion agendarPeticionSupervisor(Date fechaMinima, Date fechaMaxima, String numeroPeticion, ArrayList usuariosConHabilidad) throws Evento {
//		try {
//				AsignaSessionLocal asignacion = getAsignacion();		
//				return  asignacion.agendarPeticionSupervisor(fechaMinima,fechaMaxima,numeroPeticion,usuariosConHabilidad); 
//			} catch (Exception e) {
//				log.error("ERROR :  < AL ASIGNAR PETICIÓN A SUPERVISOR >", e);
//				throw new Evento("errorGenerico");
//			}
//	}
//	
//	private AsignaSessionLocal getAsignacion() throws NamingException, RemoteException, CreateException {
//		AsignaSessionLocalHome home = (AsignaSessionLocalHome)HomeFactory.getHome(AsignaSessionLocalHome.JNDI_NAME);
//		AsignaSessionLocal local = home.create();
//		return local;
//	}
}
