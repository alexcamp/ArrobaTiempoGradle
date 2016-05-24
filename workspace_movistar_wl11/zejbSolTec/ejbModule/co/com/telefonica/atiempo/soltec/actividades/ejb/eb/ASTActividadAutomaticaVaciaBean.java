package co.com.telefonica.atiempo.soltec.actividades.ejb.eb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTActividadAutomaticaVacia
 */
public class ASTActividadAutomaticaVaciaBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB
	{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		act.setVacia(true);
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}


}
