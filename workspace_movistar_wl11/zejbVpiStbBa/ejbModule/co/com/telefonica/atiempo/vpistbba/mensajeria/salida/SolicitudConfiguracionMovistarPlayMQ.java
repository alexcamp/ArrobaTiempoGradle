/*
 * SolicitudConfiguracionServiciosTV.java
 *
 * Created on 9 de abril de 2007, 11:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR605E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * mensaje TR_017_E
 *
 * @author francois
 */
public class SolicitudConfiguracionMovistarPlayMQ
    extends QManagerService
{
    private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_MOVISTAR_PLAY_Q";
    
    /** Creates a new instance of SolicitaDecoTarjActualizar */
    public SolicitudConfiguracionMovistarPlayMQ ()
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
        // TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_010_E
        if (obj instanceof TR605E)
        {
			TR605E tr605e = (TR605E) obj;
			
		}
        return obj;
    }   
}
