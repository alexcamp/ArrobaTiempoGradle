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

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CommunicatorWSBasic {
	
	private transient Logger log = LoggerFactory.getLogger (getClass()) ;

	/**
	 * Ejecuta la peticion httpRequest hacia el servicio solicitado de acuerdo a los parametros de entrada
	 * @param endpoint URI del servicio a ejecutar
	 * @param trama Trama de Peticion del servicio
	 * @param Timeout ReadTimeout del servicio
	 * @return String Trama de respuesta dada por el servicio
	 */
	public synchronized String sendMessageByTRxxxE(String endpoint, String trama,  int Timeout) {
			
//		String endpoint = ""; 
//		String trama = ""; 
//		int Timeout = 30;
		
//		Properties_BDLocalHome propertiesBDLocalHome;
//		Properties_BDLocal propertiesBDLocal;

		
		StringBuffer outputString = new StringBuffer();
		
		try{
			
//			propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
//			propertiesBDLocal = propertiesBDLocalHome.findByPrimaryKey(ComunInterfaces.LLAVE_PROPERTIES_PS_INTERNET_MOVIL);
			
			//TimeOut para java 1.4
			System.setProperty("sun.net.client.defaultConnectTimeout", ""+Timeout);
			System.setProperty("sun.net.client.defaultReadTimeout", ""+Timeout);
			
			// Conexión WebService mediante HTTP Request
			String responseString = "";
			
			URL url = new URL(endpoint);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] buffer = new byte[trama.length()];
			buffer = trama.getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();
			String SOAPAction = "http://www.telefonica.com/CreacionServicio/CreacionServicio";
		
			// Configuracion parametros HTTP
			httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			httpConn.setRequestProperty("SOAPAction", SOAPAction);
			httpConn.setRequestMethod("POST");
			//httpConn.setReadTimeout(Timeout); //para java 1.5
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
						
//			TimerTask ft = new TimerTask(){
//				   public void run(){
//				     if (!isFinished){
//				      httpConn.getInputStream().close();
//				      httpConn.getOutputStream().close();
//				     }
//				   }
//				};
//
//		    (new Timer()).schedule(ft, timeout);
			
			OutputStream out = httpConn.getOutputStream();
			// Write the content of the request to the outputstream of the HTTP
			// Connection.
			out.write(b);
			out.close();
			// Ready with sending the request.

			// Read the response.
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
			BufferedReader in = new BufferedReader(isr);

			// Write the SOAP message response to a String.
			while ((responseString = in.readLine()) != null) {
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
		}/* catch (NamingException e1) {
//			// TODO Bloque catch generado automáticamente
//			e1.printStackTrace();
//		} catch (FinderException e) {
//			// TODO Bloque catch generado automáticamente
/*			e.printStackTrace();
		}*/ catch (Exception e){
			//log.error("Error enviando servicio " + e.getMessage());
			outputString.append("Error enviando servicio " + e.getMessage());
		}

		
		return outputString.toString();
		
	}
	
	

}
