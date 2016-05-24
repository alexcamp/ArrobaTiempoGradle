/*
 * Created on Jul 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR701E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreacionActuacionAgendaSC extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		   ArrayList respuestas = new ArrayList();
		   /* Se definen las tr de entrada y salida que se van a utilizar*/
		   TR701E entrada=null;
		   TR701S salida = null;
		   /*
			* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			* una para la entrada y otra para la salida.
			*/

		   entrada = (TR701E) getXmlProcessor().unmarshal(msg);
		   salida = (TR701S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_701_S.xml"));

		   /*
			* Se consulta el properties para conocer el estado de respuesta
			* definido para este caso emulado.
			*/
		   //String resultado = getTrProperties().getProperty("tr_701_s.status");
		   
		   String resultado="ok";
		   /*
			* De acuerdo al resultado se procesan los datos segun convenga.
			*/
		   if (resultado.equals("ok")) {
			  
			   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
			   salida.setId(entrada.getId());
				
			   /* Se setea error false en la respuesta*/
			   salida.setError("false");
			   salida.setIdSchedule(entrada.getIdSchedule());
		   } else {
		   	   salida.setId(entrada.getId());
			   salida.setErrorMessage("Error en creacion de actuación Agenda SC.");
			   salida.setErrorCode("");
			   salida.setIdSystemOrigin("AGENDA_SC");
			   salida.setIdSchedule(entrada.getIdSchedule());
		   }
		   /*
			* Se genera el o los String de salida y se agregan a la lista de respuestas.
			*/
		   String responseMessage = getXmlProcessor().marshal(salida);
		   RespuestaCreacionActuacionAgendaSC r = new RespuestaCreacionActuacionAgendaSC(responseMessage);
		   respuestas.add(r);
		   return respuestas;
}

}
