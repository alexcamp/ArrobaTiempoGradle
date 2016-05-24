/*
 * Created on Jun 3, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.opr;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR003S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EmuladorTr3 extends TRMessageProcess  {
	public List emulateResponse(String message) {
		 /* Este ArrayList es para cargar las respuestas*/        
		 ArrayList respuestas = new ArrayList();
		 /* Se definen las tr de entrada y salida que se van a utilizar*/
		// TR003E entrada=null;
		 TR003S salida = null;
		 /*
		  * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		  * una para la entrada y otra para la salida.
		  */
		 log.info("Mensaje de entrada: " + message);
		// entrada = (TR003E) getXmlProcessor().unmarshal(message);
		 salida = (TR003S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_012_S.xml"));
       
		 /*
		  * Se consulta el properties para conocer el estado de respuesta
		  * definido para este caso emulado.
		  */
		 String resultado = getTrProperties().getProperty("tr_012_s.status");
		// salida.setId(entrada.getId());
		 /*
		  * De acuerdo al resultado se procesan los datos segun convenga.
		  */
		 if (resultado.equals("ok")) {
			 String zone = getTrProperties().getProperty("tr_012_s.zone");
			 /* Simplemente aqui se mueve el ID de la entrada a la salida*/
			 /* Se setea error false en la respuesta*/
			 salida.setError(false);
			 salida.setBox(zone);
		 } else {
			 salida.setError(true);
			 salida.setErrorMessage("Error obteniendo las zonas de cobertura.");
		 }
		 /*
		  * Se genera el o los String de salida y se agregan a la lista de respuestas.
		  */
		 String responseMessage = getXmlProcessor().marshal(salida);
		 OPR_Respuesta r = new OPR_Respuesta(responseMessage);
		 respuestas.add(r);
		 return respuestas;
	 }

}
