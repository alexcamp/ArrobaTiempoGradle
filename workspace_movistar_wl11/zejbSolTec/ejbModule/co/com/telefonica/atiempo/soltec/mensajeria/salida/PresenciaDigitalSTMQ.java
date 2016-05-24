/*
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR054E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * mensaje TR_025_E
 *
 * @author mfmendez
 */
public class PresenciaDigitalSTMQ
    extends QManagerService
{
    private static final String QUEUE = "IT_BUS_PRESENCIA_DIGITAL_ST";
    
    public PresenciaDigitalSTMQ () throws ATiempoAppEx {
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
		if (obj instanceof TR054E)
		{
			TR054E tr025e = (TR054E) obj;
	
		}
        return obj;
    }   
}
