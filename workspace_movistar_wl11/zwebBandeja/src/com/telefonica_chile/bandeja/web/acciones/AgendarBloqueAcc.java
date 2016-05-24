/*
 * Created on Mar 2, 2005
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

public class AgendarBloqueAcc extends Accion {

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
//		HttpSession session = request.getSession(true);
//		String numeroPeticion = request.getParameter("numeroPeticion");
//		ArrayList usuariosConHabilidad =
//			(ArrayList) session.getAttribute("usuarios");
//
//		try {
//
//			if (usuariosConHabilidad.size() > 0) {
//
//				for (int k = 0; k < 12; k++) {
//
//					for (int j = 0; j < 5; j++) {
//
//						String radio =
//							UtilesWeb.Revisa_Valor_CheckBox(
//								request.getParameter("radio_" + k + "_" + j));
//
//						if (radio.equalsIgnoreCase("1")) {
//							String horaInicio =
//								request
//									.getParameter("fecha_" + k + "_" + j)
//									.trim()
//									+ " "
//									+ request.getParameter(
//										"horaInicio_" + k + "_" + j);
//							String horaTermino =
//								request
//									.getParameter("fecha_" + k + "_" + j)
//									.trim()
//									+ " "
//									+ request.getParameter(
//										"horaTermino_" + k + "_" + j);
//							peticion objPeticion =
//								agendarPeticion(
//									horaInicio,
//									horaTermino,
//									numeroPeticion,
//									usuariosConHabilidad);
//
//							if (objPeticion.getPeticionResultado()) {
//								request.setAttribute("peticionOk", objPeticion);
//								setResultado("exitoAgenda");
//							} else {
//								request.setAttribute("peticionOk", objPeticion);
//								setResultado("errorAgenda");
//							}
//
//						}
//					}
//				}
//
//			}
//
//		} catch (Exception e) {
//			request.setAttribute(
//				"msj",
//				new String("No es posible asignar Error" + e));
//			setResultado("errorAgenda");
//		}
//
//	}
//
//	private peticion agendarPeticion(
//		String horaInicio,
//		String horaTermino,
//		String numeroPeticion,
//		ArrayList usuariosConHabilidad)
//		throws Evento {
//		try {
//			AsignaSessionLocal asignacion = getAsignacion();
//			return asignacion.agendarPeticion(
//				UtilesWeb.convertirFecha(horaInicio, UtilesWeb.formatStandard),
//				UtilesWeb.convertirFecha(horaTermino, UtilesWeb.formatStandard),
//				numeroPeticion,
//				usuariosConHabilidad);
//		} catch (Exception e) {
//			log.error("ERROR :  < AL AGENDAR PETICIÓN >", e);
//			throw new Evento("errorGenerico");
//		}
//	}
//
//	private AsignaSessionLocal getAsignacion()
//		throws NamingException, RemoteException, CreateException {
//		AsignaSessionLocalHome home =
//			(AsignaSessionLocalHome) HomeFactory.getHome(AsignaSessionLocalHome.JNDI_NAME);
//		AsignaSessionLocal local = home.create();
//		return local;
//	}
}
