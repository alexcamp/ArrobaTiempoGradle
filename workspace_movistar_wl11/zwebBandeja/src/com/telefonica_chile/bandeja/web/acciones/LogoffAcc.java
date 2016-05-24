package com.telefonica_chile.bandeja.web.acciones;

import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import co.com.atiempo.sesion.SessionManager;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.ldap.SimpleLdap;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocal;
import com.telefonica_chile.bandeja.datos.usuarios.UsuariosSessionLocalHome;

public class LogoffAcc extends Accion {
	/**
	 * Esta implementacion "trata" de desloggear al usuario asumiendo Single Logon.
	 * En este esquema, los links a una sesion de usuario pueden estar en dos partes:
	 * 
	 * 1) Cookies  Usuadas por el WAS para referenciar sesion Single Logon.
	 * 2) Sesion Ahi guardo el controlador de aplicacion.
	 * 3) Ldap. (isUserConnected de LdapBean). Esto lo maneja el WAS asi que lo que
	 * hago es *intentar* desloggear al usuario de este medio. No es seguro que tenga
	 * algun efecto.
	 * 
	 * TODO Falta probar si funciona... 
	 */
	private static Logger log=Logger.getLogger(LogoffAcc.class);
	
	private DataSource getDataSource() throws NamingException {
		DataSource ds = null;
		return (DataSource) new InitialContext().lookup("jdbc/vpistbba");
	}
	
	protected void ejecutar(HttpServletRequest request) throws Evento {
		try
		{
			UsuariosSessionLocalHome usuariosHome = (UsuariosSessionLocalHome) HomeFactory.getHome(UsuariosSessionLocalHome.JNDI_NAME);
			UsuariosSessionLocal usuariosSession =  usuariosHome.create();
			//Mato la variable de session controlador de aplicacion.
//			log.debug("PAse por el logoof acc");
			HttpSession session=request.getSession();
			
			String login = request.getUserPrincipal() == null ? "DUMMY" : request.getUserPrincipal().getName();
			log.info("Removiendo sesión con login "+login);
			new SessionManager(getDataSource()).removeSession(login);
			log.info("La sesión con login "+login+" ha sido removida.");
			
			Enumeration enumeration=session.getAttributeNames();
			while(enumeration.hasMoreElements())
			{
				String valor=(String)enumeration.nextElement();
//				log.debug("Removiendo Session:"+valor);
				session.removeAttribute(valor);	
			}
			
//			Trato de salir de ldap
			SimpleLdap simple = SimpleLdap.getInstance();
			simple.logoff(request);

			// Trato de matar la sesion
			session.invalidate();
			//log.debug("invalidé la session");
			
			//com.ibm.websphere.security.WSSecurityHelper.revokeSSOCookies(request, null);
			
			// Trato de eliminar las cookies que pueda.
			Cookie[] cookies = request.getCookies();
			for (int i = 0; cookies != null && i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
			}
			//log.debug("Termine aca");
			String miniTok=sacaTok(request);
			if(request.getParameter("usuario_token")!=null)
			{
				usuariosSession.updateToKen((String)request.getParameter("usuario_token"),miniTok);
			}
			else
				usuariosSession.deleteToKen(miniTok);	 
		}
		catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private String sacaTok(HttpServletRequest request) {
		return sinNull(request.getRemoteAddr(),"_")+sinNull(request.getRemoteHost(),"_");
	}
	
	private String sinNull(String string, String del)
	{
		if(string==null)
			return ""+del;
		return string+del;
	}
}
