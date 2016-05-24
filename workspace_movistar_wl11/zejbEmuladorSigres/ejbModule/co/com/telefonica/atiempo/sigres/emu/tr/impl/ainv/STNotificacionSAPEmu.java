/*
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ainv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR020E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020ERequestHeader;
import co.com.telefonica.atiempo.interfaces.atiempo.TR020S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;

/**
 * @author mfmendez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class STNotificacionSAPEmu extends TRMessageProcess_ST{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR020E entrada=null;
		TR020S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR020E) getXmlProcessor().unmarshal(msg);
	   salida = (TR020S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_020_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_020_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   
	   salida.setDestination("ATIEMPO");
	   salida.setInterfaz("NOTIFICACION_SAP_ST");
	   salida.setSource("ESB");
	   salida.setVersion("1.0");
	   
	   Collection reqHeads = entrada.getRequestsHeader();
	   if(reqHeads != null && reqHeads.iterator().hasNext()){
	   		TR020ERequestHeader rh = (TR020ERequestHeader)reqHeads.iterator().next();
	   		salida.setAtiempoRequestNumber(rh.getAtiempoRequestNumber());
	   }else{
	   		salida.setAtiempoRequestNumber("");
	   }
	   
	   if (resultado.equals("ok")) {
		  
		   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
		   salida.setId(entrada.getId());
		   /* Se setea error false en la respuesta*/
		   salida.setError("0");
		   salida.setErrorMessage("");
		  
		  
	   } else {
	       salida.setId(entrada.getId());
		   /* Se setea error true en la respuesta*/
	       salida.setError("1");
		   salida.setErrorMessage("Error por timeout en la respuesta desde MM SAP");		   
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   STNotificacionSAPEmuRespuesta r = new STNotificacionSAPEmuRespuesta(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}

}
