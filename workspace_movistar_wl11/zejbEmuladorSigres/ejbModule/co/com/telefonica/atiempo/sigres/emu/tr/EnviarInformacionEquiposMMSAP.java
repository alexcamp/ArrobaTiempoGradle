/*
 * Created on 28/02/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR029E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR029S;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos.Env_Inf_Equ_MMSAP_Respuesta;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author fmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnviarInformacionEquiposMMSAP extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR029E entrada=null;
		TR029S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR029E) getXmlProcessor().unmarshal(msg);
	   salida = (TR029S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_029_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_029_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   
	   salida.setDestination("Atiempo");
	   salida.setInterfaz("ENVIO_EQUIPOS_MM_SAP");
	   salida.setSource("ESB");
	   salida.setVersion("1.0");
	   
	   if (resultado.equals("ok")) {
		  
		   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
		   salida.setId(entrada.getId());
		   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
		   /* Se setea error false en la respuesta*/
		   salida.setError("0");
		   salida.setErrorMessage("");
		  
		  
	   } else {
	       salida.setId(entrada.getId());
	       salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
		   /* Se setea error true en la respuesta*/
	       salida.setError("1");
		   salida.setErrorMessage("Error por timeout en la respuesta desde MM SAP");
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   Env_Inf_Equ_MMSAP_Respuesta r = new Env_Inf_Equ_MMSAP_Respuesta(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}

}
