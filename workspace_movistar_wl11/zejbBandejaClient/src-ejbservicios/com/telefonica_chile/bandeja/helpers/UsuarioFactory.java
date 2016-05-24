package com.telefonica_chile.bandeja.helpers;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class UsuarioFactory {

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	private UsuarioLocalHome usuarioHome;
	public UsuarioLocalHome getUsuarioHome() throws FactoryException {
		if (usuarioHome != null)
			return usuarioHome;
		try {
			usuarioHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		} catch (NamingException e) {
			log.error("Problemas con home usuario: " + UsuarioLocalHome.JNDI_NAME, e);
			throw new FactoryException("Problemas manipulando usuario", e);
		}
		return usuarioHome;
	}

	public UsuarioLocal getUsuarioPorLogin(String login) throws RegistroNoEncontradoException, FactoryException {
		UsuarioLocal usuarioEntity;
		try {
			usuarioEntity = getUsuarioHome().findByLogin(login);
		} catch (FinderException e) {
			log.warn("No se encontro UsuarioEntity: " + login, e);
			throw new RegistroNoEncontradoException("Usuario: " + login);
		}
		return usuarioEntity;	
	}
	
	public UsuarioLocal getUsuarioPorId(Long idUser) throws RegistroNoEncontradoException, FactoryException {
		UsuarioLocal usuarioEntity;
		try {
			UsuarioKey userK = new UsuarioKey();
			userK.usua_id = idUser;
			usuarioEntity = getUsuarioHome().findByPrimaryKey(userK);
		} catch (FinderException e) {
			log.warn("No se encontro UsuarioEntity ID: " + idUser, e);
			throw new RegistroNoEncontradoException("Usuario ID: " + idUser);
		}
		return usuarioEntity;	
	}

}
