/*
 * Created on Jan 6, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;

/**
 * @author idrincon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConsultarLineasTroncalSip extends TRMessageProcess{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		//TR708E entrada=null;
		//TR708S salida = null;
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		//entrada = (TR708E) getXmlProcessor().unmarshal(msg);
		
		return respuestas;
	}

}
