package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: AObtenerInfBASigres
 */
public class AObtenerInfBASigresBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

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
		
		 log.debug("*** SIGRES *** ejecutando AObtenerInfBASigresBean: onInicio --> nro de Peticion = "+act.getNumeroPeticion());
		 try {
		     // Correccion Sigres defecto en produccion - 06/08/2009 - agonz
		     if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				 PeticionKey peticion_Key = new PeticionKey(act.getNumeroPeticion());
				 PeticionLocalHome peticion_LocalHome =  (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				 PeticionLocal peticion_Local = peticion_LocalHome.findByPrimaryKey(peticion_Key); 
	
				 Iterator it_ps = act.getPsOk().iterator();
				 PsVsOcVO psprim =(PsVsOcVO)it_ps.next();			 
				 
				 Producto_servicioKey producto_servicioKey = new Producto_servicioKey(psprim.getPsId());
				 Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
				 Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(producto_servicioKey);  
				 RecursosBADelegate delegate=new RecursosBADelegate();
				 long faps_id = ((Familia_producto_servicioKey)producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey()).faps_id.longValue();
	
				 log.debug("*** SIGRES *** ejecutando AObtenerInfBASigresBean: onInicio --> busco ps = "+faps_id);
				
				 Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
					boolean esTrasladoAlta=false;
					Traslado_solobaLocal traslado_solobaLocal=null;
					try{
						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(act.getNumeroPeticion());
						esTrasladoAlta=true;
					} catch (FinderException e) {
						log.warn("No es traslado alta BA");
					}
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
					     if(!esTrasladoAlta){				
					     	//arreglo Ba naked
							 delegate.enviarConfiguracionActualBASigres(act.getNumeroPeticion().toString(),act.getCodigoActividad(), null);
							//arreglo Ba naked
					     }else{				        
					        delegate.enviarConfiguracionActualBASigres(String.valueOf(peticion_Local.getpreviousServicePhone()),act.getNumeroPeticion().toString(),act.getCodigoActividad(), null);
					     }
					 } catch (ATiempoAppEx e) {
						 log.warn("Error en Actividad Informacion de BA",e);
						 throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
					 }		
					
						
				 }
		     }else{
				act.setObservacion("En reversa no es necesario realizar la consulta.");
				act.setRealizarTerminoInmediato(true);	
		     }
		 } catch (NamingException e) {
			 
			 e.printStackTrace();
			 throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
		 } catch (FinderException e) {
			 
			 e.printStackTrace();
			 throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
		 } catch (TnProcesoExcepcion e) {

			 e.printStackTrace();
			 throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
		 } catch (ATiempoAppEx e) {
            
            e.printStackTrace();
            throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion BA", e);
        }
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

}
