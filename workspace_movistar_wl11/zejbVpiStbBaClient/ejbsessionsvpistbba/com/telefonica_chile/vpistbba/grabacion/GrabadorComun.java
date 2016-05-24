/*
 * Creado el Feb 9, 2005
 *
 */
package com.telefonica_chile.vpistbba.grabacion;

import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.cierre_observaciones.session.CierreActividadDTO;
import com.telefonica_chile.comun.cierre_observaciones.session.CierreActividadSessionLocal;
import com.telefonica_chile.comun.cierre_observaciones.session.CierreActividadSessionLocalHome;

/**
 * @author VictorMan
 *
 */
public abstract class GrabadorComun extends DefaultGrabador {
	
//	protected Map datosOriginales;
//
//	private Map datosModificados = new HashMap();
//	protected Long idActividad;
//	protected Long idPeticion;
//	private boolean esClave = false;
//
//	protected abstract void grabarDatosPropios(HttpServletRequest request);	
//
//	/* (non-Javadoc)
//	 * @see com.telefonica_chile.atiempo.vpistbba.flujo.grabadores.DefaultGrabador#getActividad(javax.servlet.http.HttpServletRequest)
//	 */
//	protected IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx {
//		//IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, this.instanciaActividad);
//		//Esto se hace porque hay problemas al pasar ids de instancia actividad con el símbolo '+' en la URL
//
//		ActividadFactoryEJB actF = new ActividadFactoryEJB();
//		Long idPeticion = new Long( this.instanciaProceso);
//		IActividadEJB actividad = actF.getActividad(idPeticion, codigoActividad,this.instanciaActividad,null);
//		
//		String observaciones = request.getParameter("observaciones");
//		if(request.getParameter("usuariobound")!=null)
//			observaciones+=":Realizado por "+request.getParameter("usuariobound");
//		log.debug("observaciones:" +observaciones);
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
////		GrabadorComun.setMapDatosFromRequest(actividad, this.getRequest());
//		for (Iterator iter = datosModificados.keySet().iterator(); iter.hasNext();) {
//			String o = (String) iter.next();
//			actividad.setDato(o, (String) datosModificados.get(o));
//		}
//		return actividad;
//		
//	}
//
//	protected void ejecutarLogica(HttpServletRequest request) {
//		// parte implementada por subclases
//		this.grabarDatosPropios(request);
//
//		// parte común
//		Long idPeticion = new Long(this.instanciaProceso);
//		Long cierre = new Long(request.getParameter("cierre"));
//		String codActividad  = request.getParameter("codigoActividad");
//
//		CierreActividadDTO cierreDto = null;
//		if (cierre.intValue() != 0) {
//			try {
//				CierreActividadSessionLocalHome cierreLocalHome =
//					(CierreActividadSessionLocalHome) HomeFactory.getHome(CierreActividadSessionLocalHome.JNDI_NAME);
//				CierreActividadSessionLocal cierreSessionLocal = cierreLocalHome.create();
//				cierreDto = cierreSessionLocal.recuperaCierreActividadId(cierre);
//			} catch (NamingException e) {
//				log.error("NamingException. CierreActividadSessionHome [" + idProceso + "," + codActividad + "," + cierre + "]");
//			} catch (CreateException e) {
//				log.error("CreateException. CierreActividadSessionHome [" + idProceso + "," + codActividad + "," + cierre + "]");
//			}
//		}
//
//		String tipoTermino = request.getParameter("terminar_act_wf");
//		log.debug("Tipo Termino:" + tipoTermino);
////		if ( tipoTermino!=null && !"0".equals(tipoTermino) ) {
//		if ( !"0".equals(tipoTermino) ) {
//			log.debug("Terminando Actividad [" + idPeticion + "," + codActividad + "," + cierreDto.getCodigo() + "," + cierreDto.getValor() + "]");
//			setDatoWF( cierreDto.getCodigo(), cierreDto.getValor() );
//		}	
//	}
//	
//	protected void ejecutarLogicaSinTerminar(HttpServletRequest request) {
//		this.grabarDatosPropios(request);
//	}	
//
//	protected void setDatoWF(String clave, String valor) {
//		datosModificados.put(clave, valor);
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
//	
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

//protected Long idActividad;
//protected Long idPeticion;
  private boolean esClave = false;

  protected abstract void grabarDatosPropios(HttpServletRequest request);	

  /* (non-Javadoc)
   * @see com.telefonica_chile.atiempo.vpistbba.flujo.grabadores.DefaultGrabador#getActividad(javax.servlet.http.HttpServletRequest)
   */
  protected IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx {
	  //IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, this.instanciaActividad);
	  //Esto se hace porque hay problemas al pasar ids de instancia actividad con el símbolo '+' en la URL

	  ActividadFactoryEJB actF = new ActividadFactoryEJB();
//	  Long idPeticion = new Long( this.instanciaProceso);
	  IActividadEJB actividad = actF.getActividad(codigoActividad);
		
		
//	  String observaciones = request.getParameter("observaciones");
//	  if(request.getParameter("usuariobound")!=null)
//		  observaciones+=":Realizado por "+request.getParameter("usuariobound");
//	  log.debug("observaciones:" +observaciones);
//	  String causa = request.getParameter("causa");
//	  Long idCausa = null;
//	  try {
//		  idCausa = new Long(causa);
//	  } catch (NumberFormatException e) {
//	  }
//
//	  actividad.setObservacion( observaciones );
//	  actividad.setIdCausaCierre( idCausa );
//		
////		IActividad actividad = ActividadFactory.getInstance().getActividad(this.idProceso, this.instanciaProceso, this.codigoActividad, null);
////		GrabadorComun.setMapDatosFromRequest(actividad, this.getRequest());
//	  for (Iterator iter = datosModificados.keySet().iterator(); iter.hasNext();) {
//		  String o = (String) iter.next();
//		  actividad.setDato(o, (String) datosModificados.get(o));
//	  }
	  return actividad;
		
  }

  protected void ejecutarLogica(HttpServletRequest request) {
	  // parte implementada por subclases
	  this.grabarDatosPropios(request);

	  // parte común
	  Long idPeticion = new Long(this.instanciaProceso);
	  Long cierre = new Long(request.getParameter("cierre"));
	  String codActividad  = request.getParameter("codigoActividad");

	  CierreActividadDTO cierreDto = null;

	  if (cierre.intValue() != 0) {
		  try {
			  CierreActividadSessionLocalHome cierreLocalHome =
				  (CierreActividadSessionLocalHome) HomeFactory.getHome(CierreActividadSessionLocalHome.JNDI_NAME);
			  CierreActividadSessionLocal cierreSessionLocal = cierreLocalHome.create();
			  cierreDto = cierreSessionLocal.recuperaCierreActividadId(cierre);
			  
			  //@idrincon req 3709			  
			  if( cierreDto.getNombre().equals("Visita Tecnica")){
				try {
					RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
					recursosBADelegate.actualizarRecursosBA(idPeticion);
				} catch (ATiempoAppEx e) {
					log.debug("Error no se pudo actualizar RecursosBA");
				}
				
			  }
			  //
			  
		  } catch (NamingException e) {
			  log.error("NamingException. CierreActividadSessionHome [" + idProceso + "," + codActividad + "," + cierre + "]");
		  } catch (CreateException e) {
			  log.error("CreateException. CierreActividadSessionHome [" + idProceso + "," + codActividad + "," + cierre + "]");
		  }
	  }
	  
	  

	  String tipoTermino = request.getParameter("terminar_act_wf");
	  log.debug("Tipo Termino:" + tipoTermino);
//	  if ( tipoTermino!=null && !"0".equals(tipoTermino) ) {
	  if ( !"0".equals(tipoTermino) ) {
		  log.debug("Terminando Actividad [" + idPeticion + "," + codActividad + "," + cierreDto.getCodigo() + "," + cierreDto.getValor() + "]");
		  setDatoWF( cierreDto.getCodigo(), cierreDto.getValor() );
	  }	
  }
	
  protected void ejecutarLogicaSinTerminar(HttpServletRequest request) {
	  this.grabarDatosPropios(request);
  }	

  protected void setDatoWF(String clave, String valor) {
	  datosModificados.put(clave, valor);
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
