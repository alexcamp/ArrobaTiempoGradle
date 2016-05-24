/*
 * Created on 08-02-2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.salida;

import co.com.telefonica.atiempo.interfaces.atiempo.TR511E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Victor
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfiguracionAutomaticaSTBMQ extends QManagerService {

    // OJO ACA HAY QUE DEFINIR LA COLA EN LA QUE SE VAN A PONER LOS MENSAJES QUE IRAN A COMPETIR
	private static final String QUEUE = "jms/QMAT/IT_BUS_CONF_CENTRAL_Q";
	
	public ConfiguracionAutomaticaSTBMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		if (obj instanceof TR511E)
		{
			TR511E tr511e = (TR511E) obj;
			tr511e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr511e.getId())));
		}
		return obj;
	}
}
