package com.telefonica_chile.soltec.grabacion;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocal;
import com.telefonica_chile.bandeja.servicios.publicador.PublicadorBandejaLocalHome;

public abstract class DefaultStGrabador implements GrabadorSt {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
//	protected HttpServletRequest request = null;
//	protected String idProceso = null;
//	protected String instanciaProceso = null;
//	protected String codigoActividad = null;
//	protected String instanciaActividad = null;
//	
//	//By Pa-T!, el ídolo pop por antonomasia
//	private boolean terminarActividad = true;
//	
//	
//	protected abstract IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx;
//
//	public final void grabar(HttpServletRequest request) {
//		//log.debug("Usuario de cierre : " + request.getAttribute("USUARIO_CIERRE"));
//		this.request = request;
//		this.setCamposActividadFromRequest(request);
//		//Lo primero es ejecutar la lógica...
//		this.ejecutarLogica(request);
//		if (this.isTerminarActividad()) {
//			String msgErr = "";
//			try {
//				IActividadEJB iActiv = this.getActividad();
//				msgErr += iActiv.getCodigoActividad() + "," +
//					iActiv.getNumeroPeticion() + "," +
//					iActiv.getActImplCorrelID() ;
//		
//				Long id_usuario = (Long) request.getAttribute("USUARIO_CIERRE");
//				iActiv.setIdUserCierre( id_usuario );
//				iActiv.terminar();
//			} catch (TnProcesoExcepcion e) {
//				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage());
//			} catch (ATiempoAppEx e) {
//				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage());
//			}
//		}
//		else
//		{
//			log.debug("\n<=============ACTIVIDAD NO TERMINADA=============>");
//		}
//	
//	}
//	public final void grabarSinTerminar(HttpServletRequest request) {
//			this.request = request;
//			this.setCamposActividadFromRequest(request);
//			//Lo primero es ejecutar la lógica...
//			this.ejecutarLogica(request);
//	}
//	
//	public final void derivar(HttpServletRequest request) {
//		this.request = request;
//		this.setCamposActividadFromRequest(request);
//		String msgErr = "";
//		try {
//			IActividadEJB iActiv = this.getActividad();
//			msgErr += iActiv.getCodigoActividad() + "," +
//				iActiv.getNumeroPeticion() + "," +
//				iActiv.getActImplCorrelID() ;
//		
//			Long id_usuarioC = (Long) request.getAttribute("USUARIO_CIERRE");
//			Long id_usuario = new Long(request.getParameter("tecnicoDeriva"));
//			
//			String obs = "Se ha derivado a otro usuario.";
//			if (request.getParameter("mensajeDeriva")!=null){
//				obs=obs + request.getParameter("mensajeDeriva");
//			}
//			iActiv.setIdUserCierre( id_usuarioC );
//			iActiv.setObservacion(obs);
//			iActiv.cambiarUsuario(id_usuario);			
//		} catch (TnProcesoExcepcion e) {
//			log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage(),e);
//		} catch (ATiempoAppEx e) {
//			log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage(),e);
//		}		
//	}
//
//	protected void setCamposActividadFromRequest(HttpServletRequest request) {
//		this.idProceso = request.getParameter("template");
//		this.instanciaActividad = request.getParameter("instanciaActividad");
//		this.instanciaProceso = request.getParameter("instanciaProceso");
//		this.codigoActividad = 	request.getParameter("codigoActividad");
//	}
//
//	protected abstract void ejecutarLogica(HttpServletRequest request);
//
//
//	/* (non-Javadoc)
//	 * @see com.telefonica_chile.atiempo.Grabador#getRequest()
//	 */
//	public HttpServletRequest getRequest() {
//		return this.request;
//	}
//
//	/**
//	 * @return
//	 */
//	public boolean isTerminarActividad() {
//		return terminarActividad;
//	}
//
//	/**
//	 * @param b
//	 */
//	public void setTerminarActividad(boolean b) {
//		terminarActividad = b;
//	}
//
//	private PublicadorBandejaLocal getPublicadorBandeja() throws NamingException, CreateException, RemoteException {
//		PublicadorBandejaLocalHome publicadorHome = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
//		return publicadorHome.create();
//	}

	protected Map datosModificados = new HashMap();

	protected HttpServletRequest request = null;
	protected String idProceso = null;
	protected String instanciaProceso = null;
	protected String codigoActividad = null;
	protected String instanciaActividad = null;
	
	private boolean terminarActividad = true;
	
	
	protected abstract IActividadEJB getActividad() throws TnProcesoExcepcion, ATiempoAppEx;

	public final void grabar(HttpServletRequest request) {
		//log.debug("Usuario de cierre : " + request.getAttribute("USUARIO_CIERRE"));
		this.request = request;
		this.setCamposActividadFromRequest(request);
		//Lo primero es ejecutar la lógica...
		this.ejecutarLogica(request);
		if (this.isTerminarActividad()) {
			String msgErr = "";
			try {
				IActividadEJB iActiv = this.getActividad();
				ActividadEJBDTO actDto = iActiv.getActividadEJBDTO(new Long(this.instanciaProceso),this.codigoActividad,this.instanciaActividad,null);

				String observaciones = request.getParameter("observaciones");
				if(request.getParameter("usuariobound")!=null)
					observaciones+=":Realizado por "+request.getParameter("usuariobound");
				String causa = request.getParameter("causa");
				Long idCausa = null;
				try {
					idCausa = new Long(causa);
				} catch (NumberFormatException e) {
				}

				actDto.setObservacion( observaciones );
				actDto.setIdCausaCierre( idCausa );
								
				for (Iterator iter = datosModificados.keySet().iterator(); iter.hasNext();) {
					String o = (String) iter.next();
					actDto.setDato(o, (String) datosModificados.get(o));
				}
				msgErr += actDto.getCodigoActividad() + "," +
					actDto.getNumeroPeticion() + "," +
					actDto.getActImplCorrelID() ;
		
				Long id_usuario = (Long) request.getAttribute("USUARIO_CIERRE");
				actDto.setIdUsuarioCierre( id_usuario );
				iActiv.terminar(actDto);
			} catch (TnProcesoExcepcion e) {
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage());
			} catch (ATiempoAppEx e) {
				log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage());
			}
		}
		else
		{
			log.debug("\n<=============ACTIVIDAD NO TERMINADA=============>");
		}
	
	}
	public final void grabarSinTerminar(HttpServletRequest request) {
			this.request = request;
			this.setCamposActividadFromRequest(request);
			//Lo primero es ejecutar la lógica...
			this.ejecutarLogica(request);
	}
	
	public final void derivar(HttpServletRequest request) {
		this.request = request;
		this.setCamposActividadFromRequest(request);
		String msgErr = "";
		try {
			IActividadEJB iActiv = this.getActividad();
			ActividadEJBDTO actDto = iActiv.getActividadEJBDTO(new Long(this.instanciaProceso),this.codigoActividad,this.instanciaActividad,null);
				
			msgErr += actDto.getCodigoActividad() + "," +
				actDto.getNumeroPeticion() + "," +
				actDto.getActImplCorrelID() ;
		
			Long id_usuarioC = (Long) request.getAttribute("USUARIO_CIERRE");
			Long id_usuario = new Long(request.getParameter("tecnicoDeriva"));
			
			String obs = "Se ha derivado a otro usuario.";
			if (request.getParameter("mensajeDeriva")!=null){
				obs=obs + request.getParameter("mensajeDeriva");
			}
			actDto.setIdUsuarioCierre( id_usuarioC );
			actDto.setObservacion(obs);
			iActiv.cambiarUsuario(actDto,id_usuario);			
		} catch (TnProcesoExcepcion e) {
			log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage(),e);
		} catch (ATiempoAppEx e) {
			log.error("No se pudo Terminar Actividad. (" + msgErr + "): " + e.getMessage(),e);
		}		
	}

	protected void setCamposActividadFromRequest(HttpServletRequest request) {
		this.idProceso = request.getParameter("template");
		this.instanciaActividad = request.getParameter("instanciaActividad");
		this.instanciaProceso = request.getParameter("instanciaProceso");
		this.codigoActividad = 	request.getParameter("codigoActividad");
	}

	protected abstract void ejecutarLogica(HttpServletRequest request);


	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.Grabador#getRequest()
	 */
	public HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * @return
	 */
	public boolean isTerminarActividad() {
		return terminarActividad;
	}

	/**
	 * @param b
	 */
	public void setTerminarActividad(boolean b) {
		terminarActividad = b;
	}

	private PublicadorBandejaLocal getPublicadorBandeja() throws NamingException, CreateException, RemoteException {
		PublicadorBandejaLocalHome publicadorHome = (PublicadorBandejaLocalHome) HomeFactory.getHome(PublicadorBandejaLocalHome.JNDI_NAME);
		return publicadorHome.create();
	}

}
