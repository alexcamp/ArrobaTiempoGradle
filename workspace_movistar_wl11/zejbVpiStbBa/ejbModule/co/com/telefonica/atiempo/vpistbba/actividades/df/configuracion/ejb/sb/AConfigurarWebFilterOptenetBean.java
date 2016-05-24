package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarWebFilterOptenet
 */
public class AConfigurarWebFilterOptenetBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements
		javax.ejb.SessionBean {
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	 protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {	
		
		 log.debug("Ejecutando AConfigurarWebFilterOptenetBean: nro de Peticion = "+act.getNumeroPeticion());
		 
		 try {
		 	RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
		 	
		 	String ocSVA = recursosBADelegate.validaOperacionComercialSVA(act, act.getNumeroPeticion());
		 	
		 	if(ocSVA != null && !ocSVA.equals(ComunInterfaces.OC_MODIFICACION_SVA)){	
		 		if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
					Iterator it_ps = act.getPsOk().iterator();			
				 	recursosBADelegate.enviarConfiguracionWebFilterOptenet(act, act.getNumeroPeticion(),act.getCodigoActividad(), (PsVsOcVO)it_ps.next());
				}else{
					act.setObservacion("Reversa Baja.");
					act.setRealizarTerminoInmediato(true);	
				}
		 	}else{
		 		act.setObservacion("Se inhibe la actividad, por tener 2 operaciones comerciales diferentes.");
				act.setRealizarTerminoInmediato(true);
		 	}
			
		 } catch (ATiempoAppEx e) {
            e.printStackTrace();
            throw new TnProcesoExcepcion("Error en Actividad AConfigurarWebFilterOptenet", e);
        }
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
}
