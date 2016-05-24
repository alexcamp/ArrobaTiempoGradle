/*
 * Creado el 16/08/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainvTV;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR018E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author cacano
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ActualizarInventario_TV_ST extends TRMessageProcess_ST {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		   ArrayList respuestas = new ArrayList();
		   /* Se definen las tr de entrada y salida que se van a utilizar*/
			TR018E entrada=null;
			TR018S salida = null;
		   /*
			* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			* una para la entrada y otra para la salida.
			*/

		   entrada = (TR018E) getXmlProcessor().unmarshal(msg);
		   salida = (TR018S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_018_S.xml"));

		   /*
			* Se consulta el properties para conocer el estado de respuesta
			* definido para este caso emulado.
			*/
		   String resultado = getTrProperties().getProperty("tr_018_s.status");
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
			   salida.setErrorMessage("Error informando la actualizacion de inventario TV.");
		   }
		   /*
			* Se genera el o los String de salida y se agregan a la lista de respuestas.
			*/
		   String responseMessage = getXmlProcessor().marshal(salida);
			ACINV_TV_Respuesta r = new ACINV_TV_Respuesta(responseMessage);
		   respuestas.add(r);
		   return respuestas;
	}

}
