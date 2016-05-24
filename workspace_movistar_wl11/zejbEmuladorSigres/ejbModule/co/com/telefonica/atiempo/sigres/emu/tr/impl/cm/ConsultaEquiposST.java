/*
 * Created on Mar 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.cm;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR026E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR026S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConsultaEquiposST extends TRMessageProcess_ST {
    
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
			   TR026E entrada=null;
			   TR026S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR026E) getXmlProcessor().unmarshal(msg);
			   salida = (TR026S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_026_S.xml"));
       
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_026_s.status");
			   /*
				* De acuerdo al resultado se procesan los datos segun convenga.
				*/
			   if (resultado.equals("ok")) {
				  
				   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
				   salida.setId(entrada.getId());
					salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
				   /* Se setea error false en la respuesta*/
				   salida.setErrorCode(0);
				  
				  
			   } else {
			       salida.setId(entrada.getId());
			       salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
				   salida.setErrorCode(1);
				   salida.setErrorMessage("Error obteniendo los equipos.");
			   }
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
			   CE_Respuesta_ST r = new CE_Respuesta_ST(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
