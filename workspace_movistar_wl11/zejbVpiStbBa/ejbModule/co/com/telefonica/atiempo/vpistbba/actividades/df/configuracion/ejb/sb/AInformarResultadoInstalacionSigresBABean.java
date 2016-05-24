//CR4860 SIGRES - GUSTAVOG - 02/05/2008

package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam1;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: AInformarResultadoInstalacionSigresBA
 */
public class AInformarResultadoInstalacionSigresBABean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {




	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	 
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		log.debug("Inicio Actividad Informar Resultado Instalacion Sigres BA [" + act.getNumeroPeticion() + "]");

		try
		{
		    if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
		        boolean esTraslado = false;
			    
			    RecursosBADelegate delegate =  new RecursosBADelegate();
				esTraslado = delegate.esTrasladoBa(act.getNumeroPeticion());
				
				if(esTraslado){
//				  Defecto 20476 - agonz - 24/11/2008
			        InformacionAdslDTO informacionAdslActual = null;
			        boolean esTrasladoIgualZona = false;
				  
					informacionAdslActual = delegate.obtenerDatosActualAdsl( act.getNumeroPeticion() );
					//Si no tengo IP del Dslam es como si no tuviera nada
					//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
					String codZonaAtend = null;
					if(informacionAdslActual!=null){
						codZonaAtend = informacionAdslActual.getCodZonaAtend();
					}
					if (codZonaAtend==null || codZonaAtend.equals("") ){
						informacionAdslActual=null;
					}else
					{
						log.debug("Dslam ADSL:" + codZonaAtend);
					}
					RecursosInterfaces recSTB = new RecursosDelegate();
					InformacionTecnicaDTO infSTB = recSTB.obtenerRecursosTecnicos(act.getNumeroPeticion());
					//si es traslado BA, Comparo los Dslam del linea nueva con el Dslam ADSL de origen
					if (infSTB.LineaNueva!= null){
						 if(informacionAdslActual!= null){
							for (Iterator iter=infSTB.LineaNueva.getZonas().iterator();iter.hasNext();){
								Dslam1 zonasLocal = (Dslam1)iter.next();
								log.debug("Zona Rec Linea:" + zonasLocal.getIp());
								if (zonasLocal.getIp().equals(codZonaAtend)){
									esTrasladoIgualZona=true;
									break;
								}
							}
						 }else{
						     log.warn("Error en Actividad Configurar Internet Traslado Sigres. No se encontro Zona Origen");
							 throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Traslado de Sigres. No se encontro Zona Origen");
						 }
					}
				    if(!esTrasladoIgualZona){
				
//				  	Se realiza la actividad informa resultado de instalación
		            RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
					recursosBADelegate.informarResultadoInstalacionSigres(act.getNumeroPeticion(),act.getCodigoActividad(),act);
					}else{
//		          	No se realiza la actividad informa resultado de instalación
					act.setObservacion("La zona nueva y la zona actual son iguales. No se informa resultado de instalación  Sigres");
					act.setRealizarTerminoInmediato(true);
					}
				}else{
				    //Se realiza la actividad informa resultado de instalación
		            RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
					recursosBADelegate.informarResultadoInstalacionSigres(act.getNumeroPeticion(),act.getCodigoActividad(),act);
				    
				}
			}else{
				act.setObservacion("En reversa no es necesario realizar la consulta.");
				act.setRealizarTerminoInmediato(true);	
			}
		} catch (ATiempoAppEx e) {
			log.debug("Error en Informar Resultado Instalacion Sigres BA",e);
			throw new TnProcesoExcepcion("Error en Actividad Informar Resultado Instalacion Sigres BA Bean");
		} 
		
		log.debug("Fin Actividad Informar Resultado Instalacion Sigres BA [" + act.getNumeroPeticion() + "]");
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
