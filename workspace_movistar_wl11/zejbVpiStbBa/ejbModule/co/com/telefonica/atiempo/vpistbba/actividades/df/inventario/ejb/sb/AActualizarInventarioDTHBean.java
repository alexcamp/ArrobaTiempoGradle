package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Inventario_dthKey;
import co.com.telefonica.atiempo.ejb.eb.Inventario_dthLocal;
import co.com.telefonica.atiempo.ejb.eb.Inventario_dthLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: AActualizarInventarioDTH
 */
public class AActualizarInventarioDTHBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Actualizar Inventario BA [" + act.getNumeroPeticion() + "]");

		//LOGICA LCA
		try
		{
			boolean inventarioListo=false;
			RecursosTVDelegate recursosTVDelegate=new RecursosTVDelegate();
			Inventario_dthLocalHome home=(Inventario_dthLocalHome) HomeFactory.getHome(Inventario_dthLocalHome.JNDI_NAME);
			try
			{
				Inventario_dthLocal local=home.findByPrimaryKey(new Inventario_dthKey(new PeticionKey(act.getNumeroPeticion())));
				if(local.getEstado()!=null && local.getEstado().equals("1"))
				{
					act.setObservacion("La actualización de Inventario DTH ya se ha realizado");
					act.setRealizarTerminoInmediato(true);
				}
				else
					recursosTVDelegate.enviaActualizacionInventarioTV(act.getNumeroPeticion().longValue(),act.getCodigoActividad());
			}
			catch (FinderException e1)
			{
				recursosTVDelegate.enviaActualizacionInventarioTV(act.getNumeroPeticion().longValue(),act.getCodigoActividad());
			}
		}
		catch (ATiempoAppEx e)
		{
			log.warn("Error en Actividad Actualizar Inventario DTH",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario DTH", e);
		} catch (NamingException e)
		{
			log.warn("Error en Actividad Actualizar Inventario DTH",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario DTH", e);
		}

		log.debug("Fin Actividad Actualizar Inventario BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
