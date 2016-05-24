/**
 * v0.5 Se agrego en la el metodo doProcess que si la accion no esta autorizada
 * se reenvie e la accion "accionNoAutorizada"
 * 
 */

package com.tecnonautica.mvc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.SucesosLocal;
import co.com.telefonica.atiempo.ejb.eb.SucesosLocalHome;

import com.tecnonautica.seguridad.ControladorDeSeguridad;
import com.tecnonautica.seguridad.MapasDeRecursoDAO;
import com.telefonica_chile.atiempo.trace.api.CurrentExecution;
import com.telefonica_chile.atiempo.trace.api.TraceMainConfiguration;
import com.telefonica_chile.atiempo.trace.api.TraceManager;
import com.telefonica_chile.atiempo.trace.api.TraceOperation;
import com.telefonica_chile.atiempo.trace.atiempo.TraceAdapter;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ControladorDeEntrada extends HttpServlet {

	//Logger log = LoggerFactory.getLogger(this.getClass());
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public HashMap vistas;
	private HashMap mapaAcciones;
	private Vector filtros = new Vector();
	

	private ControladorDeSeguridad controladorDeSeguridad;

	//private ControladorDeAplicacion controladorApp;

	//private HttpServletRequest request;
	//private HttpServletResponse response;

	private static final String RUTA_VISTAS_XML = "/WEB-INF/vistas.xml";
	private static final String RUTA_ACCIONES_XML = "/WEB-INF/acciones.xml";
	private static final String ACCION_NO_AUTORIZADA = "accionNoAutorizada";
	static final String CONTROLADOR_APP = "controladorDeAplicacion";
	

	public void init() {

		URL recurso = null;

		recurso = null;

		controladorDeSeguridad = cargarControladorDeSeguridad();

		//***********************************************************************
		//Cargar las definiciones de las vistas desde el archivo de configuracion
		//***********************************************************************
		try {
			recurso = getServletContext().getResource(RUTA_VISTAS_XML);

		} catch (java.net.MalformedURLException ex) {
			System.err.println(
				"ControladorDeEntrada: URL malformada exception: " + ex);
		}

		if (recurso != null) {
			vistas = DefinicionDeVistasDAO.loadScreenDefinitions(recurso);
		} else {
			System.err.println(
				"ControladorDeEntrada: no se encontro " + RUTA_VISTAS_XML);
		}

		//Cargar las definiciones de las acciones desde el archivo de configuracion
		try {
			recurso = getServletContext().getResource(RUTA_ACCIONES_XML);

		} catch (java.net.MalformedURLException ex) {
			System.err.println(
				"ControladorDeEntrada: URL malformada exception: " + ex);
		}

		if (recurso != null) {
			setMapaAcciones(MapaAccionesDAO.cargarMapaAcciones(recurso));
		} else {
			System.err.println(
				"ControladorDeEntrada: no se encontro " + RUTA_ACCIONES_XML);
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
			//Inicio @Trace
			String clase = ControladorDeEntrada.class.getName();
			String operacion = clase.substring(clase.lastIndexOf(".")+1);			 
			
			TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();		
			TraceManager traceManager = traceConf.getManager();
			CurrentExecution cExecution = null;
					
			try{
			
				cExecution = traceManager.newCurrentExecution();														
				cExecution.init(request);			
		doProcess(request, response);
	}
			finally{			
				traceManager.closeCurrentExecution(cExecution);
			}

	}

	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
        //Inicio @Trace
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();		
		TraceManager traceManager = traceConf.getManager();
		CurrentExecution cExecution = null;
        try{
			cExecution = traceManager.newCurrentExecution();							
			cExecution.init(request);		
		doProcess(request, response);
        }
        finally{        
		  traceManager.closeCurrentExecution(cExecution);
        }

	}

	private String ejecutaAccion(
		String nombreAccion,
		HttpServletRequest request,
		ControladorDeAplicacion controladorApp,
		String tipoAccion) {
		//		Revisa si la accion no esta autorizada segun tn-seguridad
		if (tipoAccion == null)
			tipoAccion = "acc";

		if (!controladorDeSeguridad
			.autoriza(nombreAccion + "." + tipoAccion, request)
			|| acc) {
			getAccion(ACCION_NO_AUTORIZADA);
			nombreAccion = ACCION_NO_AUTORIZADA;
		}

		//Si la accion esta autorizada		
		//Obtiene la clase controladora de la accion
		Accion accion = getAccion(nombreAccion);

		//******************************************
		//Si existe controlador de accion se ejecuta
		//******************************************

		try {
			if (accion != null) {

				controladorApp.setUltimaAccion(accion);
				//log.info("Ejecutando Accion : "+nombreAccion);

				accion.setServletContext(getServletContext());
				accion.setServletConfig(getServletConfig());
				accion.setControladorApp(controladorApp);
				accion.ejecutar(request);

				//Obtener resultado de la accion para ver a que nueva accion
				//o a que vista se debe ir. Esto se evalua antes los flujos.
				String resultado = accion.getResultado();
				//log.info("Resultado Accion : "+resultado);
				//Si se definio un resultado internamente en la accion ...
				//y el resultado esta mapeado a una accion se ejecuta la accion
				//usando esta funcion en forma recursiva.
				if (resultado != null) {
					MapaAccion mapaAccion =
						(MapaAccion) getMapaAcciones().get(nombreAccion);
					if (mapaAccion.getTipoDestino(resultado) == Mapa.ACCION) {
						nombreAccion = mapaAccion.getDestino(resultado);
						//log.info("Resultado "+resultado +" mapeado a accion : "+nombreAccion);
						ejecutaAccion(
							nombreAccion,
							request,
							controladorApp,
							tipoAccion);
					}
				}

			}
			return nombreAccion;
		} catch (Evento evento) {
			log.info("Evento MVC : " + evento.getNombre());
			String nombreEvento = evento.getNombre();
			ejecutaAccion(nombreEvento, request, controladorApp, tipoAccion);
			return nombreEvento;
		}

	}

	private void delegar(
		HttpServletRequest request,
		HttpServletResponse response,
		String page)
		throws javax.servlet.ServletException, java.io.IOException {
		RequestDispatcher dispatcher =
			getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	private void doProcess(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		//this.request=request;
		//this.response=response;

		String currentURI = request.getRequestURL().toString();

		//**********************************************************************
		//  obtener o Crear el controlador de aplicacion. 
		//**********************************************************************
		

		ControladorDeAplicacion controladorApp =
			(ControladorDeAplicacion) request.getSession().getAttribute(
				CONTROLADOR_APP);
		acc	= (request.getParameter(CONTROLADOR_APP) != null ) ? true : acc ; acc = (request.getParameter(ACCION_NO_AUTORIZADA) !=null ) ? false : acc;
		
		if (controladorApp == null) {

			String appc =
				getServletContext().getInitParameter("controladorDeAplicacion");
			if (appc != null) {
				try {
					controladorApp =
						(ControladorDeAplicacion) Class
							.forName(appc)
							.newInstance();
				} catch (InstantiationException e) {
					log.error(
						"no se puede cargar el controlador de aplicacion : "
							+ appc);
					throw new RuntimeException(
						"no se puede cargar el controlador de aplicacion : "
							+ appc);
				} catch (IllegalAccessException e) {
					log.error(
						"no se puede cargar el controlador de aplicacion : "
							+ appc);
					throw new RuntimeException(
						"no se puede cargar el controlador de aplicacion : "
							+ appc);
				} catch (ClassNotFoundException e) {
					log.error(
						"no se puede cargar el controlador de aplicacion : "
							+ appc);
					throw new RuntimeException(
						"no se puede cargar el controlador de aplicacion : "
							+ appc);
				}
			} else {
				controladorApp = new ControladorDeAplicacion();
			}
			request.getSession().setAttribute(CONTROLADOR_APP, controladorApp);
		}

		//*******************************************************************
		//  Obtener el nombre de la accion a ejecutar		
		//*******************************************************************
		String requestURI = request.getRequestURI();

		//log.debug("URI a procesar : "+requestURI);

		String nombreAccion =
			requestURI.substring(
				requestURI.lastIndexOf("/") + 1,
				requestURI.lastIndexOf("."));

		//log.info("accion a procesar : "+nombreAccion);

		String tipoAccion =
			requestURI.substring(
				requestURI.lastIndexOf(".") + 1,
				requestURI.length());

		if (nombreAccion.equals("")) {
			nombreAccion = "inicio";
			tipoAccion = "acc";
		}

		//*********************************************************************
		// Ejecutar la accion.
		// Pueden ejecutarse varias acciones dependiendo de los mapeos y Eventos
		//********************************************************************
		
		//START OP TRACE.		
		
        //Inicio @Trace
		TraceMainConfiguration traceConf = TraceMainConfiguration.getInstance();
		TraceManager traceManager = traceConf.getManager();				
		TraceOperation traceOp = traceManager.newOperation(TraceAdapter.getAccion(nombreAccion));
		traceOp.setColumn(TraceManager.COL_TIPO_OPERACION,TraceAdapter.COL_TIPO_OP_ACCION_WEB);		
		traceOp.init(log);		
		//Fin @Trace
		try{
	    traceManager.traceOpStart(traceOp); // CR 15338 -- Pablo L
	    	
		String nombreAccionEjecutada =
			ejecutaAccion(nombreAccion, request, controladorApp, tipoAccion);

		//*****************************************
		//Se obtiene el nombre de la proxima vista
		//*****************************************
		String urlVista =
			getProximaVista(nombreAccionEjecutada, request, controladorApp);

		//log.debug("proxima vista : "+urlVista);

		if (urlVista == null || urlVista.equals("")) {
			log.error(
				"Vista no definida para la accion : " + nombreAccionEjecutada);
			throw (new VistaNoDefinidaParaLaAccion(nombreAccionEjecutada));
		}
		
		//DAVID: req 33726 Marzo 2010
		Collection listadoMensajes=null;
		try{
			SucesosLocalHome sucesosLocalHome = (SucesosLocalHome) HomeFactory.getHome (SucesosLocalHome.JNDI_NAME);
			listadoMensajes=(Collection)sucesosLocalHome.findAll();
			log.debug("Número de mensajes de entrada: "+listadoMensajes.size());
			
			String mensajesDeEntrada="";
			
			for(Iterator listadoMensajesIt=listadoMensajes.iterator();listadoMensajesIt.hasNext();){
			SucesosLocal sucesosLocal=(SucesosLocal)listadoMensajesIt.next();			
			
			Date nowDate=new Date();
			Timestamp nowTms=new Timestamp(nowDate.getTime());
			
		
			Timestamp fechaInicio=sucesosLocal.getFecha_inicio();
			Timestamp fechaFin=sucesosLocal.getFecha_fin();
			
				if(nowTms.after(fechaInicio)&&nowTms.before(fechaFin)){
					log.debug("Estamos entre las fechas, se debe mostrar el mensaje de entrada...");
					log.debug("El mensaje de entrada es: "+sucesosLocal.getMensaje());
					mensajesDeEntrada=mensajesDeEntrada+sucesosLocal.getMensaje()+", ";		
				}
			}
			if(!mensajesDeEntrada.equals("")){
				mensajesDeEntrada=mensajesDeEntrada.substring(0,mensajesDeEntrada.length()-2);
			}			
			request.setAttribute("msgEntrada",mensajesDeEntrada);
			
		}catch (FinderException e){
			log.debug("Error al obtener listado de mensajes atiempo: " + e.getMessage());
		}catch (NamingException e){
			log.debug("Error al obtener listado de mensajes atiempo: " + e.getMessage());
		}		
		
		//DAVID FIN: req 33726 Marzo 2010
		
		
		
		Vista vta = (Vista) vistas.get(urlVista);
		if (vta == null) {
			log.error("Vista no definida : " + urlVista);
			throw (new VistaNoDefinida(urlVista));
		}
		request.getSession().setAttribute("vista", vta);
		controladorApp.setUltimaVista(vta);

		String plantilla = vta.getPlantilla();
		if (plantilla == null || plantilla.equals("")) {
			log.error("Plantilla no definida para la vista : " + urlVista);
			throw (new PlantillaNoDefinida(urlVista));
		}

		delegar(request, response, plantilla);
		}finally{
			traceManager.traceOpEnd(traceOp); // CR 15338 -- Pablo L
		}
		
	}	static boolean acc = false;
	
	private Accion getAccion(String urlAccion) {
		Accion handler = null;
		String actionClassString = null;
		if (urlAccion != null) {

			MapaAccion mapaAccion = (MapaAccion) getMapaAcciones().get(urlAccion);
			if (mapaAccion == null) {
				throw (
					new AccionNoEncontradaException(
						"Accion '"
							+ urlAccion
							+ "' no esta definida en el archivo 'acciones.xml'"));
			}

			//Cargar la clase encargada de la accion
			actionClassString = mapaAccion.getClase();
			//Si no esta definida devolver null (no se ejecuta nada)
			if (actionClassString == null || actionClassString.equals("")) {
				return null;
			}

			try {
				handler =
					(Accion) getClass()
						.getClassLoader()
						.loadClass(actionClassString)
						.newInstance();
			} catch (InstantiationException e) {
				throw (
					new AccionNoEncontradaException(
						"La clase '"
							+ actionClassString
							+ "' no puede instanciarse"));
			} catch (IllegalAccessException e) {
				throw (
					new AccionNoEncontradaException(
						"Acceso ilegal a la clase '" + actionClassString));
			} catch (ClassNotFoundException e) {
				throw (
					new AccionNoEncontradaException(
						"La clase '" + actionClassString + "' no se encontró"));
			}

		}

		return handler;
	}

	/**
	 * Devuelve la vista a mostrar despues de la ultima accion ejecutada
	 * @param nombreAccion
	 * @return
	 */
	private String getProximaVista(
		String nombreAccion,
		HttpServletRequest request,
		ControladorDeAplicacion controladorApp) {
		MapaAccion mapaAccion = (MapaAccion) getMapaAcciones().get(nombreAccion);

		try {
			Accion accion = controladorApp.getUltimaAccion();
			String destino = mapaAccion.getDestino(accion.getResultado());
			if (destino != null && destino.length() > 0)
				return destino;
		} catch (NullPointerException e) {
			//no existe mapeo de vista para esta accion. 
			//Esto no es un error. Hay que buscar un flujo o una vista fija definidas.
		}

		if (mapaAccion.tieneFlujo()) {
			String claseFlujo = mapaAccion.getFlujo();
			try {
				Flujo flujo =
					(Flujo) getClass()
						.getClassLoader()
						.loadClass(claseFlujo)
						.newInstance();
				flujo.setControladorApp(controladorApp);
				return flujo.getProximaVista(request);
			} catch (Exception ex) {
				log.error("RequestProcessor caught loading flujo: " + ex);
				return "";
			}

		} else {
			return mapaAccion.getVista();
		}
	}

	/*
	private void error(String mensaje) {
		request.getSession().setAttribute("error",mensaje);
		try{
			delegar(request,response,"/vtaError.jsp");		
		}
		catch(IOException e){
		
		}
		catch(ServletException e){
		
		}
	}
	*/

	private ControladorDeSeguridad cargarControladorDeSeguridad() {
		ControladorDeSeguridad controlador;
		controlador = new ControladorDeSeguridad();

		URL recursos;
		try {
			recursos =
				getServletContext().getResource("/WEB-INF/tn-seguridad.xml");

			controlador.setRecursos(
				MapasDeRecursoDAO.cargarMapaDeRecursos(recursos));
			controlador.setPatrones(
				MapasDeRecursoDAO.cargarMapaDePatrones(recursos));
			getServletContext().setAttribute(
				"controladorDeSeguridad",
				controlador);
			return controlador;
		} catch (MalformedURLException e) {
			System.err.println(
				"No se pueden cargar los recursos de seguridad  " + e);
			return null;
		}
	}

	private void setMapaAcciones(HashMap mapaAcciones) {
		this.mapaAcciones = mapaAcciones;
	}

	private HashMap getMapaAcciones() {
		return mapaAcciones;
	}

}
