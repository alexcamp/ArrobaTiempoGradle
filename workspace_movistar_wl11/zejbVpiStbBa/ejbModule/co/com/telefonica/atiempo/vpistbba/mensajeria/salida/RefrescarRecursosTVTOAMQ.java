/*
 * ActualizacionInventarioTVMQ.java
 *
 * Created on 18 de abril de 2007, 11:05 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 *
 * @author francois
 */
public class RefrescarRecursosTVTOAMQ
    extends QManagerService
{
    // TODO revisar nombre cola
    private static final String QUEUE = "jms/QMAT/IT_BUS_REFRESH_TV_TOA_Q";
    
    /** Creates a new instance of ActualizacionInventarioTVMQ */
    public RefrescarRecursosTVTOAMQ ()
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
