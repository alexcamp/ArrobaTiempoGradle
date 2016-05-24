/*
 * Creado el 28/02/2011
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TRSMSE;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;

/**
 * @author damartinezv
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EnviarSMSACS extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
   
		   ArrayList respuestas = new ArrayList();
		   TRSMSE entrada=null;
		   entrada = (TRSMSE) getXmlProcessor().unmarshal(msg);		   
		   return respuestas;
	}
}
