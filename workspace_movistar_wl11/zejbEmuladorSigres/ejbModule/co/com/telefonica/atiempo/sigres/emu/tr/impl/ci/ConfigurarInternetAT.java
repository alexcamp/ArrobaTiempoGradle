/*
 * Created on May 22, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.ci;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR013E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR013S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfigurarInternetAT extends TRMessageProcess{

	public List emulateResponse(String msg) {
		respuestas = new ArrayList();
		TR013E entrada = (TR013E)getXmlProcessor().unmarshal(msg);
		TR013S respuestaHC = (TR013S)getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_013_S.xml"));
		
		String resultado = ServiceLocatorEmulator.getTRProperties().getProperty("tr_013_s.status");
		
		respuestaHC.setAtisRequestNumber(entrada.getAtisRequestNumber());
		respuestaHC.setId(entrada.getId());	
		respuestaHC.setActualDslamIp("192.168.1.1");
		if("ok".equals(resultado)){
			
			respuestaHC.setError(false);
		}else{
			
					respuestaHC.setError(true);
					respuestaHC.setErrorMessage("No hay recursos en HC.");
		}
	
		String message=getXmlProcessor().marshal(respuestaHC);
		CI_respuestaHC respuesta = new CI_respuestaHC(message);
		
		respuestas.add(respuesta);
		return respuestas;
	}

}
