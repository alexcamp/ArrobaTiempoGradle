/*
 * Creado el 3/04/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicioba.naked;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author Juan David Grisales Garzon
 *
 * Clase que selecciona el servicio que debe instanciar ACSServicioDelegate
 */
public class ACSSelectorServicio {
	
	/*public ACSServicioLocal getACSServicioBeanPorOCyProd (String OperacionComercial, String Producto) {
		
		//Consultamos la Operacion comercial AUTOINSTALACION
		char OCAlta = ComunInterfaces.opCoTipoAlta.charAt(0);
		char OCBaja = ComunInterfaces.opCoTipoBaja.charAt(0);
		char OCPosVenta = ComunInterfaces.opCoTipoPosVenta.charAt(0);

		switch (OperacionComercial.charAt(0)){
			case 'A':
				switch (Producto) {
				case value:
					
					break;

				default:
					break;
				}
				break;
			case 'C':
				break;
			case 'P':
				break;
			default:
				break;
		}
		
		return null;
	}*/
	
	public PeticionLocal getPeticionPorPetiNumero (Long petiNumero) throws NamingException, FinderException{
		PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		//Se consulta la peticion por numero
		PeticionKey peticionkey = new PeticionKey(petiNumero);
		return peticionLocalHome.findByPrimaryKey(peticionkey);
	}
	
	public void getProductoServiciosPorPeticion (PeticionLocal peticion) {
		Collection productoServiciosList = peticion.getProducto_servicio_peticion();
		ArrayList productoServicioList = new ArrayList();
		for (Iterator productoServicioIterator = productoServiciosList.iterator(); productoServicioIterator.hasNext();) {
			Producto_servicio_peticionLocal productoServicio = (Producto_servicio_peticionLocal) productoServicioIterator.next();
			productoServicio.getOperacion_comercial().getPrimaryKey();
		}
	}
	
	/*public void getNombresProductoServiciosPorOperacionComercial (PeticionLocal peticion){
		
		Producto_servicioLocalHome p = new Producto_servicioLocalHome();
		//p.findBy
		
		if (peticion.getTica_id()=="A") { //Si la peticion es Alta
			
		} else if (peticion.getTica_id()=="B") { //Si la peticion es Baja
			
		} else if (peticion.getTica_id()=="P") { //Si la peticion es PosVenta
			
		} else if (peticion.getTica_id()=="S") { //Si la peticion es Suspenciòn
			
		} else if (peticion.getTica_id()=="R") { //Si la peticion es Reconexiòn
			
		} else { //Si la peticion es Alta
			
		}
	}*/

}
