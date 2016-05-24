package co.com.telefonica.atiempo.sigres.emu.tr.impl.paqueteFijoMovil;

import java.util.ArrayList;
import java.util.List;

import com.telefonica_chile.comun.ComunInterfaces;

import co.com.telefonica.atiempo.interfaces.atiempo.TR614E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR614S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author joeroa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RecargaFijoMovilEmu extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR614E entrada=null;
		TR614S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR614E) getXmlProcessor().unmarshal(msg);
	   salida = (TR614S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_614_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_614_s.status");
	   resultado="ok";
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   
	   salida.setDestination("ATIEMPO");
	   salida.setInterfaz(ComunInterfaces.INTERFAZ_CARACT_FIJA_MOVIL);
	   salida.setSource("ESB");
	   salida.setVersion("1.0");
	   salida.setId(entrada.getId());
	   
	   salida.setAtiempoRequestNumber(entrada.getAtisRequestNumber());	   
	   
	   if (resultado.equals("ok")) {
		   /* Se setea error false en la respuesta*/
		   salida.setError("0");
		   salida.setErrorMessage("");
		   salida.setEstado("3");
	   } else {
		   salida.setError("0");
		   salida.setErrorMessage("Error seteado para el emulador.");
		   salida.setEstado("3");
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   RecargaFijoMovilEmuRespuesta r = new RecargaFijoMovilEmuRespuesta(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}
	
}
