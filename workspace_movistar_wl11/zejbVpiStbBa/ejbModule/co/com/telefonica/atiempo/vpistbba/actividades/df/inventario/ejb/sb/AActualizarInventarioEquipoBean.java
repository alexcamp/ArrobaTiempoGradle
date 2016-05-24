package co.com.telefonica.atiempo.vpistbba.actividades.df.inventario.ejb.sb;

import java.io.File;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.EquipoLocal;
import co.com.telefonica.atiempo.ejb.eb.EquipoLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: AActualizarInventarioBA
 */
public class AActualizarInventarioEquipoBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Actualizar Inventario Equipo [" + act.getNumeroPeticion() + "]");

		//LCA LOGICA
		EquipoLocal elocal = null;
		try {
            elocal = ((EquipoLocalHome) HomeFactory.getHome(EquipoLocalHome.JNDI_NAME)).create();           
        } catch (CreateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		try
		{
			
			boolean nohayequipo = elocal.noHayEquipoParaActualizarInventarioBa(act.getNumeroPeticion());
			if(nohayequipo)
			{
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("No hay Equipos capturado Por lo tanto no tengo que Actualizar inventario");
			}
			else
			    elocal.enviaActualizaInventarioEquipo(act.getNumeroPeticion().toString(),act.getCodigoActividad());
				
				File dir = new File(VpistbbaConfig.getVariable("DIRECCION_PDF"));
				String listaArchivos[] = dir.list();
			  
				if (listaArchivos!=null){
					for(int i=0; i<listaArchivos.length; i++){
						String nomArchivo = listaArchivos[i];
						if (nomArchivo.indexOf(act.getNumeroPeticion().toString()) > 0){
							File archivo = new File(dir, nomArchivo);
							archivo.delete();
						}
					}
				}
		} catch (ATiempoAppEx e)
		{
			log.warn("Error en Actividad Actualizar Inventario Equipo",e);
			throw new TnProcesoExcepcion("Error en Actualizar Inventario Equipo", e);
		}

		log.debug("Fin Actividad Actualizar Inventario Equipo [" + act.getNumeroPeticion() + "]");
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
