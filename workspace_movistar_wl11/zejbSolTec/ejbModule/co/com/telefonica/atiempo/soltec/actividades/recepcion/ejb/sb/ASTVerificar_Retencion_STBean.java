package co.com.telefonica.atiempo.soltec.actividades.recepcion.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Regla_RetencionesLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.soltec.actividades.ActividadManualSTEJB;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: ASTVerificar_Retencion_ST
 */
public class ASTVerificar_Retencion_STBean	extends	ActividadManualSTEJB {
	
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Verificando Retencion Incidencia [" + act.getNumeroPeticion().toString() + "]");
		
	try {
		
		PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
		Regla_RetencionesLocal reglaRetencion= peticionesDelegate.verificarRetencion(act.getNumeroPeticion());
		if(reglaRetencion!=null){
			act.setObservacion("Incidencia afectada por Regla de retencion cod: "+ reglaRetencion.getRegla_id()+" -"+ reglaRetencion.getDescripcion());
		}else{
			act.setObservacion("Incidencia no es afectada por ninguna Regla de retencion");
			act.setRealizarTerminoInmediato(true);
		}
		
		
	} catch (ATiempoAppEx e) {
		log.debug("Error verificando Retencion.",e);
		throw new TnProcesoExcepcion("ATiempoAppEx",e);
	}
	
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		try {
			PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
			Regla_RetencionesLocal reglaRetencion= peticionesDelegate.verificarRetencion(act.getNumeroPeticion());
			
			if(reglaRetencion!=null){
				act.setObservacion("<BR> Incidencia afectada por una nueva Regla cod: "+ reglaRetencion.getRegla_id()+" -"+ reglaRetencion.getDescripcion());
				act.setNoTerminar(true);
			}else{
				peticionesDelegate.marcarGranite(act.getNumeroPeticion());
//				CR23110 - Correccion - PCawen -
				//luego q la paticion deja de ser afectada po una regla, se setea
				//su regla_ID a null.
				try{
				Peticion_stLocalHome peticionHome=(Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stKey key=new Peticion_stKey(act.getNumeroPeticion());
				Peticion_stLocal peticionStLocal=peticionHome.findByPrimaryKey(key);
				peticionStLocal.setRegla_id(null);
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
