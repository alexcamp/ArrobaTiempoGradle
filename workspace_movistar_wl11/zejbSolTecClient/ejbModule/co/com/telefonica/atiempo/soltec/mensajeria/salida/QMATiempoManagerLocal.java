package co.com.telefonica.atiempo.soltec.mensajeria.salida;

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
	public void enviarMensaje(String queue, String queueDestino,String mensajeSalida) throws ATiempoAppEx;

}
