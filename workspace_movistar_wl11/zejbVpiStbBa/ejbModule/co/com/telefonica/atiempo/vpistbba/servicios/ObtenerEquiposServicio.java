/*
 * Created on Mar 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.interfaces.atiempo.TR027S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObtenerEquiposServicio extends ServicioBasico{

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
     */
    protected void procesar(Object[] obj) throws ATiempoAppEx {
        EquipoDelegate delegate = new EquipoDelegate();		
		TR027S tr027S = (TR027S) obj[0];		
		delegate.recepcionConfiguracionActualEquipo(tr027S);
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
     */
    protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
        Object[] obj = new Object[1];
		TR027S tr027S = (TR027S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr027S;
		return obj;
    }

}
