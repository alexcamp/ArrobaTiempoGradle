/*
 * Creado el 14/12/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.confZTE;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR056E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR056S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;

/**
 * @author cacano
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ActivarCamaraAgendaSCEmu extends TRMessageProcess {

	/*
	 * (sin Javadoc)
	 * 
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas */
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar */
		TR709E salida = null;
		TR709S entrada = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder
		 * modificarlas una para la entrada y otra para la salida.
		 */

		salida = (TR709E) getXmlProcessor().unmarshal(msg);
		entrada = new TR709S();

		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_709_s.status");

		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		if (resultado.equals("ok")) {
			salida.setId(entrada.getId());
			salida.setError("0");
		} else {
			salida.setId(entrada.getId());
			salida.setErrorMessage("No se pudo realizar la configuracion");
			salida.setError("1");
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de
		 * respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(salida);
		ActivarCamaraAgendaSCEmuRespuesta res = new ActivarCamaraAgendaSCEmuRespuesta(
				responseMessage);
		respuestas.add(res);
		return respuestas;
	}

}