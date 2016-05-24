/*
 * SolicitaDecoTarjActualizar.java
 *
 * Created on 9 de abril de 2007, 11:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR016E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * mensaje TR_016_E
 *
 * @author francois
 */
public class SolicitudSTDecoTarjActualizarMQ
    extends QManagerService
{
    // 
    private static final String QUEUE = "IT_BUS_ALISTAR_DTH_DECOS_Q";
	private static final String QUEUEDESTINATION = "RT_AT_ALISTAR_DTH_DECOS2_Q";
    
    /** Creates a new instance of SolicitaDecoTarjActualizar */
    public SolicitudSTDecoTarjActualizarMQ ()
    throws ATiempoAppEx
    {
		super(QUEUE, QUEUEDESTINATION);
    }
    
    /**
     * fbd: algun dia entendere a que sirve este metodo
     *
     * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
     */
    protected Object procesarDatos (Object obj)
    {
        // TODO-TCS: IMPLEMENTAR PARSEO SALIDA TR_010_E
		if (obj instanceof TR016E)
		{
			TR016E tr016e = (TR016E) obj;
	
		}
        return obj;
    }   
}
