package co.com.telefonica.atiempo.sigres.emu.tr.impl.ci;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR030E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR031S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR032S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR034S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * ConfigurarInternet
 * 
 * Corresponde al proceso emulado de la actividad de configurar internet.
 * 
 * @author Gonzalo Arreche
 *
 */
public class ConfigurarInternet extends TRMessageProcess {

	public List emulateResponse(String msg) {

		respuestas = new ArrayList();
		/* Entradas del proceso*/
		TR030E entrada = null;
		/* Posibles respuestas*/
		TR031S ack = null;
		TR032S respuesta = null;
		TR034S cierre = null;
		/* Instancia del mensaje de entrada.*/
		entrada = (TR030E) getXmlProcessor().unmarshal(msg);

		/*
		 * Se consulta el properties para conocer el estado de respuesta
		 * definido para este caso emulado.
		 */
		String resultado = getTrProperties().getProperty("tr_031_s.status");

		if (resultado.equalsIgnoreCase("ok")) {
			/* Instancias de las respuestas de SIGRES*/
			ack = (TR031S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_031_s.xml"));
			respuesta = (TR032S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_032_s.xml"));
			cierre = (TR034S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_034_s.xml"));

			/* Move del campo id */
			ack.setId(entrada.getId());
			ack.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			respuesta.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			respuesta.setId(entrada.getId());
			cierre.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			cierre.setId(entrada.getId());
			
			ack.setAction("0");
			ack.setError(false);
			cierre.setError(false);

			/* Las transformo en XML para enviarlas*/
			String ack_msj = getXmlProcessor().marshal(ack);
			String respuesta_msj = getXmlProcessor().marshal(respuesta);
			String cierre_msj = getXmlProcessor().marshal(cierre);

			CI_ACK r_ack = new CI_ACK(ack_msj);
			CI_Respuesta r_respuesta = new CI_Respuesta(respuesta_msj);
			CI_Cierre r_cierre = new CI_Cierre(cierre_msj);

			/* Las agrego a la lista de respuestas para que se envien secuencialmente.*/
			respuestas.add(r_ack);
			respuestas.add(r_cierre);
			respuestas.add(r_respuesta);
			

		} else if (resultado.equalsIgnoreCase("failed")) {

			/* Instancias de las respuestas de SIGRES*/
			ack = (TR031S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_031_s.xml"));

			/* Move del campo id */
			ack.setId(entrada.getId());
			ack.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());

			//ack.setError(true);
			//ack.setErrorMessage("Error en el ACK.");
			ack.setCode("0002");
			ack.setDescription("No hay recursos disponibles");
			ack.setAction("1");
			
			/* Las transformo en XML para enviarlas*/
			String ack_msj = getXmlProcessor().marshal(ack);

			CI_ACK r_ack = new CI_ACK(ack_msj);

			/* Las agrego a la lista de respuestas para que se envien secuencialmente.*/
			respuestas.add(r_ack);

		} else if (resultado.equalsIgnoreCase("aprov-failed")) {

			/* Instancias de las respuestas de SIGRES*/
			ack = (TR031S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_031_s.xml"));
			cierre = (TR034S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_034_s.xml"));

			/* Move del campo id */
			ack.setId(entrada.getId());
			ack.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());
			cierre.setId(entrada.getId());
			cierre.setAtiempoRequestNumber(entrada.getAtiempoRequestNumber());

			ack.setError(false);
			cierre.setCode("0001");
			cierre.setErrorMessage("Error de aprovisionamiento en SIGRES.");

			/* Las transformo en XML para enviarlas*/
			String ack_msj = getXmlProcessor().marshal(ack);
			String cierre_msj = getXmlProcessor().marshal(cierre);

			CI_ACK r_ack = new CI_ACK(ack_msj);
			CI_Cierre r_cierre = new CI_Cierre(cierre_msj);

			/* Las agrego a la lista de respuestas para que se envien secuencialmente.*/
			respuestas.add(r_ack);
			respuestas.add(r_cierre);

		} else {
			log.error("Valor '"+resultado+"' incorrecto del properties de Configurar internet");
		}

		return respuestas;
	}
}
