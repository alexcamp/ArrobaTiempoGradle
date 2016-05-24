package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ModemKey;
import co.com.telefonica.atiempo.ejb.eb.ModemLocal;
import co.com.telefonica.atiempo.ejb.eb.ModemLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ADesconfigurarModem
 */
public class ADesconfigurarModemBean
extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("------- pasa por ADesconfigurarModem");
		try {
			String informacion = null;
			RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
			ModemLocalHome modemLocalHome = (ModemLocalHome) HomeFactory.getHome(ModemLocalHome.JNDI_NAME);
			Collection  collectionModems = modemLocalHome.findPeticion(act.getNumeroPeticion());
			
			if(collectionModems != null && collectionModems.size() > 0){
				for( Iterator iterator = collectionModems.iterator(); iterator.hasNext(); ){
					ModemLocal modemLocal = (ModemLocal) iterator.next();
					if( modemLocal.getAccion().intValue() == ComunInterfaces.accionModemLiberar ){
						ModemVpiDTO modemVpiDTO = new ModemVpiDTO();
						
						modemVpiDTO.setPeti_numero( ((ModemKey)modemLocal.getPrimaryKey()).peticion_peti_numero );
						modemVpiDTO.setMarca( modemLocal.getModem_marca() );
						modemVpiDTO.setSerial( ((ModemKey)modemLocal.getPrimaryKey()).serial );
						modemVpiDTO.setModelo( modemLocal.getModelo() );
						modemVpiDTO.setTelefono( modemLocal.getTelefono() );
						modemVpiDTO.setAccion( modemLocal.getAccion().shortValue() );
						modemVpiDTO.setTipo( modemLocal.getTipo().shortValue() );
						
						//informacion = recursosBADelegate.llamadoConfModemAutoInstalacion( modemVpiDTO,act.getCodigoActividad(),null,true, false);
						//REQ BA NAKED 
						//se cambia el direccionamiento de ejecucion del antiguo llamado por webservice hacia direccionamiento por cola
						ACSServicioDelegate aCSServicioDelegate = new ACSServicioDelegate();
						aCSServicioDelegate.enviarAutoConfiguracion(modemVpiDTO,act.getCodigoActividad(), null);
						//FIN REQ NAKED
//						
//						if(informacion != null ){
//							act.setObservacion("Se envía TR-135 y se obtiene la siguiente respuesta: "+informacion);
//						}else{
//							act.setObservacion("Error: No se envía TR-135 porque no hay respuesta del webservice.");
//						}
						//act.setRealizarTerminoInmediato(true);
					}else{
						act.setObservacion("No se envía la TR-135 porque el modem no esta configurado.");
						act.setRealizarTerminoInmediato(true);
					}
				}
			}else{
				act.setObservacion("Se inhibe la actividad porque no existe ningun modem asociado a la peticion.");
				act.setRealizarTerminoInmediato(true);
			}
		} catch (NamingException ne) {
			log.debug("Error NamingException " + ne.toString());
		}catch (FinderException fe) {
			log.debug("Error no se encontro " + fe.toString());
		} catch (ATiempoAppEx ae) {
			log.debug("Error al crear delegate " +ae.toString());
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

}
