package com.telefonica_chile.comun.parametro.session;
/**
 * Local interface for Enterprise Bean: ParametroComunSession
 */
public interface ParametroComunSessionLocal extends javax.ejb.EJBLocalObject {
	public ParametroDTO obtenerPorNombre(String nombreParametro);
	public String obtenerFlujoNulo();
}
