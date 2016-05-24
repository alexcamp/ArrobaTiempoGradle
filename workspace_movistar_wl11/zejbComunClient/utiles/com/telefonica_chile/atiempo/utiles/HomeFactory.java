/*
 * 
 * @author amartoq
 * 
 * Created on Aug 10, 2005
 * Copyright (C) 2005 www.xperience.cl www.interplanet.cl
 */
package com.telefonica_chile.atiempo.utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * <P>
 * Factory para localizar Enterprise Java Beans de los proyectos atiempo.
 * </P> 
 * 
 * <P>
 * Mantiene en un punto centralizado todo el codigo relativo a la ubicacion
 * de EJB's en servidores locales o remotos.
 * </P>
 * 
 * <P>
 * Para utilizar un EJB en el mismo servidor, se debe agregar a la clase LocalHome un String
 * con el nombre JNDI_NAME con el cual se registra. Luego de esto, utilizar la siguiente receta:
 * </P>
 * 
 * <code>
 * EJBLocalHome ejbLocalHome = (EJBLocalHome) HomeFactory.getHome(EJBLocalHome.JNDI_NAME);
 * EJBLocal ejbLocal = ejbHome.create();
 * </code>
 * 
 * <P>
 * Para utilizar un EJB en un servidor remoto, se debe utilizar alguna de las constantes HomeFactory.REMOTO_*
 * , asi como registrar en la clase Home un String con el nombre JNDI_NAME con el cual se registra.
 * Luego de esto, utilizar la siguiente receta de ejemplo:
 * </P>
 * 
 * <code>
 * EJBHome ejbHome = (EJBHome) HomeFactory.getHome(EJBHome.JNDI_NAME, HomeFactory.REMOTO_BANDEJA);
 * EJB ejb = ejbHome.create();
 * </code>
 * 
 * 
 * @author Aldrin Martoq <a href="amartoq@interplanet.cl">amartoq@interplanet.cl</a> 
 * @since Created on Aug 10, 2005 @ 12:31:34 PM
 */
public class HomeFactory {

	//
	// public constans
	//

	/**
	 * Constante a usar para el servidor remoto donde esta el proyecto zearBandeja.
	 */
	//cambio leo
	public static final String REMOTO_BANDEJA_VPI=ApplicationConfig.APP_BANDEJA_VPI;
	public static final String REMOTO_BANDEJA_ST=ApplicationConfig.APP_BANDEJA_ST;
	//
	
	public static final String REMOTO_BANDEJA = ApplicationConfig.APP_BANDEJA;

	/**
	 * Constante a usar para el servidor remoto donde esta el proyecto zearVpiStbBa.
	 */
	public static final String REMOTO_VPISTBBA = ApplicationConfig.APP_VPISTBBA;

	/**
	 * Constante a usar para el servidor remoto donde esta el proyecto zearVpiSSDD.
	 */
	public static final String REMOTO_VPISSDD = ApplicationConfig.APP_VPISSDD;

	/**
	 * Constante a usar para el servidor remoto donde esta el proyecto zearSolTec.
	 */
	public static final String REMOTO_ATST = ApplicationConfig.APP_ATST;

	/**
	 * Constante a usar para el servidor remoto donde esta el proyecto zearWorkflow.
	 */
	public static final String REMOTO_WORKFLOW = ApplicationConfig.APP_WORKFLOW;

	//
	// private constants
	//

	/**
	 * prefijo para busquedas locales.
	 */
	private static final String _local = "local:ejb/";

	//
	// private fields
	//

	/**
	 * Obtencion de logger
	 */
	protected static Logger log = LoggerFactory.getLogger(HomeFactory.class);

	/**
	 * Profundidad del stack. Sirve para mostrar en el logger la clase que nos invoco.
	 */
	private static int _getcalldefaultdepth = 3;

	/**
	 * Contexto utilizado para realizar el lookup.
	 */
	private static InitialContext initialContext = null;

	//
	// init code
	//

	static {
		init();
	}

	/**
	 * Inicializa el HomeFactory.
	 */
	static synchronized void init() {
		if (initialContext == null) {
			log.info("### Inicializando HomeFactory ###");
			log.info("1. Creando contexto para coneccion JNDI.");
			try {
				initialContext = new InitialContext();
				log.debug("Contexto creado.");
			} catch (NamingException ne) {
				log.error("ERROR AL CREAR CONTEXTO INICIAL.", ne);
				log.fatal(
					"############# LA APLICACION NO FUNCIONARA ############# ");
			}
			log.info("### HomeFactory Inicializado ###");
		}
	}

	//
	// public methods
	//

	/**
	 * Obtiene el home desde un servidor remoto.
	 * 
	 * Se debe configurar el archivo de properties con el nombre de
	 * host y puerto donde ubicar la interfaz corba iiop-rmi del servidor.
	 * 
	 * @param ejbRef
	 * @param perfil
	 * @return
	 * @throws NamingException
	 */
	/*public static Object getHome(String ejbRef, String perfil)
		throws NamingException {
		String ref = null;

//		if ( log.isDebugEnabled() )
//			log.debug("Buscando ejb remoto: ejbRef=[" + ejbRef + "] perfil=[" + perfil + "] .");

		// 1. primero verificamos el perfil. si es nulo, esto es un error
		if (null == perfil) {
			String msg =
				"Perfil incorrecto: ejbRef=["
					+ ejbRef
					+ "] perfil=["
					+ perfil
					+ "]";
			NamingException ne = new NamingException(msg);

			log.error(msg, ne);
			log.error(
				"La clase incorrecta es: className=["
					+ getCallerClassName(_getcalldefaultdepth)
					+ "]",
				ne);

			throw ne;
		}

		// 2. ahora buscamos el prefijo para el lookup jndi en el archivo de propiedades. Si no se encuentra, tambien es un error.
		String base = null;
		try {
			base = ApplicationConfig.getNameSpace(perfil);
		} catch (Exception e) {
			String msg =
				"No se ha encontrado el perfil especificado en el archivo de propiedades:"
					+ " ejbRef=["
					+ ejbRef
					+ "]"
					+ " perfil=["
					+ perfil
					+ "]";
			log.error(msg, e);
			throw new NamingException(msg);
		}

		// 3. intentamos hacer el lookup. Si algo falla, primero anotamos en el log antes de re-lanzar la NamingException.
		try {
			ref = base + ejbRef;
			Object object =
				javax.rmi.PortableRemoteObject.narrow(
					lookup(ref),
					EJBHome.class);
			return object;
		} catch (NamingException ne) {
			log.error(
				"Error buscando Home remoto: ejbRef=["
					+ ejbRef
					+ "] perfil=["
					+ perfil
					+ "]", ne);
			log.error("La referencia buscada es: ref=[" + ref + "]");
			log.error(
				"La clase incorrecta es: className=["
					+ getCallerClassName(_getcalldefaultdepth)
					+ "]",
				ne);
			throw ne;
		}
	}*/

	/**
	 * Obtiene el Home local.
	 * 
	 * @param ejbRef
	 * @return
	 * @throws NamingException
	 */
	public static Object getHome(String ejbRef) throws NamingException {
		String ref = null;
		Object object = null;
//		if ( log.isDebugEnabled() )
//			log.debug("Buscando ejb local: ejbRef=[" + ejbRef + "] .");

		// 1. armo la referencia final, concatenando el prefijo con el nombre dado
		ref = _local + ejbRef;

		// 2. intentamos hacer el lookup. Si algo falla, primero anotamos en el log antes de re-lanzar la NamingException.
		try {
			object = lookup(ref);
		} catch (NamingException ne) {
			log.error("Error buscando Home local: ejbRef=[" + ejbRef + "]");
			log.error(
				"La clase incorrecta es: className=["
					+ getCallerClassName(_getcalldefaultdepth)
					+ "]",
				ne);
			throw ne;
		}

		if (object instanceof EJBLocalHome) {
			return object;
		} else {
			String msg =
				"Se ha buscado un HOME de un EJB remoto incorrectamente. utilice getHome(ref, SERVIDOR);";
			NamingException ne = new NamingException(msg);

			log.fatal(msg);
			log.fatal("La referencia buscada es: ref=[" + ref + "]");
			log.fatal(
				"La clase incorrecta es: className=["
					+ getCallerClassName(_getcalldefaultdepth)
					+ "]",
				ne);
			throw ne;
		}
	}

	//
	// private methods
	//

	/**
	 * Gets the caller class name from the stack.
	 *
	 * @param depth
	 *            stack depth
	 * @return the caller name of the calling class, or a blank String if it
	 *         could not be retreived.
	 */
	static String getCallerClassName(int depth) {
		StringWriter sw = new StringWriter();
		new Throwable().printStackTrace(new PrintWriter(sw));
		BufferedReader sr =
			new BufferedReader(new StringReader(sw.getBuffer().toString()));
		String ret = "";

		try {
			int lineCount = 0;
			String line;

			while ((line = sr.readLine()) != null) {
				if (lineCount == depth) {
					int ini = line.indexOf("at ") + 3;
					int end = line.indexOf("(");
					ret = line.substring(ini, end);
				}
				lineCount++;
			}
		} catch (IOException ioe) {
		}

		int initBegin = ret.indexOf(".<init>");
		if (initBegin != -1) {
			ret = ret.substring(0, initBegin);
		}

		return ret;
	}

	/**
	 * Realiza la busqueda de un EJB Home en base al String ref JNDI.
	 * 
	 * @param ref nombre completo JNDI donde se encuentra la aplicacion.
	 * @return el objeto encontrado.
	 * @throws NamingException si el contexto inicial no pudo ser creado.
	 */
	static Object lookup(String ref) throws NamingException {
		if (initialContext != null) {
//			if ( log.isDebugEnabled() )
//				log.debug("Referencia en Lookup [" + ref + "]");
			try {
				Object o = initialContext.lookup(ref);
				return o;
			} catch (Exception e) {
				log.debug("Referencia en Lookup [" + ref + "]", e);
				throw new NamingException("HomeFactory: no hay InitialContext. REINICIE LA APLICACION");
			}
		} else {
			throw new NamingException("HomeFactory: no hay InitialContext. REINICIE LA APLICACION");
		}
	}
}
