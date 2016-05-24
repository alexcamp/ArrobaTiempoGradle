/*
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR020E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * mensaje TR_020_E
 *
 * @author mfmendez
 */
public class STNotificacionSAPMQ
    extends QManagerService
{
    private static final String QUEUE = "IT_BUS_NOTIFICACION_SAP_ST";
    
    public STNotificacionSAPMQ () throws ATiempoAppEx {
		super(QUEUE);
    }
    
    /**
     * fbd: algun dia entendere a que sirve este metodo
     *
     * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
     */
    protected Object procesarDatos (Object obj)
    {
		if (obj instanceof TR020E)
		{
			TR020E tr020e = (TR020E) obj;
	
		}
        return obj;
    }   
}
