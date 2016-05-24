/*
 * Created on Mar 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.cequipos;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR027E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObtEquipos extends TRMessageProcess{

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
     */
    public List emulateResponse(String msg) {
        ArrayList respuestas = new ArrayList();
		   /* Se definen las tr de entrada y salida que se van a utilizar*/
		   TR027E entrada=null;
		   TR027S salida = null;
		   /*
			* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			* una para la entrada y otra para la salida.
			*/

		   entrada = (TR027E) getXmlProcessor().unmarshal(msg);
		   salida = (TR027S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_027_S.xml"));

		   /*
			* Se consulta el properties para conocer el estado de respuesta
			* definido para este caso emulado.
			*/
		   String resultado = getTrProperties().getProperty("tr_027_s.status");
		   /*
			* De acuerdo al resultado se procesan los datos segun convenga.
			*/
		   if (resultado.equals("ok")) {
			  
			   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
			   salida.setId(entrada.getId());
				
			   /* Se setea error false en la respuesta*/
			   salida.setErrorCode(0);
			  
			  
		   } else {
			   salida.setErrorCode(1);
			   salida.setErrorMessage("Error obteniendo los modems.");
		   }
		   /*
			* Se genera el o los String de salida y se agregan a la lista de respuestas.
			*/
		   String responseMessage = getXmlProcessor().marshal(salida);
			ObtEquipos_Respuesta r = new ObtEquipos_Respuesta(responseMessage);
		   respuestas.add(r);
		   return respuestas;
    }

}
