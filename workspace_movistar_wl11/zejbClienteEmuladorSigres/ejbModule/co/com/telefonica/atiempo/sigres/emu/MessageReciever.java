package co.com.telefonica.atiempo.sigres.emu;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.intf.IServicio;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MessageReciever implements Runnable{

	private Logger logger = Logger.getLogger(this.getClass());
	private String serviceClassName;
	private String msg;
	public MessageReciever(String message, String service){
		this.serviceClassName = service;
		this.msg = message;
	}
	public void run() {
		recieveMessage();
		
	}
	
	public void recieveMessage() {

			try {
				logger.info("Recibido el mensaje");
				Thread.sleep(1000);
				logger.info("Procesando...");
				IServicio servicio =
					(IServicio) Class.forName(serviceClassName).newInstance();
				logger.debug("Mensaje Entrante-->\n" + msg);
				servicio.iniciar(msg);
			} catch (ATiempoAppEx e) {
				logger.debug(
					"-----> Error en el procesamiento del Mensaje----:" + msg);
				logger.debug("ATiempoAppEx", e);
				throw new EJBException(
					"Error al procesar el mensaje, se genera un rollback.",
					e);
			} catch (Exception e) {
				logger.debug(
					"-----> Error en el procesamiento del Mensaje----:" + msg);
				logger.debug("Exception", e);
				throw new EJBException(
					"Error al procesar el mensaje, se genera un rollback.",
					e);
			}

		}

}
