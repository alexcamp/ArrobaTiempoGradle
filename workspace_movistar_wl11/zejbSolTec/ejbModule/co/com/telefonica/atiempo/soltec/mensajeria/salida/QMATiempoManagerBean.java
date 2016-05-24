package co.com.telefonica.atiempo.soltec.mensajeria.salida;

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
			log.error("NamingException en QMATiempoManagerBean",e);
		}
	}

	public void enviarMensaje(String queue, String mensajeSalida) throws ATiempoAppEx {
		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		StringBuffer msgOut = new StringBuffer(1024);

		try {
			InitialContext ic = new InitialContext();
			log.debug("Lookup para enviar Mensaje sin ReplyToQueue.");
			Queue sendQueue = (Queue) ic.lookup(JNDI_NAME  + "jms/QMAT/" + queue);

			// Create a connection
			msgOut.append("Enviando Mensaje:\n");
			msgOut.append("---START---\n");
			msgOut.append(mensajeSalida);

			log.debug("Creando conexion para enviar Mensaje.");
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
			log.debug("Envio Mensaje sin ReplyToQueue.");
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)
			sender.close();
			session.close();
			qc.close();
			qc = null;
			msgOut.append("\n---END---");
			log.debug(msgOut.toString());
		} catch (JMSException jex) {
			log.error("JMSException en QMATiempoManagerBean",jex);
			throw new ATiempoAppEx(ATiempoAppEx.JMS, jex);
		} catch (NamingException nex) {
			log.error("NamingException en QMATiempoManagerBean",nex);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (Exception e) {
			log.error("Exception en QMATiempoManagerBean",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally {
			// Ensure that the Connection always gets closed			
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException e) {
				}
			}
		}
	}
	
	public void enviarMensaje(String queueEnvio, String queueRespuesta,String mensajeSalida) throws ATiempoAppEx {
		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		StringBuffer msgOut = new StringBuffer(1024);

		try {
			InitialContext ic = new InitialContext();
			log.debug("Lookup para enviar Mensaje Cola Salida.");
			Queue sendQueue = (Queue) ic.lookup(JNDI_NAME + "jms/QMAT/" + queueEnvio);
			log.debug("Lookup para enviar Mensaje Cola Reply.");
			Queue replyQueue = (Queue) ic.lookup(JNDI_NAME + "jms/QMBUS/" + queueRespuesta);

			// Create a connection
			msgOut.append("Enviando Mensaje:\n");
			msgOut.append("---START---\n");
			msgOut.append(mensajeSalida);
			
			log.debug("Creando conexion para enviar Mensaje.");
			qc = qcf.createQueueConnection();
			qc.start();

			// Create a session.
			log.debug("Creando Session para enviar Mensaje.");
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create a QueueSender
			log.debug("Creando QueueSender para enviar Mensaje.");
			sender = session.createSender(sendQueue);
			
			//Define la persistencia del Mensaje
			log.debug("Creando Persistencia para enviar Mensaje.");
			sender.setDeliveryMode(DeliveryMode.PERSISTENT);

			// Create a message to send to the queue...
			TextMessage message = session.createTextMessage(mensajeSalida);

			message.setJMSReplyTo(replyQueue);
			// Set CorrelationID from the input message and send		
			//message.setJMSCorrelationID(corrid);
			log.debug("Envio Mensaje con ReplyToQueue.");
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)
			sender.close();
			session.close();
			qc.close();
			qc = null;
			msgOut.append("\n---END---");
			log.debug(msgOut.toString());
		} catch (JMSException jex) {
			log.error("JMSException en QMATiempoManagerBean",jex);
			throw new ATiempoAppEx(ATiempoAppEx.JMS, jex);
		} catch (NamingException nex) {
			log.error("NamingException en QMATiempoManagerBean",nex);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (Exception e) {
			log.error("Exception en QMATiempoManagerBean",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally {
			// Ensure that the Connection always gets closed			
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException e) {
				}
			}
		}
	}
		
//	public void enviarMensaje(String queueEnvio, String queueRespuesta,String mensajeSalida) throws ATiempoAppEx {
//			try {
//				StringBuffer msgOut = new StringBuffer(1024);
//				
//				MQ mq = new MQ(STConfig.getVariable("QHOSTNAME_ST"), Integer.parseInt(STConfig.getVariable("QPORT_ST")), STConfig.getVariable("QCANAL_ST"));
//				msgOut.append("Enviando Mensaje:\n");
//				msgOut.append("---START---\n");
//				msgOut.append(mensajeSalida);
//				
//				mq.put(queueEnvio, mensajeSalida, queueRespuesta,STConfig.getVariable("QMANAGERRES_ST"));
//				//TODO: probar commit
//				//mq.commit();
//				mq.close();
//				msgOut.append("\n---END---");
//				log.debug(msgOut.toString());
//			} catch (Exception e) {
//				log.error("Exception en QMATiempoManagerBean",e);
//				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
//			}
//		}
//CR18380 Portal de relacionamiento - pcawen
	/*
	 * Envia el mensaje seteando el correlId el cual recibo por parámetro
	 */
	public void enviarMensajeWhitCorrelId(String queue, String mensajeSalida, String correlId) throws ATiempoAppEx {
		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		StringBuffer msgOut = new StringBuffer(1024);

		try {
			InitialContext ic = new InitialContext();
			log.debug("Lookup para enviar Mensaje con correl Id");
			Queue sendQueue = (Queue) ic.lookup(JNDI_NAME  + "jms/QMAT/" + queue);

			// Create a connection
			msgOut.append("Enviando Mensaje:\n");
			msgOut.append("---START---\n");
			msgOut.append(mensajeSalida);

			log.debug("Creando conexion para enviar Mensaje.");
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
			message.setJMSCorrelationID(correlId);
			log.debug("Envio Mensaje con correlID = " + correlId);
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)
			sender.close();
			session.close();
			qc.close();
			qc = null;
			msgOut.append("\n---END---");
			log.debug(msgOut.toString());
		} catch (JMSException jex) {
			log.error("JMSException en QMATiempoManagerBean",jex);
			throw new ATiempoAppEx(ATiempoAppEx.JMS, jex);
		} catch (NamingException nex) {
			log.error("NamingException en QMATiempoManagerBean",nex);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (Exception e) {
			log.error("Exception en QMATiempoManagerBean",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}finally {
			// Ensure that the Connection always gets closed			
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException e) {
				}
			}
		}
	}


}
