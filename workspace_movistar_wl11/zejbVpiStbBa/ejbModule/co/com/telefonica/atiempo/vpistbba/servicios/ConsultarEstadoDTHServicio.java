/*
 * Creado el 26/08/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.interfaces.atiempo.TR053S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ConsultarEstadoDTHServicio extends ServicioBasico {
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
     */
    protected void procesar(Object[] obj) throws ATiempoAppEx {
        EquipoDelegate eDelegate = new EquipoDelegate();
        eDelegate.recepcionConsultarEstadoDTH((TR053S) obj[0]);

    }

    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
     */
    protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
        TR053S tr053S = (TR053S) XMLUtilities.unmarshall(mensaje);

        Object[] obj = new Object[1];

        obj[0] = tr053S;

        return obj;
    }

}
