/*
 * Creado el 21/09/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR035E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.ba.RespuestaCreacionActuacionAgendaSC;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CreacionActuacionAgendaSCST extends TRMessageProcess_ST{
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR701E entrada=null;
		TR701S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		entrada = (TR701E) getXmlProcessor().unmarshal(msg);
		salida = (TR701S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_701_S.xml"));
       
		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		//String resultado = getTrProperties().getProperty("tr_701_s.status");
		String resultado="ok";
		salida.setId(entrada.getId());
		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		if (resultado.equals("ok")) {
			/* Simplemente aqui se mueve el ID de la entrada a la salida*/
			/* Se setea error false en la respuesta*/
			salida.setAtisRequestNumber(entrada.getId());
			//salida.setError("false");
			salida.setErrorCode("0");
			salida.setErrorMessage("No hay error");
			salida.setId(entrada.getId());
			salida.setIdSchedule(entrada.getIdSchedule());
			salida.setIdSystemOrigin(entrada.getIdSystemOrigin());
			
		} else {
			salida.setAtisRequestNumber(entrada.getId());
			//salida.setError("true");
			salida.setErrorCode("1");
			salida.setErrorMessage("Error en creación de actuación al recibir respuesta de alta de actuación.");
			salida.setId(entrada.getId());
			salida.setIdSchedule(entrada.getIdSchedule());
			salida.setIdSystemOrigin(entrada.getIdSystemOrigin());
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de respuestas.
		 */
		 String responseMessage = getXmlProcessor().marshal(salida);
		 RespuestaCreacionActuacionAgendaSCST r = new RespuestaCreacionActuacionAgendaSCST(responseMessage);
		 respuestas.add(r);
		 return respuestas;
	}
}
