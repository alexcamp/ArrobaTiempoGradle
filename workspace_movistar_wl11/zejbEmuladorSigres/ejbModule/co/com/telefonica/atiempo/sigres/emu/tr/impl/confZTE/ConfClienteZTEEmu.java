package co.com.telefonica.atiempo.sigres.emu.tr.impl.confZTE;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR056E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR056S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR612E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR612S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;

/**
 * @author cacano
 * 
 */
public class ConfClienteZTEEmu extends TRMessageProcess {

	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas */
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar */
		TR056E entrada = null;
		TR056S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder
		 * modificarlas una para la entrada y otra para la salida.
		 */

		entrada = (TR056E) getXmlProcessor().unmarshal(msg);
		salida = new TR056S();

		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_056_s.status");

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
		ConfClienteZTEEmuRespuesta res = new ConfClienteZTEEmuRespuesta(
				responseMessage);
		respuestas.add(res);
		return respuestas;
	}

}