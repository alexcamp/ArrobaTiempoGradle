/*
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR025E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * mensaje TR_025_E
 *
 * @author mfmendez
 */
public class STVistaGlobalMQ
    extends QManagerService
{
    private static final String QUEUE = "IT_BUS_VISTA_GLOBAL_ST";
    
    public STVistaGlobalMQ () throws ATiempoAppEx {
		super(QUEUE);
    }
    
    /**
     * fbd: algun dia entendere a que sirve este metodo
     *
     * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
     */
    protected Object procesarDatos (Object obj)
    {
        // TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_010_E
		if (obj instanceof TR025E)
		{
			TR025E tr025e = (TR025E) obj;
	
		}
        return obj;
    }   
}
