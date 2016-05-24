/*
 * Created on Feb 20, 2007
 */

package co.com.telefonica.atiempo.vpistbba.inventario ;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR004S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR011S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR512S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * fbd: 
 * - cambio de interfaces, se recibe ahora los objetos Java DTO-XML (TR???S)
 * - extiende una ComunInterfaces para tener acceso a constanstes como estadoOk
 *
 * @author CS, francois
 */

public interface InventarioInterfaces 
  extends ComunInterfaces
{
    /* fbd: version anterior
    public void actualizarInventarioSS (Integer peticionAtisId, Integer agrupacionId, String respuesta) throws ATiempoAppEx;
    public void actualizarInventarioSTB (Integer ordenServicioId, Boolean respuesta, String telefono) throws ATiempoAppEx;
     **/
    
    // recepcion de la respuesta
    
    public void actualizarInventarioSS (TR011S tr011s) throws ATiempoAppEx;
    public void actualizarInventarioSTB (TR004S tr004s) throws ATiempoAppEx;
    public void actualizarInventarioSTBGR (TR512S tr512s) throws ATiempoAppEx;//CR14657 Granite
    
    // envio de la consulta
    public void enviarInventarioSTB (long peticion, String codigoActividad) throws ATiempoAppEx ;

    public void enviarInventarioSTBGR (long peticion, ActividadEJBDTO codigoActividad) throws ATiempoAppEx ;//CR14657 Granite
    
    public void enviarInventarioSS (long peticion, String codigoActividad,Integer actividadFlujo) throws ATiempoAppEx ;
    public boolean tieneODS (long idPeticion)   throws ATiempoAppEx;
}
