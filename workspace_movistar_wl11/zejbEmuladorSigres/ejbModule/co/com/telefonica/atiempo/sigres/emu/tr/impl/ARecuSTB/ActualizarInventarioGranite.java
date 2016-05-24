/*
 * Created on Oct 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ARecuSTB;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR512E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActualizarInventarioGranite extends TRMessageProcess{
	public List emulateResponse(String msg) {
		/* Este ArrayList es para cargar las respuestas*/        
			   ArrayList respuestas = new ArrayList();
			   /* Se definen las tr de entrada y salida que se van a utilizar*/
				TR512E entrada=null;
				TR512S salida = null;
			   /*
				* Se generan objetos a partir de las TR de ejemplo para poder modificarlas
				* una para la entrada y otra para la salida.
				*/
    
			   entrada = (TR512E) getXmlProcessor().unmarshal(msg);
			   salida = (TR512S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_512_S.xml"));
       
			   /*
				* Se consulta el properties para conocer el estado de respuesta
				* definido para este caso emulado.
				*/
			   String resultado = getTrProperties().getProperty("tr_512_s.status");
			   /*
				* De acuerdo al resultado se procesan los datos segun convenga.
				*/
			   if (resultado.equals("ok")) {
				  
				   /* Simplemente aqui se mueve el ID de la entrada a la salida*/
				   salida.setId(entrada.getId());
					salida.setAtiempoRequestNumber(entrada.getAtisRequestNumber().longValue());
				   /* Se setea error false en la respuesta*/
				   salida.setErrorCode(0);
				   
				   // Si se trata de un traslado con igual origen que destino seteo el phoneNumber a 0 así 
				   // la actividad ActualizarInventario no me actualiza el campo recursos_linea_basica.tel_asg
				   if (getTrProperties().getProperty("tr_512_s.origenYDestinoIguales").equals("true")) {
				   		salida.setPhoneNumber(0);
				   }
				  
				  
			   } else {
				   salida.setErrorCode(1);
				   salida.setErrorMessage("Error informando la actualizacion de inventario .");
			   }
			   /*
				* Se genera el o los String de salida y se agregan a la lista de respuestas.
				*/
			   String responseMessage = getXmlProcessor().marshal(salida);
				AInvGR_Respuesta r = new AInvGR_Respuesta(responseMessage);
			   respuestas.add(r);
			   return respuestas;
	}

}
