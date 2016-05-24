package com.telefonica_chile.atiempo.javaWf;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import com.telefonica_chile.atiempo.actividades.IProceso;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
/**
 * Local interface for Enterprise Bean: WFSession
 */
public interface WFSessionLocal extends javax.ejb.EJBLocalObject {
////	public void iniciarActividad(IActividad actividad)
////		throws TnProcesoExcepcion;
////	public void terminarActividad(IActividad actividad)
////		throws TnProcesoExcepcion;
////	/**
////	 * 
////	 * METODOS DEL BEAN (I.E. No del Home)
////	 * 
////	 */
////
//	public void iniciarProceso(IProceso proceso) throws TnProcesoExcepcion;
//	public void enviarMensaje(String text);
//	
//	public void crearNuevaIntanciaActividadEJB(IActividadEJB actividad)
//		throws TnProcesoExcepcion;
//	public void enviarRespuesta(IActividadEJB actividad)
//		throws TnProcesoExcepcion;
//	public void eliminarInstanciaActividad(IActividadEJB actividad)
//		throws TnProcesoExcepcion;		
//	/*private*/
////	public void eliminarInstanciaActividadByPeticion(Long idPeticion)
////		throws TnProcesoExcepcion;
//	/**
//	 * 
//	 * Este método es el que se conecta al Workflow e inicia una instancia dado un template, actualmente
//	 * está utilizando la api original del WF, no sé si es posible implementar este mismo comportamiento
//	 * a través de colas (eso lo sabrá Denis, el papito del Work-flow)
//	 * 
//	 * @param proceso
//	 * @param usuarioWf
//	 * @param passwordWf
//	 * @throws TnMQWFException
//	 */
//	//	private void iniciarProcesoEnWorkflow_OLD(
//	//		IProceso proceso,
//	//		String usuarioWf,
//	//		String passwordWf)
//	//	//TODO Probablemente haya que reemplazar la implementación de este método, utilizando colas
//	//	throws TnMQWFException {
//	//		log.info(
//	//			"Iniciando Proceso (idProceso, idInstancia) = ("
//	//				+ proceso.getIdProceso()
//	//				+ ","
//	//				+ proceso.getIdInstancia()
//	//				+ ")");
//	//
//	//		this.mqWfBean = new MQWFBean();
//	//
//	//		mqWfConectar(usuarioWf, passwordWf);
//	//
//	//		this.mqWfBean.setProcessName(proceso.getIdProceso());
//	//		this.mqWfBean.setID(proceso.getIdInstancia());
//	//		/*
//	//		 * Voy actualizando los campos con los que vienen de la pantalla
//	//		 */
//	//		Map datos = proceso.getDatos();
//	//		Iterator nombreDatos = datos.keySet().iterator();
//	//
//	//		while (nombreDatos.hasNext()) {
//	//			String nombre = (String) nombreDatos.next();
//	//			String valor = (String) datos.get(nombre);
//	//			this.mqWfBean.setCampos(nombre, valor);
//	//		}
//	//		this.mqWfBean.crearInstancia();
//	//		mqWfDesconectar();
//	//		log.info(
//	//			"Terminando Proceso (idProceso, idInstancia) = ("
//	//				+ proceso.getIdProceso()
//	//				+ ","
//	//				+ proceso.getIdInstancia()
//	//				+ ")");
//	//	}
//
//	//	private void mqWfDesconectar() throws TnMQWFException {
//	//		this.mqWfBean.desconectar();
//	//	}
//	//
//	//	private void mqWfConectar(String usuarioWf, String passwordWf)
//	//		throws TnMQWFException {
//	//		this.mqWfBean.setUserConnect(usuarioWf);
//	//		this.mqWfBean.setPassConnect(passwordWf);
//	//		this.mqWfBean.conectar();
//	//	}
//	//
//	//	public String[] getListaTemplates() throws TnProcesoExcepcion {
//	//		try {
//	//			this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
//	//			String[] templates =
//	//				(String[]) this
//	//					.mqWfBean
//	//					.getProcessTemplateNames()
//	//					.toArray(new String[] {
//	//			});
//	//			this.mqWfDesconectar();
//	//			return templates;
//	//		} catch (TnMQWFException e) {
//	//			throw new TnProcesoExcepcion("TnMQWFException en WFSession.getListaTemplates()");
//	//		}
//	//	}
//	//
//	//	public Map getValorCampos(IProceso proceso) throws TnProcesoExcepcion {
//	//		try {
//	//			this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
//	//			this.mqWfBean.actualizaContainerTemplate(proceso.getIdProceso());
//	//			Map campos = this.mqWfBean.getContainer();
//	//			this.mqWfDesconectar();
//	//			return campos;
//	//		} catch (TnMQWFException e) {
//	//			throw new TnProcesoExcepcion(
//	//				"TnMQWFException en WFSession.getValorCampos()"
//	//					+ e.getMessage());
//	//		}
//	//	}
//	//
//	//	public String[] getNombreCampos(IProceso proceso)
//	//		throws TnProcesoExcepcion {
//	//		try {
//	//			this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
//	//			this.mqWfBean.actualizaContainerTemplate(proceso.getIdProceso());
//	//			Map campos = this.mqWfBean.getContainer();
//	//			Set nombreCampos = campos.keySet();
//	//			String[] aNombreCampos =
//	//				(String[]) nombreCampos.toArray(new String[] {
//	//			});
//	//			this.mqWfDesconectar();
//	//			return aNombreCampos;
//	//		} catch (TnMQWFException e) {
//	//			throw new TnProcesoExcepcion(
//	//				"TnMQWFException en WFSession.getNombreCampos()"
//	//					+ e.getMessage());
//	//		}
//	//	}
//
//	//Busca el xml con las variables en la tabla WFInstancia
//	public void setMapaDatosActividad(IActividadEJB actividad)
//		throws TnProcesoExcepcion;
//	public void enviarMensaje(String queue, String text);

	public void iniciarProceso(IProceso proceso) throws TnProcesoExcepcion;
	
	public void crearNuevaIntanciaActividadEJB(ActividadEJBDTO actividad)
		throws TnProcesoExcepcion;
	public void enviarRespuesta(ActividadEJBDTO actividad)
		throws TnProcesoExcepcion;
	public void eliminarInstanciaActividad(ActividadEJBDTO actividad)
		throws TnProcesoExcepcion;		

	//Busca el xml con las variables en la tabla WFInstancia
	public void setMapaDatosActividad(ActividadEJBDTO actividad)
		throws TnProcesoExcepcion;
	public void enviarMensaje(String queue, String text, Long numeroPeticion, String source);
	
	public static final String FLUJO_SOLTEC = STConfig.getVariable("ESTRUCTURA_DATOS");
	public static final String FLUJO_VPI = VpistbbaConfig.getVariable("ESTRUCTURA_DATOS");
	
}
