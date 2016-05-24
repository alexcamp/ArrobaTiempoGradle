package com.tecnonautica.utiles.ldap;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

import directory.ldap.LdapBean;
import directory.util.LdapException;
import directory.util.ListResult;

/**
 * Un wrapper en torno a directory.util.LdapBean: la libreria de IBM.
 * Se recomienda inicializarlo cuando se levanta la aplicacion, 
 * por ejemplo en el init() de un servlet.
 * 
 * Ejemplo de inicializacion (se asume todo lo que se ve :))
 *      String pathEtc = getServletContext().getRealPath(getInitParameter("ldap-config-file"));
 *      String nodo = getInitParameter("ldap-config-nodo");
 *      SimpleLdap.init(pathEtc, nodo);
 * 
 * Ejemplo de uso:
 *      SimpleLdap simple = SimpleLdap.getInstance();
 *      if (simple.existe(VpistbbaConfig.getVariable("DUMMY"))) { print "existe usuario por defecto en el Directorio" }
 *  
 */
public class SimpleLdap {
	private static SimpleLdap simple;
	private LdapBean ldapBean;
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public static void init(String pathEtc, String nodo) throws LdapException {
		simple = new SimpleLdap(pathEtc, nodo);
	}

	private SimpleLdap(String pathEtc, String nodo) throws LdapException {
		ldapBean = new LdapBean();
		ldapBean.init(pathEtc, nodo);
		log.info("Inicializado SimpleLdap:"+pathEtc+" - "+nodo);
	}

	public static SimpleLdap getInstance() {
		if (simple == null)
			throw new SimpleLdapException("No se ha inicializado SimpleLdap");
		return simple;
	}

	public boolean existe(String login) {
		validaLdap();

		Object cosa;
		try {

//			String otro="CN="+login+",CN=ldapadm,OU=intranet,OU=Workflow,O=ctc,C=cl";

			cosa = ldapBean.getUserData(login);
			return cosa instanceof ListResult && ((ListResult) cosa).hasMoreElements();
		} catch (LdapException e) {
			log.warn("Problema comunicando con ldap", e);
		}
		return false;
	}


	public void validaLdap() {
		if (ldapBean == null) {
			log.warn("SimpleLdap no ha sido inicializado!");
			throw new SimpleLdapException("SimpleLdap no ha sido inicializado");
		}
	}

	public boolean autenticaUsuario(String login, String password, HttpServletRequest request) {
		validaLdap();

		log.debug("Autenticando usuario " + login);
		if (login == null || login.trim().length() == 0 || password == null || password.trim().length() == 0) {
			log.warn("Login o passwords nulo (" + login + "/" + password + ")");
			return false;
		}

		try {
			if (ldapBean.isUserConnected(login)) {
				log.debug("Usuario " + login + " ya estaba conectado");
				if (ldapBean.correctCredentials(login, password))
					return true;
				log.warn("Credenciales invalidas para usuario (ya conectado) " + login);
			} else {
				Object cosa = ldapBean.executeAuthentication(login, password, request);
				if (cosa instanceof ListResult && ((ListResult) cosa).hasMoreElements()) {
					log.debug("Se autentico usuario " + login + " en ldap");
					return true;
				}
			}
		} catch (Exception e) {
			log.warn("No se pudo autenticar usuario " + login, e);
			throw new SimpleLdapException("No se pudo autenticar usuario " + login, e);
		}
		
		return false;
	}

	public boolean esAutentico(String login, String password) {
		validaLdap();
		log.debug("Verificando autenticidad del usuario " + login);
		try {
			if (ldapBean.correctCredentials(login, password)) {
				log.debug("Usuario " + login + ", y PASSWORD["+password+"] es autentico");
				return true;
			}
			String prefijo=ApplicationConfig.getVariable("PREFIJO_CP");
			String sufijo=ApplicationConfig.getVariable("SUFIJO_CP");
			String valor=prefijo+login+sufijo;
			log.info("Valor:"+valor);
			if (ldapBean.correctCredentials(valor, password))
			{
				log.debug("Usuario " + login.toLowerCase() + ", y PASSWORD["+password.toLowerCase()+"] es autentico");
				return true;
			}
		} catch (Exception e)
		{
			log.debug(e);
			log.warn("Problemas verificando credenciales para usuario " + login + ": " + e);
		}
		log.warn("Credenciales invalidas para usuario " + login);
		return false;
	}

	public void cambiaClave(String login, String newClave) {
		validaLdap();

		log.debug("Cambiando clave a usuario " + login);
	
		Properties userProp = new Properties();
		userProp.setProperty("cn", login);
		userProp.setProperty("userPassword", newClave);
		try {
			ldapBean.executeUpdate(login, userProp);
		} catch (Exception e) {
			log.warn("Error al cambiar clave a usuario " + login, e);
			throw new SimpleLdapException("Error al cambiar clave a usuario " + login, e);
		}
	}
	
	public void logoff(HttpServletRequest request) {
		validaLdap();

		log.debug("Deslogeando usuario");

		try {
			ldapBean.logOff(request);
		} catch (Exception e) {
			log.warn("Error al tratar de deslogear a usuario: " + e);
		}
	}
	
	public void insertaUsuario(String login, String password) {
		validaLdap();

		Properties datos = new Properties();
		// datos.setProperty("cn", login);
		datos.setProperty("userPassword", password);
		try {
			ldapBean.executeInsert(login, datos);
		} catch (LdapException e) {
			log.warn("Error al tratar de insertar usuario " + login, e);
			throw new SimpleLdapException("Error al tratar de insertar usuario " + login, e);
		}
	}
}
