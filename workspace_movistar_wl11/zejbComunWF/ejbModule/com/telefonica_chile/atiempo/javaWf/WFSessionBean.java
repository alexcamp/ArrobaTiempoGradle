package com.telefonica_chile.atiempo.javaWf;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import javax.ejb.Handle;
import javax.ejb.Local;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.ParamsExtracterEJB;
import co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePILocal;
import co.com.telefonica.atiempo.ejb.eb.IncidenciasDesdePILocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadKey;
import co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadLocal;
import co.com.telefonica.atiempo.ejb.eb.Wf_instancia_actividadLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;

import com.tecnonautica.mqwf.MQWFBean;
import com.tecnonautica.mqwf.TnMQWFException;
import com.telefonica_chile.atiempo.actividades.IProceso;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: WFSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same
// information - dregueira - May 20, 2009
@Stateless
@Local
public class WFSessionBean implements javax.ejb.SessionBean, WFSession {

	//	private static final String USUARIO_WF = "ADMIN";
	//	private static final String PASSWORD_WF = "password";
	//
	//	/**
	//	 * OJO! - Este QUEUE CONNECTION FACTORY debe quedar con el mismo nombre en
	//	 * el EJB Deployment Descriptor. Ver las referencias del WFSession, debe
	//	 * tener una Resource Reference a "jms/VPISTBBA_TO_WF_QCF"
	//	 */
	//	private static final String QUEUECONNECTIONFACTORY = "jms/WF_QCF";
	//	private static final String RESPONSEQUEUE = "jms/WF/WF_INPUT_Q";
	//	private static final String ERROR_RESPONSEQUEUE =
	// "jms/WF/WF_TO_APP_ERR_Q";
	//
	//	private javax.ejb.SessionContext mySessionCtx;
	//
	//	private QueueConnectionFactory qcf = null;
	//	private Queue sendQueue = null;
	//	private Queue sendQueueVariable = null;
	//	private Queue errorResponseQueue = null;
	//	private InitialContext initialContext;
	//
	//	/**
	//	 * Obtencion de logger.
	//	 * @author amartoq
	//	 */
	//	protected transient Logger log = LoggerFactory.getLogger(getClass());
	//
	//	//Lo inicializo en ejbCreate...
	//	private MQWFBean mqWfBean = null;
	//
	//	/**
	//	 * getSessionContext
	//	 */
	//	public javax.ejb.SessionContext getSessionContext() {
	//		return mySessionCtx;
	//	}
	//	/**
	//	 * setSessionContext
	//	 */
	//	public void setSessionContext(javax.ejb.SessionContext ctx) {
	//		mySessionCtx = ctx;
	//	}
	//	/**
	//	 * ejbCreate
	//	 */
	//	public void ejbCreate() throws javax.ejb.CreateException {
	//
	//		/*
	//		 * Para inicializar cola de respuesta al workflow
	//		 */
	//		try {
	//			this.initialContext = new InitialContext();
	//			qcf = (QueueConnectionFactory) this.getJMS(QUEUECONNECTIONFACTORY);
	//			sendQueue = (Queue) this.getJMS(RESPONSEQUEUE);
	//			errorResponseQueue = (Queue) this.getJMS(ERROR_RESPONSEQUEUE);
	//			// NOTA: descomentar esta linea para pruebas de VpiSSDD
	//			//this.mqWfBean = new MQWFBean();
	//		} catch (javax.naming.NamingException e) {
	//			log.error("NamingException",e);
	//		}
	//
	//	}
	//	/**
	//	 * ejbActivate
	//	 */
	//	public void ejbActivate() {
	//	}
	//	/**
	//	 * ejbPassivate
	//	 */
	//	public void ejbPassivate() {
	//	}
	//	/**
	//	 * ejbRemove
	//	 */
	//	public void ejbRemove() {
	//	}
	//
	//	/**
	//	 *
	//	 * METODOS DEL BEAN (I.E. No del Home)
	//	 *
	//	 */
	//
	//	public void iniciarProceso(IProceso proceso) throws TnProcesoExcepcion {
	//		try {
	//			this.iniciarProceso(proceso, USUARIO_WF, PASSWORD_WF);
	//		} catch (TnMQWFException e) {
	//			throw new TnProcesoExcepcion(
	//				"TnMQWFException en WFSession.iniciarProceso() : "
	//					+ e.getMessage());
	//		}
	//	}
	//
	//	private void iniciarProceso(
	//		IProceso proceso,
	//		String usuarioWf,
	//		String passwordWf)
	//		throws TnMQWFException, TnProcesoExcepcion {
	//		proceso.preInicio();
	//		iniciarProcesoEnWorkflow(proceso, usuarioWf, passwordWf);
	//		proceso.onInicio();
	//	}
	//
	//	private void iniciarProcesoEnWorkflow(
	//		IProceso proceso,
	//		String usuarioWf,
	//		String passwordWf)
	//	//TODO Probablemente haya que reemplazar la implementaci�n de este
	// m�todo, utilizando colas
	//	throws TnMQWFException {
	//		//Metodo antiguo
	//		/*log.debug("Iniciando proceso idProceso=" + proceso.getIdProceso() + ",
	// idInstancia=" + proceso.getIdInstancia());
	//		
	//		this.mqWfBean = new MQWFBean();
	//		
	//		mqWfConectar(usuarioWf, passwordWf);
	//		
	//		this.mqWfBean.setProcessName(proceso.getIdProceso());
	//		this.mqWfBean.setID(proceso.getIdInstancia());
	//		log.debug("proceso.getIdProceso() : " + proceso.getIdProceso());
	//		log.debug("proceso.getIdInstancia() : " + proceso.getIdInstancia());
	//		*/
	//		/*
	//		 * Voy actualizando los campos con los que vienen de la pantalla
	//		 */
	//		/*Map datos = proceso.getDatos();
	//		Iterator nombreDatos = datos.keySet().iterator();
	//		
	//		while (nombreDatos.hasNext()) {
	//			String nombre = (String) nombreDatos.next();
	//			String valor = (String) datos.get(nombre);
	//			log.debug("Poniendo Campo '" + nombre + "' = '" + valor + "'");
	//			this.mqWfBean.setCampos(nombre, valor);
	//		}
	//		this.mqWfBean.crearInstancia();
	//		mqWfDesconectar();
	//		log.debug("Fin iniciarProceso(" + proceso.getIdProceso() + "," +
	// proceso.getIdInstancia() + ")");
	//		*/
	//		//Metodo Nuevo
	//		// VERSION NUEVA QUE USA JMS PARA LA CREACION DE INSTANCIAS -- Denis, 26
	// de Abril de 2004 (!)
	//
	//		//mqWfConectar(usuarioWf, passwordWf);
	//
	//		String processTemplate = proceso.getIdProceso();
	//		String processInstanceName = proceso.getIdInstancia();
	//		String nombreContenedor = proceso.getNombreEstructuraDatos();
	//
	//		/*
	//		 * Voy actualizando los campos con los que vienen de la pantalla
	//		 */
	//		Map datos = proceso.getDatos();
	//		Iterator nombreDatos = datos.keySet().iterator();
	//
	//		String XMLCampos = "";
	//		while (nombreDatos.hasNext()) {
	//			String nombre = (String) nombreDatos.next();
	//			String valor = (String) datos.get(nombre);
	//			//log.debug("Poniendo Campo '" + nombre + "' = '" + valor + "'");
	//			// this.mqWfBean.setCampos( nombre, valor );
	//			XMLCampos += "<" + nombre + ">" + valor + "</" + nombre + ">\n";
	//		}
	//
	//		String mensajeXML =
	//			""
	//				+ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
	//				+ "<WfMessage>\n"
	//				+ "<WfMessageHeader>\n"
	//				+ "<ResponseRequired>Yes</ResponseRequired>\n"
	//				+ "<UserContext></UserContext>\n"
	//				+ "</WfMessageHeader>\n"
	//				+ "<ProcessTemplateCreateAndStartInstance>\n"
	//				+ "<ProcTemplName>"
	//				+ processTemplate
	//				+ "</ProcTemplName>\n"
	//				+ "<ProcInstName>"
	//				+ processInstanceName
	//				+ "</ProcInstName>\n"
	//				+ "<KeepName>true</KeepName>\n"
	//				+ "<ProcInstInputData>\n"
	//				+ "<"
	//				+ nombreContenedor
	//				+ ">\n"
	//				+ XMLCampos
	//				+ "</"
	//				+ nombreContenedor
	//				+ ">\n"
	//				+ "</ProcInstInputData>\n"
	//				+ "</ProcessTemplateCreateAndStartInstance>\n"
	//				+ "</WfMessage>\n";
	//
	//		//this.mqWfBean.crearInstancia();
	//		//mqWfDesconectar();
	//
	//		log.info(
	//			"Iniciando Proceso (Proceso, Instancia, Contenedor) = ("
	//				+ processTemplate
	//				+ ","
	//				+ processInstanceName
	//				+ ","
	//				+ nombreContenedor
	//				+ ")");
	//
	//		enviarMensaje(mensajeXML);
	//
	//	}
	//
	//	/**
	//	 *
	//	 * Este m�todo es el que se conecta al Workflow e inicia una instancia
	// dado un template, actualmente
	//	 * est� utilizando la api original del WF, no s� si es posible implementar
	// este mismo comportamiento
	//	 * a trav�s de colas (eso lo sabr� Denis, el papito del Work-flow)
	//	 *
	//	 * @param proceso
	//	 * @param usuarioWf
	//	 * @param passwordWf
	//	 * @throws TnMQWFException
	//	 */
	//// private void iniciarProcesoEnWorkflow_OLD(
	//// IProceso proceso,
	//// String usuarioWf,
	//// String passwordWf)
	//// //TODO Probablemente haya que reemplazar la implementaci�n de este
	// m�todo, utilizando colas
	//// throws TnMQWFException {
	//// log.info(
	//// "Iniciando Proceso (idProceso, idInstancia) = ("
	//// + proceso.getIdProceso()
	//// + ","
	//// + proceso.getIdInstancia()
	//// + ")");
	////
	//// this.mqWfBean = new MQWFBean();
	////
	//// mqWfConectar(usuarioWf, passwordWf);
	////
	//// this.mqWfBean.setProcessName(proceso.getIdProceso());
	//// this.mqWfBean.setID(proceso.getIdInstancia());
	//// /*
	//// * Voy actualizando los campos con los que vienen de la pantalla
	//// */
	//// Map datos = proceso.getDatos();
	//// Iterator nombreDatos = datos.keySet().iterator();
	////
	//// while (nombreDatos.hasNext()) {
	//// String nombre = (String) nombreDatos.next();
	//// String valor = (String) datos.get(nombre);
	//// this.mqWfBean.setCampos(nombre, valor);
	//// }
	//// this.mqWfBean.crearInstancia();
	//// mqWfDesconectar();
	//// log.info(
	//// "Terminando Proceso (idProceso, idInstancia) = ("
	//// + proceso.getIdProceso()
	//// + ","
	//// + proceso.getIdInstancia()
	//// + ")");
	//// }
	//
	//// private void mqWfDesconectar() throws TnMQWFException {
	//// this.mqWfBean.desconectar();
	//// }
	////
	//// private void mqWfConectar(String usuarioWf, String passwordWf)
	//// throws TnMQWFException {
	//// this.mqWfBean.setUserConnect(usuarioWf);
	//// this.mqWfBean.setPassConnect(passwordWf);
	//// this.mqWfBean.conectar();
	//// }
	////
	//// public String[] getListaTemplates() throws TnProcesoExcepcion {
	//// try {
	//// this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
	//// String[] templates =
	//// (String[]) this
	//// .mqWfBean
	//// .getProcessTemplateNames()
	//// .toArray(new String[] {
	//// });
	//// this.mqWfDesconectar();
	//// return templates;
	//// } catch (TnMQWFException e) {
	//// throw new TnProcesoExcepcion("TnMQWFException en
	// WFSession.getListaTemplates()");
	//// }
	//// }
	////
	//// public Map getValorCampos(IProceso proceso) throws TnProcesoExcepcion
	// {
	//// try {
	//// this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
	//// this.mqWfBean.actualizaContainerTemplate(proceso.getIdProceso());
	//// Map campos = this.mqWfBean.getContainer();
	//// this.mqWfDesconectar();
	//// return campos;
	//// } catch (TnMQWFException e) {
	//// throw new TnProcesoExcepcion(
	//// "TnMQWFException en WFSession.getValorCampos()"
	//// + e.getMessage());
	//// }
	//// }
	////
	//// public String[] getNombreCampos(IProceso proceso)
	//// throws TnProcesoExcepcion {
	//// try {
	//// this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
	//// this.mqWfBean.actualizaContainerTemplate(proceso.getIdProceso());
	//// Map campos = this.mqWfBean.getContainer();
	//// Set nombreCampos = campos.keySet();
	//// String[] aNombreCampos =
	//// (String[]) nombreCampos.toArray(new String[] {
	//// });
	//// this.mqWfDesconectar();
	//// return aNombreCampos;
	//// } catch (TnMQWFException e) {
	//// throw new TnProcesoExcepcion(
	//// "TnMQWFException en WFSession.getNombreCampos()"
	//// + e.getMessage());
	//// }
	//// }
	//
	////Busca el xml con las variables en la tabla WFInstancia
	//	public void setMapaDatosActividad(IActividadEJB actividad)
	//		throws TnProcesoExcepcion {
	//
	//		try {
	//			this.retrivePrimaryKeyActividad(actividad);
	//			/*
	//			if (null != actividad.getDatos() && !actividad.getDatos().isEmpty() ){
	//				log.debug("### getMapaDatos encontro datos en la actividad");
	//				return actividad.getDatos();
	//			}
	//			*/
	//			//Busco el registro en la BD
	//			Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
	//			wfInstanciaActividadHome =
	//				getWfInstanciaHome(wfInstanciaActividadHome);
	//			Wf_instancia_actividadKey wfK= new Wf_instancia_actividadKey();
	//			wfK.id_instancia_actividad=actividad.getActImplCorrelID();
	//			Wf_instancia_actividadLocal wfInstanciaActividad =
	//				wfInstanciaActividadHome.findByPrimaryKey(wfK);
	//				
	//			String xmlParams = wfInstanciaActividad.getXmlparams();
	//			if (null != xmlParams) {
	//				new ParamsExtracterEJB(xmlParams, actividad).setMapDatos();
	//				log.debug("Seteo variables Actividad");
	//			}
	//		} catch (Exception e) {
	//			log.error("Error al Buscar las variables de la Actividad",e);
	//			throw new TnProcesoExcepcion(
	//				e.getClass().getName()
	//					+ " en WFSessionBean.getMapaDatos(IActividad) : "
	//					+ e.getMessage());
	//		}
	//
	//	}
	//
	//// public void iniciarActividad(IActividad actividad)
	//// throws TnProcesoExcepcion {
	////
	//// if (actividad.esActividadVacia())
	//// enviarRespuesta(actividad);
	//// else {
	//// // La actividad se guarda en WF_INSTANCIA_ACTIVIDAD solo
	//// // cuando se necesita (Ej: No se necesita para las automaticas).
	//// // TODO: Ver si funciona para SSDD.
	////// if (actividad.esGrabarWfInstancia())
	////// crearNuevaIntanciaActividad(actividad);
	////
	//// actividad.onInicio();
	////// if (!actividad.esGrabarWfInstancia()
	////// && !actividad.isRealizarTermino())
	////
	//// // Solo grabo si no se termina la actividad.
	//// if ( !actividad.isRealizarTermino() )
	//// crearNuevaIntanciaActividad(actividad);
	//// }
	//// }
	//
	//// public void terminarActividad(IActividad actividad)
	//// throws TnProcesoExcepcion {
	//// retrivePrimaryKeyActividad(actividad);
	////
	//// //TODO ver si tengo alguna forma de determinar si se ejecut� bien
	// onTermino y tomar alguna acci�n si no
	//// //AQUI INVOCO A LA LOGICA DE TERMINO!!
	//// actividad.onTermino();
	////
	//// if (log.isDebugEnabled())
	//// log.debug(
	//// "Inicio de Termino: ["
	//// + actividad.getIdInstanciaProceso()
	//// + ","
	//// + actividad.getCodigoActividad()
	//// + ","
	//// + actividad.isRealizarTermino()
	//// + "]");
	////
	//// enviarRespuesta(actividad);
	////
	//// //Borro la instancia de la tabla de Instancias
	//// eliminarInstanciaActividad(actividad);
	//// }
	//
	//// public void terminarInstancia(IActividad actividad)
	//// throws TnProcesoExcepcion {
	////
	//// try {
	//// retrivePrimaryKeyActividad(actividad);
	//// } catch (TnProcesoExcepcion e) {
	//// log.info(
	//// "No Encontrada en WFInstancia : ["
	//// + actividad.getIdInstanciaProceso() + "," +
	// actividad.getCodigoActividad()
	//// + "," + e.getMessage() + "]");
	//// }
	//// try {
	//// actividad.onDelete();
	//// } catch (TnProcesoExcepcion e) {
	//// log.info(
	//// "Problemas en OnDelete: ["
	//// + actividad.getIdInstanciaProceso() + "," +
	// actividad.getCodigoActividad()
	//// + "," + e.getMessage() + "]");
	//// }
	//// try {
	//// eliminarInstanciaActividad(actividad);
	//// } catch (TnProcesoExcepcion e) {
	//// log.info(
	//// "Problemas en Eliminar WFInstancia : ["
	//// + actividad.getIdInstanciaProceso() + "," +
	// actividad.getCodigoActividad()
	//// + "," + e.getMessage() + "]");
	//// }
	////
	//// enviarBorrar(actividad);
	////
	//// }
	//
	//// public List getListaActividadesTerminadas(String idProceso)
	//// throws TnProcesoExcepcion {
	//// WfInstanciaActividadLocalHome wfInstanciaActividadHome = null;
	//// wfInstanciaActividadHome =
	// getWfInstanciaHome(wfInstanciaActividadHome);
	//// try {
	//// Collection instancias =
	//// wfInstanciaActividadHome
	//// .findInstanciasActividadTerminadasByIdProceso(
	//// idProceso);
	//// List listRet = new ArrayList();
	//// for (Iterator iter = instancias.iterator(); iter.hasNext();) {
	//// WfInstanciaActividadLocal instancia =
	//// (WfInstanciaActividadLocal) iter.next();
	//// String[] datos = new String[4];
	//// datos[0] = instancia.getIdProceso();
	//// datos[1] = instancia.getIdInstanciaProceso();
	//// datos[2] = instancia.getCodigoActividad();
	//// datos[3] = (String) instancia.getPrimaryKey();
	//// listRet.add(datos);
	//// }
	//// return listRet;
	//// } catch (FinderException e) {
	//// throw new TnProcesoExcepcion(
	//// e.getClass().getName()
	//// + "en WfSessionBean.getListaActividadesPendientes("
	//// + idProceso
	//// + ")");
	//// }
	//// }
	//
	//// private WfInstanciaActividadLocal getInstanciaActividad(IActividad
	// actividad)
	//// throws TnProcesoExcepcion, InstanciaActividadNoEncontradaException {
	////
	//// try {
	//// WfInstanciaActividadLocalHome wfIaHome =
	//// (WfInstanciaActividadLocalHome) HomeFactory.getHome(
	//// WfInstanciaActividadLocalHome.JNDI_NAME);
	//// String idInstanciaActividad = actividad.getIdInstancia();
	//// if (idInstanciaActividad == null) {
	////
	//// if (actividad.getIdProceso() == null
	//// || actividad.getIdInstanciaProceso() == null
	//// || actividad.getCodigoActividad() == null) {
	//// throw new IllegalArgumentException(
	//// "Para obtener una instanciaActividad, la actividad debe tener definido
	// el id de la instancia de actividad, o bien los tres valores: idProceso,
	// idInstanciaProceso y codigo Actividad. idProceso="
	//// + actividad.getIdProceso()
	//// + ", idInstanciaProceso= "
	//// + actividad.getIdInstanciaProceso()
	//// + ", codigoActividad="
	//// + actividad.getCodigoActividad());
	//// } else {
	//// WfInstanciaActividadLocal wfInstanciaActividad =
	//// wfIaHome.findInstanciaByProcesoTemplateActividad(
	//// actividad.getIdProceso(),
	//// actividad.getIdInstanciaProceso(),
	//// actividad.getCodigoActividad());
	//// actividad.setIdInstancia(
	//// (String) wfInstanciaActividad.getPrimaryKey());
	//// return wfInstanciaActividad;
	//// }
	//// } else {
	//// return wfIaHome.findByPrimaryKey(idInstanciaActividad);
	//// }
	//// } catch (FinderException e) {
	//// throw new InstanciaActividadNoEncontradaException("No se encuentra la
	// instanciaActividad especificada");
	//// } catch (Exception e) {
	//// throw new TnProcesoExcepcion(
	//// e.getClass().getName()
	//// + " en WFSessionBean.getInstanciaActividad(IActividad) : "
	//// + e.getMessage());
	//// }
	////
	//// }
	//
	//	private void retrivePrimaryKeyActividad(IActividadEJB actividad)
	//		throws TnProcesoExcepcion {
	//		String idInstanciaActividad = actividad.getActImplCorrelID();
	//		if (idInstanciaActividad == null) {
	//			if (actividad.getIdTemplateWf() == null
	//				|| actividad.getNumeroPeticion() == null
	//				|| actividad.getCodigoActividad() == null) {
	//				throw new IllegalArgumentException(
	//					"Para terminar una actividad, esta debe tener definido el id de la
	// instancia de actividad, o bien los tres valores: idProceso,
	// idInstanciaProceso y codigo Actividad. idProceso="
	//						+ actividad.getIdTemplateWf()
	//						+ ", numeroPeticion= "
	//						+ actividad.getNumeroPeticion()
	//						+ ", codigoActividad="
	//						+ actividad.getCodigoActividad());
	//			} else {
	//				try {
	//					//Si el id de la instancia es nulo, pero est�n definidos los otros tres
	// atributos de una actividad, la obtengo a partir de estos...
	//					Wf_instancia_actividadLocalHome wfIaHome =
	//						(Wf_instancia_actividadLocalHome) HomeFactory.getHome(
	//						Wf_instancia_actividadLocalHome.JNDI_NAME);
	//					Wf_instancia_actividadLocal wfIa =
	//						wfIaHome.findInstanciaByProcesoTemplateActividad(
	//							actividad.getIdTemplateWf(),
	//							actividad.getNumeroPeticion(),
	//							actividad.getCodigoActividad());
	//
	//					actividad.setActImplCorrelID(((Wf_instancia_actividadKey)wfIa.getPrimaryKey()).id_instancia_actividad);
	//
	//				} catch (EJBException e) {
	//					throw new TnProcesoExcepcion(
	//						"EJBException en WfSessionBean.terminarActividad(IActividad actividad)"
	//							+ e.getMessage());
	//				} catch (NamingException e) {
	//					throw new TnProcesoExcepcion(
	//						"NamingException en WfSessionBean.terminarActividad(IActividad
	// actividad)"
	//							+ e.getMessage());
	//				} catch (FinderException e) {
	//					throw new TnProcesoExcepcion(
	//						"No se Encontro Registro en WfInstanciaActivdad ["
	//							+ actividad.getIdTemplateWf()
	//							+ ","
	//							+ actividad.getNumeroPeticion()
	//							+ ","
	//							+ actividad.getCodigoActividad()
	//							+ "] "
	//							+ e.getMessage());
	//				}
	//			}
	//		}
	//	}
	//
	//	/**
	//	 * @param actividad
	//	 */
	//// public void crearNuevaIntanciaActividad(IActividad actividad)
	//// throws TnProcesoExcepcion {
	////
	//// Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
	//// wfInstanciaActividadHome =
	// getWfInstanciaHome(wfInstanciaActividadHome);
	////
	//// try {
	//// wfInstanciaActividadHome.create(actividad);
	//// } catch (CreateException e) {
	//// throw new
	// TnProcesoExcepcion("WfSessionBean.crearNuevaIntanciaActividad :
	// CreateException");
	//// }
	////
	//// }
	//	
	//	public void crearNuevaIntanciaActividadEJB(IActividadEJB actividad)
	//		throws TnProcesoExcepcion {
	//
	//		Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
	//		wfInstanciaActividadHome = getWfInstanciaHome(wfInstanciaActividadHome);
	//		Long numPet= actividad.getNumeroPeticion();
	//		try {
	//			wfInstanciaActividadHome.create(actividad.getActImplCorrelID(),actividad.getIdTemplateWf(),numPet,actividad.getCodigoActividad(),actividad.getXMLDatos());
	//		} catch (CreateException e) {
	//			log.error("Error al crear instancia Actividad:",e);
	//			throw new TnProcesoExcepcion("WfSessionBean.crearNuevaIntanciaActividad :
	// CreateException");
	//		}
	//
	//	}
	//
	//	/**
	//	 * @param actividad
	//	 */
	//	public void eliminarInstanciaActividad(IActividadEJB actividad)
	//		throws TnProcesoExcepcion {
	//		try {
	//			this.retrivePrimaryKeyActividad(actividad);
	//			Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
	//			wfInstanciaActividadHome =
	//				getWfInstanciaHome(wfInstanciaActividadHome);
	//			Wf_instancia_actividadKey wfK= new Wf_instancia_actividadKey();
	//			wfK.id_instancia_actividad=actividad.getActImplCorrelID();
	//			Wf_instancia_actividadLocal wfInstanciaActividad =
	//				wfInstanciaActividadHome.findByPrimaryKey(wfK);
	//
	//			//21-03-2005
	//			//TODO Se modifica esta linea para editar un estado en vez de borrar
	//			//wfInstanciaActividad.remove();
	//			log.debug("Realizo el Update de la actividad:" +
	// actividad.getCodigoActividad());
	//			wfInstanciaActividad.setEstado(new Integer(1));
	//		} catch (FinderException e) {
	//			throw new TnProcesoExcepcion(
	//				"FinderException en WFSessionBean.eliminarIntanciaActividad "
	//					+ e.getMessage());
	//		} catch (EJBException e) {
	//			throw new TnProcesoExcepcion(
	//				"EJBException en WFSessionBean.eliminarIntanciaActividad "
	//					+ e.getMessage());
	//		}
	//
	//	}
	//
	//	/**
	//	 * @param actividad
	//	 */
	//// public void eliminarInstanciaActividadByPeticion(Long idPeticion)
	//// throws TnProcesoExcepcion {
	//// try {
	//// Wf_instancia_actividadLocalHome wfInstanciaActividadHome =
	//// (Wf_instancia_actividadLocalHome) HomeFactory.getHome(
	//// Wf_instancia_actividadLocalHome.JNDI_NAME);
	//// Collection wf =
	//// wfInstanciaActividadHome.findByIdProceso(idPeticion);
	////
	//// int i = 1;
	//// for (Iterator iter = wf.iterator(); iter.hasNext();) {
	//// log.debug(
	//// i + "� ingreso a for para remover WF_INSTANCIA_ACTIVIDAD ");
	//// Wf_instancia_actividadLocal wfLocal =
	//// (Wf_instancia_actividadLocal) iter.next();
	//// wfLocal.remove();
	//// log.debug(i + "� registro eliminado");
	//// }
	//// } catch (FinderException e) {
	//// throw new TnProcesoExcepcion(
	//// "FinderException en WFSessionBean.eliminarIntanciaActividad "
	//// + e.getMessage());
	//// } catch (EJBException e) {
	//// throw new TnProcesoExcepcion(
	//// "EJBException en WFSessionBean.eliminarIntanciaActividad "
	//// + e.getMessage());
	//// } catch (RemoveException e) {
	//// throw new TnProcesoExcepcion(
	//// "ERROR: al eliminar registro en WF_INSTANCIA_ACTIVIDAD para peticion "
	//// + idPeticion
	//// + ".");
	//// } catch (NamingException e) {
	//// throw new TnProcesoExcepcion(
	//// "NamingExceptionen WFSessionBean.eliminarInstanciaActividad "
	//// + idPeticion
	//// + ".");
	//// }
	//// }
	//
	//	private Wf_instancia_actividadLocalHome
	// getWfInstanciaHome(Wf_instancia_actividadLocalHome
	// wfInstanciaActividadHome)
	//		throws TnProcesoExcepcion {
	//		try {
	//			wfInstanciaActividadHome =
	//				(Wf_instancia_actividadLocalHome) HomeFactory.getHome(
	//					Wf_instancia_actividadLocalHome.JNDI_NAME);
	//
	//		} catch (NamingException e) {
	//			throw new TnProcesoExcepcion("WfSessionBean.getWfInstanciaHome :
	// NamingException");
	//		}
	//		return wfInstanciaActividadHome;
	//	}
	//
	//// private void enviarRespuesta(IActividad actividad) {
	////
	//// StringBuffer XMLBuffer = new StringBuffer();
	//// String XMLCampos = "";
	//// String idRespuesta = actividad.getIdInstancia();
	//// String nombreEstructuraDatos = actividad.getNombreEstructuraDatos();
	////
	//// try {
	//// Iterator iteradorLlavesCampos = actividad.keySet().iterator();
	////
	//// while (iteradorLlavesCampos.hasNext()) {
	//// String llave = (String) iteradorLlavesCampos.next();
	//// String valor = actividad.getDato(llave);
	////
	//// /*
	//// * El XML para los campos puede ser un poco largo as� que
	//// * hay una versi�n con StringBuffer (m�s rapida pero menos legible)
	//// * y la versi�n con String (m�s lenta, pero legible).
	//// */
	////
	//// //XMLCampos = XMLCampos + "\n<" + llave + ">" + valor + "</" + llave +
	// ">";
	////
	//// XMLBuffer.append("\n<");
	//// XMLBuffer.append(llave);
	//// XMLBuffer.append(">");
	//// XMLBuffer.append(valor);
	//// XMLBuffer.append("</");
	//// XMLBuffer.append(llave);
	//// XMLBuffer.append(">");
	//// }
	////
	//// XMLCampos = XMLBuffer.toString();
	//// } catch (NullPointerException e) {
	//// //Si no habia datos, no los incluimos en el mensaje
	//// }
	////
	//// String mensajeRespuesta =
	//// ""
	//// + "<WfMessage>"
	//// +
	// "\n<WfMessageHeader><ResponseRequired>Yes</ResponseRequired></WfMessageHeader>"
	//// + "\n<ActivityImplInvokeResponse>"
	//// + "\n<ActImplCorrelID>"
	//// +
	//// // Copiar aqui el "ActImplCorrelID" del mensaje enviado
	//// //
	// "RUEAAAABAINAgwAAAAAAAAAAAAAABAAAAAEAhsAAAAAAAAAAAAAAAAAEQQAAAAEAg0CEAAAAAAAAAABF"
	// +
	//// idRespuesta
	//// + "</ActImplCorrelID>"
	//// + "\n<ProgramRC>0</ProgramRC>"
	//// + "\n<ProgramOutputData>"
	//// + "\n<"
	//// + nombreEstructuraDatos
	//// + ">"
	//// + XMLCampos
	//// + "\n</"
	//// + nombreEstructuraDatos
	//// + ">"
	//// + "\n</ProgramOutputData>"
	//// + "\n</ActivityImplInvokeResponse>"
	//// + "\n</WfMessage>";
	////
	//// // Responder. Se comenta para el Logger.-
	//// if (log.isDebugEnabled())
	//// log.debug(
	//// "Enviando Mensaje a WF (Peticion, Instancia) = "
	//// + "("
	//// + actividad.getIdInstanciaProceso()
	//// + ","
	//// + idRespuesta
	//// + ")\n");
	////
	//// Date iniDate = new Date();
	//// enviarMensaje(mensajeRespuesta);
	//// log.info(
	//// "\nPublica Envio Mensaje ["
	//// + actividad.getIdInstanciaProceso()
	//// + ","
	//// + ((new Date()).getTime() - iniDate.getTime())
	//// + "]");
	////
	//// //log.info("Mensaje Enviado a WF (Peticion, Instancia) = " +
	//// // "(" + actividad.getIdInstanciaProceso() + "," + idRespuesta +
	// ")\n");
	//// }
	//	
	//	public void enviarRespuesta(IActividadEJB actividad) throws
	// TnProcesoExcepcion{
	//
	//		StringBuffer XMLBuffer = new StringBuffer();
	//		String XMLCampos = "";
	//		//Si no tiene el IdInstancia de la Actividad, lo va a buscar a la BD a la
	// tabla WFInstanciaActividad
	//		//Las Actividades Automaticas que se cierran de inmediato, tienen el ID.
	//		this.retrivePrimaryKeyActividad(actividad);
	//		String idRespuesta = actividad.getActImplCorrelID();
	//		String nombreEstructuraDatos = actividad.getNombreEstructuraDatos();
	//
	//		try {
	//			Iterator iteradorLlavesCampos = actividad.getDatos().keySet().iterator();
	//
	//			while (iteradorLlavesCampos.hasNext()) {
	//				String llave = (String) iteradorLlavesCampos.next();
	//				String valor = actividad.getDato(llave);
	//
	//				/*
	//				 * El XML para los campos puede ser un poco largo as� que
	//				 * hay una versi�n con StringBuffer (m�s rapida pero menos legible)
	//				 * y la versi�n con String (m�s lenta, pero legible).
	//				 */
	//
	//				//XMLCampos = XMLCampos + "\n<" + llave + ">" + valor + "</" + llave +
	// ">";
	//
	//				XMLBuffer.append("\n<");
	//				XMLBuffer.append(llave);
	//				XMLBuffer.append(">");
	//				XMLBuffer.append(valor);
	//				XMLBuffer.append("</");
	//				XMLBuffer.append(llave);
	//				XMLBuffer.append(">");
	//			}
	//
	//			XMLCampos = XMLBuffer.toString();
	//		} catch (NullPointerException e) {
	//			//Si no habia datos, no los incluimos en el mensaje
	//		}
	//
	//		String mensajeRespuesta =
	//			""
	//				+ "<WfMessage>"
	//				+
	// "\n<WfMessageHeader><ResponseRequired>Yes</ResponseRequired></WfMessageHeader>"
	//				+ "\n<ActivityImplInvokeResponse>"
	//				+ "\n<ActImplCorrelID>"
	//				+
	//			// Copiar aqui el "ActImplCorrelID" del mensaje enviado
	//		//
	// "RUEAAAABAINAgwAAAAAAAAAAAAAABAAAAAEAhsAAAAAAAAAAAAAAAAAEQQAAAAEAg0CEAAAAAAAAAABF"
	// +
	//	idRespuesta
	//		+ "</ActImplCorrelID>"
	//		+ "\n<ProgramRC>0</ProgramRC>"
	//		+ "\n<ProgramOutputData>"
	//		+ "\n<"
	//		+ nombreEstructuraDatos
	//		+ ">"
	//		+ XMLCampos
	//		+ "\n</"
	//		+ nombreEstructuraDatos
	//		+ ">"
	//		+ "\n</ProgramOutputData>"
	//		+ "\n</ActivityImplInvokeResponse>"
	//		+ "\n</WfMessage>";
	//
	//		// Responder. Se comenta para el Logger.-
	//		if (log.isDebugEnabled())
	//			log.debug(
	//				"Enviando Mensaje a WF (Peticion, Instancia) = "
	//					+ "("
	//					+ actividad.getNumeroPeticion()
	//					+ ","
	//					+ idRespuesta
	//					+ ")\n");
	//
	//		Date iniDate = new Date();
	//		enviarMensaje(mensajeRespuesta);
	//		log.info(
	//			"\nPublica Envio Mensaje ["
	//				+ actividad.getNumeroPeticion()
	//				+ ","
	//				+ ((new Date()).getTime() - iniDate.getTime())
	//				+ "]");
	//
	//		//log.info("Mensaje Enviado a WF (Peticion, Instancia) = " +
	//		// "(" + actividad.getIdInstanciaProceso() + "," + idRespuesta + ")\n");
	//	}
	//
	//// private void enviarBorrar(IActividad actividad) {
	////
	//// //StringBuffer XMLBuffer = new StringBuffer();
	//// //String XMLCampos = "";
	//// String idRespuesta = actividad.getIdInstanciaProceso();
	//// String nombreEstructuraDatos = actividad.getNombreEstructuraDatos();
	////
	//// String mensajeRespuesta =
	//// ""
	//// + "<WfMessage>"
	//// +
	// "\n<WfMessageHeader><ResponseRequired>Yes</ResponseRequired></WfMessageHeader>"
	//// + "\n<ProcessInstanceTerminate>"
	//// + "\n<ProcInstName>"
	//// + idRespuesta
	//// + "\n</ProcInstName>"
	//// + "\n</ProcessInstanceTerminate>"
	//// + "\n</WfMessage>";
	////
	//// // Responder
	//// //log.debug("Voy a enviar mensaje de termino ...");
	//// log.info(
	//// "Enviando Mensaje Termino a WF (Peticion, Instancia) = "
	//// + "("
	//// + actividad.getIdProceso()
	//// + ","
	//// + idRespuesta
	//// + ")\n");
	//// enviarMensaje(mensajeRespuesta);
	//// //log.info("Mensaje TerminoEnviado a WF(Peticion, Instancia) = " +
	//// // "(" + actividad.getIdProceso() + "," + idRespuesta + ")\n");
	////
	//// }
	//
	//	public void enviarMensaje(String text) {
	//
	//		log.info("Envio Mensaje WF.");
	//
	//		if (log.isDebugEnabled())
	//			log.debug(
	//				"Enviando Mensaje a WF para Peticion, \n***\n"
	//					+ text
	//					+ "\n***");
	//		QueueConnection qc = null;
	//		QueueSession session = null;
	//		QueueSender sender = null;
	//
	//		try {
	//			// Create a connection
	//			qc = qcf.createQueueConnection();
	//			qc.start();
	//
	//			// Create a session.
	//			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	//
	//			// Create a QueueSender
	//			sender = session.createSender(sendQueue);
	//
	//			// Create a message to send to the queue...
	//			TextMessage message = session.createTextMessage(text);
	//			message.setJMSReplyTo(this.errorResponseQueue);
	//
	//			// Set CorrelationID from the input message and send
	//			//message.setJMSCorrelationID(corrid);
	//			sender.send(message);
	//
	//			// Close the connection (close calls will cascade to other objects)
	//			sender.close();
	//			session.close();
	//			qc.close();
	//			qc = null;
	//
	//		} catch (JMSException e) {
	//			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
	//			Exception le = e.getLinkedException();
	//		} catch (Exception e) {
	//			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
	//		} finally {
	//			// Ensure that the Connection always gets closed
	//			if (qc != null) {
	//				try {
	//					qc.close();
	//				} catch (JMSException e) {
	//				}
	//			}
	//		}
	//	}
	//
	//	public void enviarMensaje(String queue,String text) {
	//
	//		log.warn("Envio Mensaje WF.");
	//
	//		if (log.isDebugEnabled())
	//			log.debug(
	//				"Enviando Mensaje a WF para Peticion, \n***\n"
	//					+ text
	//					+ "\n***");
	//		QueueConnection qc = null;
	//		QueueSession session = null;
	//		QueueSender sender = null;
	//
	//		try {
	//			// Create a connection
	//			qc = qcf.createQueueConnection();
	//			qc.start();
	//
	//			// Create a session.
	//			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	//
	//			// Create Queue
	//			sendQueueVariable = (Queue) this.getJMS(queue);
	//			
	//			// Create a QueueSender
	//			sender = session.createSender(sendQueueVariable);
	//
	//			// Create a message to send to the queue...
	//			TextMessage message = session.createTextMessage(text);
	//			message.setJMSReplyTo(this.errorResponseQueue);
	//
	//			// Set CorrelationID from the input message and send
	//			//message.setJMSCorrelationID(corrid);
	//			sender.send(message);
	//
	//			// Close the connection (close calls will cascade to other objects)
	//			sender.close();
	//			session.close();
	//			qc.close();
	//			qc = null;
	//
	//		} catch (JMSException e) {
	//			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
	//			Exception le = e.getLinkedException();
	//		} catch (Exception e) {
	//			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
	//		} finally {
	//			// Ensure that the Connection always gets closed
	//			if (qc != null) {
	//				try {
	//					qc.close();
	//				} catch (JMSException e) {
	//				}
	//			}
	//		}
	//	}
	//
	//	private Object getJMS(String jmsRef) throws NamingException {
	//		if (initialContext != null) {
	//			Object nsObject =
	//				initialContext.lookup(
	//					new StringBuffer("java:comp/env/")
	//						.append(jmsRef)
	//						.toString());
	//			return nsObject;
	//		} else {
	//			throw new NamingException("HomeFactory: no InitialContext");
	//		}
	//	}
	//	private static final String USUARIO_WF = "ADMIN";
	//	private static final String PASSWORD_WF = "password";

	/**
	 * OJO! - Este QUEUE CONNECTION FACTORY debe quedar con el mismo nombre en
	 * el EJB Deployment Descriptor. Ver las referencias del WFSession, debe
	 * tener una Resource Reference a "jms/VPISTBBA_TO_WF_QCF"
	 */

	private static final String RESPONSEQUEUE_ALTAS = "jms/WF/WF_INPUT_Q";
	private static final String RESPONSEQUEUE_BAJAS = "jms/WF/WF_INPUT_BAJAS_Q";
	private static final String RESPONSEQUEUE_INCIDENCIAS = "jms/WF/WF_INPUT_INCIENCIAS_Q";

	private static final String ERROR_RESPONSEQUEUE_ALTAS = "jms/WF/WF_TO_APP_ERR_Q";
	private static final String ERROR_RESPONSEQUEUE_BAJAS = "jms/WF/WF_TO_APP_ERR_BAJAS_Q";
	private static final String ERROR_RESPONSEQUEUE_INCIDENCIAS = "jms/WF/WF_TO_APP_ERR_INCIDENCIAS_Q";
	
	private static final String QUEUECONNECTIONFACTORY_ALTAS = "jms/WF_QCF";
	private static final String QUEUECONNECTIONFACTORY_BAJAS = "jms/WF_QCF_BAJAS";
	private static final String QUEUECONNECTIONFACTORY_INCIDENCIAS = "jms/WF_QCF_INCIDENCIAS";

	private javax.ejb.SessionContext mySessionCtx;

	private QueueConnectionFactory qcf = null;

	private Queue sendQueue = null;

	private Queue sendQueueVariable = null;

	private Queue errorResponseQueue = null;

	private InitialContext initialContext;
	
	private String sourceEntry=null;

	/**
	 * Obtencion de logger.
	 * 
	 * @author amartoq
	 */
	protected static transient Logger log = log = LoggerFactory.getLogger(WFSessionBean.class);

	//Lo inicializo en ejbCreate...
	private MQWFBean mqWfBean = null;
	
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {

		/*
		 * Para inicializar cola de respuesta al workflow
		 */
		try {
			this.initialContext = new InitialContext();
			//qcf = (QueueConnectionFactory) this.getJMS(QUEUECONNECTIONFACTORY_ALTAS);
			//sendQueue = (Queue) this.getJMS(RESPONSEQUEUE_ALTAS);
			//errorResponseQueue = (Queue) this.getJMS(ERROR_RESPONSEQUEUE_ALTAS);
			// NOTA: descomentar esta linea para pruebas de VpiSSDD
			//this.mqWfBean = new MQWFBean();
		} catch (javax.naming.NamingException e) {
			log.error("NamingException", e);
		}

	}
		
	/**
	 * Crea los objetos de conexi�n para mensajeria de las colas 
	 * del workflow, dependiendo de que tipo de petici�n es. Alta, baja, incidencia
	 * @param requestId N�mero de petici�n de atiempo o n�mero de incidente
	 * @param source Establece si la solicitud viene de VPI o de soltec
	 */
	private void createQueueConnectionObjects(Long requestId, String source){
		try {
			WfQueueConnection data = new WfQueueConnection();
			data.setDateImplWF(new Fecha(getParameterBD("FECHA_IMPL_WF"),"aaaammdd"));
			data.setDateImplWFPos(new Fecha(getParameterBD("FECHA_IMPL_WF_POS"),"aaaammdd"));
			boolean on = (new Boolean(getParameterBD("ACTIVAR_IMPL_WF")).booleanValue());
			data.setQueueConnectionFactory(QUEUECONNECTIONFACTORY_ALTAS);
			data.setQueue(RESPONSEQUEUE_ALTAS);
			data.setErrorQueue(ERROR_RESPONSEQUEUE_ALTAS);
			data.setSource(source);
			data.setId(requestId);
			if(on){
				if(WFSessionLocal.FLUJO_VPI.equals(source)){
					createQueueConnectionObjectsVPI(data);
				}else if(WFSessionLocal.FLUJO_SOLTEC.equals(source)){
					//Se desactiva el redireccionamiento de WF para SOLTEC
					//createQueueConnectionObjectsSoltec(data);
				}else{
					log.error("Error creando los datos de conexion para env�o de mensaje a workflow, dato (source) incorrecto: "+ source);
					throw new RuntimeException("Error creando los datos de conexion para envio de mensaje a workflow, dato (source) incorrecto: "+ source);
				}
			}
			log.info("Envio mensaje a WF: DivisionWF: "+String.valueOf(on)+", Fecha Imple. WF: "+data.getDateImplWF().getYYYYMMDD()+" qcf: "+ data.getQueueConnectionFactory()+", queue: "+data.getQueue()+", errorQueue: "+ data.getErrorQueue());
			qcf = (QueueConnectionFactory)this.getJMS(data.getQueueConnectionFactory());
			sendQueue = (Queue) this.getJMS(data.getQueue());
			errorResponseQueue = (Queue) this.getJMS(data.getErrorQueue());
		} catch (NamingException e) {
			log.error("NamingException en createQueueConnectionObjects.",e);
			throw new RuntimeException(e);
		} catch (FinderException e) {
			log.error("FinderException en createQueueConnectionObjects.",e);
			throw new RuntimeException(e);
		} catch (FechaException e) {
			log.error("FechaException en createQueueConnectionObjects.",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Crea los objetos de conexi�n al workflow cuando es una petici�n de soltec
	 * @param data Estrucutra con datos de entrada
	 * @throws NamingException
	 * @throws FinderException
	 * @throws FechaException
	 */
	private void createQueueConnectionObjectsSoltec(WfQueueConnection data) throws NamingException, FinderException, FechaException{
		Peticion_stLocalHome ps = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
		Peticion_stLocal psLocal = ps.findByPrimaryKey(new Peticion_stKey(data.getId()));
		Fecha dateEntry = new Fecha(psLocal.getFec_cit_ff().replaceAll("/",""),"ddmmaaaa");
		if (dateEntry.esMayor(data.getDateImplWF()) || dateEntry.esIgual(data.getDateImplWF())) {
			data.setQueueConnectionFactory(QUEUECONNECTIONFACTORY_INCIDENCIAS);
			data.setQueue(RESPONSEQUEUE_INCIDENCIAS);
			data.setErrorQueue(ERROR_RESPONSEQUEUE_INCIDENCIAS);
		}
		log.info("Envio mensaje a WF: Incidencias, (Peticion_st.Fec_cit_ff): "+dateEntry.toString());
	}
	
	/**
	 * Crea los objetos de conexi�n al workflow cuando es una petici�n de VPI
	 * @param data Estrucutra con datos de entrada
	 * @throws FinderException
	 * @throws NamingException
	 */
	private void createQueueConnectionObjectsVPI(WfQueueConnection data) throws NamingException, FinderException{
		PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		PeticionLocal peticion=peticionLocalHome.findByPrimaryKey(new PeticionKey(data.getId()));
		Fecha dateEntry = new Fecha(peticion.getPeti_fecha_ingreso());
		String info = null;
		if(dateEntry.esMayor(data.getDateImplWF()) || dateEntry.esIgual(data.getDateImplWF())){
			if(Pattern.matches("^B$",peticion.getTica_id())){//Bajas
				data.setQueueConnectionFactory(QUEUECONNECTIONFACTORY_BAJAS);
				data.setQueue(RESPONSEQUEUE_BAJAS);
				data.setErrorQueue(ERROR_RESPONSEQUEUE_BAJAS);
				info="Bajas, ";
				//Posventas
			}else if(Pattern.matches("^P$",peticion.getTica_id()) && dateEntry.esMayor(data.getDateImplWFPos()) || dateEntry.esIgual(data.getDateImplWFPos())){
				data.setQueueConnectionFactory(QUEUECONNECTIONFACTORY_BAJAS);
				data.setQueue(RESPONSEQUEUE_BAJAS);
				data.setErrorQueue(ERROR_RESPONSEQUEUE_BAJAS);
				info = "Posventas, Fecha Imple. WF Posventas: "+data.getDateImplWFPos().getYYYYMMDD()+", ";
			}
		}
		log.info("Envio mensaje a WF: "+(info==null?"Altas, ":info)+"(Peticion.tica_id): "+ peticion.getTica_id()+". (Peticion.Peti_fecha_ingreso): "+dateEntry.getYYYYMMDD());
	}
	
	/**
	 * Obtiene un parametro de la base de datos tabla parametros_bd
	 * @param codeParameter C�digo del par�metro
	 * @return Un string con el valor encontrado del par�metro requerido
	 * @throws NamingException
	 * @throws FinderException
	 */
	private static String getParameterBD(String codeParameter) throws NamingException, FinderException{
		Properties_BDLocalHome properties_BDLocalHome;
		properties_BDLocalHome = (Properties_BDLocalHome) HomeFactory
					.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal properties_BDLocal = properties_BDLocalHome
			.findByPrimaryKey(codeParameter);
			return properties_BDLocal.getValor();
	}
	
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}

	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	/**
	 * 
	 * METODOS DEL BEAN (I.E. No del Home)
	 *  
	 */

	//	public void iniciarProceso(IProceso proceso) throws TnProcesoExcepcion {
	//		try {
	//// this.iniciarProceso(proceso, USUARIO_WF, PASSWORD_WF);
	//			this.iniciarProceso(proceso);
	//
	//		} catch (TnMQWFException e) {
	//			throw new TnProcesoExcepcion(
	//				"TnMQWFException en WFSession.iniciarProceso() : "
	//					+ e.getMessage());
	//		}
	//	}
	public void iniciarProceso(IProceso proceso) throws TnProcesoExcepcion {
		try {
			proceso.preInicio();
			//		iniciarProcesoEnWorkflow(proceso, usuarioWf, passwordWf);
			iniciarProcesoEnWorkflow(proceso);
			proceso.onInicio();
		} catch (TnMQWFException e) {
			throw new TnProcesoExcepcion(
					"TnMQWFException en WFSession.iniciarProceso() : "
							+ e.getMessage(), e);
		}
	}

	private void iniciarProcesoEnWorkflow(IProceso proceso)
	//		String usuarioWf,
			//		String passwordWf)
			//TODO Probablemente haya que reemplazar la implementaci�n de este
			// m�todo, utilizando colas
			throws TnMQWFException {
		//Metodo antiguo
		/*
		 * log.debug("Iniciando proceso idProceso=" + proceso.getIdProceso() + ",
		 * idInstancia=" + proceso.getIdInstancia());
		 * 
		 * this.mqWfBean = new MQWFBean();
		 * 
		 * mqWfConectar(usuarioWf, passwordWf);
		 * 
		 * this.mqWfBean.setProcessName(proceso.getIdProceso());
		 * this.mqWfBean.setID(proceso.getIdInstancia());
		 * log.debug("proceso.getIdProceso() : " + proceso.getIdProceso());
		 * log.debug("proceso.getIdInstancia() : " + proceso.getIdInstancia());
		 */
		/*
		 * Voy actualizando los campos con los que vienen de la pantalla
		 */
		/*
		 * Map datos = proceso.getDatos(); Iterator nombreDatos =
		 * datos.keySet().iterator();
		 * 
		 * while (nombreDatos.hasNext()) { String nombre = (String)
		 * nombreDatos.next(); String valor = (String) datos.get(nombre);
		 * log.debug("Poniendo Campo '" + nombre + "' = '" + valor + "'");
		 * this.mqWfBean.setCampos(nombre, valor); }
		 * this.mqWfBean.crearInstancia(); mqWfDesconectar(); log.debug("Fin
		 * iniciarProceso(" + proceso.getIdProceso() + "," +
		 * proceso.getIdInstancia() + ")");
		 */
		//Metodo Nuevo
		//		VERSION NUEVA QUE USA JMS PARA LA CREACION DE INSTANCIAS -- Denis, 26
		// de Abril de 2004 (!)
		//mqWfConectar(usuarioWf, passwordWf);
		String processTemplate = proceso.getIdProceso();
		String processInstanceName = proceso.getIdInstancia();
		String nombreContenedor = proceso.getNombreEstructuraDatos();

		/*
		 * Voy actualizando los campos con los que vienen de la pantalla
		 */
		Map datos = proceso.getDatos();
		Iterator nombreDatos = datos.keySet().iterator();

		String XMLCampos = "";
		//TCS - CR25138 - Eliminacion de Repetitive calls of same method for
		// same information - dregueira - May 18, 2009
		String nombre = "";
		String aux = "";
		String valor = "";
		while (nombreDatos.hasNext()) {
			nombre = (String) nombreDatos.next();
			//TODO Inicio AT-1308
			//String valor = (String) datos.get(nombre);
			aux = ((String) datos.get(nombre)).trim();
			valor = (aux == null || (aux).equals("")) ? "X" : (String) datos
					.get(nombre);
			//TODO Fin AT-1308
			//log.debug("Poniendo Campo '" + nombre + "' = '" + valor + "'");
			// this.mqWfBean.setCampos( nombre, valor );
			XMLCampos += "<" + nombre + ">" + valor + "</" + nombre + ">\n";
		}

		String mensajeXML = ""
				+ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
				+ "<WfMessage>\n" + "<WfMessageHeader>\n"
				+ "<ResponseRequired>Yes</ResponseRequired>\n"
				+ "<UserContext></UserContext>\n" + "</WfMessageHeader>\n"
				+ "<ProcessTemplateCreateAndStartInstance>\n"
				+ "<ProcTemplName>" + processTemplate + "</ProcTemplName>\n"
				+ "<ProcInstName>" + processInstanceName + "</ProcInstName>\n"
				+ "<KeepName>true</KeepName>\n" + "<ProcInstInputData>\n" + "<"
				+ nombreContenedor + ">\n" + XMLCampos + "</"
				+ nombreContenedor + ">\n" + "</ProcInstInputData>\n"
				+ "</ProcessTemplateCreateAndStartInstance>\n"
				+ "</WfMessage>\n";

		//this.mqWfBean.crearInstancia();
		//mqWfDesconectar();

		log.info("Iniciando Proceso (Proceso, Instancia, Contenedor) = ("
				+ processTemplate + "," + processInstanceName + ","
				+ nombreContenedor + ")");
		
		//para saber si viene de soltec (inci_#####)
		Pattern pattern = Pattern.compile("^(inci_)(\\d+)$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(processInstanceName);
		Long requestNumber = matcher.matches()? new Long(matcher.group(2)): new Long(processInstanceName);
		String source = matcher.matches()? WFSessionLocal.FLUJO_SOLTEC:WFSessionLocal.FLUJO_VPI;
		enviarMensaje(mensajeXML, requestNumber, source);
	}

	/**
	 * 
	 * Este m�todo es el que se conecta al Workflow e inicia una instancia dado
	 * un template, actualmente est� utilizando la api original del WF, no s� si
	 * es posible implementar este mismo comportamiento a trav�s de colas (eso
	 * lo sabr� Denis, el papito del Work-flow)
	 * 
	 * @param proceso
	 * @param usuarioWf
	 * @param passwordWf
	 * @throws TnMQWFException
	 */
	//	private void iniciarProcesoEnWorkflow_OLD(
	//		IProceso proceso,
	//		String usuarioWf,
	//		String passwordWf)
	//	//TODO Probablemente haya que reemplazar la implementaci�n de este
	// m�todo, utilizando colas
	//	throws TnMQWFException {
	//		log.info(
	//			"Iniciando Proceso (idProceso, idInstancia) = ("
	//				+ proceso.getIdProceso()
	//				+ ","
	//				+ proceso.getIdInstancia()
	//				+ ")");
	//
	//		this.mqWfBean = new MQWFBean();
	//
	//		mqWfConectar(usuarioWf, passwordWf);
	//
	//		this.mqWfBean.setProcessName(proceso.getIdProceso());
	//		this.mqWfBean.setID(proceso.getIdInstancia());
	//		/*
	//		 * Voy actualizando los campos con los que vienen de la pantalla
	//		 */
	//		Map datos = proceso.getDatos();
	//		Iterator nombreDatos = datos.keySet().iterator();
	//
	//		while (nombreDatos.hasNext()) {
	//			String nombre = (String) nombreDatos.next();
	//			String valor = (String) datos.get(nombre);
	//			this.mqWfBean.setCampos(nombre, valor);
	//		}
	//		this.mqWfBean.crearInstancia();
	//		mqWfDesconectar();
	//		log.info(
	//			"Terminando Proceso (idProceso, idInstancia) = ("
	//				+ proceso.getIdProceso()
	//				+ ","
	//				+ proceso.getIdInstancia()
	//				+ ")");
	//	}
	//	private void mqWfDesconectar() throws TnMQWFException {
	//		this.mqWfBean.desconectar();
	//	}
	//
	//	private void mqWfConectar(String usuarioWf, String passwordWf)
	//		throws TnMQWFException {
	//		this.mqWfBean.setUserConnect(usuarioWf);
	//		this.mqWfBean.setPassConnect(passwordWf);
	//		this.mqWfBean.conectar();
	//	}
	//
	//	public String[] getListaTemplates() throws TnProcesoExcepcion {
	//		try {
	//			this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
	//			String[] templates =
	//				(String[]) this
	//					.mqWfBean
	//					.getProcessTemplateNames()
	//					.toArray(new String[] {
	//			});
	//			this.mqWfDesconectar();
	//			return templates;
	//		} catch (TnMQWFException e) {
	//			throw new TnProcesoExcepcion("TnMQWFException en
	// WFSession.getListaTemplates()");
	//		}
	//	}
	//
	//	public Map getValorCampos(IProceso proceso) throws TnProcesoExcepcion {
	//		try {
	//			this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
	//			this.mqWfBean.actualizaContainerTemplate(proceso.getIdProceso());
	//			Map campos = this.mqWfBean.getContainer();
	//			this.mqWfDesconectar();
	//			return campos;
	//		} catch (TnMQWFException e) {
	//			throw new TnProcesoExcepcion(
	//				"TnMQWFException en WFSession.getValorCampos()"
	//					+ e.getMessage());
	//		}
	//	}
	//
	//	public String[] getNombreCampos(IProceso proceso)
	//		throws TnProcesoExcepcion {
	//		try {
	//			this.mqWfConectar(USUARIO_WF, PASSWORD_WF);
	//			this.mqWfBean.actualizaContainerTemplate(proceso.getIdProceso());
	//			Map campos = this.mqWfBean.getContainer();
	//			Set nombreCampos = campos.keySet();
	//			String[] aNombreCampos =
	//				(String[]) nombreCampos.toArray(new String[] {
	//			});
	//			this.mqWfDesconectar();
	//			return aNombreCampos;
	//		} catch (TnMQWFException e) {
	//			throw new TnProcesoExcepcion(
	//				"TnMQWFException en WFSession.getNombreCampos()"
	//					+ e.getMessage());
	//		}
	//	}
	//Busca el xml con las variables en la tabla WFInstancia
	public void setMapaDatosActividad(ActividadEJBDTO actividad)
			throws TnProcesoExcepcion {

		try {
			this.retrivePrimaryKeyActividad(actividad);
			/*
			 * if (null != actividad.getDatos() &&
			 * !actividad.getDatos().isEmpty() ){ log.debug("### getMapaDatos
			 * encontro datos en la actividad"); return actividad.getDatos(); }
			 */
			//Busco el registro en la BD
			Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
			wfInstanciaActividadHome = getWfInstanciaHome(wfInstanciaActividadHome);
			Wf_instancia_actividadKey wfK = new Wf_instancia_actividadKey();
			wfK.id_instancia_actividad = actividad.getActImplCorrelID();
			Wf_instancia_actividadLocal wfInstanciaActividad = wfInstanciaActividadHome
					.findByPrimaryKey(wfK);

			String xmlParams = wfInstanciaActividad.getXmlparams();
			if (null != xmlParams) {
				new ParamsExtracterEJB(xmlParams, actividad).setMapDatos();
				log.debug("Seteo variables Actividad");
			}
		} catch (Exception e) {
			throw new TnProcesoExcepcion(e.getClass().getName()
					+ " en WFSessionBean.getMapaDatos(IActividad) : "
					+ e.getMessage());
		}

	}

	//	public void iniciarActividad(IActividad actividad)
	//		throws TnProcesoExcepcion {
	//
	//		if (actividad.esActividadVacia())
	//			enviarRespuesta(actividad);
	//		else {
	//			// La actividad se guarda en WF_INSTANCIA_ACTIVIDAD solo
	//			// cuando se necesita (Ej: No se necesita para las automaticas).
	//			// TODO: Ver si funciona para SSDD.
	//// if (actividad.esGrabarWfInstancia())
	//// crearNuevaIntanciaActividad(actividad);
	//
	//			actividad.onInicio();
	//// if (!actividad.esGrabarWfInstancia()
	//// && !actividad.isRealizarTermino())
	//
	//			// Solo grabo si no se termina la actividad.
	//			if ( !actividad.isRealizarTermino() )
	//				crearNuevaIntanciaActividad(actividad);
	//		}
	//	}

	//	public void terminarActividad(IActividad actividad)
	//		throws TnProcesoExcepcion {
	//		retrivePrimaryKeyActividad(actividad);
	//
	//		//TODO ver si tengo alguna forma de determinar si se ejecut� bien
	// onTermino y tomar alguna acci�n si no
	//		//AQUI INVOCO A LA LOGICA DE TERMINO!!
	//		actividad.onTermino();
	//
	//		if (log.isDebugEnabled())
	//			log.debug(
	//				"Inicio de Termino: ["
	//					+ actividad.getIdInstanciaProceso()
	//					+ ","
	//					+ actividad.getCodigoActividad()
	//					+ ","
	//					+ actividad.isRealizarTermino()
	//					+ "]");
	//
	//		enviarRespuesta(actividad);
	//
	//		//Borro la instancia de la tabla de Instancias
	//		eliminarInstanciaActividad(actividad);
	//	}

	//	public void terminarInstancia(IActividad actividad)
	//		throws TnProcesoExcepcion {
	//
	//		try {
	//			retrivePrimaryKeyActividad(actividad);
	//		} catch (TnProcesoExcepcion e) {
	//			log.info(
	//				"No Encontrada en WFInstancia : ["
	//					+ actividad.getIdInstanciaProceso() + "," +
	// actividad.getCodigoActividad()
	//					+ "," + e.getMessage() + "]");
	//		}
	//		try {
	//			actividad.onDelete();
	//		} catch (TnProcesoExcepcion e) {
	//			log.info(
	//				"Problemas en OnDelete: ["
	//					+ actividad.getIdInstanciaProceso() + "," +
	// actividad.getCodigoActividad()
	//					+ "," + e.getMessage() + "]");
	//		}
	//		try {
	//			eliminarInstanciaActividad(actividad);
	//		} catch (TnProcesoExcepcion e) {
	//			log.info(
	//				"Problemas en Eliminar WFInstancia : ["
	//					+ actividad.getIdInstanciaProceso() + "," +
	// actividad.getCodigoActividad()
	//					+ "," + e.getMessage() + "]");
	//		}
	//
	//		enviarBorrar(actividad);
	//
	//	}

	//	public List getListaActividadesTerminadas(String idProceso)
	//		throws TnProcesoExcepcion {
	//		WfInstanciaActividadLocalHome wfInstanciaActividadHome = null;
	//		wfInstanciaActividadHome = getWfInstanciaHome(wfInstanciaActividadHome);
	//		try {
	//			Collection instancias =
	//				wfInstanciaActividadHome
	//					.findInstanciasActividadTerminadasByIdProceso(
	//					idProceso);
	//			List listRet = new ArrayList();
	//			for (Iterator iter = instancias.iterator(); iter.hasNext();) {
	//				WfInstanciaActividadLocal instancia =
	//					(WfInstanciaActividadLocal) iter.next();
	//				String[] datos = new String[4];
	//				datos[0] = instancia.getIdProceso();
	//				datos[1] = instancia.getIdInstanciaProceso();
	//				datos[2] = instancia.getCodigoActividad();
	//				datos[3] = (String) instancia.getPrimaryKey();
	//				listRet.add(datos);
	//			}
	//			return listRet;
	//		} catch (FinderException e) {
	//			throw new TnProcesoExcepcion(
	//				e.getClass().getName()
	//					+ "en WfSessionBean.getListaActividadesPendientes("
	//					+ idProceso
	//					+ ")");
	//		}
	//	}

	//	private WfInstanciaActividadLocal getInstanciaActividad(IActividad
	// actividad)
	//		throws TnProcesoExcepcion, InstanciaActividadNoEncontradaException {
	//
	//		try {
	//			WfInstanciaActividadLocalHome wfIaHome =
	//				(WfInstanciaActividadLocalHome) HomeFactory.getHome(
	//					WfInstanciaActividadLocalHome.JNDI_NAME);
	//			String idInstanciaActividad = actividad.getIdInstancia();
	//			if (idInstanciaActividad == null) {
	//
	//				if (actividad.getIdProceso() == null
	//					|| actividad.getIdInstanciaProceso() == null
	//					|| actividad.getCodigoActividad() == null) {
	//					throw new IllegalArgumentException(
	//						"Para obtener una instanciaActividad, la actividad debe tener definido el
	// id de la instancia de actividad, o bien los tres valores: idProceso,
	// idInstanciaProceso y codigo Actividad. idProceso="
	//							+ actividad.getIdProceso()
	//							+ ", idInstanciaProceso= "
	//							+ actividad.getIdInstanciaProceso()
	//							+ ", codigoActividad="
	//							+ actividad.getCodigoActividad());
	//				} else {
	//					WfInstanciaActividadLocal wfInstanciaActividad =
	//						wfIaHome.findInstanciaByProcesoTemplateActividad(
	//							actividad.getIdProceso(),
	//							actividad.getIdInstanciaProceso(),
	//							actividad.getCodigoActividad());
	//					actividad.setIdInstancia(
	//						(String) wfInstanciaActividad.getPrimaryKey());
	//					return wfInstanciaActividad;
	//				}
	//			} else {
	//				return wfIaHome.findByPrimaryKey(idInstanciaActividad);
	//			}
	//		} catch (FinderException e) {
	//			throw new InstanciaActividadNoEncontradaException("No se encuentra la
	// instanciaActividad especificada");
	//		} catch (Exception e) {
	//			throw new TnProcesoExcepcion(
	//				e.getClass().getName()
	//					+ " en WFSessionBean.getInstanciaActividad(IActividad) : "
	//					+ e.getMessage());
	//		}
	//
	//	}

	private void retrivePrimaryKeyActividad(ActividadEJBDTO actividad)
			throws TnProcesoExcepcion {
		String idInstanciaActividad = actividad.getActImplCorrelID();
		if (idInstanciaActividad == null) {
			String idTemplateWf = actividad.getIdTemplateWf();
			Long numeroPeticion = actividad.getNumeroPeticion();
			String codigoActividad = actividad.getCodigoActividad();
			if (idTemplateWf == null || numeroPeticion == null
					|| codigoActividad == null) {
				throw new IllegalArgumentException(
						"Para terminar una actividad, esta debe tener definido el id de la instancia de actividad, o bien los tres valores: idProceso, idInstanciaProceso y codigo Actividad. idProceso="
								+ idTemplateWf
								+ ", numeroPeticion= "
								+ numeroPeticion
								+ ", codigoActividad="
								+ codigoActividad);
			} else {
				try {
					//Si el id de la instancia es nulo, pero est�n definidos
					// los otros tres atributos de una actividad, la obtengo a
					// partir de estos...
					Wf_instancia_actividadLocalHome wfIaHome = (Wf_instancia_actividadLocalHome) HomeFactory
							.getHome(Wf_instancia_actividadLocalHome.JNDI_NAME);
					Wf_instancia_actividadLocal wfIa = wfIaHome
							.findInstanciaByProcesoTemplateActividad(
									idTemplateWf, numeroPeticion,
									codigoActividad);

					actividad
							.setActImplCorrelID(((Wf_instancia_actividadKey) wfIa
									.getPrimaryKey()).id_instancia_actividad);

				} catch (EJBException e) {
					throw new TnProcesoExcepcion(
							"EJBException en WfSessionBean.terminarActividad(IActividad actividad)"
									+ e.getMessage());
				} catch (NamingException e) {
					throw new TnProcesoExcepcion(
							"NamingException en WfSessionBean.terminarActividad(IActividad actividad)"
									+ e.getMessage());
				} catch (FinderException e) {
					throw new TnProcesoExcepcion(
							"No se Encontro Registro en WfInstanciaActivdad ["
									+ idTemplateWf + "," + numeroPeticion + ","
									+ codigoActividad + "] " + e.getMessage());
				}
			}
		}
	}

	/**
	 * @param actividad
	 */
	//	public void crearNuevaIntanciaActividad(IActividad actividad)
	//		throws TnProcesoExcepcion {
	//
	//		Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
	//		wfInstanciaActividadHome = getWfInstanciaHome(wfInstanciaActividadHome);
	//
	//		try {
	//			wfInstanciaActividadHome.create(actividad);
	//		} catch (CreateException e) {
	//			throw new TnProcesoExcepcion("WfSessionBean.crearNuevaIntanciaActividad :
	// CreateException");
	//		}
	//
	//	}
	public void crearNuevaIntanciaActividadEJB(ActividadEJBDTO actividad)
			throws TnProcesoExcepcion {

		Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
		wfInstanciaActividadHome = getWfInstanciaHome(wfInstanciaActividadHome);
		Long numPet = actividad.getNumeroPeticion();
		try {
			wfInstanciaActividadHome.create(actividad.getActImplCorrelID(),
					actividad.getIdTemplateWf(), numPet, actividad
							.getCodigoActividad(), actividad.getXMLDatos());
		} catch (CreateException e) {
			log.error("Error al crear instancia Actividad:", e);
			throw new TnProcesoExcepcion(
					"WfSessionBean.crearNuevaIntanciaActividad : CreateException",
					e);
		}

	}

	/**
	 * @param actividad
	 */
	public void eliminarInstanciaActividad(ActividadEJBDTO actividad)
			throws TnProcesoExcepcion {
		try {
			this.retrivePrimaryKeyActividad(actividad);
			Wf_instancia_actividadLocalHome wfInstanciaActividadHome = null;
			wfInstanciaActividadHome = getWfInstanciaHome(wfInstanciaActividadHome);
			Wf_instancia_actividadKey wfK = new Wf_instancia_actividadKey();
			wfK.id_instancia_actividad = actividad.getActImplCorrelID();
			Wf_instancia_actividadLocal wfInstanciaActividad = wfInstanciaActividadHome
					.findByPrimaryKey(wfK);

			//21-03-2005
			//TODO Se modifica esta linea para editar un estado en vez de
			// borrar
			//wfInstanciaActividad.remove();
			log.debug("Realizo el Update de la actividad:"
					+ actividad.getCodigoActividad());
			wfInstanciaActividad.setEstado(new Integer(1));
		} catch (FinderException e) {
			throw new TnProcesoExcepcion(
					"FinderException en WFSessionBean.eliminarIntanciaActividad "
							+ e.getMessage());
		} catch (EJBException e) {
			throw new TnProcesoExcepcion(
					"EJBException en WFSessionBean.eliminarIntanciaActividad "
							+ e.getMessage());
		}

	}

	/**
	 * @param actividad
	 */
	//	public void eliminarInstanciaActividadByPeticion(Long idPeticion)
	//		throws TnProcesoExcepcion {
	//		try {
	//			Wf_instancia_actividadLocalHome wfInstanciaActividadHome =
	//				(Wf_instancia_actividadLocalHome) HomeFactory.getHome(
	//				Wf_instancia_actividadLocalHome.JNDI_NAME);
	//			Collection wf =
	//				wfInstanciaActividadHome.findByIdProceso(idPeticion);
	//
	//			int i = 1;
	//			for (Iterator iter = wf.iterator(); iter.hasNext();) {
	//				log.debug(
	//					i + "� ingreso a for para remover WF_INSTANCIA_ACTIVIDAD ");
	//				Wf_instancia_actividadLocal wfLocal =
	//					(Wf_instancia_actividadLocal) iter.next();
	//				wfLocal.remove();
	//				log.debug(i + "� registro eliminado");
	//			}
	//		} catch (FinderException e) {
	//			throw new TnProcesoExcepcion(
	//				"FinderException en WFSessionBean.eliminarIntanciaActividad "
	//					+ e.getMessage());
	//		} catch (EJBException e) {
	//			throw new TnProcesoExcepcion(
	//				"EJBException en WFSessionBean.eliminarIntanciaActividad "
	//					+ e.getMessage());
	//		} catch (RemoveException e) {
	//			throw new TnProcesoExcepcion(
	//				"ERROR: al eliminar registro en WF_INSTANCIA_ACTIVIDAD para peticion "
	//					+ idPeticion
	//					+ ".");
	//		} catch (NamingException e) {
	//			throw new TnProcesoExcepcion(
	//				"NamingExceptionen WFSessionBean.eliminarInstanciaActividad "
	//					+ idPeticion
	//					+ ".");
	//		}
	//	}
	private Wf_instancia_actividadLocalHome getWfInstanciaHome(
			Wf_instancia_actividadLocalHome wfInstanciaActividadHome)
			throws TnProcesoExcepcion {
		try {
			wfInstanciaActividadHome = (Wf_instancia_actividadLocalHome) HomeFactory
					.getHome(Wf_instancia_actividadLocalHome.JNDI_NAME);

		} catch (NamingException e) {
			throw new TnProcesoExcepcion(
					"WfSessionBean.getWfInstanciaHome : NamingException");
		}
		return wfInstanciaActividadHome;
	}

	//	private void enviarRespuesta(IActividad actividad) {
	//
	//		StringBuffer XMLBuffer = new StringBuffer();
	//		String XMLCampos = "";
	//		String idRespuesta = actividad.getIdInstancia();
	//		String nombreEstructuraDatos = actividad.getNombreEstructuraDatos();
	//
	//		try {
	//			Iterator iteradorLlavesCampos = actividad.keySet().iterator();
	//
	//			while (iteradorLlavesCampos.hasNext()) {
	//				String llave = (String) iteradorLlavesCampos.next();
	//				String valor = actividad.getDato(llave);
	//
	//				/*
	//				 * El XML para los campos puede ser un poco largo as� que
	//				 * hay una versi�n con StringBuffer (m�s rapida pero menos legible)
	//				 * y la versi�n con String (m�s lenta, pero legible).
	//				 */
	//
	//				//XMLCampos = XMLCampos + "\n<" + llave + ">" + valor + "</" + llave +
	// ">";
	//
	//				XMLBuffer.append("\n<");
	//				XMLBuffer.append(llave);
	//				XMLBuffer.append(">");
	//				XMLBuffer.append(valor);
	//				XMLBuffer.append("</");
	//				XMLBuffer.append(llave);
	//				XMLBuffer.append(">");
	//			}
	//
	//			XMLCampos = XMLBuffer.toString();
	//		} catch (NullPointerException e) {
	//			//Si no habia datos, no los incluimos en el mensaje
	//		}
	//
	//		String mensajeRespuesta =
	//			""
	//				+ "<WfMessage>"
	//				+
	// "\n<WfMessageHeader><ResponseRequired>Yes</ResponseRequired></WfMessageHeader>"
	//				+ "\n<ActivityImplInvokeResponse>"
	//				+ "\n<ActImplCorrelID>"
	//				+
	//			// Copiar aqui el "ActImplCorrelID" del mensaje enviado
	//		//
	// "RUEAAAABAINAgwAAAAAAAAAAAAAABAAAAAEAhsAAAAAAAAAAAAAAAAAEQQAAAAEAg0CEAAAAAAAAAABF"
	// +
	//	idRespuesta
	//		+ "</ActImplCorrelID>"
	//		+ "\n<ProgramRC>0</ProgramRC>"
	//		+ "\n<ProgramOutputData>"
	//		+ "\n<"
	//		+ nombreEstructuraDatos
	//		+ ">"
	//		+ XMLCampos
	//		+ "\n</"
	//		+ nombreEstructuraDatos
	//		+ ">"
	//		+ "\n</ProgramOutputData>"
	//		+ "\n</ActivityImplInvokeResponse>"
	//		+ "\n</WfMessage>";
	//
	//		// Responder. Se comenta para el Logger.-
	//		if (log.isDebugEnabled())
	//			log.debug(
	//				"Enviando Mensaje a WF (Peticion, Instancia) = "
	//					+ "("
	//					+ actividad.getIdInstanciaProceso()
	//					+ ","
	//					+ idRespuesta
	//					+ ")\n");
	//
	//		Date iniDate = new Date();
	//		enviarMensaje(mensajeRespuesta);
	//		log.info(
	//			"\nPublica Envio Mensaje ["
	//				+ actividad.getIdInstanciaProceso()
	//				+ ","
	//				+ ((new Date()).getTime() - iniDate.getTime())
	//				+ "]");
	//
	//		//log.info("Mensaje Enviado a WF (Peticion, Instancia) = " +
	//		// "(" + actividad.getIdInstanciaProceso() + "," + idRespuesta + ")\n");
	//	}

	public void enviarRespuesta(ActividadEJBDTO actividad)
			throws TnProcesoExcepcion {

		StringBuffer XMLBuffer = new StringBuffer();
		String XMLCampos = "";
		//Si no tiene el IdInstancia de la Actividad, lo va a buscar a la BD a
		// la tabla WFInstanciaActividad
		//Las Actividades Automaticas que se cierran de inmediato, tienen el
		// ID.
		this.retrivePrimaryKeyActividad(actividad);

		String idRespuesta = actividad.getActImplCorrelID();
		idRespuesta = idRespuesta.replaceAll("%2B", "+");
		idRespuesta = idRespuesta.replaceAll("%2F", "/");

		String nombreEstructuraDatos = actividad.getNombreEstructuraDatos();

		try {
			Iterator iteradorLlavesCampos = actividad.getDatos().keySet()
					.iterator();

			//TODO:CR11267
			String instanciasPiAnteriores = STConfig
					.getVariable("INSTANCIAS_PI_ANTERIORES");
			//log.debug("INSTANCIAS_PI_ANTERIORES: " + instanciasPiAnteriores);
			//fin CR11267

			while (iteradorLlavesCampos.hasNext()) {
				String llave = (String) iteradorLlavesCampos.next();
				//TODO Inicio AT-1308
				//String valor = actividad.getDato(llave);
				String aux = actividad.getDato(llave);
				String valor = (aux == null || aux.equals("")) ? "X" : aux;
				//TODO Fin AT-1308

				/*
				 * El XML para los campos puede ser un poco largo as� que hay
				 * una versi�n con StringBuffer (m�s rapida pero menos legible)
				 * y la versi�n con String (m�s lenta, pero legible).
				 */

				//XMLCampos = XMLCampos + "\n<" + llave + ">" + valor + "</" +
				// llave + ">";
				/*
				 * TODO: CR11267 esto fue agregado para solucionar el problema
				 * que ocurrio con las Acitvidades instanciadas previas al
				 * montaje del nuevo FDL (es decir, para compatibilidad "hacia
				 * atras" Existe una propiedad en ConfigST.properties:
				 * INSTANCIAS_PI_ANTERIORES, que sera "true" mientras existan
				 * actividades instanciadas con la version 1 del FDL de SOLTEC
				 * Cuando no hayan mas acitvidades instanciadas, debera cambiar
				 * el valor: INSTANCIAS_PI_ANTERIORES=false **** SOLO PARA
				 * SOLTEC (idAplicacion = 2)*****
				 * 
				 * descripcion de la solucion: debido a que "quedaban" con las
				 * condiciones anteriores, si la actividad que llama al workflow
				 * es PI, debera harcodearse SOLUCION_STB=N, para que salga del
				 * subflujo... Esto no es necesario con las nuevas peticiones
				 */

				if (instanciasPiAnteriores.toLowerCase().equals("true")
						&& actividad.getIdAplicacion().longValue() == 2) {
					//si la actividad que esta llamando al worfkflow es Planta
					// Interna, (act_id = 1055, que tiene cod="Director de
					// Flujos.Solucion_STB.PI_STB.Planta_Interna_STB"), pongo
					// SOLUCION_STB="N"
					if (llave
							.equals(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_STB.solucion_stb)
							&& actividad
									.getCodigoActividad()
									.trim()
									.equals(
											"Director de Flujos.Solucion_STB.PI_STB.Planta_Interna_STB")) {
						//guardo el verdadero destino en la tabla
						// INCIDENCIA_DESDE_PI
						try {
							// si existe, actualizo destino
							IncidenciasDesdePILocalHome incidenciasDesdePILocalHome = (IncidenciasDesdePILocalHome) HomeFactory
									.getHome(IncidenciasDesdePILocalHome.JNDI_NAME);
							IncidenciasDesdePILocal incidencia = null;

							try {
								incidencia = incidenciasDesdePILocalHome
										.findByKey(actividad
												.getNumeroPeticion());
							} catch (FinderException e2) {
								//si no existe, creo un nuevo registro
								incidencia = incidenciasDesdePILocalHome
										.create(actividad.getNumeroPeticion());
							}
							//guardo el destino verdadero, en la tabla
							// incidencia desde PI
							incidencia.setDestino(valor);
						} catch (NamingException e1) {
							log.debug("error en WfSessionBean");
							e1.printStackTrace();
						} catch (CreateException e3) {
							// TODO Auto-generated catch block
							log
									.debug("error al crear un nuevo registro en  INCIDENCIA_DESDE_PI");
							e3.printStackTrace();
						}
						//seteo el valor N a SOLUCION_STB, de esta forma, sale
						// del Subflujo de Planta interna
						valor = "N";
					}
					// fin CR11267

					XMLBuffer.append("\n<");
					XMLBuffer.append(llave);
					XMLBuffer.append(">");
					XMLBuffer.append(valor);
					XMLBuffer.append("</");
					XMLBuffer.append(llave);
					XMLBuffer.append(">");
				} else {
					//sigue igual que antes del nuevo FDL
					XMLBuffer.append("\n<");
					XMLBuffer.append(llave);
					XMLBuffer.append(">");
					XMLBuffer.append(valor);
					XMLBuffer.append("</");
					XMLBuffer.append(llave);
					XMLBuffer.append(">");
				}
				//fin CR11267
			}

			XMLCampos = XMLBuffer.toString();
		} catch (NullPointerException e) {
			//Si no habia datos, no los incluimos en el mensaje
		}

		String mensajeRespuesta = ""
				+ "<WfMessage>"
				+ "\n<WfMessageHeader><ResponseRequired>Yes</ResponseRequired></WfMessageHeader>"
				+ "\n<ActivityImplInvokeResponse>" + "\n<ActImplCorrelID>" +
				// Copiar aqui el "ActImplCorrelID" del mensaje enviado
				// "RUEAAAABAINAgwAAAAAAAAAAAAAABAAAAAEAhsAAAAAAAAAAAAAAAAAEQQAAAAEAg0CEAAAAAAAAAABF"
				// +
				idRespuesta + "</ActImplCorrelID>"
				+ "\n<ProgramRC>0</ProgramRC>" + "\n<ProgramOutputData>"
				+ "\n<" + nombreEstructuraDatos + ">" + XMLCampos + "\n</"
				+ nombreEstructuraDatos + ">" + "\n</ProgramOutputData>"
				+ "\n</ActivityImplInvokeResponse>" + "\n</WfMessage>";

		// Responder. Se comenta para el Logger.-
		if (log.isDebugEnabled())
			log
					.debug("Enviando Mensaje a WF (Peticion, Instancia) = "
							+ "(" + actividad.getNumeroPeticion() + ","
							+ idRespuesta + ")\n");

		Date iniDate = new Date();
		//Se establece de donde procede el flujo para el env�o del mensaje
		String source = STConfig.getVariable("ESTRUCTURA_DATOS").equals(nombreEstructuraDatos)? WFSessionLocal.FLUJO_SOLTEC : 
					VpistbbaConfig.getVariable("ESTRUCTURA_DATOS").equals(nombreEstructuraDatos)? WFSessionLocal.FLUJO_VPI :"";
		
		enviarMensaje(mensajeRespuesta, actividad.getNumeroPeticion(), source);
		log.info("\nPublica Envio Mensaje [" + actividad.getNumeroPeticion()
				+ "," + ((new Date()).getTime() - iniDate.getTime()) + "]");

		//log.info("Mensaje Enviado a WF (Peticion, Instancia) = " +
		//	"(" + actividad.getIdInstanciaProceso() + "," + idRespuesta + ")\n");
	}

	//	private void enviarBorrar(IActividad actividad) {
	//
	//		//StringBuffer XMLBuffer = new StringBuffer();
	//		//String XMLCampos = "";
	//		String idRespuesta = actividad.getIdInstanciaProceso();
	//		String nombreEstructuraDatos = actividad.getNombreEstructuraDatos();
	//
	//		String mensajeRespuesta =
	//			""
	//				+ "<WfMessage>"
	//				+
	// "\n<WfMessageHeader><ResponseRequired>Yes</ResponseRequired></WfMessageHeader>"
	//				+ "\n<ProcessInstanceTerminate>"
	//				+ "\n<ProcInstName>"
	//				+ idRespuesta
	//				+ "\n</ProcInstName>"
	//				+ "\n</ProcessInstanceTerminate>"
	//				+ "\n</WfMessage>";
	//
	//		// Responder
	//		//log.debug("Voy a enviar mensaje de termino ...");
	//		log.info(
	//			"Enviando Mensaje Termino a WF (Peticion, Instancia) = "
	//				+ "("
	//				+ actividad.getIdProceso()
	//				+ ","
	//				+ idRespuesta
	//				+ ")\n");
	//		enviarMensaje(mensajeRespuesta);
	//		//log.info("Mensaje TerminoEnviado a WF(Peticion, Instancia) = " +
	//		// "(" + actividad.getIdProceso() + "," + idRespuesta + ")\n");
	//
	//	}

	private void enviarMensaje(String text, Long numeroPeticion, String source) {

		text = text.replaceAll("%2B", "+");
		text = text.replaceAll("%2F", "/");

		log.info("Envio Mensaje WF (1 parametro).");

		if (log.isDebugEnabled())
			log.debug("Enviando Mensaje a WF para Peticion, \n***\n" + text
					+ "\n***");
		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		try {
			// Create a connection
			createQueueConnectionObjects(numeroPeticion,source);
			qc = qcf.createQueueConnection();
			qc.start();

			// Create a session.
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create a QueueSender
			sender = session.createSender(sendQueue);

			// Create a message to send to the queue...
			TextMessage message = session.createTextMessage(text);
			message.setJMSReplyTo(this.errorResponseQueue);

			// Set CorrelationID from the input message and send
			//message.setJMSCorrelationID(corrid);
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)
			sender.close();
			session.close();
			qc.close();
			qc = null;

		} catch (JMSException e) {
			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
			Exception le = e.getLinkedException();
		} catch (Exception e) {
			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
		} finally {
			// Ensure that the Connection always gets closed
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException e) {
				}
			}
		}
	}

	public void enviarMensaje(String queue, String text, Long numeroPeticion, String origen) {

		text = text.replaceAll("%2B", "+");
		text = text.replaceAll("%2F", "/");

		log.warn("Envio Mensaje WF (2 parametros).");

		if (log.isDebugEnabled())
			log.debug("Enviando Mensaje a WF para Peticion, \n***\n" + text
					+ "\n***");
		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		try {
			// Create a connection
			createQueueConnectionObjects(numeroPeticion,origen);
			qc = qcf.createQueueConnection();
			qc.start();

			// Create a session.
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create Queue
			sendQueueVariable = (Queue) this.getJMS(queue);

			// Create a QueueSender
			sender = session.createSender(sendQueueVariable);

			// Create a message to send to the queue...
			TextMessage message = session.createTextMessage(text);
			message.setJMSReplyTo(this.errorResponseQueue);

			// Set CorrelationID from the input message and send
			//message.setJMSCorrelationID(corrid);
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)
			sender.close();
			session.close();
			qc.close();
			qc = null;

		} catch (JMSException e) {
			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
			Exception le = e.getLinkedException();
		} catch (Exception e) {
			log.error("OJO: NO SE PUDO ENVIAR MENSAJE A WF.", e);
		} finally {
			// Ensure that the Connection always gets closed
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException e) {
				}
			}
		}
	}
	
	private Object getJMS(String jmsRef) throws NamingException {
		if (initialContext != null) {
			Object nsObject = initialContext.lookup(new StringBuffer(
					"java:comp/env/").append(jmsRef).toString());
			return nsObject;
		} else {
			throw new NamingException("HomeFactory: no InitialContext");
		}
	}

	@Override
	public EJBHome getEJBHome() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Handle getHandle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrimaryKey() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIdentical(EJBObject obj) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove() throws RemoteException, RemoveException {
		// TODO Auto-generated method stub
		
	}
}