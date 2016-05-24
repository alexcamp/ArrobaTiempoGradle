/*
 * Created on May 22, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR007E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR007S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizarInventarioBA extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		   respuestas = new ArrayList();
		   /* Se definen las tr de entrada y salida que se van a utilizar*/
		   TR007E entrada=null;
		   TR007S salida = null;
		   /*
			* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			* una para la entrada y otra para la salida.
			*/
    
		   entrada = (TR007E) getXmlProcessor().unmarshal(msg);
		   salida = (TR007S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_007_S.xml"));
       
		   /*
			* Se consulta el properties para conocer el estado de respuesta
			* definido para este caso emulado.
			*/
		   String resultado = getTrProperties().getProperty("tr_007_s.status");
		   /*
			* De acuerdo al resultado se procesan los datos segun convenga.
			*/
		   if (resultado.equals("ok")) {
				  
			   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
			   salida.setId(entrada.getId());
				salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			   /* Se setea error false en la respuesta*/
			   salida.setError(false);
			   salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			   salida.setId(entrada.getId());
				  
				  
		   } else {
			   salida.setError(true);
			   salida.setErrorMessage("Error actualizando inventario.");
			   salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			   salida.setId(entrada.getId());
		   }
		   /*
			* Se genera el o los String de salida y se agregan a la lista de respuestas.
			*/
		   String responseMessage = getXmlProcessor().marshal(salida);
		   ACINV_Respuesta r = new ACINV_Respuesta(responseMessage);
		   respuestas.add(r);
		   return respuestas;
	}

}
