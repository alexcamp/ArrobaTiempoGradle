/*
 * SolicitudConfiguracionServiciosTV.java
 *
 * Created on 9 de abril de 2007, 11:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR017E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * mensaje TR_017_E
 *
 * @author francois
 */
public class SolicitudConfiguracionServiciosTVMQ
    extends QManagerService
{
    private static final String QUEUE = "jms/QMAT/IT_BUS_GESTIONAR_DTH_Q";
    
    /** Creates a new instance of SolicitaDecoTarjActualizar */
    public SolicitudConfiguracionServiciosTVMQ ()
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
        if (obj instanceof TR017E)
        {
			TR017E tr017e = (TR017E) obj;
			
		}
        return obj;
    }   
}
