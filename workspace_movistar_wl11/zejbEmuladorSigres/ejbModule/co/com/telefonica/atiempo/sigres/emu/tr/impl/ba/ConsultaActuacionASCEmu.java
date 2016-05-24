/*
 * Created on Jun 29, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR702E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR702S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR702SSchedule;
import co.com.telefonica.atiempo.interfaces.atiempo.TR703E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR703S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConsultaActuacionASCEmu extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		   ArrayList respuestas = new ArrayList();
		   /* Se definen las tr de entrada y salida que se van a utilizar*/
		   TR702E entrada=null;
		   TR702S salida = null;
		   /*
			* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			* una para la entrada y otra para la salida.
			*/

		   entrada = (TR702E) getXmlProcessor().unmarshal(msg);
		   salida = (TR702S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_702_S.xml"));

		   /*
			* Se consulta el properties para conocer el estado de respuesta
			* definido para este caso emulado.
			*/
		   String resultado = getTrProperties().getProperty("tr_702_s.status");
		   
		   //String resultado="ok";
		   /*
			* De acuerdo al resultado se procesan los datos segun convenga.
			*/
		   if (resultado.equals("ok")) {
			  
			   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
			   salida.setId(entrada.getId());
				
			   /* Se setea error false en la respuesta*/
			   salida.setError("0");
			   salida.setErrorMessage("");
			   Collection schedules = salida.getSchedules();
			   
			   boolean encontro = false;
			   for(Iterator i = schedules.iterator(); i.hasNext() && !encontro;){
			   		encontro = true;
			   		((TR702SSchedule)i.next()).setIdSchedule(entrada.getIdSchedule());			   		
			   }

		   } else {
		   	   salida.setId(entrada.getId());
		   }
		   /*
			* Se genera el o los String de salida y se agregan a la lista de respuestas.
			*/
		   String responseMessage = getXmlProcessor().marshal(salida);
		   ConsultaActuacionASCEmuRespuesta r = new ConsultaActuacionASCEmuRespuesta(responseMessage);
		   respuestas.add(r);
		   return respuestas;
	}
}
