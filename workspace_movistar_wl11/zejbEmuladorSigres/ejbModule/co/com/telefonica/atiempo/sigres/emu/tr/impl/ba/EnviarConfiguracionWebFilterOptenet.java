/*
 * Creado el 22/11/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR044EEquipment;
import co.com.telefonica.atiempo.interfaces.atiempo.TR048E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR048S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EnviarConfiguracionWebFilterOptenet extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		   ArrayList respuestas = new ArrayList();
		   /* Se definen las tr de entrada y salida que se van a utilizar*/
		   TR048E entrada=null;
		   TR048S salida = null;
		   /*
			* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
			* una para la entrada y otra para la salida.
			*/

		   entrada = (TR048E) getXmlProcessor().unmarshal(msg);
		   salida = (TR048S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_048_S.xml"));

		   /*
			* Se consulta el properties para conocer el estado de respuesta
			* definido para este caso emulado.
			*/
		   String resultado = getTrProperties().getProperty("tr_048_s.status");
		   
		   resultado="ok";
		   /*
			* De acuerdo al resultado se procesan los datos segun convenga.
			*/
		   if (resultado.equals("ok")) {
			  
			   salida.setId(entrada.getId());
			   salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			   salida.setDestination(entrada.getDestination());
			   salida.setErrorCode("0");
			   salida.setInterfaz(entrada.getInterfaz());
			   salida.setSource(entrada.getSource());
			   salida.setVersion(entrada.getVersion());
			   salida.setError("No error");
			   salida.setErrorMessage("No error message");
			   
		   } else {
		   	   salida.setId(entrada.getId());
			   salida.setAtisRequestNumber(entrada.getAtisRequestNumber());
			   salida.setDestination(entrada.getDestination());
			   salida.setErrorCode("00");
			   salida.setInterfaz(entrada.getInterfaz());
			   salida.setSource(entrada.getSource());
			   salida.setVersion(entrada.getVersion());
			   salida.setError("Error");
			   salida.setErrorMessage("Error message");
		   }
		   /*
			* Se genera el o los String de salida y se agregan a la lista de respuestas.
			*/
		   String responseMessage = getXmlProcessor().marshal(salida);
		   ObtenerConfiguracionWebFilterOptenet r = new ObtenerConfiguracionWebFilterOptenet(responseMessage);
		   respuestas.add(r);
		   return respuestas;
}
}
