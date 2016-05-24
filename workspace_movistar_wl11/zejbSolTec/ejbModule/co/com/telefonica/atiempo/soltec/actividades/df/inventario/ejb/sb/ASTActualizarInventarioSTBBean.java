package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.soltec.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTActualizarInventarioSTB
 */
public class ASTActualizarInventarioSTBBean
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
		log.debug("Inicio enviar Actualizacion Inventario STB [" + act.getNumeroPeticion() + "]");

//TODO: LOGICA , lca
		
		try {
			PeticionesInterfaces pI = new PeticionesDelegate();
			RecursosInterfaces delegate=new RecursosDelegate();
			if(delegate.isIDPC(act.getNumeroPeticion())){
				boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
			   	//RecursosDelegate delegate=new RecursosDelegate();		   	
			   	if(esGranite){
			   		delegate.envioMarcaLineaEnServicioGR(act.getNumeroPeticion().toString(),act.getCodigoActividad());
			   	}else{
			   		delegate.envioMarcaLineaEnServicio(act.getNumeroPeticion().toString(),act.getCodigoActividad());
			   	}
			}else{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe por que no viene IDPC");
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.ACUALIZA_INV_STB.control_act_inv_stb,"N");
			}
		} catch (ATiempoAppEx e) {
			log.warn("Error al enviar actualizacion inventario STB",e);
			throw new TnProcesoExcepcion("Error al enviar actualizacion inventario STB", e);
		}
		

		log.debug("Fin enviar actualizacion inventario STB [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

}
