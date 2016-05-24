/*
 * Creado el 10/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.confsxrxims;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR606E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR606S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfigurarSxRxIMSEmu extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
				TR606E entrada=null;
				TR606S salida = null;
			   
				entrada = (TR606E) getXmlProcessor().unmarshal(msg);
				salida = (TR606S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_606_S.xml"));
				salida.setId(entrada.getId());
				   
				//salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
				
				/*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
				//salida.setId(entrada.getId());
				
			   String responseMessage = getXmlProcessor().marshal(salida);
			   ConfigurarSxRxIMSEmuRespuesta r = new ConfigurarSxRxIMSEmuRespuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}
}
