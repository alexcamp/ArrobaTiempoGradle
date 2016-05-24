/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.adecos;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR016E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR016S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AlistarDecos extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
			   TR016E entrada=null;
			   TR016S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR016E) getXmlProcessor().unmarshal(msg);
			   salida = (TR016S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_016_S.xml"));
       
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_016_s.status");
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
				   salida.setError(true);
				   salida.setErrorMessage("Error obteniendo los decos.");
			   }
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
	  			ADecos_Respuesta r = new ADecos_Respuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
