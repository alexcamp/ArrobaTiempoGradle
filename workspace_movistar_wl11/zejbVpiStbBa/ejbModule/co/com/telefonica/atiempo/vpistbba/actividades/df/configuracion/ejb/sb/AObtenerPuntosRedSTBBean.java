package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AObtenerPuntosRedSTB
 */
public class AObtenerPuntosRedSTBBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Obtener Puntos de Red STB [" + act.getNumeroPeticion() + "]");
		
		try {
			
			//CR14657 - Granite - adocarmo - 04-11-2008
			PeticionesInterfaces pI = new PeticionesDelegate();
		   	boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
			//CR14657 - Granite - adocarmo - fin	
		   	
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				
//				dcardena 27/03/2015 FTTC se agrega validacion para inhibir la actividad obtener en caso que no sea una posventa
				PeticionLocalHome petHome = (PeticionLocalHome) HomeFactory.getHome( PeticionLocalHome.JNDI_NAME);
				PeticionLocal petLocal = petHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
				
				boolean traePCLinea = pI.tienePCAltaLB(act.getNumeroPeticion());
//				if(petLocal.getTica_id().equals(ComunInterfaces.opCoTipoAlta)){
				if(traePCLinea && petLocal.getTica_id().equals(ComunInterfaces.opCoTipoAlta)){
					act.setObservacion("No es necesario consultar los datos del abonado.");
					act.setRealizarTerminoInmediato(true);	
					return;
				}
				
				RecursosDelegate delegate=new RecursosDelegate();
				
			//CR14657 - Granite - adocarmo - inicio
				if (esGranite) {
					delegate.envioPuntosRedGraniteSTB(act.getNumeroPeticion().toString(),act.getCodigoActividad());
				}
				else {
					delegate.envioPuntosRedSTB(act.getNumeroPeticion().toString(),act.getCodigoActividad());
				}	
			//CR14657 - Granite - adocarmo - fin
			}else{
				act.setObservacion("En reversa no es necesario realizar la consulta.");
				act.setRealizarTerminoInmediato(true);	
			}
			
		} catch (ATiempoAppEx e) {
			log.warn("Error en Actividad Obtener Puntos de Red STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Puntos de Red STB", e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("Error en Actividad Obtener Puntos de Red STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Puntos de Red STB", e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("Error en Actividad Obtener Puntos de Red STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Puntos de Red STB", e);
		}

		log.debug("Fin Actividad Obtener Puntos de Red STB [" + act.getNumeroPeticion() + "]");
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// Dcardena 05/03/2015 unicamente para traslado se setea que ya hubo asignacion de recursos para no ejecutar actividad Obtener puntos Ba
//		if(validaTraslado(act.getNumeroPeticion())){
//			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"S");	
//		}
	}

	//funcion que valida si la peticion es de traslado
//	protected boolean validaTraslado(Long petiNumero){
//		try{
//		PeticionLocalHome petiHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//		PeticionLocal petiLocal = petiHome.findByPrimaryKey(new PeticionKey (petiNumero));
//		Collection psp= petiLocal.getProducto_servicio_peticion();
//		
//		Producto_servicio_peticionLocal producto_servicio_peticionLocal=null;
//		
//		for(Iterator iter = psp.iterator();iter.hasNext();)
//		{
//			producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
//			Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
//			Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
//			if(petiLocal.getTica_id().equals("P")&&producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tras().equals(ComunInterfaces.opCoTras_Traslado)){
//				return true;
//			}
//		}
//		
//			
//		} catch (FinderException e1) {
//			return false;
//		
//		} catch (NamingException e) {
//			return false;
//		}
//
//		return false;
//	}
//	
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
