/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR605S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

/**
 * fbd: cambio en la interfaz
 *
 * @author TCS
 *
 * Mensaje: TR_011_S
 */
public class ConfiguracionMovistarPlayServicio extends ServicioBasico
{
    
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
     */
    protected void procesar (Object[] obj) throws ATiempoAppEx
    {
    	RecursosTVDelegate delegate = new RecursosTVDelegate();
    	delegate.respuestaConfigMovistarPlay((TR605S) obj [0]);
//        
//        inventario.actualizarInventarioSS ((TR605S) obj [0]) ;
    }
    
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
     */
    protected Object[] convertirMensaje (String mensaje) throws ATiempoAppEx
    {
        // TODO-TCS: MARSHALL TR_605_S
    	TR605S tr605s = (TR605S) XMLUtilities.unmarshall (mensaje);
        Object[] obj = new Object[1];
		obj[0] = tr605s;
        
        return obj;
    }
    
}
