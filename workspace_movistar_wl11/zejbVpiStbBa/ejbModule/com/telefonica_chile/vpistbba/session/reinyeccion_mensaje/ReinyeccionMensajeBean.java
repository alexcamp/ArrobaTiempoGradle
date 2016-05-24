package com.telefonica_chile.vpistbba.session.reinyeccion_mensaje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: ReinyeccionMensaje
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ReinyeccionMensajeBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String QUEUECONNECTIONFACTORY = "jms/WF_QCF";
	private static final String ERROR_RESPONSEQUEUE = "jms/WF_TO_APP_ERR_Q";
	
	private QueueConnectionFactory qcf = null;
	private Queue sendQueue = null;
	private InitialContext initialContext;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
		try {
			this.initialContext = new InitialContext();
			log.debug("WFMenssageBean inicializando - initialContext ok");
			qcf = (QueueConnectionFactory) this.getJMS(QUEUECONNECTIONFACTORY);
			log.debug("WFMenssageBean inicializando - QCF ok");
			sendQueue = (Queue) this.getJMS(ERROR_RESPONSEQUEUE);
			log.debug("WFMenssageBean inicializando - cola respuesta ok");
		} catch (javax.naming.NamingException e) {
			log.debug("inicialización de MDB falló: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/**
	 * Metodo para reinyectar un mensaje cuando la Base de Datos falla
	 * @param entradaXML
	 */
	public void reinyectarMensaje(String entradaXML){
		
		//********** AQUI SE REINYECTARA EL MENSAJE AL WORKFLOW ***********//

		QueueConnection qc = null;
		QueueSession session = null;
		QueueSender sender = null;

		log.info(
			"Se va a reinyectar el siguiente mensaje a la Cola de Error: \n***\n"
				+ entradaXML
				+ "\n***");
		try {
			// Escribimos a archivo.
			writeToFile( entradaXML );
			
			// Si esta seteado que no se reinyecte... no lo hago.
			if ( "N".equals( VpistbbaConfig.getVariable("WF-COLA-REINYECTA") ) )
				return;

			// Create a connection
			qc = qcf.createQueueConnection();
			qc.start();

			// Create a session.
			session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create a QueueSender
			//log.debug("Prueba MDB creando emisor por cola");
			sender = session.createSender(sendQueue);

			// Create a message to send to the queue...
			TextMessage message = session.createTextMessage(entradaXML);

			// Set CorrelationID from the input message and send
			sender.send(message);

			// Close the connection (close calls will cascade to other objects)
			sender.close();
			session.close();
			qc.close();
			qc = null;

		} catch (Exception e) {
			if (qc != null) {
				try {
					qc.close();
				} catch (JMSException jmsex) {
				}
			}
			log.info("NO SE PUEDO REINYECTAR EL MENSAJE: "+e);
			//throw new Exception("Excepcion al intentar reinyectar el mensaje!");
		}
	}
	
	/**
	 * @param entradaXML
	 */
	private synchronized void writeToFile(String msgXml) {
		
		
		if ( !"S".equals( VpistbbaConfig.getVariable("WF-ESCRIBIR-FILE") ) )
			return;

		String fileName = VpistbbaConfig.getVariable("WF-ERROR-FILE");
		
		if ( fileName==null || fileName.length()==0 )
			return;
		
		try {
			File outputFile = new File( fileName );
			if ( !outputFile.exists() )
				outputFile.createNewFile();
						
			FileOutputStream f =  new FileOutputStream(outputFile.getAbsolutePath(), true);
			f.write( "INICIO MENSAJE\n".getBytes() );
			f.write( msgXml.getBytes() );
			f.write( "\nFIN MENSAJE".getBytes() );
			f.close();
			log.debug("Mensaje Escrito en Archivo...");
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException. No pude Agregar Mensaje XML a Archivo [" + fileName + "]");
		} catch (IOException e) {
			log.error("IOException. No pude Agregar Mensaje XML a Archivo [" + fileName + "]");
		}
	}
	/**
	 * Metodo sencillo para recuperar referencias a objetos de JMS
	 */
	private Object getJMS(String jmsRef) throws NamingException {
		if (initialContext != null) {
			Object nsObject =
				initialContext.lookup(
					new StringBuffer("java:comp/env/")
						.append(jmsRef)
						.toString());
			//log.debug("class=" + nsObject.getClass());
			return nsObject;
		} else {
			throw new NamingException("HomeFactory: no InitialContext");
		}
	}
}
