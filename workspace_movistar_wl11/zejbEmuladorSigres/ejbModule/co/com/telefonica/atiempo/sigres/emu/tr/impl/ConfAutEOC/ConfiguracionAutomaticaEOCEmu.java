/*
 * Created on Jun 29, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ConfAutEOC;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR518E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR518S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR705E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR705S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfiguracionAutomaticaEOCEmu extends TRMessageProcess {


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
				TR518E entrada=null;
				TR518S salida = null;
			   
				entrada = (TR518E) getXmlProcessor().unmarshal(msg);
				salida = (TR518S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_518_S.xml"));
				salida.setId(entrada.getId());
				   
			   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			   
				/*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
				//salida.setId(entrada.getId());
				
			   String responseMessage = getXmlProcessor().marshal(salida);
			   ConfiguracionAutomaticaEOCEmuRespuesta r = new ConfiguracionAutomaticaEOCEmuRespuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}
}