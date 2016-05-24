/*
 * Created on Feb 20, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.inventario;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR004S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR011S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.inventario.ejb.InventarioServicesLocal;
import co.com.telefonica.atiempo.vpistbba.inventario.ejb.InventarioServicesLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * fbd: cambio interfaz
 *
 * para probar mi nuevo usuario de CVS
 *
 * @author TCS, francois
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class InventarioDelegate implements InventarioInterfaces
{
        
    private InventarioServicesLocal ejbInventarioServices;
    
    public InventarioDelegate () throws ATiempoAppEx
    {
        try
        {
            ejbInventarioServices =
                     ((InventarioServicesLocalHome) HomeFactory.getHome (InventarioServicesLocalHome.JNDI_NAME)).create ();
        }
        catch (NamingException nex)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NAMING, nex);
        }
        catch (CreateException cex)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NAMING, cex);
        }
    }
    
    /**
     * @see co.com.telefonica.atiempo.vpistbba.inventario.InventarioServicesBusinessInterface#actualizarInventarioSS()
     */
    
    public void actualizarInventarioSS (TR011S tr011s)
    throws ATiempoAppEx
    {
        ejbInventarioServices.actualizarInventarioSS (tr011s) ;
    }
    
    /**
     * @param ordenServicioId
     * @param respuesta
     * @param telefono
     */
    public void actualizarInventarioSTB (TR004S tr004s)
    throws ATiempoAppEx
    {
        ejbInventarioServices.actualizarInventarioSTB (tr004s);
    }
    
    public void enviarInventarioSS (long peticion, String codigoActividad,Integer actividadFlujo)
    throws ATiempoAppEx
    {
        ejbInventarioServices.enviarInventarioSS (peticion, codigoActividad,actividadFlujo) ;
    }

    public void enviarInventarioSTB (long peticion, String codigoActividad)
    throws ATiempoAppEx
    {
        ejbInventarioServices.enviarInventarioSTB (peticion, codigoActividad) ;
    }

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.inventario.InventarioInterfaces#actualizarInventarioSTBGR(co.com.telefonica.atiempo.interfaces.atiempo.TR512S)
	 */
	public void actualizarInventarioSTBGR(TR512S tr512s) throws ATiempoAppEx {
		  ejbInventarioServices.actualizarInventarioSTBGR(tr512s) ;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.inventario.InventarioInterfaces#enviarInventarioSTBGR(long, co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void enviarInventarioSTBGR(long peticion, ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		ejbInventarioServices.enviarInventarioSTBGR(peticion,act) ;
	}	
    
	  public boolean tieneODS (long idPeticion)   throws ATiempoAppEx{
	  		return	ejbInventarioServices.tieneODS(idPeticion);
	  }
}
