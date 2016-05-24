package co.com.telefonica.atiempo.sigres.emu.tr.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.telefonica.atiempo.sigres.emu.tr.TRMessageProcess;



/**
 * ConfigurarInternet
 * 
 * Corresponde al proceso emulado de la actividad de configurar internet.
 * 
 * @author Gonzalo Arreche
 *
 */
public class ConfigurarInternet extends TRMessageProcess{

    public List emulateResponse(String msg) {
    	
        respuestas = new ArrayList();
//        /* Entradas del proceso*/
//        TR030E entrada =null;
//        /* Posibles respuestas*/
//        TR031S ack = null;
//        TR032S respuesta = null;
//        TR034S cierre = null;
//        /* Instancia del mensaje de entrada.*/
//        entrada = (TR030E)getXmlProcessor().unmarshal(msg);
//        
//        /*
//         * Se consulta el properties para conocer el estado de respuesta
//         * definido para este caso emulado.
//         */
//        String resultado = getTrProperties().getProperty("tr_031_s.status");
//        
//        if(resultado.equalsIgnoreCase("ok")){
//            /* Instancias de las respuestas de SIGRES*/
//            ack = (TR031S)getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_031_s.xml"));
//            respuesta = (TR032S)getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_032_s.xml"));
//            cierre = (TR034S)getXmlProcessor().unmarshal(ServiceLocatorEmulator.getRecurso("tr_034_s.xml"));
//            
//            /* Move del campo id */
//            ack.setId(entrada.getId());
//            respuesta.setId(entrada.getId());
//            cierre.setId(entrada.getId());
//            
//            ack.setError(false);
//            cierre.setError(false);
//            
//            /* Las transformo en XML para enviarlas*/
//            String ack_msj = getXmlProcessor().marshal(ack);
//            String respuesta_msj = getXmlProcessor().marshal(respuesta);
//            String cierre_msj = getXmlProcessor().marshal(cierre);
//            
//            /* Las agrego a la lista de respuestas para que se envien secuencialmente.*/
//            respuestas.add(ack_msj);
//            respuestas.add(respuesta_msj);
//            respuestas.add(cierre_msj);
//            
//        }else{
//            
//        }
        
        return respuestas;
    }

}
