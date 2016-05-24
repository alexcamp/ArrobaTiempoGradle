package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Estado_ps_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR044EEquipment;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AGenerarRecibo
 */
public class AGenerarReciboBean	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {
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
		log.debug("Entro a AGenerarReciboBean para la petición: "+act.getNumeroPeticion());
		
		boolean psTVSolaQuebrado = false;
		
		try{
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			Estado_ps_peticionLocalHome estadoPSPeticionLocalHome = (Estado_ps_peticionLocalHome)HomeFactory.getHome(Estado_ps_peticionLocalHome.JNDI_NAME);
			
			PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			
			Collection peticionFlujoList = peticionLocal.getPeticion_flujo();
			
			for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
				Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
				
				if (peticionFlujoLocal.getFk_acti_2_pefl().getActi_codigo().equals(ComunInterfaces.ACT_GENERAR_RECIBO)){
					RecursosBADelegate delegate = new RecursosBADelegate();
					
					
					if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
						Collection equiposList = new ArrayList();
						
						TR044EEquipment equipos = new TR044EEquipment();
						equipos.setCodigoPS(peticionFlujoLocal.getPrse_id().longValue());
						equipos.setSerial("");
						InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
						Subpeticion_caracteristicasLocal sc=infoComunColombiaBI.obtenerCaracteristica(act.getNumeroPeticion(),new Long(ComunInterfaces.CODIGO_CARACTERISTICA_EQUIPO_QW));
						if(sc!=null){
							equipos.setCodigoCaracteristica(sc.getCod_val_crc_cd());
						}
						equiposList.add(equipos);
					
						
						delegate.solicitarPrimeraFacturaInternetEquipado(act.getNumeroPeticion(), equiposList, act.getCodigoActividad(), ComunInterfaces.MENSAJE_ENV_FACTURA_AVANCE,act.getIdActividadFlujo());
					}else{
						Collection productoServicioPeticionList = peticionLocal.getProducto_servicio_peticion();
						
						for (Iterator productoServicioPeticionIter = productoServicioPeticionList.iterator(); productoServicioPeticionIter.hasNext();){
							Producto_servicio_peticionLocal productoServicioPeticionLocal = (Producto_servicio_peticionLocal)productoServicioPeticionIter.next();
							
							Collection estadoPSPeticionList = productoServicioPeticionLocal.getEstado_ps_peticion();
							
							for (Iterator estadoPSPeticionIter = estadoPSPeticionList.iterator();estadoPSPeticionIter.hasNext();){
								Estado_ps_peticionLocal estadoPSPeticionLocal = (Estado_ps_peticionLocal)estadoPSPeticionIter.next();
								
								Producto_servicioKey productoServicioKey = (Producto_servicioKey)estadoPSPeticionLocal.getProducto_servicio().getPrimaryKey();
								
								if (productoServicioKey.ps_id.longValue() == peticionFlujoLocal.getPrse_id().longValue()){
									if (estadoPSPeticionLocal.getCod_estado_cierre().intValue() != ComunInterfaces.estadoOk){
										psTVSolaQuebrado = true;
									}
								}
							}
						}
						
						if (psTVSolaQuebrado){
							if (peticionLocal.getPago_tv_sola_ok() != null && peticionLocal.getPago_tv_sola_ok().equals(ComunInterfaces.INDICADOR_PAGO_OK)){
								Collection equiposList = new ArrayList();
								
								TR044EEquipment equipos = new TR044EEquipment();
								equipos.setCodigoPS(peticionFlujoLocal.getPrse_id().longValue());
								equipos.setSerial("");
								
								equiposList.add(equipos);
								
								delegate.solicitarPrimeraFacturaInternetEquipado(act.getNumeroPeticion(), equiposList, act.getCodigoActividad(), ComunInterfaces.MENSAJE_ENV_FACTURA_REVERSA,act.getIdActividadFlujo());
							}else{
								log.debug("No se manda mensaje de reversa de pago a Gesrec porque el cliente no pago: "+ act.getNumeroPeticion());
								act.setObservacion("No se manda mensaje de reversa de pago a Gesrec porque el cliente no pago", true);
								act.setRealizarTerminoInmediato(true);
							}
						}else{
							log.debug("No se manda mensaje de reversa de pago a Gesrec porque el PS de TV sola no esta quebrado: "+ act.getNumeroPeticion());
							act.setObservacion("No se manda mensaje de reversa de pago a Gesrec porque el PS de TV sola no esta quebrado", true);
							act.setRealizarTerminoInmediato(true);
						}
					}
					
					break;
				}
			}
		}catch(Exception ex){
			log.error("Se presento un error de tipo NamingException en la ejecucion del proceso en onInicioActividadVPI de la clase AConfigurarFacturaBean:" ,ex);
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	
	
}