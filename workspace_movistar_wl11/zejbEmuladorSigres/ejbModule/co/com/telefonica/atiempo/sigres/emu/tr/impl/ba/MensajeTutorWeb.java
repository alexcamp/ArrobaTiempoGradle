/*
 * Created on Dec 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR047E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR047S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA.RespuestaTutorWeb;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MensajeTutorWeb extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
			/* Este ArrayList es para cargar las respuestas*/        
			ArrayList respuestas = new ArrayList();
			/* Se definen las tr de entrada y salida que se van a utilizar*/
			TR047E entrada=null;
			TR047S salida = null;
			/*
			 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			 * una para la entrada y otra para la salida.
			 */
			entrada = (TR047E) getXmlProcessor().unmarshal(msg);
			salida = (TR047S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_047_S.xml"));
	       
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
				salida.setId(entrada.getId());
				//salida.setError("false");
			} else {
				salida.setId(entrada.getId());
			}
			/*
			 * Se genera el o los String de salida y se agregan a la lista de respuestas.
			 */
			 String responseMessage = getXmlProcessor().marshal(salida);
			 RespuestaTutorWeb r = new RespuestaTutorWeb(responseMessage);
			 respuestas.add(r);
			 return respuestas;
	}
}
