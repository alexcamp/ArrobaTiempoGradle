//CR4860 SIGRES - GUSTAVOG - 28/04/2008

package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: AObtenerCuentaCorreoSigresBA
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class AObtenerCuentaCorreoSigresBABean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	 
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		log.debug("Inicio Actividad Obtener Cuenta Correo Sigres BA [" + act.getNumeroPeticion() + "]");
		 PeticionLocalHome peticionLocalHome = null ;
		try
		{
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
				//veo si es alta traslado
				//Correccion defecto 00023671 - 9 marzo 2009 
				String numeroServicio=null; 
					
				 boolean esTraslado = recursosBADelegate.esTrasladoBa(act.getNumeroPeticion());
				 boolean esCambioDeNumero = recursosBADelegate.esCambioDeLinea(act.getNumeroPeticion());
				
				if(esTraslado){
					Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
					Traslado_solobaLocal traslado_solobaLocal=null;
					try{
						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(act.getNumeroPeticion());
						numeroServicio=traslado_solobaLocal.getLinea_anterior();
						log.debug("Es traslado solo BA");
					} catch (FinderException e) {
					    log.debug("Es traslado LB y BA");
					    PeticionLocal petiLocal;
                        try {
                            if(peticionLocalHome==null)
                            	peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
                            
                            petiLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
                            numeroServicio=petiLocal.getIdentificadorOriLinea();
                            //numeroServicio=petiLocal.getNum_ide_nu_stb();
                        } catch (FinderException e1) {
                            log.debug("Error en Obtener Cuenta Correo Sigres BA",e);
                			throw new TnProcesoExcepcion("Error en Actividad Obtener Cuenta Correo Sigres BA Bean No se pudo obtener el telefono de traslado");
                        }
  
					}
				
				}
				//DAVID: cambio de numero, Mayo 4 2011
				else if(esCambioDeNumero){
					PeticionLocal petiLocal;
					try{
						if(peticionLocalHome==null)
	                    	peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
						  petiLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
	                      Collection rLb=petiLocal.getRecursos_linea_basica();
	                      
	                      Iterator rLbIt=null;
	                      
	                      Long numeroTelefonoAnterior=new Long(0);
	                      
	                      for(rLbIt=rLb.iterator();rLbIt.hasNext();){
	                      	Recursos_linea_basicaLocal recursos_linea_basicaLocal = (Recursos_linea_basicaLocal)rLbIt.next();
	                      	if(recursos_linea_basicaLocal.getTelefono_ant()!=null){
	                      		numeroTelefonoAnterior=recursos_linea_basicaLocal.getTelefono_ant();
	                      		break;
	                      	}
	                      }
                    	 
	                      numeroServicio=numeroTelefonoAnterior.toString();
	                      
					}catch(FinderException e){
						log.debug("Error al buscar id pc para cambio de numero...",e);
					}
					
				}

				ArrayList arrayList = act.getPsOk();		
				PsVsOcVO psprim = ActividadEJBDTO.getPsPrimario(arrayList);	
			
				//si es traslado alta, busco la cuenta de correo donde esta instalada la linea
				recursosBADelegate.obtenerCuentaCorreoSigres(act.getNumeroPeticion(),numeroServicio, act.getCodigoActividad(),psprim);
				//Fin Correccion defecto 00023671 - 9 marzo 2009 
			}else{
				act.setObservacion("En reversa no es necesario realizar la consulta.");
				act.setRealizarTerminoInmediato(true);	
			}
		} catch (ATiempoAppEx e) {
			log.debug("Error en Obtener Cuenta Correo Sigres BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Cuenta Correo Sigres BA Bean");
		} 
		catch (NamingException e) {
			log.debug("Error en Obtener Cuenta Correo Sigres BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Obtener Cuenta Correo Sigres BA Bean");
		}
		log.debug("Fin Actividad Obtener Cuenta Correo Sigres BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		log.debug("cod:" + act.getCodigoActividad());
//		if(VpistbbaConfig.getVariable("COD_ACTIVIDAD_OBT_CORREO_BA").equals(act.getCodigoActividad())){ //Si solo Obt config BA normal, no para CDS
//			if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_cuenta_correo))
//			{//La seteo solo en los flujos que conocen esa variable
//				//Para que no haga el obtener configuracion BA Terra
//				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_cuenta_correo,"S");
//			}
//		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
