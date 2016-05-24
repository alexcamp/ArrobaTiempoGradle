package co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR035E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RefrescarDatosBA_ST extends TRMessageProcess_ST{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR035E entrada=null;
		TR035S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		log.info("Mensaje de entrada: " + msg);
		entrada = (TR035E) getXmlProcessor().unmarshal(msg);
		salida = (TR035S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_035_S.xml"));
       
		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_035_s.status");
		salida.setId(entrada.getId());
		/*
		 * De acuerdo al resultado se procesan los datos segun convenga.
		 */
		if (resultado.equals("ok")) {
			/* Simplemente aqui se mueve el ID de la entrada a la salida*/
			/* Se setea error false en la respuesta*/
			salida.setServiceNumber(entrada.getServiceNumber());
			salida.setCode("0000");
			salida.setDescription("OK");
			
		} else {
			salida.setError(true);
			salida.setCode("0003");
			salida.setErrorMessage("Error en Refrescar Recursos.");
		}
		/*
		 * Se genera el o los String de salida y se agregan a la lista de respuestas.
		 */
		String responseMessage = getXmlProcessor().marshal(salida);
		RefrescarDatosBA_Respuesta_ST r = new RefrescarDatosBA_Respuesta_ST(responseMessage);
		respuestas.add(r);
		return respuestas;
	}

}
