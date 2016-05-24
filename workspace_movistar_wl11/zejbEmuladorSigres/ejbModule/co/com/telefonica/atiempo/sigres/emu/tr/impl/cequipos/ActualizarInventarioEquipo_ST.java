/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos;

import java.util.ArrayList;
import java.util.List;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActualizarInventarioEquipo_ST extends TRMessageProcess_ST {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
			   TR028E entrada=null;
			   TR028S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR028E) getXmlProcessor().unmarshal(msg);
			   salida = (TR028S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_028_S.xml"));
       
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_028_s.status");
			   /*
				* De acuerdo al resultado se procesan los datos segun convenga.
				*/
			   if (resultado.equals("ok")) {
				  
				   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
				   salida.setId(entrada.getId());
					
				   /* Se setea error false en la respuesta*/
				   salida.setErrorCode(0);
				  
				  
			   } else {
			       salida.setId(entrada.getId());
				   salida.setErrorCode(1);
				   salida.setErrorMessage("Error obteniendo los modems.");
			   }
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
				ACINV_RespuestaEquipo_ST r = new ACINV_RespuestaEquipo_ST(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
