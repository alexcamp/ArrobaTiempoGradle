/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR011S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.inventario.InventarioDelegate;
import co.com.telefonica.atiempo.vpistbba.inventario.InventarioInterfaces;

/**
 * fbd: cambio en la interfaz
 *
 * @author TCS
 *
 * Mensaje: TR_011_S
 */
public class ActualizacionInventarioSSServicio extends ServicioBasico
{
    
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
     */
    protected void procesar (Object[] obj) throws ATiempoAppEx
    {
        InventarioInterfaces inventario = new InventarioDelegate ();
        
        inventario.actualizarInventarioSS ((TR011S) obj [0]) ;
    }
    
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
     */
    protected Object[] convertirMensaje (String mensaje) throws ATiempoAppEx
    {
        // TODO-TCS: MARSHALL TR_011_S
        TR011S tr011s = (TR011S) XMLUtilities.unmarshall (mensaje);
        Object[] obj = new Object[1];
        
        obj[0] = tr011s ;
        
        return obj;
    }
    
}
