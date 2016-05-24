package com.telefonica_chile.bandeja.bintegrada.session;

import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;

import com.tecnonautica.utiles.claves.ClaveUtil;
import com.tecnonautica.utiles.ldap.SimpleLdap;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.ChangePass;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.ejbutiles.MailSessionFactory;
import com.telefonica_chile.comun.mail.session.MailSessionLocal;
import com.telefonica_chile.comun.mail.session.MailSessionLocalHome;

public class ClaveHelper {
	private Session mailSession;
	private UsuarioLocalHome homeUsuario;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public ClaveHelper() throws BandejaException {
		try {
			MailSessionLocalHome mailHome = (MailSessionLocalHome)HomeFactory.getHome(MailSessionLocalHome.JNDI_NAME);
			MailSessionLocal mailLocal = mailHome.create();
			mailSession = mailLocal.getMailSession();
			log.debug("  === mailSession Instanciado ===");
			//mailSession = MailSessionFactory.getMailSession();
		} catch (NamingException e) {
			log.error("Problemas recuperando jndi " + MailSessionFactory.JNDI, e);
			throw new BandejaException("Problemas recuperando sesion de correo", e);
		}catch (CreateException e){
			log.error(e);
		}

		try {
			homeUsuario = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas recuperando jndi " + UsuarioLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas recuperando Usuario", e);
		}
	}

	/**
	 * Constructor para el caso de Cambio de Clave. (no se instancia un javax.mail.Session pq no se necesita
	 * envío a E-MAIL)
	 * */
	public ClaveHelper(String cambiaClave) throws BandejaException{
		try {
			homeUsuario = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas recuperando jndi " + UsuarioLocalHome.JNDI_NAME, e);
			throw new BandejaException("Problemas recuperando Usuario", e);
		}
		log.info("ClaveHelper Instanciado");
	}

	public boolean nuevaClave(String username, String email) throws UsuarioNoEncontradoException, BandejaException {
		boolean isOk = false;

		String claveNueva = new ClaveUtil().generaClave();
		log.debug("NUEVA CLAVE =>"+claveNueva);
		UsuarioLocal usuarioEntity = recuperaUsuario(username, email);
		SimpleLdap simple = SimpleLdap.getInstance();
		if (usuarioEntity != null) {
			if (simple.existe(username))
				simple.cambiaClave(username, claveNueva.trim());
			else
				simple.insertaUsuario(username, claveNueva.trim());
			try {
				enviaCorreo(username, email, claveNueva.trim());
				log.debug("NUEVA CLAVE TRIM=>"+claveNueva.trim());
			} catch (MessagingException e) {
				log.warn("Problemas envio de correo a " + email, e);
			}
			isOk = true;
		} else {
			throw new UsuarioNoEncontradoException(username, email);
		}
		
		return isOk;
	}

	public boolean cambiaClaveUsuario(Long idUsuario, String oldClave, String newClave) throws BandejaException, UsuarioNoAutenticadoException, UsuarioNoEncontradoException {
		boolean isOk = false;
		UsuarioLocal usuarioEntity = recuperaUsuario(idUsuario);
		if (usuarioEntity != null) {
			String login = usuarioEntity.getUsua_login();
			SimpleLdap simple = SimpleLdap.getInstance();
			ChangePass changePass = null;
			if (changePass == null) {
				Properties prop = new Properties();
				prop.setProperty("HOST_LDAP", ApplicationConfig.getVariable("HOST_LDAP"));
				prop.setProperty("DN_ADMIN", ApplicationConfig.getVariable("DN_ADMIN"));
				prop.setProperty("PSWD_ADMIN", ApplicationConfig.getVariable("PSWD_ADMIN"));
				prop.setProperty("BASE_NODE_USERS", ApplicationConfig.getVariable("BASE_NODE_USERS"));
				prop.setProperty("BASE_NODE_APP", ApplicationConfig.getVariable("BASE_NODE_APP"));
				changePass = new ChangePass();
				changePass.setProperties(prop);
			}
			try
			{
				log.debug( "Cambiando clave: " + login.toLowerCase());
//				new ChangePass(login.toLowerCase(),oldClave,newClave);
				simple.cambiaClave(login, newClave);
				isOk = true;
			}
			catch(Exception e)
			{
				try
				{
					new ChangePass(login.toLowerCase(),oldClave,newClave);
					String prefijo=ApplicationConfig.getVariable("PREFIJO_CP");
					String sufijo=ApplicationConfig.getVariable("SUFIJO_CP");
					simple.cambiaClave(prefijo+login+sufijo, newClave);
					isOk = true;
				}
				catch(Exception o)
				{
					throw new UsuarioNoAutenticadoException("Usuario o Clave incorrecta");
				}
			}
		}
		else
		{
			throw new UsuarioNoEncontradoException(idUsuario);
		}
		return isOk;
	}


	/**
	 * TODO Si hay problemas para enviar el correo, hay que definir que se puede hacer (como reintentar mas tarde por ejemplo)
	 */
	private void enviaCorreo(String username, String email, String claveNueva) throws MessagingException {
		MimeMessage msg = new MimeMessage(mailSession);
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject("Clave modificada");
		msg.setText("La clave fue modificada para " + username + ". La nueva es: " + claveNueva);
		Transport.send(msg);
	}


	private UsuarioLocal recuperaUsuario(Long idUsuario) {
		UsuarioLocal usuarioEntity = null;
		try {
			UsuarioKey usuarioKey=new UsuarioKey(idUsuario);
			usuarioEntity = homeUsuario.findByPrimaryKey(usuarioKey);
		} catch (FinderException e) {
		}
		return usuarioEntity;
	}

	private UsuarioLocal recuperaUsuario(String username, String email) throws BandejaException {
		UsuarioLocal usuarioEntity = null;
		try {
			usuarioEntity = homeUsuario.findByLoginEmail(username, email);
		} catch (FinderException e) {
		}
		return usuarioEntity;
	}
}
