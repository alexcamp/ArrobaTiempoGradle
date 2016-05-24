/*
 * SolicitudInformacionTecnicaTV.java
 *
 * Created on 18 de abril de 2007, 03:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * mensaje tr019e
 *
 * @author francois
 */
public class SolicitudInformacionTecnicaTV
    extends QManagerService
{
    // TODO revisar nombre cola
    private static final String QUEUE = "jms/QMAT/IT_BUS_GEST_CONF_ACTUAL_Q";
    
    /** Creates a new instance of ActualizacionInventarioTVMQ */
    public SolicitudInformacionTecnicaTV ()
    throws ATiempoAppEx
    {
        super (QUEUE);
    }
    
    /**
     * fbd: algun dia entendere a que sirve este metodo
     *
     * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
     */
    
    protected Object procesarDatos (Object obj)
    {
        return obj;
    }   
}
