package co.com.telefonica.atiempo.soltec.actividades.df.recuperar_inf.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.soltec.dto.PeticionStMasivaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Familia_producto_servicio_stBean;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ASTRecuperarInfBASigres
 */
public class ASTRecuperarInfBASigresBean extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {	
	}

	/* (non-Javadoc)
	 * si la incidencia tiene una familia de ps correspondiente a IC, se sigue gestionando en HC, sino, se gestiona en SIGRES
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {	
		
		log.debug("*** SIGRES *** ejecutando ASTRecuperarInfBASigresBean: onInicio --> nro de Incidencia = "+act.getNumeroPeticion());
		try {
			RecursosBADelegate delegate=new RecursosBADelegate();
			if(delegate.isIDPC(act.getNumeroPeticion())){
				Peticion_stKey peticion_stKey = new Peticion_stKey(act.getNumeroPeticion());
				Peticion_stLocalHome peticion_stLocalHome =  (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stLocal peticion_stLocal = peticion_stLocalHome.findByPrimaryKey(peticion_stKey); 
				
				Producto_servicioKey producto_servicioKey = new Producto_servicioKey(peticion_stLocal.getCod_pro_ser_cd()); 
				Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);  
				long faps_id = ((Familia_producto_servicioKey)producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey()).faps_id.longValue();
				log.debug("*** SIGRES *** ejecutando ASTRecuperarInfBASigresBean: onInicio --> busco ps = "+faps_id);
				
				/*
				 * veo si es de IC
				 */			
				if (faps_id==com.telefonica_chile.comun.ComunInterfaces.familiaIC){	
					try {
						log.debug("*** SIGRES *** envio TR014S a HC");
						delegate.enviarConfiguracionActualBA(act.getNumeroPeticion().toString(),act.getCodigoActividad());
					} catch (ATiempoAppEx e) {
						log.warn("Error en Actividad Informacion de BA",e);
						throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
					}	
				}else{
					try {
						log.debug("*** SIGRES *** envio TR035S a Sigres");
						delegate.enviarConfiguracionActualBASigres(act.getNumeroPeticion().toString(),act.getCodigoActividad());
					} catch (ATiempoAppEx e) {
						log.warn("Error en Actividad Informacion de BA",e);
						throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
					}		
					
						
				}
			}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC");
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TnProcesoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
				
			
			
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {		
	}

 
}
