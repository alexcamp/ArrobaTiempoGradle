package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AInstalarTOA
 */
public class AInstalarTOABean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB {

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		try {
			log.debug("Ingreso a Instalar TOA");
//			RQ Cambio plan BA 25956 @dcardena 13/06/2014
			// DCARDENA se agrega validacion para cauando halla reversa y se halla inhibido la actividad de Desconfigurar IMS anterioirmente, 
			//tambien se inhiba la actividad de (TOA)
			Bitacora_peticionLocalHome bitacora_peticionLocalHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			Collection  bitacorasDeLaPeticion=bitacora_peticionLocalHome.findbyNumeroPeticion(act.getNumeroPeticion());
			boolean seinhibe=false;
			for(Iterator iterator2=bitacorasDeLaPeticion.iterator();iterator2.hasNext();)
			{
				log.debug("se valida si de sebe inhibir la activida de toa por ejecucion de asistencia remota");
				Bitacora_peticionLocal bitacora_peticionLocal =(Bitacora_peticionLocal) iterator2.next();
				//se consulta si la actividad en la q estoy anterioirmente se inhibio o se ejecuto
				if(bitacora_peticionLocal.getBipe_observacion().startsWith("Se inhibe la actividad de instalación por que se ejecuto la actividad Asistencia remota"))
				{
					log.debug("se inhibe la actividad de TOA por ejecucion de Asitencia remota");
					seinhibe=true;
					break;
				}
			}
//				se agrega validacion si se debe inhivir la reversa de la actividad segun la validacion anterior
			if(seinhibe){
					// codigo que inhibe la reversa
				log.debug("Se inhibe la actividad de instalación TOA por que se ejecuto la actividad Asistencia remota");
				act.setObservacion("");				
				
				Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
				ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				ActividadLocal actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(act.getCodigoActividad(), idAplicacion);
				ActividadKey actividadKey = (ActividadKey) actividadLocal.getPrimaryKey();
				log.debug("Se busca bitacora para peticion: " + act.getNumeroPeticion() + " y actividad: "+act.getIdActividadFlujo());
				Bitacora_peticionLocalHome btHome =  (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
				Bitacora_peticionLocal btLocal = btHome.findbyMaxActivity(act.getNumeroPeticion(), actividadKey.act_id);
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe la actividad de instalación TOA por que se ejecuto la actividad Asistencia remota");
				btLocal.setBipe_observacion("Se inhibe la actividad de instalación TOA por que se ejecuto la actividad Asistencia remota");
				return;
			}else{
				
				PeticionLocalHome peticionHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal peticionLocal = peticionHome.findByPetiNumero(act.getNumeroPeticion());
				LocalidadLocal localidad = (LocalidadLocal)peticionLocal.getFk_01_localidad();
				if(localidad.getLocalidad_toa().intValue() != ComunInterfaces.LOCALIDAD_TOA){
					log.debug("Se inhibe por no ser Localidad TOA");
					Bitacora_peticionLocalHome btHome =  (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
					
					log.debug("Se busca bitacora para peticion: " + act.getNumeroPeticion() + " y actividad: "+act.getIdActividadFlujo());
					
					Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
					ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
					ActividadLocal actividadLocal = actividadLocalHome.findByCodigoActividadIdAplicacion(act.getCodigoActividad(), idAplicacion);
					ActividadKey actividadKey = (ActividadKey) actividadLocal.getPrimaryKey();
					
					Bitacora_peticionLocal btLocal = btHome.findbyMaxActivity(act.getNumeroPeticion(), actividadKey.act_id);
					act.setRealizarTerminoInmediato(true);
					act.setObservacion("Se inhibe por no ser Localidad TOA");
					btLocal.setBipe_observacion("Se inhibe por no ser Localidad TOA");
				}else{
					
				log.debug("Se envìa a TOAServicio");
				TOADelegate delegate = new TOADelegate();
				delegate.crearActuacionTOA(act);
				}
		}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("AInstalarTOABean.onInicioActividadVPI:Al instanciar procesos");
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("AInstalarTOABean.onInicioActividadVPI:Al realizar busqueda en la base de datos");
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("AInstalarTOABean.onInicioActividadVPI:Al realizar busqueda en la base de datos");
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}
	
}