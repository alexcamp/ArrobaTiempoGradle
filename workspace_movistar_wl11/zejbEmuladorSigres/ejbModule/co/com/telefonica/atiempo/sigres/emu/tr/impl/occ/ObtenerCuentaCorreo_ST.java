/*
 * Created on May 7, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.occ;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR043E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ObtenerCuentaCorreo_ST extends TRMessageProcess_ST{

	
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
			   TR043E entrada=null;
			   TR043S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR043E) getXmlProcessor().unmarshal(msg);
			   salida = (TR043S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_043_S.xml"));
       
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_043_s.status");
			   /*
				* De acuerdo al resultado se procesan los datos segun convenga.
				*/
			   if (resultado.equals("ok")) {
				   String email = getTrProperties().getProperty("tr_043_s.fatherEmail");
				   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
				   salida.setId(entrada.getId());
					salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
				   /* Se setea error false en la respuesta*/
				   salida.setError(false);
				   salida.setCode("0000");
				   salida.setFatherEmail(email);
				  
			   } else {
			       salida.setId(entrada.getId());
				   salida.setError(true);
				   salida.setCode("0001");
				   salida.setErrorMessage("Error obteniendo la cuenta de Correo.");
			   }
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
			   OCC_Respuesta_ST r = new OCC_Respuesta_ST(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
