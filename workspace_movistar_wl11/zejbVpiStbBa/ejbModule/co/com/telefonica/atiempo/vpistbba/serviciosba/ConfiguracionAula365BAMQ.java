/*
 * Created on 08-02-2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba;

import co.com.telefonica.atiempo.interfaces.atiempo.TR024E;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService;

import com.telefonica_chile.atiempo.utiles.Utiles;

/**
 * @author Victor
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfiguracionAula365BAMQ extends QManagerService {

    // OJO ACA HAY QUE DEFINIR LA COLA EN LA QUE SE VAN A PONER LOS MENSAJES QUE IRAN A COMPETIR
	private static final String QUEUE = "jms/QMAT/IT_BUS_APROV_PROD_A365_Q";
	
	public ConfiguracionAula365BAMQ() throws ATiempoAppEx {
		super(QUEUE);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.mensajeria.util.QManagerService#procesarDatos(java.lang.Object)
	 */
	protected Object procesarDatos(Object obj) {
		// OJO HAY QUE SUSTITUIR TR023E POR LA TR QUE SE DEFINA PARA AULA365
		if (obj instanceof TR024E)
		{
			TR024E tr024e = (TR024E) obj;
			tr024e.setId(Utiles.ConversorGuion(filtraInvalidaCharacter(tr024e.getId())));
		}
		return obj;
	}
}
