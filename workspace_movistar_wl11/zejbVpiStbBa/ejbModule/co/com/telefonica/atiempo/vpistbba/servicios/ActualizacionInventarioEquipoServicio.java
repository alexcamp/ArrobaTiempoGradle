/*
 * Created on Mar 24, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;


import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.interfaces.atiempo.TR028S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author 807793
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ActualizacionInventarioEquipoServicio extends ServicioBasico {
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
     */
    protected void procesar(Object[] obj) throws ATiempoAppEx {
        EquipoDelegate eDelegate = new EquipoDelegate();
        eDelegate.recepcionActualizaInventarioBA((TR028S) obj[0]);

    }

    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
     */
    protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
        TR028S tr028S = (TR028S) XMLUtilities.unmarshall(mensaje);

        Object[] obj = new Object[1];

        obj[0] = tr028S;

        return obj;
    }

}
