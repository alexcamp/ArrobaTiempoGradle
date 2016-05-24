package com.telefonica_chile.vpistbba.session.reinyeccion_mensaje;
/**
 * Local interface for Enterprise Bean: ReinyeccionMensaje
 */
public interface ReinyeccionMensajeLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Metodo para reinyectar un mensaje cuando la Base de Datos falla
	 * @param entradaXML
	 */
	public void reinyectarMensaje(String entradaXML);
}
