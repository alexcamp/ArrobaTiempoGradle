package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR036E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR037S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BajaBASigres extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR036E entrada=null;
		TR037S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		log.info("Mensaje de entrada: " + msg);
		entrada = (TR036E) getXmlProcessor().unmarshal(msg);
		salida = (TR037S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_037_S.xml"));
       
		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_037_s.status");
		salida.setId(entrada.getId());
		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		if (resultado.equals("ok")) {
			/* Simplemente aqui se mueve el ID de la entrada a la salida*/
			/* Se setea error false en la respuesta*/
			salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
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
		BajaBASigres_Respuesta r = new BajaBASigres_Respuesta(responseMessage);
		respuestas.add(r);
		return respuestas;
	}

}
