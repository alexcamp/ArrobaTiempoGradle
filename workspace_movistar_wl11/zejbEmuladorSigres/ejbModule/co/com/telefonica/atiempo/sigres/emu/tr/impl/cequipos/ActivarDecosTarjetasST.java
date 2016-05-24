package co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR017E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActivarDecosTarjetasST extends TRMessageProcess_ST {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR017E entrada = null;
		TR017S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */

		entrada = (TR017E) getXmlProcessor().unmarshal(msg);
		salida =
			(TR017S) getXmlProcessor().unmarshal(
				ServiceLocatorEmulator.getRecurso("TR_017_S.xml"));

		/* Simplemente aqui se mueve el ID de la entrada a la salida*/
		salida.setId(entrada.getId());
		salida.setAtisRequestNumber(entrada.getAtisRequestNumber());

		/* Se setea error false en la respuesta*/
		salida.setError(false);

		/*
		 * Se genera el o los String de salida y se agregan a la lista de respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(salida);
		ADT_RespuestaST r = new ADT_RespuestaST(responseMessage);
		respuestas.add(r);
		return respuestas;
	}

}
