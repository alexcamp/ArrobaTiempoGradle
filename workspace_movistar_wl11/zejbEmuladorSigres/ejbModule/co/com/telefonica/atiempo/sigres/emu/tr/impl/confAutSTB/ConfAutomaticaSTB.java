package co.com.telefonica.atiempo.sigres.emu.tr.impl.confAutSTB;

import java.util.ArrayList;
import java.util.List;


import co.com.telefonica.atiempo.interfaces.atiempo.TR511E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR511S;

import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfAutomaticaSTB extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
				TR511E entrada=null;
				TR511S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR511E) getXmlProcessor().unmarshal(msg);
			   salida = (TR511S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_511_S.xml"));
		
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_511_s.status");
			   /*
				* De acuerdo al resultado se procesan los datos segun convenga.
				*/
			   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
		   		salida.setId(entrada.getId());
		   		salida.setAtisRequestNumber(entrada.getAtisRequestNumber().longValue());	
			   if (resultado.equals("ok")) {
				   /* Se setea error false en la respuesta*/
				   salida.setErrorCode(0);
			   } else {
				   salida.setErrorCode(601);
				   salida.setErrorMessage("Error Emulando Configuracion Automatica de Recursos STB para Granite.");
			   }			   
			   
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
			   ConfAutomaticaSTBRespuesta r = new ConfAutomaticaSTBRespuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
