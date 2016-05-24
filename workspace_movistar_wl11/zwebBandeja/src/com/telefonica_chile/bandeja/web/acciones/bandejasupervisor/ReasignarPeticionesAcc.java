/*
 * Created on Mar 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones.bandejasupervisor;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.accionesMasivas.AccionesMasivasSessionLocal;
import com.telefonica_chile.bandeja.accionesMasivas.AccionesMasivasSessionLocalHome;
import com.telefonica_chile.bandeja.web.utiles.UtilesWeb;


/**
 * @author rbpizar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReasignarPeticionesAcc extends Accion {
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	protected void ejecutar(HttpServletRequest request) throws Evento {
		
		try{
			int cantidadPeticiones = Integer.parseInt(request.getParameter("cantidad"));
			String nuevoUsuario = request.getParameter("cmbusuario");
			String reasignarTC = request.getParameter("reasignarTC");
			
			
			for (int i = 0; i < cantidadPeticiones; i++) {
				
				//Verifico que haya checkeado una peticion				
				//String checkActivo = UtilesWeb.Revisa_Valor_CheckBox(request.getParameter("check_"+i));
				String checkActivo = UtilesWeb.Revisa_Valor_CheckBox(request.getParameter("checkbox"+i));
				if ( checkActivo.equalsIgnoreCase("1") && (nuevoUsuario!=null) ) {
					//Averiguo N° Petición
					String idPeticion = request.getParameter("petiId_"+i);
					String idActividad = request.getParameter("actividadId_"+i);
					String antiguoUsuario = request.getParameter("duenoPeticion_"+i);

					//Actualizar Petición a otro Usuario
					log.info("Reasignando Peticion [" + idPeticion + "," + idActividad + "," + antiguoUsuario + "]");
					boolean flag = false;

					boolean updateBintegrada = actualizarBIntegradaUsuario(new Long(nuevoUsuario), new Long(idPeticion), 
						new Long(idActividad), new Long(antiguoUsuario));
					if ( !updateBintegrada )
						log.info("No se Pudo Reasignar Peticion Bandeja [" + idPeticion + "," + idActividad + "," + antiguoUsuario + "]");

					if ( updateBintegrada ) {
						try {
							flag = actualizarPeticionUsuario( new Long(nuevoUsuario), new Long(idPeticion),
								 new Long(idActividad), new Long(antiguoUsuario) );
						} catch (Exception e1) {
							flag = false;
							log.error("No se Pudo Reasignar Peticion Bitacora [" + idPeticion + "," + idActividad + "," + antiguoUsuario + "] : " + e1.getMessage());
							updateBintegrada = actualizarBIntegradaUsuario(new Long(antiguoUsuario), new Long(idPeticion), 
								new Long(idActividad), new Long(nuevoUsuario));
						}
					}
				}
			}

			if ("TC".equals(reasignarTC) )
				setResultado("exitoReasignacionTC");
			else
				setResultado("exitoReasignacion");
			
		}catch (Exception e) {
			log.error("No se Pudo Reasignar Peticion. ", e);
			request.setAttribute("msj",new String("Error al Intentar Reasignar Peticiones..."));
			setResultado("errorReasignacion");	
		}
	}




	private boolean actualizarPeticionUsuario(Long nuevoUsuario, Long idPeticion , Long idActividad , Long antiguoUsuario) {
		//TODO: ESTO NO DEBERIA SER SEGUN EL AMPBITO DE LA PETICION????? A CADA @????

		//ServicioSessionLocal local = getServicioSessionBean();
		//Determinamos si la aplicacion relacionada a la peticion es de ATST. En ese caso solo reasignamos
		//ya que no tenemos bitacoras de las peticiones de la bandeja.
		AccionesMasivasSessionLocal local = null;
		try {
			local = getAccionMasivaSession();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

		if ( local == null )
			return false;

		if( local.getNombreAplicacionPeticion(idPeticion).equals("ATST") )
		   return true;

		/*ServicioSession remote = getServicioSessionBean();
		if (remote == null)
			return false;*/
		
		boolean f = false;
		/*try {
			f = remote.reasignarPeticion( nuevoUsuario, idPeticion, idActividad, antiguoUsuario);
		} catch (com.telefonica_chile.bandeja.datos.bandeja.BandejaException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}*/
		
		return (f);
	}
	
	private boolean actualizarBIntegradaUsuario(Long nuevoUsuario, Long idPeticion , Long idActividad , Long antiguoUsuario) throws NamingException, CreateException, BandejaException, com.telefonica_chile.bandeja.datos.bandeja.BandejaException, RemoteException {
		AccionesMasivasSessionLocal local = getAccionMasivaSession();
		return (local.updateUsuarioBIntegrada(nuevoUsuario,idPeticion,idActividad,antiguoUsuario));		
	}
	
	
		
	private AccionesMasivasSessionLocal getAccionMasivaSession() throws NamingException, CreateException {
		AccionesMasivasSessionLocalHome home =  
					(AccionesMasivasSessionLocalHome)HomeFactory.getHome(AccionesMasivasSessionLocalHome.JNDI_NAME);			
		AccionesMasivasSessionLocal local = home.create();
		return local;
	}
	
//	private ServicioSession getServicioSessionBean()  {
//		ServicioSession remote = null;
//		try {
//			ServicioSessionHome home = (ServicioSessionHome) HomeFactory.getHome(ServicioSessionHome.JNDI_NAME, HomeFactory.REMOTO_VPISTBBA);
//			remote = home.create();
//		} catch (NamingException e) {
//			log.error("No se Pudo Reasignar Peticion. NamingException.", e );
//		} catch (RemoteException e) {
//			log.error("No se Pudo Reasignar Peticion. RemoteException.", e );
//		} catch (CreateException e) {
//			log.error("No se Pudo Reasignar Peticion. CreateException.", e );
//		}
//
//		return remote;
//	}

}
