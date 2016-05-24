/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR601E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR601S;
import co.com.telefonica.atiempo.sigres.emu.tr.NoMessageDefFoundException;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Altamira extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR601E entrada=null;
		
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		
		entrada = (TR601E) getXmlProcessor().unmarshal(msg);
		ServicioAltamira servicio = null;
		try {
			servicio = ServicioAltamiraFactory.getServicio(entrada);
		} catch (NoMessageDefFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Generacion de respuesta sincrona - Inicio
		TR601S respuestaSincrona = null;
		respuestaSincrona = (TR601S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_601_S.xml"));
		
		String statusRespSincrona = servicio.getEstadoRespuestaSincrona();
		
		/* Simplemente aqui se mueve el ID de la entrada a la salida*/
		respuestaSincrona.setId(entrada.getId());
		
		// no se si esta bien
		respuestaSincrona.setAtiempoRequestNumber(entrada.getAtisRequestNumber());
		
		// German P - Se setea el nombre de servicio
		respuestaSincrona.setAltamiraServiceName(servicio.getNombreServicio());
		
		if (statusRespSincrona.equals("ok")) {
			// Simplemente al numero de actuación le asigno el id ya que se que es único
			respuestaSincrona.setActionSecuence(entrada.getId());
			
			/* Se setea error false en la respuesta*/
			respuestaSincrona.setErrorCode(0);
			
		} else {		   	
			// Tal vez la info que se pone en caso de error depende del servicio. En ese caso, en la clase servicio deberá
			// haber un método que setee estos valores
			respuestaSincrona.setErrorCode(1);
			//respuestaSincrona.setErrorMessage("Mensaje de Error de Broker.");
			//respuestaSincrona.setErrorCodeMessage("Mensaje de Error de Competir");
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(respuestaSincrona);
		Altamira_Respuesta r = new Altamira_Respuesta(responseMessage);
		respuestas.add(r);
		
		//Generacion de respuesta sincrona - Fin
		
		// Generacion de respuesta asincrona - Inicio
		TR601S respuestaAsincrona = null;
		String statusRespAsincrona = null; 
		
		System.out.println("GENERAR RESPUESTA ASINCRONA:" + servicio.generarRespuestaAsincrona());
		
		if (servicio.generarRespuestaAsincrona()) {
			
			respuestaAsincrona = (TR601S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_601_S.xml"));				   
			
			respuestaAsincrona.setId(entrada.getId());
			
			respuestaAsincrona.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			
			respuestaAsincrona.setActionSecuence(entrada.getId());
			
			// German P - Se setea el nombre de servicio
			respuestaAsincrona.setAltamiraServiceName(servicio.getNombreServicio());
			
			statusRespAsincrona = servicio.getEstadoRespuestaAsincrona();
			
			System.out.println("STATUS RESPUESTA ASINCRONA:" + statusRespAsincrona);
			System.out.println("STATUS RESPUESTA ASINCRONA BOOLEAN:" + statusRespAsincrona.equals("ok"));
			
			if (statusRespAsincrona.equals("ok")) {
				/* Se setea error false en la respuesta*/
				respuestaAsincrona.setErrorCode(0);
			} else {
				// Tal vez la info que se pone en caso de error depende del servicio. En ese caso, en la clase servicio deberá
				// haber un método que setee estos valores					   
				respuestaAsincrona.setErrorCode(2);
				respuestaAsincrona.setAltamiraServiceName(servicio.getNombreServicio());
			}
			String responseMessage2 = getXmlProcessor().marshal(respuestaAsincrona);
			Altamira_Respuesta r2 = new Altamira_Respuesta(responseMessage2);
			respuestas.add(r2);				   
		}
		// Generacion de respuesta asincrona - Fin
		
		return respuestas;
	}

}
