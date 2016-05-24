/*
 * Creado el 28/05/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios.web.comun;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CommunicatorWS {
	
	private transient Logger log = LoggerFactory.getLogger (getClass()) ;
	
	private String endpoint; 
	private String headerMessage;
	private String footerMessage;
	private int timeOut;
	private String soapAction;
	private String responseString;
	private String contentType;
	private String requestMethod;
	private boolean doOutput;
	private boolean doInput;

	/**
	 * Ejecuta la peticion httpRequest hacia el servicio solicitado de acuerdo a los parametros de entrada
	 * @param TRxxxEBase
	 * @return String Trama de respuesta dada por el servicio
	 */
	public synchronized String sendMessageByTRxxxE(ITRxxxBase trXXXe) {
			
		
		//Conexión WebService mediante HTTP Request
		String trXXXeClassName = "";
		String trXXXeXML = "";
		byte[] trXXXeByteArray;
		
		
		//Conector de web service por protocolo HTTP
		HttpURLConnection wsConnector;
		//Canal de la peticion
		OutputStream outputStream;
		//Mensaje de salida de canal
		ByteArrayOutputStream outputMessage = new ByteArrayOutputStream();
		//Mensaje de salida de canal en array de bytes
		byte[] outStreamTRxxxE;
		//Lector de mensaje de entrada de canal
		InputStreamReader inputStreamReader;
		//Lector de mensaje de entrada java
		BufferedReader bufferedReader;
		//Almacenador de mensajes de String
		StringBuffer outputString = new StringBuffer();
		
		try{
			buildCommunicatorWSProperties();
			//Transformacion de la Interfaz del mensaje ///////////////////////////////////////////////////////////////
//			Obtenemos el nombre de la interfaz
			trXXXeClassName = trXXXe.getClass().getName().split("\\.")[trXXXe.getClass().getName().split("\\.").length-1];
			//Obtenemos el XML del objeto
			trXXXeXML = (trXXXe.toString().replaceAll("<"+trXXXeClassName+">",headerMessage)).replaceAll("</"+trXXXeClassName+">",footerMessage);
			/*trXXXeXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:man=\"http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/\">" +
						"<soapenv:Header/>" +
						"<soapenv:Body>" +
						"<man:CreacionServicio>" +
						"<XA_FAMILIA>XA_FAMILIA</XA_FAMILIA>" +
						"<XA_SOURCE_SYSTEM>XA_SOURCE_SYSTEM</XA_SOURCE_SYSTEM>" +
						"<cname>cname</cname>" +
						"<XA_CUSTOMER_ID_TYPE>XA_CUSTOMER_ID_TYPE</XA_CUSTOMER_ID_TYPE>" +
						"<customer_number>customer_number</customer_number>" +
						"<XA_CUSTOMER_SEGMENT>XA_CUSTOMER_SEGMENT</XA_CUSTOMER_SEGMENT>" +
						"<XA_CUSTOMER_SUBSEGMENT>XA_CUSTOMER_SUBSEGMENT</XA_CUSTOMER_SUBSEGMENT>" +
						"<XA_DAT_AGDMTO_1>XA_DAT_AGDMTO_1</XA_DAT_AGDMTO_1>" +
						"<XA_DAT_AGDMTO_2>XA_DAT_AGDMTO_2</XA_DAT_AGDMTO_2>" +
						"<XA_CUSTOMER_VIP>XA_CUSTOMER_VIP</XA_CUSTOMER_VIP>" +
						"<XA_CONTACT_NAME>XA_CONTACT_NAME</XA_CONTACT_NAME>" +
						"<address>address</address>" +
						"<XA_CITY_CODE>XA_CITY_CODE</XA_CITY_CODE>" +
						"<city>city</city>" +
						"<XA_STATE_CODE>XA_STATE_CODE</XA_STATE_CODE>" +
						"<state>state</state>" +
						"<XA_NEIGHBORHOOD>XA_NEIGHBORHOOD</XA_NEIGHBORHOOD>" +
						"<acoord_x>10.2</acoord_x>" +
						"<acoord_y>10.1</acoord_y>" +
						"<XA_QUADRANT>XA_QUADRANT</XA_QUADRANT>" +
						"<XA_WORK_ZONE_KEY>XA_WORK_ZONE_KEY</XA_WORK_ZONE_KEY>" +
						"<aworktype_label>aworktype_label</aworktype_label>" +
						"<XA_WORK_TYPE>XA_WORK_TYPE</XA_WORK_TYPE>" +
						"<id_actuacion>id_actuacion</id_actuacion>" +
						"<XA_ORDER_ATIS>XA_ORDER_ATIS</XA_ORDER_ATIS>" +
						"<XA_ORDER_NUMBER>XA_ORDER_NUMBER</XA_ORDER_NUMBER>" +
						"<XA_CREATION_DATE>XA_CREATION_DATE</XA_CREATION_DATE>" +
						"<date>date</date><!--1 or more repetitions:-->" +
						"<XA_NUMBER_DECODERS>XA_NUMBER_DECODERS</XA_NUMBER_DECODERS>" +
						"</man:CreacionServicio>" +
						"</soapenv:Body>" +
						"</soapenv:Envelope>";*/
			
			//Obtenemos la trama de bytes
			trXXXeByteArray = new byte[trXXXeXML.length()];
			trXXXeByteArray = trXXXeXML.getBytes();
			//Creamos el mensaje de salida que viaja por el canal de salida del servicio web
			outputMessage.write(trXXXeByteArray);
			outStreamTRxxxE = outputMessage.toByteArray();
			//Registramos en el Log la data de debug
			log.debug("CommunicatorWS.sendMessageByTRxxxE: Nombre Clase: " + trXXXeClassName);
			System.out.println("CommunicatorWS.sendMessageByTRxxxE: Nombre Clase: " + trXXXeClassName);
			log.debug("CommunicatorWS.sendMessageByTRxxxE: XML Clase: " + trXXXeXML);
			System.out.println("CommunicatorWS.sendMessageByTRxxxE: XML Clase: " + trXXXeXML);
			
			//Configuracion Web Service////////////////////////////////////////////////////////////////////////////////////////////
			//TimeOut para java 1.4 - Alternativa 1
			System.setProperty("sun.net.client.defaultConnectTimeout", ""+timeOut);
			System.setProperty("sun.net.client.defaultReadTimeout", ""+timeOut);
			//Configuramos Web Service de acuerdo a la TR asignada y el tamaño del mensaje
			wsConnector = buildWebServiceConector(trXXXeClassName, outStreamTRxxxE.length);
			/*//Alternativa 2 para TimeOut, requiere importacion de librerias de terceros
			TimerTask ft = new TimerTask(){
			   public void run(){
			     if (!isFinished){
			      wsConnector.getInputStream().close();
			      wsConnector.getOutputStream().close();
			     }
			   }
			};
			(new Timer()).schedule(ft, timeout);*/
			
			//Enviamos peticion al servicio web//////////////////////////////////////////////////////////////////////////////////
			//Creamos el canal para enviar la peticion
			outputStream = wsConnector.getOutputStream();
			// Escribimos el mensaje de la peticion en el canal de salida del servicio web.
			outputStream.write(outStreamTRxxxE);
			//Cerramos el canal una vez se halla enviado el mensaje.
			outputStream.close();

			//Recibimos la respuesta de la peticion al servicio web////////////////////////////////////////////////////////////
			//Creamos el lector de mensaje de entrada del canal y obtenemos la respuesta
			inputStreamReader = new InputStreamReader(wsConnector.getInputStream());
			//Transformamos el mensaje de entrada en un buffer de lectura de java para su posterior impresion
			bufferedReader = new BufferedReader(inputStreamReader);

			//Almacenamos en un String la respuesta del Web Service
			while ((responseString = bufferedReader.readLine()) != null) {
				outputString.append(responseString);
			}
			
			// Parse the String output to a org.w3c.dom.Document and be able to
			// reach every node with the org.w3c.dom API.
			

		}catch(MalformedURLException mfe){
			log.error("URL Mal Formada " + endpoint);
			outputString.append("CommunicatorWS.URL Mal Formada " + endpoint);
		}catch(IOException ioe){
			//log.error("Error enviando servicio " + ioe.getMessage());
			outputString.append("Error enviando servicio " + ioe.getMessage());
		}catch (Exception e){
			//log.error("Error enviando servicio " + e.getMessage());
			outputString.append("Error enviando servicio " + e.getMessage());
		}
		
		return outputString.toString();
		
	}
	
	public void buildCommunicatorWSProperties (){
		endpoint = "http://BOG-PF04QJNM:8088/mockCreacionServicioSOAP?wsdl";
		headerMessage = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:man=\"http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/\">" +
						"<soapenv:Header/>" +
						"<soapenv:Body>" +
						"<man:CreacionServicio>";
		footerMessage = "</man:CreacionServicio>" +
						"</soapenv:Body>" +
						"</soapenv:Envelope>";
		timeOut = 20000;
		soapAction = "http://www.telefonica.com/CreacionServicio/CreacionServicio";
		contentType = "text/xml; charset=utf-8";
		requestMethod = "POST";
		doOutput = true;
		doInput = true;
	}
	
	/**
	 * @param trXXXeByteArray
	 * @return
	 */
	private int getByteArrayOutputStream(byte[] trXXXeByteArray) {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	private HttpURLConnection buildWebServiceConector (String trXXXeClassName, int lengthMessage) {
		
		URL url;
		URLConnection connection;
		HttpURLConnection wsConnector = null;
		try {
			url = new URL(endpoint);
			connection = url.openConnection();
			wsConnector = (HttpURLConnection) connection;
			
			//Configuracion parametros HTTP
			wsConnector.setRequestProperty("Content-Length", String.valueOf(lengthMessage));
			wsConnector.setRequestProperty("Content-Type", contentType);
			wsConnector.setRequestProperty("SOAPAction", soapAction);
			wsConnector.setRequestMethod(requestMethod);
			//wsConnector.setReadTimeout(Timeout); //para java 1.5
			wsConnector.setDoOutput(doOutput);
			wsConnector.setDoInput(doInput);
		} catch (MalformedURLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
				
		return wsConnector;
	}
	
}
