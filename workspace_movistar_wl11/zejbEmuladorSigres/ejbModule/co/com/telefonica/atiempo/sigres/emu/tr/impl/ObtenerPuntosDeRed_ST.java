package co.com.telefonica.atiempo.sigres.emu.tr.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.TR012E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;


/**
 * ObtenerPuntosDeRed
 * 
 * Es el proceso emulado del paso Obtener puntos de red para el Alta de BA.
 * 
 * @author Gonzalo Arreche
 *  
 */
public class ObtenerPuntosDeRed_ST extends TRMessageProcess {

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
        TR012E entrada=null;
        TR012S salida = null;
        /*
         * Se generan objetos a partir de las TR de ejemplo para poder modificarlas
         * una para la entrada y otra para la salida.
         */
    
        entrada = (TR012E) getXmlProcessor().unmarshal(message);
        salida = (TR012S) getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("TR_012_S.xml"));
       
        /*
         * Se consulta el properties para conocer el estado de respuesta
         * definido para este caso emulado.
         */
        String resultado = getTrProperties().getProperty("tr_012_s.status");
        /*
         * De acuerdo al resultado se procesan los datos segun convenga.
         */
        if (resultado.equals("ok")) {
            String zone = getTrProperties().getProperty("tr_012_s.zone");
            /* Simplemente aqui se mueve el ID de la entrada a la salida*/
            salida.setId(entrada.getId());
            /* Se setea error false en la respuesta*/
            salida.setError(false);
            salida.setBox(zone);
        } else {
            salida.setError(true);
            salida.setErrorMessage("Error obteniendo las zonas de cobertura.");
        }
        /*
         * Se genera el o los String de salida y se agregan a la lista de respuestas.
         */
        String responseMessage = getXmlProcessor().marshal(salida);
        respuestas.add(responseMessage);
        return respuestas;
    }

}
