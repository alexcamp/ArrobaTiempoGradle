package com.tecnonautica.mqwf;

import java.beans.PropertyVetoException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.Local;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.ibm.workflow.api.Agent;
import com.ibm.workflow.api.ContainerElement;
import com.ibm.workflow.api.ExecutionService;
import com.ibm.workflow.api.FmcException;
import com.ibm.workflow.api.ProcessInstance;
import com.ibm.workflow.api.ProcessTemplate;
import com.ibm.workflow.api.ReadOnlyContainer;
import com.ibm.workflow.api.ReadWriteContainer;
import com.ibm.workflow.api.WorkItem;
import com.ibm.workflow.api.WorkList;
import com.ibm.workflow.api.ServicePackage.AbsenceIndicator;
import com.ibm.workflow.api.ServicePackage.SessionMode;
import com.telefonica_chile.atiempo.actividades.IProceso;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.javaWf.WFSession;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author Ricardo Ramos
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


public class MQWFBean{

	private Agent agent;
	private ExecutionService service;
	private WorkList[] worklists;
	private WorkItem[] workitems;
	private ProcessTemplate[] procesos;

	private WorkItem workItem = null;
	private ReadOnlyContainer inctnr = null;
	private ReadWriteContainer outctnr = null;
	private String nameProcesso = "";

	// Datos para la conexion y Asignacion de Usuarios
	private String userConnect = "";
	private String passConnect = "";
	private String userDestino = "";
	private Hashtable campos = new Hashtable();
	private String idInstancia = "";

	private final int MAX_QUERY_WF = 10; // Intentos para el QueryWorkItems
	private final int MAX_CONNECT_WF = 3; // Intentos para conectarse al WF 
	private final int TIME_WAIT = 500;
	// Tiempo de Espera entre iteraciones de QueryWorkItems

	private Hashtable Tiempos = new Hashtable();
	private Date fIni;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/* 
	 * Metodo que imprime los Tiempos de accesoa l WF. 
	 */
	public void imprime() {
		Enumeration e = Tiempos.keys();
		String llave, str = "";
		while (e.hasMoreElements()) {
			llave = (String) e.nextElement();
			str += "PROFILE:" + llave + " = " + Tiempos.get(llave) + "\n";
		}
		log.info("\nInstancia (" + idInstancia + ")\n" + str);
	}

	/*
	 * Actualiza los valores en la Tabla Tiempos
	 */
	private void profile(Date inicio, Date fin, String metodo) {
		int valor = 0;
		try {
			if (Tiempos.get(metodo) != null) {
				valor = ((Integer) Tiempos.get(metodo)).intValue();
			}
			Long Linicio = new Long(inicio.getTime());
			Long Ltermino = new Long(fin.getTime());
			Long Ldif = new Long(Ltermino.intValue() - Linicio.intValue());
			valor += Ldif.intValue();
		} catch (Exception ex) {
		}

		Tiempos.put(metodo, new Integer(valor));
	}

	// Seteo de los Datos de Conexion
	public void setUserConnect(String str) {
		userConnect = str;
	}

	public void setPassConnect(String str) {
		passConnect = str;
	}

	public void setUserDestino(String str) {
		userDestino = str;
	}

	public void setCampos(String key, String value) {
		campos.put(key, value);
	}

	public void setID(String str) {
		idInstancia = str;
	}

	public void setProcessName(String str) {
		nameProcesso = str;
	}

	/*
	 * Genera una Conexion al WorkFlow con el UserConnect y PasConnect.
	 */
	public void conectar() throws TnMQWFException {
		fIni = new Date();
		for (int i = 0; i < MAX_CONNECT_WF; i++) { // 3 INTENTOS
			try {
				agent = new Agent();
				agent.setLocator(Agent.LOC_LOCATOR);
				agent.setName("AGENT");
				service = agent.locate("", "");
				service.logon2(
					userConnect,
					passConnect,
					SessionMode.DEFAULT,
					AbsenceIndicator.LEAVE);
				profile(fIni, new Date(), "Conectar(" + userConnect + ")");
			} catch (PropertyVetoException e) {
				throw new TnMQWFException(
					"PropertyVetoException al logear en el WF :"
						+ e.getMessage());
			} catch (FmcException e) {
				throw new TnMQWFException(
					"FmcException al logear en el WF :" + e.getMessage());
			}
		}

	}

	public HashMap getContainer() {
		String llave = "", valor = "";
		HashMap container = new HashMap();
		try {
			if (inctnr != null) {
				for (int i = 0; i < inctnr.structMembers().length; i++) {
					//llave = ((ContainerElement)outctnr.structMembers()[i]).fullName();
					llave =
						((ContainerElement) inctnr.structMembers()[i])
							.fullName();
					try {
						valor =
							new String(inctnr.getElement(llave).getString());
					} catch (Exception e) {
						valor = "";
					}
					container.put(llave, valor);
				}
			} else {
				log.warn("input container es nulo");
			}
		} catch (Exception e) {
			// log.debug( e.P );
			e.printStackTrace();
		}
		return container;
	}

	public void getWorkLists() {
		try {
			if (isLoggedOn()) {
				worklists = service.queryWorkLists();
			}
		} catch (Exception e) {
			log.warn("Error en getWorkLists", e);
		}
	}

	/*
	 * Retorna true si la conexion esta activa
	 */
	public boolean isLoggedOn() {
		try {
			if (service != null)
				return service.isLoggedOn();
		} catch (Exception e) {
		}
		return false;
	}

	public void setStringElementValue(String elementname, String valor) {
		try {
			if (outctnr != null)
				outctnr.setString(elementname, valor);
		} catch (Exception e) {
			log.warn("Error en SetStringElementValue", e);
		}

	}

	/*
	 * Para una instancia a su siguiente actividad.
	 */
	public void avanzarInstancia() {
		try {
			if (workItem != null && isLoggedOn()) {
				fIni = new Date();
				inctnr = workItem.checkOut();
				outctnr = inctnr.asReadWriteContainer();

				// Seteamos los Campos para la instancia
				Enumeration j = campos.keys();
				while (j.hasMoreElements()) {
					String llave = (String) j.nextElement();
					outctnr.setString(llave, (String) campos.get(llave));
				}

				// Hacemos el CheckIn para Liberar la Instancia.
				workItem.checkIn(outctnr, 0);
				profile(fIni, new Date(), "Conectar(" + userConnect + ")");
			}
		} catch (Exception e) {
			log.warn("Error en AvanzarActividad (" + idInstancia + ")", e);
		}
	}

	/*
	 * Cancela el WorkItem Actual == La deja disponible.
	 */
	public void cancelarInstancia() {
		try {
			if (workItem != null && isLoggedOn()) {
				workItem.cancelCheckOut();
				workItem = null;
				inctnr = null;
				outctnr = null;
			}
		} catch (Exception e) {
			log.warn(e);
		}

	}

	/*
	 * Reasigna una Instancia al Usuario Destino.
	 * Si no puede reasignarla trata con el Usuario Defecto.
	 */
	public String transferInstancia() {
		try {
			if (workItem != null && isLoggedOn()) {
				workItem.transfer(userDestino);
				return (userDestino);
			}
		} catch (Exception e) {
			log.warn(
				"No pude Transferir Instancia ("
					+ idInstancia
					+ ") a '"
					+ userDestino
					+ "'",
				e);
		}

		return "WF-ERROR";
	}

	/*
	 * Avanza una Instancia a la DFActividad Siguiente. 
	 */
	public void terminarInstancia() {
		try {
			if (workItem != null && isLoggedOn()) {
				workItem.checkIn(outctnr, 0);
			}
		} catch (Exception e) {
			log.warn("Error en TerminarActividad (" + idInstancia + ")", e);
		}
	}

	/*
	 * Obtiene los Process Template del WF.
	 */
	public void getProcessTemplates() {
		try {
			if (isLoggedOn())
				procesos =
					service.queryProcessTemplates(
						"",
						"NAME DESC",
						new Integer(100));

			//procesos[0].inContainer();

		} catch (Exception e) {
			log.warn("Error en getProcessTemplates ", e);
		}

	}

	public void actualizaContainerTemplate(String template) {

		try {
			getProcessTemplates();

			for (int i = 0; procesos != null && i < procesos.length; i++) {
				if (procesos[i].name().equalsIgnoreCase(template)) {
					inctnr =
						procesos[i].initialInContainer().asReadOnlyContainer();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/*
	 * Obtiene los datos de los procesos que corren
	 */

	public Vector getWorkItemNames() {
		try {
			WorkList worklist = worklists[0]; //worklists[pos];
			workitems = worklist.queryWorkItems();
			Vector vctTareas = new Vector();
			for (int ndx = 0; ndx < workitems.length; ndx++) {
				log.debug("processInstanceName() : " + workitems[ndx].processInstanceName());
				vctTareas.addElement(workitems[ndx].processInstanceName());
			}
			return vctTareas;
		} catch (Exception e) {
			//log.warn(e);
			return null;
		}

	}

	/*
	 * Obtiene los nombres de los process templates
	 */

	public Vector getProcessTemplateNames() {
		Vector processTemplateNames = new Vector();

		if (procesos == null) {
			getProcessTemplates();
		}

		for (int i = 0; procesos != null && i < procesos.length; i++) {
			try {
				processTemplateNames.add(procesos[i].name());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return processTemplateNames;

	}

	/*
	 * Desconecta la Session del WF.
	 */
	public void desconectar() throws TnMQWFException {
		try {
			service.logoff();
		} catch (FmcException e) {
			throw new TnMQWFException("FmcException al desconectar del WF :" + e.getMessage());
		} finally {
			workItem = null;
			inctnr = null;
			outctnr = null;
		}
	}

	/*
	 * Metodo que elimina una Instancia del WF.
	 */
	public void borrarInstancia() {
		try {
			if (workItem != null && isLoggedOn())
				workItem.forceFinish();
		} catch (Exception e) {
			log.warn("Error en borrarActividad (" + idInstancia + ")", e);
		}
	}

	/*
	 * Metodo que busca una Instancia en el WF.
	 * Realiza la consulta MAX_QUERY_WF veces.
	 */
	// CR 25136, AT-2157 - Se comenta Metodo al no ser usuado - Germ�n P.
//	public HashMap getInstancia() {
//		try {
//			if (!isLoggedOn()) {
//				return null;
//			}
//
//			fIni = new Date();
//			int ite = 0;
//			workitems = null;
//			workItem = null;
//			while (++ite < MAX_QUERY_WF
//				&& (workitems == null || workitems.length == 0)) {
//				workitems =
//					service.queryWorkItems(
//						"PROCESS_NAME='" + idInstancia + "'",
//						"PROCESS_NAME ASC",
//						null);
//				if (workitems == null || workitems.length == 0)
//					Thread.sleep(TIME_WAIT);
//			}
//
//			HashMap aux = new HashMap();
//			for (int ndx = 0; ndx < workitems.length; ndx++) {
//				if (!idInstancia.equals(workitems[ndx].processInstanceName()))
//					continue;
//				// Encontre el ID
//				aux.put("ID_RECLAMO", idInstancia);
//				aux.put("ACTIVIDAD", workitems[ndx].name());
//				aux.put("USUARIO", workitems[ndx].owner());
//				workItem = workitems[ndx];
//				inctnr = workItem.inContainer();
//				profile(fIni, new Date(), "GetInstancia(" + userConnect + ")");
//				return aux;
//			}
//
//			return null;
//		} catch (Exception e) {
//			log.debug("Error en getInstancia: (" + idInstancia + ")");
//			return null;
//		}
//
//	}

	/*
	 * Obtiene el Listado de Todas Las Instancias del Flujo.
	 */
	public Vector getListaInstancia() {
		try {
			if (!isLoggedOn())
				return null;

			// Obtengo los WorkList
			getWorkLists();
			WorkList worklist = worklists[0]; //worklists[pos];
			workitems = worklist.queryWorkItems();
			//workitems = service.queryWorkItems("", "PROCESS_NAME ASC", null);
			Vector v = new Vector();
			for (int ndx = 0; ndx < workitems.length; ndx++) {
				HashMap aux = new HashMap();
				aux.put("ID_RECLAMO", workitems[ndx].processInstanceName());
				aux.put("ACTIVIDAD", workitems[ndx].name());
				aux.put("USUARIO", workitems[ndx].owner());
				v.add(aux);
			}

			return v;
		} catch (Exception e) {
			log.warn("Error en getInstancia", e);
			return null;
		}

	}

	/*
	 * Metodo que crea una instancia en el WF
	 */
	public void crearInstancia() {

		try {
			fIni = new Date();
			// Obtenemos el Process Template.
			getProcessTemplates();
			// Creo la Instancia en el Workflow
			for (int i = 0; procesos != null && i < procesos.length; i++) {
				if (!procesos[i].name().equals(nameProcesso))
					continue;
				ReadWriteContainer contenedor =
					procesos[i].initialInContainer();
				Enumeration j = campos.keys();
				while (j.hasMoreElements()) {
					String llave = (String) j.nextElement();
					contenedor.setString(llave, (String) campos.get(llave));
				}

				ProcessInstance pr =
					procesos[i].createAndStartInstance3(
						idInstancia,
						"",
						"",
						contenedor,
						true,
						null);
				pr.refresh();
				procesos[i].refresh();
				profile(
					fIni,
					new Date(),
					"CrearInstancia(" + userConnect + ")");
			}
		} catch (Exception e) {
			String aux = "";
			try { 
				Enumeration j = campos.keys();
				while (j.hasMoreElements()) {
					String llave = (String) j.nextElement();
					aux += "'" + llave + "' = '" + (String) campos.get(llave) + "'\n"; 
				}
			} catch (Exception e1) {
				log.debug("Error al Tratar de leer Contenedor", e1);
			}

			log.warn("Error en Crear Instancia (" + idInstancia + ").\nContendor:\n" + aux, e);
		}
	}



}
