/*
 * Creado el Feb 9, 2005
 *
 */
package com.telefonica_chile.soltec.grabacion;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

public class GrabadorStComun extends DefaultStGrabador {
	
//	protected Map datosOriginales;
//
//	/* (non-Javadoc)
//	 * @see com.telefonica_chile.atiempo.vpistbba.flujo.grabadores.DefaultGrabador#getActividad(javax.servlet.http.HttpServletRequest)
//	 */
//	protected IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx {
//		//IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, this.instanciaActividad);
//		//Esto se hace porque hay problemas al pasar ids de instancia actividad con el símbolo '+' en la URL
//
//		ActividadFactorySTEJB actF = new ActividadFactorySTEJB();
//		Long idPeticion = new Long( this.instanciaProceso);
//		IActividadEJB actividad = actF.getActividad(idPeticion, codigoActividad,this.instanciaActividad, null);
//
//		String observaciones = request.getParameter("observaciones");
//		if(request.getParameter("usuariobound")!=null)
//			observaciones+=":Realizado por "+request.getParameter("usuariobound");
//		String causa = request.getParameter("causa");
//		Long idCausa = null;
//		try {
//			idCausa = new Long(causa);
//		} catch (NumberFormatException e) {
//		}
//
//		actividad.setObservacion( observaciones );
//		actividad.setIdCausaCierre( idCausa );
//		
////		IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, null);
////		GrabadorStComun.setMapDatosFromRequest(actividad, this.getRequest());
//		return actividad;
//		
//	}
//
//	protected void ejecutarLogica(HttpServletRequest request) {
//		//TODO Si es que hay alguna lógica común, se puede implementar aquí...		
//	}
//	
//	protected void ejecutarLogicaSinTerminar(HttpServletRequest request) {
//		//TODO Si es que hay alguna lógica común, se puede implementar aquí...
//		
//	}	
//	/**
//		 * Este método es utilizado en el GrabadorComun que implementa el comportamiento por defecto
//		 * de los grabadores. La idea es que todos los parámetros que vengan en el request y comiencen
//		 * por 'WF_', son parámetros que corresponden a un valor a poner en la estructura de datos (phi)
//		 * de la actividad. Hay que estudiar si esta forma de hacer las cosas es adecuada, está aquí más
//		 * que nada para hacer funcionar las pruebas...
//		 * 
//		 * @param request
//		 * @return
//		 */
////		public static void setMapDatosFromRequest(IActividadEJB actividad, HttpServletRequest request) {
////			Enumeration parameterNames =
////				request.getParameterNames();
////		
////			while (parameterNames.hasMoreElements()) {
////				String nombreParametro =
////					(String) parameterNames.nextElement();
////		
////				// Si el parametro comienza con "WF_", entonces le saco el "WF_" y
////				// lo llevo al contenedor
////				if (nombreParametro.startsWith("WF_")) {
////					String parametro = nombreParametro.substring(3);
////					//TODO evaluar bien que pasa aquí... ¿cómo le paso un null? ¿se necesita?
////					if (request.getParameter(nombreParametro) != null) {
////						actividad.setDato(parametro,request.getParameter(nombreParametro));
////					}
////				}
////			}
////		}
////	
////		public static Map getMapDatosFromRequest(HttpServletRequest request) {
////			Enumeration parameterNames = request.getParameterNames();
////			Map outMap = new HashMap();
////			while (parameterNames.hasMoreElements()) {
////				String nombreParametro = (String) parameterNames.nextElement();
////				// Si el parametro comienza con "WF_", entonces le saco el "WF_" y
////				// lo llevo al contenedor
////				if (nombreParametro.startsWith("WF_")) {
////					String parametro = nombreParametro.substring(3);
////					//TODO evaluar bien que pasa aquí... ¿cómo le paso un null? ¿se necesita?
////					if(request.getParameter(nombreParametro) != null) {
////						outMap.put(parametro,request.getParameter(nombreParametro));
////					}
////				}
////			}
////			return outMap;
////		}

  protected Map datosOriginales;

  /* (non-Javadoc)
   * @see com.telefonica_chile.atiempo.vpistbba.flujo.grabadores.DefaultGrabador#getActividad(javax.servlet.http.HttpServletRequest)
   */
  protected IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx {
	  //IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, this.instanciaActividad);
	  //Esto se hace porque hay problemas al pasar ids de instancia actividad con el símbolo '+' en la URL

	  ActividadFactorySTEJB actF = new ActividadFactorySTEJB();
	  Long idPeticion = new Long( this.instanciaProceso);
	  IActividadEJB actividad = actF.getActividad(codigoActividad);

//	  String observaciones = request.getParameter("observaciones");
//	  if(request.getParameter("usuariobound")!=null)
//		  observaciones+=":Realizado por "+request.getParameter("usuariobound");
//	  String causa = request.getParameter("causa");
//	  Long idCausa = null;
//	  try {
//		  idCausa = new Long(causa);
//	  } catch (NumberFormatException e) {
//	  }
//
//	  actividad.setObservacion( observaciones );
//	  actividad.setIdCausaCierre( idCausa );
		
//	  IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, null);
//	  GrabadorStComun.setMapDatosFromRequest(actividad, this.getRequest());
	  return actividad;
		
  }

  protected void ejecutarLogica(HttpServletRequest request) {
	  //TODO Si es que hay alguna lógica común, se puede implementar aquí...		
  }
	
  protected void ejecutarLogicaSinTerminar(HttpServletRequest request) {
	  //TODO Si es que hay alguna lógica común, se puede implementar aquí...
		
  }	
  /**
	   * Este método es utilizado en el GrabadorComun que implementa el comportamiento por defecto
	   * de los grabadores. La idea es que todos los parámetros que vengan en el request y comiencen
	   * por 'WF_', son parámetros que corresponden a un valor a poner en la estructura de datos (phi)
	   * de la actividad. Hay que estudiar si esta forma de hacer las cosas es adecuada, está aquí más
	   * que nada para hacer funcionar las pruebas...
	   * 
	   * @param request
	   * @return
	   */
//	  public static void setMapDatosFromRequest(IActividadEJB actividad, HttpServletRequest request) {
//		  Enumeration parameterNames =
//			  request.getParameterNames();
//		
//		  while (parameterNames.hasMoreElements()) {
//			  String nombreParametro =
//				  (String) parameterNames.nextElement();
//		
//			  // Si el parametro comienza con "WF_", entonces le saco el "WF_" y
//			  // lo llevo al contenedor
//			  if (nombreParametro.startsWith("WF_")) {
//				  String parametro = nombreParametro.substring(3);
//				  //TODO evaluar bien que pasa aquí... ¿cómo le paso un null? ¿se necesita?
//				  if (request.getParameter(nombreParametro) != null) {
//					  actividad.setDato(parametro,request.getParameter(nombreParametro));
//				  }
//			  }
//		  }
//	  }
//	
//	  public static Map getMapDatosFromRequest(HttpServletRequest request) {
//		  Enumeration parameterNames = request.getParameterNames();
//		  Map outMap = new HashMap();
//		  while (parameterNames.hasMoreElements()) {
//			  String nombreParametro = (String) parameterNames.nextElement();
//			  // Si el parametro comienza con "WF_", entonces le saco el "WF_" y
//			  // lo llevo al contenedor
//			  if (nombreParametro.startsWith("WF_")) {
//				  String parametro = nombreParametro.substring(3);
//				  //TODO evaluar bien que pasa aquí... ¿cómo le paso un null? ¿se necesita?
//				  if(request.getParameter(nombreParametro) != null) {
//					  outMap.put(parametro,request.getParameter(nombreParametro));
//				  }
//			  }
//		  }
//		  return outMap;
//	  }


}
