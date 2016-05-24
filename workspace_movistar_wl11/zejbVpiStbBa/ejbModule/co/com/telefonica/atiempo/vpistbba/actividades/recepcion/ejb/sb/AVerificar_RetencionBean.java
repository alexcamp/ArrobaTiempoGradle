package co.com.telefonica.atiempo.vpistbba.actividades.recepcion.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: AVerificar_Retencion
 */
public class AVerificar_RetencionBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB{
	
	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Verificando Retencion Peticion [" + act.getNumeroPeticion().toString() + "]");
		
		try {
			
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			Regla_RetencionesLocal reglaRetencion= peticionesDelegate.verificarRetencion(act.getNumeroPeticion());
			if(reglaRetencion!=null){
				act.setObservacion("Peticion afectada por Regla cod: "+ reglaRetencion.getRegla_id()+" -"+ reglaRetencion.getDescripcion());
				
			}else{
				act.setObservacion("Peticion no es afectada por ninguna regla de retencion");
				act.setGrabaEnBitacora(true);
				act.setRealizarTerminoInmediato(true);
			}
			
			
		} catch (ATiempoAppEx e) {
			log.debug("Error verificando Retencion.",e);
			throw new TnProcesoExcepcion("ATiempoAppEx",e);
		}
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		try {
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			Regla_RetencionesLocal reglaRetencion= peticionesDelegate.verificarRetencion(act.getNumeroPeticion());
			
			if(reglaRetencion!=null){
				act.setObservacion("<BR> Peticion afectada por una nueva Regla cod: "+ reglaRetencion.getRegla_id()+" -"+ reglaRetencion.getDescripcion());
				act.setNoTerminar(true);
			}else{
				peticionesDelegate.marcarGranite(act.getNumeroPeticion());
				//CR23110 - Correccion - PCawen -
				//luego q la paticion deja de ser afectada po una regla, se setea
				//su regla_ID a null.
				try{
				PeticionLocalHome peticionHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionKey key=new PeticionKey(act.getNumeroPeticion());
				PeticionLocal peticionLocal=peticionHome.findByPrimaryKey(key);
				peticionLocal.setRegla_id(null);
				} catch (NamingException e){
					log.fatal("NamingException",e);
				} catch (FinderException e1){
					log.fatal("FinderException",e1);
				}
			}
		} catch (ATiempoAppEx e) {
			log.debug("Error validando granite.",e);
			throw new TnProcesoExcepcion("ATiempoAppEx",e);
		}
		
	}
}
