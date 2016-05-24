/*
 * Created on Oct 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.opr;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR043E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefrescarRecursosST extends TRMessageProcess_ST{
	 public List emulateResponse(String message) {
        /* Este ArrayList es para cargar las respuestas*/        
        ArrayList respuestas = new ArrayList();
        /* Se definen las tr de entrada y salida que se van a utilizar*/
        TR043E entrada=null;
        TR043S salida = null;
        /*
         * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
         * una para la entrada y otra para la salida.
         */
    	log.info("Mensaje de entrada: " + message);
        entrada = (TR043E) getXmlProcessor().unmarshal(message);
        salida = (TR043S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_043_S.xml"));
       
        /*
         * Se consulta el properties para conocer el estado de respuesta
         * definido para este caso emulado.
         */
        String resultado = getTrProperties().getProperty("tr_043_s.status");
        salida.setId(entrada.getId());
        /*
         * De acuerdo al resultado se procesan los datos segun convenga.
         */
//        if (resultado.equals("ok")) {
//            String zone = getTrProperties().getProperty("tr_012_s.zone");
//            /* Simplemente aqui se mueve el ID de la entrada a la salida*/
//            /* Se setea error false en la respuesta*/
//            salida.setError(false);
//            salida.setBox(zone);
//        } else {
//            salida.setError(true);
//            salida.setErrorMessage("Error obteniendo las zonas de cobertura.");
//        }
        /*
         * Se genera el o los String de salida y se agregan a la lista de respuestas.
         */
        String responseMessage = getXmlProcessor().marshal(salida);
        Respuesta_ST r = new Respuesta_ST(responseMessage);
        respuestas.add(r);
        return respuestas;
    }

}
