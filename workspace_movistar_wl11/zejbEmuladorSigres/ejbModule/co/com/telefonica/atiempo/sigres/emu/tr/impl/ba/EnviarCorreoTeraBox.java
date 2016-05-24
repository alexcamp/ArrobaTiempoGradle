/*
 * Created on Jan 17, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;


import co.com.telefonica.atiempo.interfaces.atiempo.TR718E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR718S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnviarCorreoTeraBox extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR718E entrada=null;
		TR718S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		
		entrada = (TR718E) getXmlProcessor().unmarshal(msg);
		salida = (TR718S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_718_S.xml"));
		
		 /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_718_s.status");
	   
	   resultado="ok";
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   if (resultado.equals("ok")) {
	   	salida.setId(entrada.getId());
	   	salida.setError("0");
	   }else{
	   	salida.setId(entrada.getId());
	   	salida.setError("Error");
	   	salida.setErrorMessage("Error message");
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   ObtenerEnvioCorreoTeraBox box = new ObtenerEnvioCorreoTeraBox(responseMessage);
	   respuestas.add(box);
	   return respuestas;
	}
}
