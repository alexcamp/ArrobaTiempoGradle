package co.com.telefonica.atiempo.soltec.actividades.df.inventario.ejb.sb;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.soltec.servicios.EquipoSTDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;


/**
 * Bean implementation class for Enterprise Bean: ASTActualizarInventarioEquipo
 */
public class ASTActualizarInventarioEquipoBean extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
     */
    protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
     */
    protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
    	log.debug("Inicio Actividad Actualizar Inventario BA [" + act.getNumeroPeticion() + "]");

		//LCA LOGICA
		EquipoSTDelegate eDelegate =null;
        try {
            eDelegate = new EquipoSTDelegate();
        } catch (ATiempoAppEx e1) {           
            e1.printStackTrace();
        }
        try
		{
			
			boolean nohayequipo = eDelegate.noHayEquipoParaActualizarInventarioST(act.getNumeroPeticion());
			if(nohayequipo)
			{
				act.setRealizarTerminoInmediato(true);
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.RECUPERA_INF_BA.fluj_mult_rec_inf_ba,"NActInvEquipo");
				act.setObservacion("No hay Equipos capturado Por lo tanto no tengo que Actualizar inventario");

			}
			else
			    eDelegate.enviaActualizaInventarioEquipo(act.getNumeroPeticion().toString(),act.getCodigoActividad());
		} catch (ATiempoAppEx e)
		{
			log.warn("Error en Actividad Actualizar Inventario BA",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario BA", e);
		}

		log.debug("Fin Actividad Actualizar Inventario BA [" + act.getNumeroPeticion() + "]");
        
    }

    /* (non-Javadoc)
     * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
     */
    protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {        
        
    }
   
}
