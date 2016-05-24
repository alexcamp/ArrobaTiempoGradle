package co.com.telefonica.atiempo.soltec.actividades.df.recuperar_inf.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTRecuperarInfTV
 */
public class ASTRecuperarInfTVBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Obtener Informacion TV [" + act.getNumeroPeticion() + "]");
		try {
			RecursosTVDelegate delegate=new RecursosTVDelegate();
			if(delegate.isIdpcTV(act.getNumeroPeticion())){
				delegate.solicitudInformacionTecnica(act.getNumeroPeticion().toString(),act.getCodigoActividad());
			}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC para TV");
			}
		} catch (ATiempoAppEx e) {
			log.warn("Error en Actividad Informacion de TV",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion TV", e);
		}
		log.debug("Fin Actividad Obtener Informacion TV [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
}
