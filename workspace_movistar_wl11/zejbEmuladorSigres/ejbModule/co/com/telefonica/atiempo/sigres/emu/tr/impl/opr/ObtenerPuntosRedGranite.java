package co.com.telefonica.atiempo.sigres.emu.tr.impl.opr;

import java.util.ArrayList;
import java.util.List;

import com.tecnonautica.utiles.db.DBManager;

import co.com.telefonica.atiempo.interfaces.atiempo.TR514E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;


/**  
 * @author 808026
 *  
 */
public class ObtenerPuntosRedGranite extends TRMessageProcess {

    public List emulateResponse(String message) {
        /* Este ArrayList es para cargar las respuestas*/        
        ArrayList respuestas = new ArrayList();
        /* Se definen las tr de entrada y salida que se van a utilizar*/
        TR514E entrada=null;
        TR514S salida = null;
        /*
         * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
         * una para la entrada y otra para la salida.
         */
    	log.info("Mensaje de entrada: " + message);
        entrada = (TR514E) getXmlProcessor().unmarshal(message);
        salida = (TR514S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_514_S.xml"));
       
        /*
         * Se consulta el properties para conocer el estado de respuesta
         * definido para este caso emulado.
         */
        String resultado = getTrProperties().getProperty("tr_514_s.status");
        salida.setId(entrada.getId());
       
		if (getTrProperties().getProperty("tr_514_s.soporta_conf_automatica").equals("true")) {
			salida.setEoc(true);
		}
		
		System.out.println("(EN EMULADORES) SOPORTA CONF AUTOMATICA: " + getTrProperties().getProperty("tr_510_s.soporta_conf_automatica"));

        /*
         * De acuerdo al resultado se procesan los datos segun convenga.
         */
        if (resultado.equals("ok")) {
            String zone = getTrProperties().getProperty("tr_514_s.zone");
            /* Simplemente aqui se mueve el ID de la entrada a la salida*/
            /* Se setea error false en la respuesta*/
            salida.setErrorCode(0);
            salida.setBox(zone);
        } else {
            //salida.setErrorCode(0);
            salida.setErrorMessage("Error obteniendo las zonas de cobertura.");
        }
        /*
         * Se genera el o los String de salida y se agregan a la lista de respuestas.
         */
        String responseMessage = getXmlProcessor().marshal(salida);
        OPRGranite_Respuesta r = new OPRGranite_Respuesta(responseMessage);
        respuestas.add(r);
        return respuestas;
    }
}
