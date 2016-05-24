package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 19, 2009
/**
 * Bean implementation class for Enterprise Bean: QMATiempoManager
 */
public class QMATiempoManagerBean extends SessionBeanAdapter {

	protected static Logger log = LoggerFactory.getLogger(QMATiempoManagerBean.class);
	
	private static final String QUEUE_CF = "jms/QMAT_QCF";
	private static final String JNDI_NAME = "java:comp/env/";

	private QueueConnectionFactory qcf = null;

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
		InitialContext ic;
		try {
			ic = new InitialContext();
			qcf = (QueueConnectionFactory) ic.lookup(JNDI_NAME + QUEUE_CF);
		} catch (NamingException e) {
			log.debug("NamingException en QMATiempoManagerBean",e);
		}
	}

	public void enviarMensaje(String queue, String mensajeSalida) throws ATiempoAppEx {
		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		StringBuffer msgOut = new StringBuffer(1024);

		try
		{
			InitialContext ic = new InitialContext();
			Queue sendQueue = (Queue) ic.lookup(JNDI_NAME + queue);

			// Create a connection
			msgOut.append("Enviando Mensaje:\n");
			msgOut.append("---START---\n");
			msgOut.append(mensajeSalida);
			
			qc = qcf.createQueueConnection();
			qc.start();

			// Create a session.
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create a QueueSender
			sender = session.createSender(sendQueue);
			//Define la persistencia del Mensaje
			sender.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			// Create a message to send to the queue...
			TextMessage message = session.createTextMessage(mensajeSalida);

			// Set CorrelationID from the input message and send		
			//message.setJMSCorrelationID(corrid);
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)

			sender.close();
			session.close();
			qc.close();
			msgOut.append("\n---END---");
			log.debug(msgOut.toString());
		}
		catch (JMSException jex)
		{
			throw new ATiempoAppEx(ATiempoAppEx.JMS, jex);
		}
		catch (NamingException nex)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		}
		finally
		{
			// Ensure that the Connection always gets closed			
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException e) {
				}
			}
			if (sender != null) {
				try {
					sender.close();
				} catch (JMSException e) {
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (JMSException e) {
				}
			}
		}
	}
	
	public void enviarMensajeWhitCorrelId(String queue, String mensajeSalida, String corrid) throws ATiempoAppEx {
		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		StringBuffer msgOut = new StringBuffer(1024);

		try
		{
			InitialContext ic = new InitialContext();
			Queue sendQueue = (Queue) ic.lookup(JNDI_NAME + queue);

			// Create a connection
			msgOut.append("Enviando Mensaje:\n");
			msgOut.append("---START---\n");
			msgOut.append(mensajeSalida);
			
			qc = qcf.createQueueConnection();
			qc.start();

			// Create a session.
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create a QueueSender
			sender = session.createSender(sendQueue);
			//Define la persistencia del Mensaje
			sender.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			// Create a message to send to the queue...
			TextMessage message = session.createTextMessage(mensajeSalida);

			// Set CorrelationID from the input message and send		
			message.setJMSCorrelationID(corrid);
			log.debug("Seteo el Correlation ID en :"+corrid);
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)

			sender.close();
			session.close();
			qc.close();
			msgOut.append("\n---END---");
			log.debug(msgOut.toString());
		}
		catch (JMSException jex)
		{
			throw new ATiempoAppEx(ATiempoAppEx.JMS, jex);
		}
		catch (NamingException nex)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		}
		finally
		{
			// Ensure that the Connection always gets closed			
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException e) {
				}
			}
			if (sender != null) {
				try {
					sender.close();
				} catch (JMSException e) {
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (JMSException e) {
				}
			}
		}
	}	

}
