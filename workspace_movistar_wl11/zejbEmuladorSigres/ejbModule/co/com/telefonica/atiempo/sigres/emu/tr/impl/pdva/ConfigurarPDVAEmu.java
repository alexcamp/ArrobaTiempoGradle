package co.com.telefonica.atiempo.sigres.emu.tr.impl.pdva;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR055E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR055S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author fmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfigurarPDVAEmu extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR055E entrada=null;
		TR055S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR055E) getXmlProcessor().unmarshal(msg);
	   salida = (TR055S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_055_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_055_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   
	   salida.setDestination("ATIEMPO");
	   salida.setInterfaz("PDVA");
	   salida.setSource("ESB");
	   salida.setVersion("1.0");
	   salida.setId(entrada.getId());
	   
	   salida.setNit(entrada.getNit());
	   salida.setTelefonoAdicional(entrada.getTelefonoAdicional());
	   salida.setTelefonoPadre(entrada.getTelefonoPadre());
	   
	   if (resultado.equals("ok")) {
		   /* Se setea error false en la respuesta*/
		   salida.setError("0");
		   salida.setErrorMessage("");
	   } else {
		   salida.setError("10001");
		   salida.setErrorMessage("Error seteado para el emulador.");
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   ConfigurarPDVAEmuRespuesta r = new ConfigurarPDVAEmuRespuesta(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}
	
}
