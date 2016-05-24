/*
 * Created on Jan 17, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;


import co.com.telefonica.atiempo.interfaces.atiempo.TR051E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR051S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnviarConfiguracionTeraBox extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR051E entrada=null;
		TR051S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		
		entrada = (TR051E) getXmlProcessor().unmarshal(msg);
		salida = (TR051S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_051_S.xml"));
		
		 /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_051_s.status");
	   
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
	   ObtenerConfiguracionTeraBox box = new ObtenerConfiguracionTeraBox(responseMessage);
	   respuestas.add(box);
	   return respuestas;
	}
}
