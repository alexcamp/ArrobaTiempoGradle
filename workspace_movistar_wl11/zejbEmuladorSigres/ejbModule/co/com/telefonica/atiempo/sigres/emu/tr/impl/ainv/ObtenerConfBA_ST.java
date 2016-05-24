/*
 * Created on May 22, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR014E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR014S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ObtenerConfBA_ST extends TRMessageProcess_ST {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
	   respuestas = new ArrayList();
	   /* Se definen las tr de entrada y salida que se van a utilizar*/
	   TR014E entrada=null;
	   TR014S salida = null;
	   /*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/
    
	   entrada = (TR014E) getXmlProcessor().unmarshal(msg);
	   salida = (TR014S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_014_S.xml"));
       
	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_014_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   if (resultado.equalsIgnoreCase("ok")) {
				  
		   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
		   salida.setId(entrada.getId());
			salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
		    salida.setDslamIp("192.168.1.1");
		    /*salida.setModemBrand(salida.getModemBrand());
		    salida.setModemModel(salida.getModemModel());
		    salida.setModemSerial(salida.getModemSerial());
		    salida.setModemType(salida.getModemType());*/
		   /* Se setea error false en la respuesta*/
		   salida.setError(false);
				  
				  
	   } else {
		   salida.setError(true);
		   salida.setErrorMessage("Error obteniendo los modems.");
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   ObCBA_Respuesta_ST r = new ObCBA_Respuesta_ST(responseMessage);
	   respuestas.add(r);
	   return respuestas;
	}

}
