/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.aula;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR024E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR024S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Aula365 extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
				TR024E entrada=null;
				TR024S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR024E) getXmlProcessor().unmarshal(msg);
			   salida = (TR024S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_024_S.xml"));
       
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_024_s.status");
			   /*
				* De acuerdo al resultado se procesan los datos segun convenga.
				*/
			   if (resultado.equals("ok")) {
				  
				   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
				   salida.setId(entrada.getId());
					salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
				   /* Se setea error false en la respuesta*/
				   salida.setError(false);
				  
			   } else {
					salida.setId(entrada.getId());
				 	salida.setAtisRequestNumber(entrada.getAtisRequestNumber());			   	
				   salida.setError(true);
				   salida.setErrorMessage("Mensaje de Error de Broker.");
				   salida.setErrorCode(601);
				   salida.setErrorCodeMessage("Mensaje de Error de Competir");
			   }
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
			   Aula365_Respuesta r = new Aula365_Respuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
