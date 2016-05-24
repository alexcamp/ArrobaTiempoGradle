/*
 * Created on 13-feb-07
 */
package co.com.telefonica.atiempo.utiles;

import javax.ejb.EJBException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.intf.IServicio;

/**
 * @author TCS
 */
public abstract class MDServicioBean extends MensajeDrivenBean {

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	private static Logger logger=Logger.getLogger(MDServicioBean.class);
	public String correlationID;
	
	public void onMessage(Message mensaje)
	{
		String msg=null;
		try
		{
			correlationID = mensaje.getJMSMessageID();
			logger.debug("El Message id es:"+correlationID);
			IServicio servicio = (IServicio) getServicio();
			
			TextMessage tm = (TextMessage) mensaje;
			msg = tm.getText();
			logger.debug("Mensaje Entrante-->\n"+msg);
			servicio.iniciar(msg);
		} catch (JMSException e) {
			logger.debug("-----> Error en el procesamiento del Mensaje----:"+msg);
			logger.debug("JMSException",e);
			throw new EJBException("Error al procesar el mensaje, se genera un rollback.",e);
		} catch (ATiempoAppEx e) {
			logger.debug("-----> Error en el procesamiento del Mensaje----:"+msg);
			logger.debug("ATiempoAppEx",e);
			throw new EJBException("Error al procesar el mensaje, se genera un rollback.",e);
		} catch(Exception e) {
			logger.debug("-----> Error en el procesamiento del Mensaje----:"+msg);
			logger.debug("Exception",e);
			throw new EJBException("Error al procesar el mensaje, se genera un rollback.",e);
		}

	}

	public abstract IServicio getServicio();

}
