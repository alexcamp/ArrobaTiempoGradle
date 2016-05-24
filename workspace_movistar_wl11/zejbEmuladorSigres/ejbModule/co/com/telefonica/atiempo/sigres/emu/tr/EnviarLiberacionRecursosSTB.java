/*
 * Created on 28/02/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR002E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR002S;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author fmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnviarLiberacionRecursosSTB extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR002E entrada=null;
		TR002S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		
		entrada = (TR002E) getXmlProcessor().unmarshal(msg);
		salida = (TR002S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_002_S.xml"));
		
		 /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_002_s.status");
	   
	   resultado="ok";
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   if (resultado.equals("ok")) {
	   	salida.setId(entrada.getId());
	   	salida.setError(false);
	   	salida.setErrorMessage("");
	   	salida.setTypeError(0);
	   	salida.setResponse(true);
	   	salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
	   }else{
	   	salida.setId(entrada.getId());
	   	salida.setError(true);
	   	salida.setErrorMessage("Error en la simulación de la TR002S");
	   	salida.setTypeError(0);
	   	salida.setResponse(false);
	   	salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   ObtenerLiberacionRecursosSTB liberacionRecursosSTB= new ObtenerLiberacionRecursosSTB(responseMessage);
	   respuestas.add(liberacionRecursosSTB);
	   return respuestas;
	}

}
