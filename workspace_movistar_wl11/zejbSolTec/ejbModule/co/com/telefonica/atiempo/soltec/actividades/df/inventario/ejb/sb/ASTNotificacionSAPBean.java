package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.mensajeria.equipo.ejb.sb.EquipoSTLocal;
import co.com.telefonica.atiempo.soltec.servicios.EquipoSTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTNotificacionSAP
 */
public class ASTNotificacionSAPBean
	extends
		co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB
	implements
		javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("--Pasa por metodo onInicioActividadST de ASTNotificacionSAPBean, en la actividad "+ act.getCodigoActividad()+", para la petici�n: "+act.getNumeroPeticion());
		
		try {
			
			//REQ TOA FASE III DCARDENA 
			//se valida si es una localdiad toa para inhibir la actvidad notificacion sap
			log.debug("Ingreso a Instalar Agenda");
			Peticion_stLocalHome peticionHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticionLocal = peticionHome.findByPrimaryKey(new Peticion_stKey(act.getNumeroPeticion()));
			
			LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadLocal localidad = localidadLocalHome.findByCod_loc(peticionLocal.getCod_loc());
			
			if(localidad.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA){
				log.debug("Se inhibe actividad por ser Localidad TOA");
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe actividad por ser Localidad TOA");
				return;
			}
			//FIN REQ TOA FASE III
			
			EquipoSTLocal delegateEquipos = new EquipoSTDelegate();		
			delegateEquipos.enviarInformacionEquiposVentaMinoristasSAPST(act, act.getNumeroPeticion(),act.getCodigoActividad());		
			
		} catch (ATiempoAppEx e) {
			log.error("ASTNotificacionSAPBean: onInicioActividadST: Error intentando enviar la informaci�n de Venta de Minoristas a SAP. "+e);
		} catch (NamingException e) {//TOA FASE III DCARDENA
			// TODO Bloque catch generado autom�ticamente
			log.debug("ASTNotificacionSAPBean: onInicioActividadST: Error intentando levantar el localhome de peticion o localidad. "+e);
		} catch (FinderException e) {//TOA FASE III DCARDENA
			// TODO Bloque catch generado autom�ticamente
			log.debug("ASTNotificacionSAPBean: onInicioActividadST: Error intentando consultar la localidad. "+e);
		}	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}


}