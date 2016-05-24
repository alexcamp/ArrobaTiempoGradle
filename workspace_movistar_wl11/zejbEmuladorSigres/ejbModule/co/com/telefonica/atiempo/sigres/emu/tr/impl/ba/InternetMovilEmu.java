/*
 * Created on 02/06/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ba;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR610E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR610S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author fmendez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InternetMovilEmu extends TRMessageProcess {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess#emulateResponse(java.lang.String)
	 */
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
		ArrayList respuestas = new ArrayList();
		/* Se definen las tr de entrada y salida que se van a utilizar*/
		TR610E entrada=null;
		TR610S salida = null;
	
		/*
		 * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		 * una para la entrada y otra para la salida.
		 */
		/*
		* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
		* una para la entrada y otra para la salida.
		*/

	   entrada = (TR610E) getXmlProcessor().unmarshal(msg);
	   salida = (TR610S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_610_S.xml"));

	   /*
		* Se consulta el properties para conocer el estado de respuesta
		* definido para este caso emulado.
		*/
	   String resultado = getTrProperties().getProperty("tr_610_s.status");
	   /*
		* De acuerdo al resultado se procesan los datos segun convenga.
		*/
	   
	   salida.setDestination("ATIEMPO");
	   salida.setInterfaz("CONFIG_INTERNET_MOVIL");
	   salida.setSource("ESB");
	   salida.setVersion("1.0");
	   salida.setId(entrada.getId());
	   
	   if (resultado.equals("ok")) {

		   /* Se setea error false en la respuesta*/
		   salida.setError("0");
		   salida.setErrorMessage("");
		   
		   salida.setIcc("ICC_PRUEBA");		   
		   salida.setClientIdType(entrada.getClientIdType());
		   salida.setClientIdNumber(entrada.getClientIdNumber());
		   salida.setModemSerialNumber("11111111111");
		   salida.setRatePlan(2);
		   salida.setObservation("OBSERVACIONES_PRUEBA");
		   salida.setSalesNumber(123456789);
		   salida.setSalesValue(1111222233);		  
		   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
		  
	   } else {	       
		   /* Se setea error true en la respuesta*/
	       salida.setError("1");
		   salida.setErrorMessage("Error de prueba para Internet Movil");
		   
		   salida.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
		   
	   }
	   /*
		* Se genera el o los String de salida y se agregan a la lista de respuestas.
		*/
	   String responseMessage = getXmlProcessor().marshal(salida);
	   InternetMovilEmuRespuesta r = new InternetMovilEmuRespuesta(responseMessage);
	   respuestas.add(r);

	   return respuestas;
	}

}
