/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.interfaces.atiempo.TR004S;
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
 * Mensaje: TR_004_S
 */
public class ActualizacionInventarioSTBServicio extends ServicioBasico
{
    
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
     */
    protected void procesar (Object[] obj) throws ATiempoAppEx
    {
        InventarioInterfaces inventario = new InventarioDelegate ();
        
        inventario.actualizarInventarioSTB ((TR004S) obj [0]) ;
    }
    
    /**
     * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
     */
    protected Object[] convertirMensaje (String mensaje) throws ATiempoAppEx
    {
        TR004S tr004S = (TR004S) XMLUtilities.unmarshall(mensaje);
        
        Object[] obj = new Object[1] ;
        
        obj [0] = tr004S ;
        
        return obj;
    }
    
}
