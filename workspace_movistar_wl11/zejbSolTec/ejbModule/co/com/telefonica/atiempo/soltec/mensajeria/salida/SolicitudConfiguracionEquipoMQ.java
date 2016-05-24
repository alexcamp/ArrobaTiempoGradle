/*
 * Created on Apr 2, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.soltec.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR027E;
import co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SolicitudConfiguracionEquipoMQ extends QManagerService{

	private static final String QUEUE = "IT_BUS_CONSULTA_EQUIPOS_ST_Q";
	//private static final String QUEUEDESTINATION = "jms/QMAT/RF_BUS_CONSULTA_EQUIPOS_ST_Q";
    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
     */
    public SolicitudConfiguracionEquipoMQ() throws ATiempoAppEx {
		super(QUEUE);		
	}
    protected Object procesarDatos(Object obj) {
        if (obj instanceof TR027E)
		{
            TR027E tr027e = (TR027E) obj;         
		}
		return obj;
    }

}
