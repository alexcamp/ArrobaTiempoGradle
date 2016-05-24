package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * Local interface for Enterprise Bean: QMATiempoManager
 */
public interface QMATiempoManagerLocal extends javax.ejb.EJBLocalObject {

	/**
	 * @param queueConnectionFactory
	 * @param queue
	 * @param mensajeSalida
	 */
	void enviarMensaje(String queue, String mensajeSalida) throws ATiempoAppEx;
	void enviarMensajeWhitCorrelId(String queue, String mensajeSalida, String correlationID)throws ATiempoAppEx;
	
}
