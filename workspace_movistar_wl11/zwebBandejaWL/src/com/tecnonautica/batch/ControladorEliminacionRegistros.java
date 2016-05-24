/*
 * Created on Sep 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tecnonautica.batch;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.basicos.StringUtil;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author Marco Alarcón "Dono"
 * @version 1.0 (versión inicial)
 * Servlet controlador del Servicio de eliminación de registros en batch
 * El servlet obtiene los parámetros iniciales del archivo de configuración
 * Es necesario configurar el XML con las tablas a mantener automáticamente, junto con sus parámetros
 * Este servlet debe ser iniciado al arrancar el servidor, después de todos los demás servlets de arranque
 */
public class ControladorEliminacionRegistros extends HttpServlet {
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	private ThreadEliminacionRegistros threadEliminacion = null;
	
	protected static final String CER_DEFAULT_KEY = "config-file";
	protected static final String RUTA_CONFIG_FILE_XML = "/WEB-INF/config-registros.xml";
	
	/**
	 * Inicializa el servlet de control con el nombre y ruta del archivo de configuración
	 * Con esto inicia el thread ejecutor de la lógica
	 * De no existir, se toma el archivo por defecto
	 */
	public void init() throws ServletException {
		super.init();
		log.debug("Iniciando configuración y arranque del Controlador de Eliminación de Registros");
		
		String conf_file = StringUtil.sinNull(getInitParameter(CER_DEFAULT_KEY));
		
		if (conf_file.trim().equals("")) {
			conf_file = RUTA_CONFIG_FILE_XML;
		}
		
		// Se carga el archivo XML y se envía al Thread de eliminación
		URL recurso;
		try {
			recurso = getServletContext().getResource(conf_file);
		} catch (MalformedURLException e) {
			log.error(e.toString());
			throw new ServletException(e.toString());
		}
		
		try {
			threadEliminacion = ThreadEliminacionRegistros.getInstance(recurso);
		} catch (ThreadEliminacionRegistrosException e) {
			log.error(e.toString());
			throw new ServletException(e.toString());
		}
		
		if (threadEliminacion == null) throw new ServletException("Error al iniciar thread de eliminación de registros, se encuentra NULO");
		
		log.debug("Configuración y arranque del Controlador de Eliminación de Registros finalizada");
	}
	
	/**
	 * Finaliza el servlet de control finalizando la calendarización del thread ejecutor
	 */
	public void destroy() {
		super.destroy();
		threadEliminacion.stopThread();
	}

}
