/*
 * SolicitaDecoTarjActualizar.java
 *
 * Created on 9 de abril de 2007, 11:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

/**
 * mensaje TR_016_E
 *
 * @author francois
 */
public class ConsultarEstadoDTHMQ
    extends QManagerService
{
    // 
    private static final String QUEUE = "jms/QMAT/IT_BUS_CONSULTAR_ESTADO_DTH_Q";
    
    /** Creates a new instance of SolicitaDecoTarjActualizar */
    public ConsultarEstadoDTHMQ ()
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
        return obj;
    }   
}
