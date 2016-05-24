package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.EquipoDelegate;
import co.com.telefonica.atiempo.ejb.eb.EquipoLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ANotificacionSAP
 */
public class ANotificacionSAPBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements
		javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("--Pasa por metodo onInicioActividadVPI de ANotificacionSAPBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
		
		try {
			//TOA FASE III DCARDENA
			//se valida si la localidad es TOA para inhibir la activdad de notificacion sap
			PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticionLocal = peticionHome.findByPetiNumero(act.getNumeroPeticion());
			LocalidadLocal localidad = (LocalidadLocal)peticionLocal.getFk_01_localidad();
			
			if(localidad.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA){
				
				log.debug("Se inhibe la actividad por ser Localidad TOA la peticion "+act.getNumeroPeticion());
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe la actividad por ser Localidad TOA");
				return;
			}
			//FIN REQ TOA FASE III
			
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Ejecuta la actividad de Notificacion SAP.");
				EquipoLocal delegateEquipos = new EquipoDelegate();			
				delegateEquipos.enviarInformacionEquiposVentaMinoristasSAP(act, act.getNumeroPeticion(),act.getCodigoActividad());
			} else{
				/*SE INHIBE LA ACTIVIDAD POR IR EN REVERSA*/
				log.debug("Se inhibe la actividad de Notificacion SAP.");
	        	act.setObservacion("Se inhibe la actividad porque la peticion va en reversa.", true);
				act.setRealizarTerminoInmediato(true);
			}
			
			
		} catch (ATiempoAppEx e) {
			log.error("ANotificacionSAPBean: onInicioActividadVPI: Error intentando enviar la información de Venta de Minoristas a SAP. "+e);
		} catch (NamingException e) {//TOA FASE III DCARDENA
			// TODO Bloque catch generado automáticamente
			log.debug("ANotificacionSAPBean: onInicioActividadVPI: Error intentando levantar el localhome de peticion o localidad. "+e);
		} catch (FinderException e) {//TOA FASE III DCARDENA
			// TODO Bloque catch generado automáticamente
			log.debug("ANotificacionSAPBean: onInicioActividadVPI: Error intentando consultar la localidad "+e);
		}		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("--Pasa por metodo onInicioActividadVPI de ANotificacionSAPBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());
		
	}
	
	
}