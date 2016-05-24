/*
 * Created on May 22, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ci;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR023E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR023S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfigurarTerra extends TRMessageProcess{

	public List emulateResponse(String msg) {
		respuestas = new ArrayList();
		TR023E entrada = (TR023E)getXmlProcessor().unmarshal(msg);
		TR023S respuestaHC = (TR023S)getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_023_S.xml"));
		
		String resultado = ServiceLocatorEmulator.getTRProperties().getProperty("tr_023_s.status");
		String cna = ServiceLocatorEmulator.getTRProperties().getProperty("tr_023_s.cna");
		
		respuestaHC.setAtisRequestNumber(entrada.getAtisRequestNumber());
		respuestaHC.setId(entrada.getId());			
		if("ok".equals(resultado)){
			
			respuestaHC.setError(false);
			respuestaHC.setCna("000");
		}else{
			
					respuestaHC.setError(true);
					respuestaHC.setErrorMessage("Error en Terra.");
					
					// codigo de error asociado a la TR023 en la tabla ERROR_LEGADO
					respuestaHC.setCna(cna);
		}
	
		String message=getXmlProcessor().marshal(respuestaHC);
		RespuestaTerra respuesta = new RespuestaTerra(message);
		
		respuestas.add(respuesta);
		return respuestas;
	}

}
