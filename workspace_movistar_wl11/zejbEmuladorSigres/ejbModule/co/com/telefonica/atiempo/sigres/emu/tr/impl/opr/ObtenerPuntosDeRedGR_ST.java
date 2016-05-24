package co.com.telefonica.atiempo.sigres.emu.tr.impl.opr;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR514E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess_ST;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;



public class ObtenerPuntosDeRedGR_ST extends TRMessageProcess_ST {

	
    /**
     * En este caso la respuesta emulada consulta las variables TR_030 dentro
     * del properties y en función de sus valores el resultado puede ser exitoso
     * o fallido y en el caso exitoso devuelve el valor definido en
     * tr_030_e.zone
     */
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
        salida = (TR514S) getXmlProcessor().unmarshal(ServiceLocatorEmulator_ST.getRecurso("TR_514_S.xml"));
       
        /*
         * Se consulta el properties para conocer el estado de respuesta
         * definido para este caso emulado.
         */
        String resultado = getTrProperties().getProperty("tr_514_s.status");
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
        OPR_RespuestaGR_ST r = new OPR_RespuestaGR_ST(responseMessage);
        respuestas.add(r);
        return respuestas;
    }
}