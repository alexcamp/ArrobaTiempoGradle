package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam1;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarInternetSigresBA
 */
public class AConfigurarInternetSiTrasladoBABean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {


	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Configurar Internet Traslado BA Sigres [" + act.getNumeroPeticion() + "]");
		InformacionAdslDTO informacionAdslActual = null;
		boolean esSVABA = false;
		
		
		//Se busca si existe la informacion de la linea existente
		try{ 	
		    RecursosBADelegate delegate =  new RecursosBADelegate();
			informacionAdslActual = delegate.obtenerDatosActualAdsl( act.getNumeroPeticion() );
			
//			PS de velocidad adicional
			Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
			String psSVABA[] = propertiesLocal.getValor().split(",");
			
			//Si no tengo IP del Dslam es como si no tuviera nada
			//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
			String codZonaAtend = null;
			if (informacionAdslActual!=null){
				codZonaAtend = informacionAdslActual.getCodZonaAtend();	
			}
			if (codZonaAtend==null || codZonaAtend.equals("") ){
				informacionAdslActual=null;
			}else
			{
				log.debug("Dslam ADSL:" + codZonaAtend);
			}
		
//			Defecto 19914	-agonz 10/11/2008
//			Pregunta si la zona origen y destino son diferentes 
//	    	en caso de que sean diferentes entonces ahi se ejecuta la actividad configurar Internet para Traslado 
//	    	en caso de que sean iguales entonces no se ejecuta la actividad 
			boolean esTrasladoIgualZona = false;
		    
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
			log.debug("Tamaño getPsOk:"+act.getPsOk().size());
			Iterator iterTemp = act.getPsOk().iterator();
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")) {
										    
				if(!esTrasladoIgualZona){
								 
				    while(iterTemp.hasNext()) {
						PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
						String opComTipo = psTemp.getOpComTipo();
						//Se valida si el PS pertenece a un SVA de aumento de velocidad
						for(int i = 0; i<psSVABA.length;i++){
							if(new Long(psSVABA[i]).equals(psTemp.getPsId()) && act.getPsOk().size()>1){
								esSVABA = true;
								break;
							}else{
								esSVABA = false;
							}
						}
						if(opComTipo==null)
							continue;
						if(!opComTipo.equals("A") && !opComTipo.equals("ACP"))
							continue;		
						
						log.debug("Resultado de SVA BA: "+esSVABA + " Para el PS:"+psTemp.getPsId());
						if(esSVABA)
							continue;
						log.debug("Inicio Actividad Configurar Internet Traslado[" + act.getNumeroPeticion() + "]"); 
						delegate.solicitudConfiguracionSigresTraslado(act.getNumeroPeticion().toString(),act.getCodigoActividad(),psTemp,false,act.getIdActividadFlujo(), act);
//						act.setObservacion("Se envia el mensaje de Configuracion Internet Traslado.");
						//TODO VALIDAR BREAK Y CAMBIO DE PLAN	
					}
					
				}else{
					act.setObservacion("La zona nueva y la zona actual son iguales. No se envia a configurar Internet Traslado Sigres");
					act.setRealizarTerminoInmediato(true);
				}
//				Fin Defecto 19914 	
											
					
			} else {
			    
			    if(!esTrasladoIgualZona){
	//				AT-1284 -agonz- Reversa de traslado 21-07-2008 
					Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
					ActividadSessionLocalHome actHome;
					try {
						actHome =
							(ActividadSessionLocalHome) HomeFactory.getHome(
								ActividadSessionLocalHome.JNDI_NAME);
						ActividadSessionLocal actLocal = actHome.create();
						ActividadDTO actDto = actLocal.recuperaActividad(act.getCodigoActividad(),idAplicacion);
						RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
						recursosBADelegate.informarResultadoInstalacionSigres(act.getNumeroPeticion(),act.getCodigoActividad(),act);
					} catch (NamingException e1) {
						e1.printStackTrace();
					} catch (CreateException e) {
						e.printStackTrace();
					}
					//TODO: CR4860 -  Reversa de Sigres Configurar Internet
					//throw new UnsupportedOperationException("Reversa de Configurar Internet Traslado de Sigres no implementada..:");
			
			    }else{
			        act.setObservacion("La zona nueva y la zona actual son iguales. No se envia Reversa de Configurar Internet Traslado Sigres");
					act.setRealizarTerminoInmediato(true);
			    }
			}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configurar Internet Traslado Sigres",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Traslado de Sigres", e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("Error en Actividad Configurar Internet Traslado Sigres",e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("Error en Actividad Configurar Internet Traslado Sigres",e);
		}
		log.debug("Fin Actividad Configurar Internet Traslado Sigres BA [" + act.getNumeroPeticion() + "]");
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
