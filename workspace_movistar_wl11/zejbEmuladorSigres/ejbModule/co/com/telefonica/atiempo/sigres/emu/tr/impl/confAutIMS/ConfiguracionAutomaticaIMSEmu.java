/*
 * Creado el 24/09/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.confAutIMS;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR510E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR510S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR602E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR602S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;


/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfiguracionAutomaticaIMSEmu extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
				TR602E entrada=null;
				TR602S salida = null;
			   
				entrada = (TR602E) getXmlProcessor().unmarshal(msg);
				salida = (TR602S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_602_S.xml"));
				salida.setId(entrada.getId());
				   
			   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
				
				/*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
				//salida.setId(entrada.getId());
				
			   String responseMessage = getXmlProcessor().marshal(salida);
			   ConfiguracionAutomaticaIMSEmuRespuesta r = new ConfiguracionAutomaticaIMSEmuRespuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}
}
