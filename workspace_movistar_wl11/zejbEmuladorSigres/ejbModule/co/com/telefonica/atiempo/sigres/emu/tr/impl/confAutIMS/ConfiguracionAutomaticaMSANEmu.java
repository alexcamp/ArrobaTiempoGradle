/*
 * Creado el 24/09/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.confAutIMS;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR602E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR602S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR603E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR603S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConfiguracionAutomaticaMSANEmu extends TRMessageProcess{
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
	   ArrayList respuestas = new ArrayList();
	   /* Se definen las tr de entrada y salida que se van a utilizar*/
		TR603E entrada=null;
		TR603S salida = null;
		entrada = (TR603E) getXmlProcessor().unmarshal(msg);
		salida = (TR603S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_603_S.xml"));
		salida.setId(entrada.getId());
		    
	   salida.setAtiempoRequestNumber(new Long (entrada.getAtiempoRequestNumber()).longValue());
		
		
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   ConfiguracionAutomaticaMSANEmuRespuesta r = new ConfiguracionAutomaticaMSANEmuRespuesta(responseMessage);
	   respuestas.add(r);
	   return respuestas;

}
	}
