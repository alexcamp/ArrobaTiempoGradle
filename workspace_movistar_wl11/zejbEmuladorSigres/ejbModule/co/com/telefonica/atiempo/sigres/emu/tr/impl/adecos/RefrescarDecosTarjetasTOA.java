package co.com.telefonica.atiempo.sigres.emu.tr.impl.adecos;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR805E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RefrescarDecosTarjetasTOA extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR805E entrada = null;
		TR805S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */

		entrada = (TR805E) getXmlProcessor().unmarshal(msg);
		salida =
			(TR805S) getXmlProcessor().unmarshal(
				ServiceLocatorEmulator.getRecurso("TR_805_S.xml"));

		/* Simplemente aqui se mueve el ID de la entrada a la salida*/
		salida.setId(entrada.getId());
		salida.setDestination(entrada.getDestination());
		salida.setSource(salida.getSource());
		salida.setInterfaz(entrada.getInterfaz());
		salida.setVersion(entrada.getVersion());
		salida.setIdSistemaOrigen(entrada.getIdSistemaOrigen());
		salida.setIdPedidoAtis(entrada.getIdPedidoAtis());
		salida.setApptNumber(entrada.getApptNumber());
		salida.setIdSchedule(entrada.getIdSchedule());
		salida.setNumeroCliente(entrada.getNumeroCliente());

		/*
		 * Se genera el o los String de salida y se agregan a la lista de respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(salida);
		ADT_Respuesta r = new ADT_Respuesta(responseMessage);
		respuestas.add(r);
		return respuestas;
	}

}
