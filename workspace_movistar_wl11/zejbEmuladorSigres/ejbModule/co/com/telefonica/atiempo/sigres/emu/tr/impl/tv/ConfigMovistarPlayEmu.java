package co.com.telefonica.atiempo.sigres.emu.tr.impl.tv;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR605E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR605S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfigMovistarPlayEmu extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR605E entrada = null;
		TR605S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */

		entrada = (TR605E) getXmlProcessor().unmarshal(msg);
		salida =
			(TR605S) getXmlProcessor().unmarshal(
				ServiceLocatorEmulator.getRecurso("TR_605_S.xml"));

		/* Simplemente aqui se mueve el ID de la entrada a la salida*/
		salida.setId(entrada.getId());
		salida.setCodeError(new Long(0));
		salida.setErrorMessage("Proceso OK");

		/* Se setea error false en la respuesta*/
		salida.setError(false);
		

		/*
		 * Se genera el o los String de salida y se agregan a la lista de respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(salida);
		ConfigMovistarPlayRespuesta r = new ConfigMovistarPlayRespuesta(responseMessage);
		respuestas.add(r);
		return respuestas;
	}

}
