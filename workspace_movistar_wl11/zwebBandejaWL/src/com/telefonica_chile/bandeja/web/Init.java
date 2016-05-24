package com.telefonica_chile.bandeja.web;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.tecnonautica.utiles.ldap.SimpleLdap;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

import directory.util.LdapException;

/**
 * Servlet para inicializar los recursos que necesito.
 */
public class Init extends HttpServlet {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public void init() throws ServletException {
		try {
			initLdap();
			log.info("Ldap inicializado");
		} catch (Exception e) {
			log.error("Problemas inicializando ldap", e);
		}
	}

	private void initLdap() throws NamingException, LdapException {
		//String pathEtc = getServletContext().getRealPath(getInitParameter("ldap-config-file"));
		String pathEtc = getInitParameter("ldap-config-file");
		String nodo = getInitParameter("ldap-config-nodo");
		log.debug(pathEtc);
		log.debug(nodo);
		SimpleLdap.init(pathEtc, nodo);
	}

}
