/*
 * Created on May 7, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.iri;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR033E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR034S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 804218
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformarResultadoInstalacion extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
	   ArrayList respuestas = new ArrayList();
	   /* Se definen las tr de entrada y salida que se van a utilizar*/
	   TR033E entrada=null;
	   TR034S salida = null;
	   /*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/
    
	   entrada = (TR033E) getXmlProcessor().unmarshal(msg);
	   salida = (TR034S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_034_S.xml"));
       
	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_034_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   if (resultado.equals("ok")) {
		   
		   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
		   salida.setId(entrada.getId());
		   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());

		   /* Se setea error false en la respuesta*/
		   salida.setError(false);
		   
	   } else {
		   salida.setError(true);
		   salida.setErrorMessage("Error en la instalacion.");
		   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
		   salida.setCode(entrada.getCode());
		   salida.setDescription(entrada.getDescription());
		   salida.setId(entrada.getId());
		   salida.setAction("1");

	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   IRI_Respuesta r = new IRI_Respuesta(responseMessage);
	   respuestas.add(r);
	   return respuestas;
	}

}
