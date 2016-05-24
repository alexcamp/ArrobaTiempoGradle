/*
 * Created on Mar 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR027E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;
import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SolicitudConfiguracionEquiposMQ extends QManagerService{
    private static final String QUEUE = "jms/QMAT/IT_BUS_CONSULTA_EQUIPOS_Q";
	
	/**
	 * @param queue
	 * @throws ATiempoAppEx
	 */
	public SolicitudConfiguracionEquiposMQ() throws ATiempoAppEx {
		super(QUEUE);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR027E)
		{
		    TR027E tr027e = (TR027E) obj;
		    tr027e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr027e.getId())));
		}
		return obj;
	}

}
