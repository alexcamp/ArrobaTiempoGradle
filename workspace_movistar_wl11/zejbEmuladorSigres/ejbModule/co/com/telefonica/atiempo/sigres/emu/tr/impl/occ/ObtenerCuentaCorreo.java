/*
 * Created on May 7, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.occ;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR043E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ObtenerCuentaCorreo extends TRMessageProcess{

	
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
			   TR043E entrada=null;
			   TR043S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR043E) getXmlProcessor().unmarshal(msg);
			   salida = (TR043S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_043_S.xml"));
       
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_043_s.status");
			   /*
				* De acuerdo al resultado se procesan los datos segun convenga.
				*/
			   if (resultado.equals("ok")) {
				   String email = getTrProperties().getProperty("tr_043_s.fatherEmail");
				   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
				   salida.setId(entrada.getId());
					salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
				   /* Se setea error false en la respuesta*/
				   salida.setError(false);
				   salida.setFatherEmail(email);
				  
			   } else {
			   	   salida.setId(entrada.getId());
				   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
				   salida.setError(true);
				   salida.setErrorMessage("Error obteniendo la cuenta de Correo.");
			   }
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
			   OCC_Respuesta r = new OCC_Respuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
