/*
 * Created on Mar 1, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR135E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnviarMensajeAutoInstalacionModem extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR135E entrada=null;
		TR135S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		
		entrada = (TR135E) getXmlProcessor().unmarshal(msg);
		salida = (TR135S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_135_S.xml"));
		
		 /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_135_s.status");
	   
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
	   ObtenerConfiguracionModemAutoInstalacion modem = new ObtenerConfiguracionModemAutoInstalacion(responseMessage);
	   respuestas.add(modem);
	   return respuestas;
	}

}
