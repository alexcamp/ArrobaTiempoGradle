/*
 * Creado el 23/09/2010
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.refBA;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR708E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ActivacionRecursosAgendaSCST extends TRMessageProcess_ST{
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
