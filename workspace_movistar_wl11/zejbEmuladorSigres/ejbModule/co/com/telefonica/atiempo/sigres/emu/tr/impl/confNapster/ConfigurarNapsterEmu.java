package co.com.telefonica.atiempo.sigres.emu.tr.impl.confNapster;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR604E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR604S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author dcardena
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfigurarNapsterEmu extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR604E entrada=null;
		TR604S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR604E) getXmlProcessor().unmarshal(msg);
	   salida = (TR604S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_604_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	  // String resultado = getTrProperties().getProperty("tr_604_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   salida.setId(entrada.getId());
	   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
	   
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   ConfigurarNapsterEmuRespuesta r = new ConfigurarNapsterEmuRespuesta(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}
	
}
