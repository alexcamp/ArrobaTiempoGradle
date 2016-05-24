package co.com.telefonica.atiempo.soltec.actividades.df.recuperar_inf.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTRecuperarInfSTB
 */
public class ASTRecuperarInfSTBBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB {

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
		log.debug("Inicio Actividad Obtener Informacion STB [" + act.getNumeroPeticion() + "]");

//TODO: LOGICA , lca
		
		try {
		   	RecursosDelegate delegate=new RecursosDelegate();
		   	if(delegate.isIDPC(act.getNumeroPeticion())){
				PeticionesInterfaces pI = new PeticionesDelegate();
			   	boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
			   	if(esGranite){
			   		delegate.envioPuntosRedSTBGR(act.getNumeroPeticion().toString(),act.getCodigoActividad(), null);		   		
			   	}else{
			   		delegate.envioPuntosRedSTB(act.getNumeroPeticion().toString(),act.getCodigoActividad(), null);			
			   	}
		   	}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC");
		   	}
		} catch (ATiempoAppEx e) {
			log.warn("Error en Actividad Informacion de STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Informacion STB", e);
		}
		

		log.debug("Fin Actividad Obtener Informacion STB [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}
}
