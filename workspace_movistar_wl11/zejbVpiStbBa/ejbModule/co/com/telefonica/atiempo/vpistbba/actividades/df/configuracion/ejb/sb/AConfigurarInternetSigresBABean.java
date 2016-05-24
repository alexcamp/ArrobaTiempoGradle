package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarInternetSigresBA
 */
public class AConfigurarInternetSigresBABean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {


	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("Inicio Actividad Configurar Internet Sigres BA [" + act.getNumeroPeticion() + "]");
		try
		{
			RecursosBADelegate delegate=new RecursosBADelegate();
			
			//TODO: 10022010 - Ajuste para asignación correcta de IP_Fija - RETA 
			//TODO: 12032010 - YCP -- Ajuste para cuando viene en una Alta la peticion de ip Fija y Modificaciones.
			
			String psIpFija = VpistbbaConfig.getVariable("PS_IPFIJA");
			long psIPFijaPeticion = 0;
			boolean peticionTipoAlta = false;
			boolean esIPFijaPeticion = false; 
			boolean entroPeticionTipoAlta = false;
			boolean noEntroPeticionTipoAlta = false;
			boolean esPsIPFija = false;
			boolean peticionTipoBaja = false;
			boolean psUnico = false;
			boolean esSVABA = false;
			Collection psPeticion = null;
			int numPS = 0;			
			
			PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			PeticionLocalHome peticionLocalHome;
			PeticionLocal peticionLocal = null;
			
			//PS de velocidad adicional
			Properties_BDLocalHome propertiesHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesLocal = propertiesHome.findByPrimaryKey(ComunInterfaces.PS_HOMOLOGADO);
			String psSVABA[] = propertiesLocal.getValor().split(",");
			
			try {
			   peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			   peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
			   psPeticion = peticionLocal.getProducto_servicio_peticion();
			} catch (NamingException e2) {
			    log.debug("No se pudo obtener PeticionLocalHome", e2);
			}
			catch (FinderException e1) {
			    log.debug("No se encontró la petición", e1);
			}
			Iterator listaPsPetIt=null;
			for(listaPsPetIt=psPeticion.iterator();listaPsPetIt.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal = (Producto_servicio_peticionLocal)listaPsPetIt.next();
				Producto_servicioLocal producto_servicioLocal = (Producto_servicioLocal) producto_servicio_peticionLocal.getProducto_servicio();
				Familia_producto_servicioKey familia_producto_servicioKey=(Familia_producto_servicioKey) producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();
				int FamiliaTem = familia_producto_servicioKey.faps_id.intValue();
//				REQ BA NAKED adicion familia PC naked
				if((FamiliaTem == ComunInterfaces.familiaBandaAncha
						|| FamiliaTem == ComunInterfaces.familiaPcBA 
						|| FamiliaTem == ComunInterfaces.familiaPcPsBANaked
						|| FamiliaTem == ComunInterfaces.familiaBandaAnchaNaked)
						&& (peticionLocal.getTica_id().equals(ComunInterfaces.opCoTipoBaja)))
					numPS++;
			}
			
			if(numPS <= 1){
				psUnico = true;
			}
			Iterator iterador = act.getPsOk().iterator();
			while(iterador.hasNext()){
				PsVsOcVO psTemp= (PsVsOcVO) iterador.next();
			    
				if(psIpFija != null && psIpFija.length() > 0){
					String[] tokens = null;
					log.debug("Los valores de psIpFija: " + psIpFija);
					tokens = psIpFija.split(",");
					for (int i = 0 ; i < tokens.length ; i++) {
						log.debug("se compara: "+ tokens[i] + " con: " + psTemp.getPsId());
						if(tokens[i].equals(String.valueOf(psTemp.getPsId()))){
							psIPFijaPeticion  = psTemp.getPsId().longValue();
							esPsIPFija = true;
							log.debug("es PsIPFija");
//							REQ BA NAKED adicion familia PC naked
							if ( ((psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaBandaAncha) 
									|| (psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcBA)
									|| (psTemp.getFaPsId().intValue()==ComunInterfaces.familiaPcPsBANaked)
									|| (psTemp.getFaPsId().intValue()==ComunInterfaces.familiaBandaAnchaNaked)) 
									&& (psTemp.getOpComTipo().equals(ComunInterfaces.opCoTipoBaja) || peticionLocal.getTica_id().equals(ComunInterfaces.opCoTipoBaja))){
								/*if (act.getPsOk().size() == 1 && psTemp.getPsId().longValue() == psIPFijaPeticion){
									psUnico = true;
									log.debug("no es peticionTipoAlta, porque trae un solo PS de IP Fija");
								}*/
								peticionTipoBaja = true;
								log.debug("es peticionTipoBaja");
							}
						}
					}
				}
//				Se valida si el PS pertenece a un SVA de aumento de velocidad
				for(int i = 0; i<psSVABA.length;i++){
					if(new Long(psSVABA[i]).equals(psTemp.getPsId()) && act.getPsOk().size()==1 
							&& !psTemp.getOpComTipo().equals("BCP")){
						esSVABA = true;
						break;
					}
				}
				log.debug("Resultado de SVA BA: "+esSVABA + " Para el PS:"+psTemp.getPsId());
//				REQ BA NAKED adicion familia PC naked
				if ( ((psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaBandaAncha)
						|| (psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaPcBA)
						|| (psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaPcPsBANaked)
						|| (psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaBandaAnchaNaked)
						|| (psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaPcBANaked)) 
					&& (psTemp.getOpComTipo().equals(ComunInterfaces.opCoTipoAlta))   ){
					peticionTipoAlta = true;
					log.debug("es peticionTipoAlta");
					
					if (act.getPsOk().size() == 1 && (psTemp.getPsId().longValue() == psIPFijaPeticion ||esSVABA)){
						peticionTipoAlta = false;
						log.debug("no es peticionTipoAlta, porque trae un solo PS de IP Fija");
					}
				}
				
				
				
			}
			//End TODO
					
			if (peticionTipoAlta)	{
				log.debug("Entra a la opción peticionTipoAlta = true");
				entroPeticionTipoAlta = true;
				Iterator iterTemp = act.getPsOk().iterator();
				boolean noInformar = false;
				while (iterTemp.hasNext() ){
					//Obtengo el primer PS
					PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
					if (psTemp.getPsId().longValue() != psIPFijaPeticion ){
						log.debug("Resultado de SVA BA: "+esSVABA + " Para el PS:"+psTemp.getPsId());
						if (psTemp.getPsId().longValue() != psIPFijaPeticion && !esSVABA){
							String opComTipo = psTemp.getOpComTipo();
							
							//se agrega validacion para PC naked el cual no debe informar tr030E
							if(opComTipo==null||
									(psTemp.getFaPsId()!=null &&psTemp.getFaPsId().intValue()==(ComunInterfaces.familiaPcBANaked)))
								continue;
							if(!opComTipo.equals("A") && !opComTipo.equals("ACP"))
								continue;
							
						}
//						if(esSVABA && act.getPsOk().size() > 0){
							for(int i = 0; i<psSVABA.length;i++){
							    if(new Long(psSVABA[i]).equals( psTemp.getPsId())){
							    	noInformar = true;
							    	break;
							    }else
							    	noInformar = false;
							   }
//						}
						
						if(noInformar)
							continue;
						if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
							log.debug("Inicio Actividad Configurar Internet [" + act.getNumeroPeticion() + "]");
							delegate.solicitudConfiguracionSigresBA(act.getNumeroPeticion().toString(),act.getCodigoActividad(),psTemp,psIPFijaPeticion,false,act.getIdActividadFlujo(), act);
//							act.setObservacion("Se envia el mensaje de Configuracion Internet.");
						} else {
							Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
							ActividadSessionLocalHome actHome;
							try {
//								REQ BA NAKED adicion familia PC naked
								if ( ((psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaPcBA)||(psTemp.getFaPsId().intValue() ==   ComunInterfaces.familiaPcPsBANaked)) 
										&& (psTemp.getOpComTipo().equals(ComunInterfaces.opCoTipoAlta))){
									actHome =(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
									ActividadSessionLocal actLocal = actHome.create();
									ActividadDTO actDto = actLocal.recuperaActividad(act.getCodigoActividad(),idAplicacion);
									RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
									recursosBADelegate.informarResultadoInstalacionSigres(act.getNumeroPeticion(),act.getCodigoActividad(),act);
								}
							} catch (NamingException e1) {
								e1.printStackTrace();
							} catch (CreateException e) {
								e.printStackTrace();
							}
							//TODO: CR4860 -  Reversa de Sigres Configurar Internet
							//throw new UnsupportedOperationException("Reversa de Configurar Internet de Sigres no implementada (ps=1)");
						}
					}
				}
			} 
			
			if(!peticionTipoAlta) {
				log.debug("Entra a la opción peticionTipoAlta = false");
				noEntroPeticionTipoAlta = true;
				boolean noIncluirPSPrim = false;
				// la unica posibilidad que entre aki es para los cambios producto.
				// pork dos peses invocan la actividad.
				log.debug("Tamaño getPsOk:"+act.getPsOk().size());
				Iterator iterTemp = act.getPsOk().iterator();
				 if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")) {
			 		while(iterTemp.hasNext()) {
						PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
						//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
							
						if (psTemp.getPsId().longValue() != psIPFijaPeticion && !esSVABA){
							String opComTipo = psTemp.getOpComTipo();
							
							if(opComTipo==null)
								continue;
							if(!opComTipo.equals("A") && !opComTipo.equals("ACP"))
								continue;
							
						}else{
							if(!esSVABA)
						 	noIncluirPSPrim = true;
						}
						
						if(peticionTipoBaja && esPsIPFija && !psUnico){
							String mensajeBitacora = new String("");
							mensajeBitacora = "Se inhibe la actividad porque es un PS de baja BA PS's configurados para enviar info. a SAP, y/o no existen equipos asociados a la petición.";
							log.debug("Mensaje Bitacora = " + mensajeBitacora);
							act.setObservacion(mensajeBitacora);
							act.setRealizarTerminoInmediato(true);
						}else{
							log.debug("Inicio Actividad Configurar Internet Cambio de Plan[" + act.getNumeroPeticion() + "]");
							delegate.solicitudConfiguracionSigresCambioPlan(act.getNumeroPeticion().toString(),act.getCodigoActividad(),psTemp,psIPFijaPeticion, false,act.getIdActividadFlujo(), noIncluirPSPrim, act);
//							act.setObservacion("Se envia el mensaje de Configuracion Internet.");
							break;
						}
						
									
							
						/*if ((psTemp.getPsId().longValue() != psIPFijaPeticion) 
								|| (act.getPsOk().size() == 1 && psTemp.getPsId().longValue() == psIPFijaPeticion)){
								
							//TODO: 29042010 - Raúl Ernesto Triviño Alvarado - Ajuste para mandar este mensjae en caso que sea un IP fija de baja 
							if (act.getPsOk().size() != 1 && psTemp.getPsId().longValue() != psIPFijaPeticion){
								String opComTipo = psTemp.getOpComTipo();
								if(opComTipo==null)
									continue;
								if(!opComTipo.equals("A") && !opComTipo.equals("ACP"))
									continue;	
							}
							//End TODO*/
							
							
						
					}
				} else {
					//AT-1284 -agonz- Reversa de traslado 21-07-2008
					Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
					ActividadSessionLocalHome actHome;
					try {
						actHome = (ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
						ActividadSessionLocal actLocal = actHome.create();
						ActividadDTO actDto = actLocal.recuperaActividad(act.getCodigoActividad(),idAplicacion);
						RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
						recursosBADelegate.informarResultadoInstalacionSigres(act.getNumeroPeticion(),act.getCodigoActividad(),act);
					} catch (NamingException e1) {
						e1.printStackTrace();
					} catch (CreateException e) {
						e.printStackTrace();
					}
					//throw new UnsupportedOperationException("Reversa de Configurar Internet de Sigres no implementada (ps>1)");
				}
			} 
			
			if (!entroPeticionTipoAlta && !noEntroPeticionTipoAlta){
				log.debug("No entro a ninguna petición");
				String m = "onInicioActividadVPI() No se configura internet sigres ps=0";
				log.error(m);
				throw new TnProcesoExcepcion(m);
			}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configurar Internet Sigres",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Sigres", e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("no se encuentra valor asociado el tabla properties_BD", e);
			throw new TnProcesoExcepcion("no se encuentra valor asociado el tabla properties_BD", e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("no se encuentra valor asociado el tabla properties_BD", e);
			throw new TnProcesoExcepcion("no se encuentra valor asociado el tabla properties_BD", e);
		}
		log.debug("Fin Actividad Configurar Internet Sigres BA [" + act.getNumeroPeticion() + "]");
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba))
		{//La seteo solo en los flujos que conocen esa variable
			//Para que no haga el obtener configuracion BA Terra y Aula
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba,"S");
		}
		
		//DAVID: falta para este caso la reversa para Configurar Internet 01/12/2010
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals("18")&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){		
			try {
				PeticionesInterfaces pI = new PeticionesDelegate();
				pI.actualizarEstadoPS(act.getNumeroPeticion(),act.getIdActividadFlujo(),act.getActividadBD().getIdActividad());
			}catch (ATiempoAppEx e) {
				log.warn("Error en Actividad Configurar Internet Sigres",e);
				throw new TnProcesoExcepcion("Error en Actividad Configurar Internet Sigres", e);
			}	
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}