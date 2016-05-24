package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.inventario.InventarioDelegate;
import co.com.telefonica.atiempo.vpistbba.inventario.InventarioInterfaces;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AActualizarInventarioSTB
 */
public class AActualizarInventarioSTBBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Actualizar Inventario STB [" + act.getNumeroPeticion() + "]");

		try {
			
			PeticionesInterfaces pI = new PeticionesDelegate();
		   	boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
		   	InventarioInterfaces inventarioInterfaces = new InventarioDelegate();
//----------------------------------------------------------------------------------------
		   	// Modificacion FTCC SVAs 27/02/15 dcardena
			//se debe inhibir la actividad crear ods cuando hallan campos fttc unicamente para los SVAS
			//Req FTTC se agrega la consulta a la tabla recursos_linea_baqsica para sabews si la peticion contiene campos de tipo FTTC para que se inhiba la actividad
		    Recursos_linea_basicaLocalHome  recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
		    Recursos_linea_basicaLocal recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
			boolean hayFTTC =false;
			boolean hayPcLinea=false;
			//se vlaida si hay recursos fttc
			if((recursos_linea_basicaLocal.getRec_fttc_asg() != null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))||
					((recursos_linea_basicaLocal.getRec_fttc_ant()) != null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S"))){
				log.debug("hay recursos FTTC en la peticion "+act.getNumeroPeticion());
				hayFTTC=true;
			}
		   	
			//FTTC se agrega la consulta a la tabla productoservicioPeticion para obtener todos los ps de la peticion
		    Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome( Producto_servicio_peticionLocalHome.JNDI_NAME);
		    Collection listaPs = (Collection) producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
		    for(Iterator iterator2=listaPs.iterator();iterator2.hasNext();)
			{
		    	//se valida si hay ps de familia PC para poder ejecutar la actividad o inhibirla
				Producto_servicio_peticionLocal producto_servicio_peticionLocal =(Producto_servicio_peticionLocal) iterator2.next();
				if(producto_servicio_peticionLocal.getFamiliaKey().faps_id.intValue()==(ComunInterfaces.familiaPcLinea)){
					hayPcLinea=true;
					break;
				}
			}
//--------------------------------------------------------------------------------------------------	
			   	
//		  CR-14657 Granite - agonz - preguntamos si la asignación de recursos se hace con Granite o no.
			if(esGranite){
				//se valida si no hay pc linea y hay campos fttc para inhibir la actividad
				if(!hayPcLinea&&hayFTTC){
					log.debug("No se envia a crear ODS ya que hay recursos FTTC para los SVAs"+ act.getNumeroPeticion());
					act.setObservacion("No se envia a crear ODS ya que hay recursos FTTC para los SVAs");
					act.setRealizarTerminoInmediato(true);
				}else{
					inventarioInterfaces.enviarInventarioSTBGR(act.getNumeroPeticion().longValue(),act);
				}
			}
			else{
				log.debug("No es granite  [" + act.getNumeroPeticion() + "]");
				//CR-22569 agonz 25 feb 2009
				if(pI.tienePS(act,ComunInterfaces.familiaPcLinea) || pI.tienePS(act,ComunInterfaces.familiaIP)
						||pI.tienePS(act,ComunInterfaces.familiaPcBANaked )||pI.tienePS(act,ComunInterfaces.familiaBandaAnchaNaked) ){
					if(inventarioInterfaces.tieneODS(act.getNumeroPeticion().longValue())){
						log.debug("Actividad Actualizar Inventario STB para PS con familia de familiaPcLinea[" + act.getNumeroPeticion() + "]");
						inventarioInterfaces.enviarInventarioSTB(act.getNumeroPeticion().longValue(),act.getCodigoActividad());
					}else{
						act.setObservacion("Se inhibe la actividad con APSC por no tener ODS creada previamente");
						act.setRealizarTerminoInmediato(true);
						
					}
				}else{
					
					act.setObservacion("Es solo servicio suplementario no Granite se inhibe la actividad con APSC");
					act.setRealizarTerminoInmediato(true);
				}
			}
			
		} catch (ATiempoAppEx e) {

			log.warn("Error Actualizar Actividad",e);
			throw new TnProcesoExcepcion("Error en Actividad Actualizar Inventario STB", e);
		} catch (NamingException e) {
			log.warn("Error en Naming recursos_linea_basica",e);
			throw new TnProcesoExcepcion("Error al llamar el naming de la tabla Recursos_linea_Basica", e);
		} catch (FinderException e) {
			log.warn("Error al consultar en recursos linea Basica",e);
			throw new TnProcesoExcepcion("Error al consultar en la Tabla recursos_linea_Basica", e);
		}
		
		log.debug("Fin Actividad Actualizar Inventario STB [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
