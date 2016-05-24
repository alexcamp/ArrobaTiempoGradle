package co.com.telefonica.atiempo.sigres.emu.tr.impl.paqueteFijoMovil;

import java.util.ArrayList;
import java.util.List;

import com.telefonica_chile.comun.ComunInterfaces;

import co.com.telefonica.atiempo.interfaces.atiempo.TR613E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR613S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author joeroa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfigurarPaqueteMovilEmu extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR613E entrada=null;
		TR613S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR613E) getXmlProcessor().unmarshal(msg);
	   salida = (TR613S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_613_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_613_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   
	   salida.setDestination("ATIEMPO");
	   salida.setInterfaz(ComunInterfaces.INTERFAZ_CONF_PAQUETE_MOVIL);
	   salida.setSource("ESB");
	   salida.setVersion("1.0");
	   salida.setId(entrada.getId());
	   
	   salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
	   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
	   
	   if (resultado.equals("ok")) {
		   /* Se setea error false en la respuesta*/
		   salida.setError("0");
		   salida.setErrorMessage("");
	   } else {
		   salida.setError("20");
		   salida.setErrorMessage("Error seteado para el emulador.");
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   ConfigurarPaqueteMovilEmuRespuesta r = new ConfigurarPaqueteMovilEmuRespuesta(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}
	
}
