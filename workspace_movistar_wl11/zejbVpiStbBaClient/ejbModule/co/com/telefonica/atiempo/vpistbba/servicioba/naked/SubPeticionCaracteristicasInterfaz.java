/*
 * Creado el 24/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import com.telefonica_chile.comun.ComunInterfaces;


import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface SubPeticionCaracteristicasInterfaz extends ComunInterfaces {
	
//	REQ BA NAKED @Dcardenas
	public String obtenerCaracteristica (Producto_servicio_peticionLocal psp,Long numeroCararcteristicas) throws ATiempoAppEx;
	
//Fin req BA NAked
	
}
