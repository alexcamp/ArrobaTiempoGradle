/*
 * Creado el 28/06/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR612E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR612S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfPaqueteMovil extends TRMessageProcess {
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas */
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar */
		TR612E entrada = null;
		TR612S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder
		 * modificarlas una para la entrada y otra para la salida.
		 */

		entrada = (TR612E) getXmlProcessor().unmarshal(msg);
		salida = (TR612S) getXmlProcessor().unmarshal(
				ServiceLocatorEmulator.getRecurso("TR_612_S.xml"));

		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_612_s.status");

		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		if (resultado.equals("ok")) {
			salida.setId(entrada.getId());
		} else {
			salida.setId(entrada.getId());
			salida.setErrorMessage("Error message");
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de
		 * respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(salida);
		RespuestaConfMediacionMovil res = new RespuestaConfMediacionMovil(
				responseMessage);
		respuestas.add(res);
		return respuestas;
	}
}

