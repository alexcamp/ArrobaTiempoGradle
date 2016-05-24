/*
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR054E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.publicidad.ConfigurarPresenciaDigitalEmuRespuesta;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author mfmendez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfigurarPresenciaDigitalEmu_ST extends TRMessageProcess_ST{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR054E entrada=null;
		TR054S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR054E) getXmlProcessor().unmarshal(msg);
	   salida = (TR054S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_054_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_054_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   
	   salida.setDestination("ATIEMPO");
	   salida.setInterfaz("PRESENCIA_DIGITAL");
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
	   ConfigurarPresenciaDigitalEmuRespuestaST r = new ConfigurarPresenciaDigitalEmuRespuestaST(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}
	

}
