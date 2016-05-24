package co.com.telefonica.atiempo.sigres.emu.tr.impl.modOperadora;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR041E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR041S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ModificarIdOperadoraSigres extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR041E entrada=null;
		TR041S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		log.info("Mensaje de entrada: " + msg);
		entrada = (TR041E) getXmlProcessor().unmarshal(msg);
		salida = (TR041S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_041_S.xml"));
       
		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_041_s.status");
		salida.setId(entrada.getId());
		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		if (resultado.equals("ok")) {
			/* Simplemente aqui se mueve el ID de la entrada a la salida*/
			/* Se setea error false en la respuesta*/
			//salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			//salida.setCode("0001");
			salida.setDescription("OK");
			salida.setError(false);
			
		} else {
			salida.setError(true);
			salida.setErrorMessage("Error en Baja Sigres cobertura.");
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(salida);
		ModificarIdOperadoraSigres_Respuesta r = new ModificarIdOperadoraSigres_Respuesta(responseMessage);
		respuestas.add(r);
		return respuestas;
	}

}
