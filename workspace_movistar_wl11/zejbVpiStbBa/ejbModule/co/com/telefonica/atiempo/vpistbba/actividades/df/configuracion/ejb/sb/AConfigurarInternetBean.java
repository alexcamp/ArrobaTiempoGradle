package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.Dslam1;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionAdslDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarInternet
 */
public class AConfigurarInternetBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		try
		{
			boolean esTrasladoIgualDslam = false;
			// CR19465 - ana santos  - inicio
			boolean esTrasladoIgualNumero = false;
			boolean esTrasladoBaja=false;
			// CR19465 - ana santos  - fin
			RecursosBAInterfaces recursosBAInterface = new RecursosBADelegate();
			InformacionAdslDTO informacionAdslActual = null;
			PeticionesDelegate peticionDelegate = new PeticionesDelegate();
			Long nroPet = act.getNumeroPeticion();
			//Se busca si existe la informacion de la linea existente
			try{ 
				informacionAdslActual = recursosBAInterface.obtenerDatosActualAdsl( nroPet );
				//Si no tengo IP del Dslam es como si no tuviera nada
				if (informacionAdslActual==null || informacionAdslActual.getDirecIpDslam()==null || informacionAdslActual.getDirecIpDslam().equals("") ){
					informacionAdslActual=null;
				}else{
					log.debug("Dslam ADSL:" + informacionAdslActual.getDirecIpDslam());
				}
			}catch (ATiempoAppEx e)
			{
				//Si no es Finder Exception
				if(e.getType() != ATiempoAppEx.FINDER){
					log.warn("Error en Actividad Configurar Internet",e);
					throw new TnProcesoExcepcion("Error en Actividad Configurar Internet", e);
				}
				else{
					//no se encontro informacion
					informacionAdslActual=null;
				}
			}
			//Si existe la informacion de linea actual, entonces se compara con los Dslam de la linea nueva
			if (informacionAdslActual!=null){
				RecursosInterfaces recSTB = new RecursosDelegate();
				InformacionTecnicaDTO infSTB=recSTB.obtenerRecursosTecnicos(nroPet);
				
				if (infSTB.LineaNueva!=null && infSTB.LineaExistente != null){// es traslado lb pk tiene las dos lineas
					for (Iterator iter=infSTB.LineaNueva.getDslams().iterator();iter.hasNext();){
						Dslam1 unDslam = (Dslam1) iter.next();
						log.debug("Dslam Rec Linea:" + unDslam.getIp());
						if (unDslam.getIp().trim().equals(informacionAdslActual.getDirecIpDslam().trim())){
							esTrasladoIgualDslam=true;
							break;
						}
					}
					
					// CR19465 - ana santos  - inicio
					// valido si hay cambio de numero en la baja de traslado
					esTrasladoIgualNumero = trasladoIgualNumero(act);
					
					// Defecto 21348 - Juan Pereyra - 19-Dic - Inicio
					//Si Nro origen = Nro destino NO ejecutar des-configuracion				
					if(esTrasladoIgualNumero){
						esTrasladoIgualNumero = esBaja(act);
					}										
					// Defecto 21348 - Juan Pereyra - 19-Dic - FIN
					
					// CR19465 - ana santos  - fin	

				}else{
					//veo si es traslado SOLO BA
					Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
					boolean esTrasladoAlta=false;
					Traslado_solobaLocal traslado_solobaLocal=null;
					try{
						traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(nroPet);
						esTrasladoAlta=true;
					} catch (FinderException e) {
						log.warn("No es traslado Alta BA");
						try{
							traslado_solobaLocal = traslado_solobaLocalHome.findByPetiBaja(nroPet);
							// CR19465 - ana santos  - inicio
							esTrasladoBaja=true;	
							// CR19465 - ana santos  - fin
						} catch (FinderException e1) {
							
						}
					}
					if(esTrasladoAlta){
						//si es traslado BA, Comparo los Dslam del linea nueva con el Dslam ADSL de origen
						if (infSTB.LineaNueva!=null){
							for (Iterator iter=infSTB.LineaNueva.getDslams().iterator();iter.hasNext();){
								Dslam1 unDslam = (Dslam1) iter.next();
								log.debug("Dslam Rec Linea:" + unDslam.getIp());
								if (unDslam.getIp().equals(informacionAdslActual.getDirecIpDslam())){
									esTrasladoIgualDslam=true;
									break;
								}
							}
						}
					}else if (esTrasladoBaja){
						//comparo el dslam del ADSL de Baja con el dslam ADSL de ALTA
						InformacionAdslDTO informacionAdslActualAlta= recursosBAInterface.obtenerDatosActualAdsl ( traslado_solobaLocal.getPeti_alta() );
						InformacionAdslDTO informacionAdslNuevaAlta = recursosBAInterface.obtenerDatosAdsl( traslado_solobaLocal.getPeti_alta() );
						if(informacionAdslNuevaAlta!=null){//Si configure ADSL es pk cambie el dslam. deberian ser distinto  
							if (informacionAdslNuevaAlta.getDirecIpDslam().equals(informacionAdslActual.getDirecIpDslam())){
								esTrasladoIgualDslam=true;
							}
						}else if(informacionAdslActualAlta!=null){//si no tiene nueva es pk no hizo la configuracion internek
							//Deberian ser iguales los dslam 
							if (informacionAdslActualAlta.getDirecIpDslam().equals(informacionAdslActual.getDirecIpDslam())){
								esTrasladoIgualDslam=true;
							}
						}
					}else{
						log.debug("No es traslado y no se puede comparar dslam. Se realiza la actividad actual.");
					}
				}
			}
			
			// CR19465 - ana santos  - inicio
			if (esTrasladoIgualNumero){
				log.debug("esTrasladoIgualNumero");
				act.setObservacion("El numero de linea nueva es igual al de linea existente. No se envia el mensaje de Configuracion Internet.");
				act.setRealizarTerminoInmediato(true);
			}else{
				log.debug("No esTrasladoIgualNumero");
			// CR19465 - ana santos  - fin
				if (esTrasladoIgualDslam){
					act.setObservacion("El Dslam de la linea Origen coincide con un Dslam asignado a la linea Nueva. No se envia el mensaje de Configuracion Internet.");
					act.setRealizarTerminoInmediato(true);
				}else{
					log.debug("NO esTrasladoIgualDslam");
					//Esta actividad se hace solo si no hay igual dslam origen destino
					RecursosBAInterfaces delegate=new RecursosBADelegate();
					//Si se hace la actividad es porque psOk > 0
					if(act.getPsOk().size()==1){
						
						Iterator iterTemp = act.getPsOk().iterator();
						//Obtengo el primer PS
						PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
						if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
							log.debug("Inicio Actividad Configurar Internet [" + nroPet + "]");
							delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,false,act.getIdActividadFlujo());
//							act.setObservacion("Se envia el mensaje de Configuracion Internet.");
						}else{
							log.debug("Inicio Actividad Reversa de Configurar Internet [" + nroPet + "]");
							//Si tiene OC de reversa hago la reversa, si no tiene OC de Reversa asignada, no hay reversa y se termina la actividad.
							if (psTemp.getOpComRevId()!=null && psTemp.getOpComRevId().longValue()>-1){
								delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,true,act.getIdActividadFlujo());
//								act.setObservacion("Se envia el mensaje de Reversa Configuracion Internet.");
							}
							else{
								act.setObservacion("No hay OC para la reversa. No se envia mensaje de reversa.");
								act.setRealizarTerminoInmediato(true);
							}
						}
					}
					else 
					    
					    /*
					    if (es UMTS && (es baja o alta de UMTS) si es A o B el OPCO TIPO (campo de la tabla Operacion Comercial)) {
					    	ejecuto el metodo solicitudConfiguracionBA
						}else{se deja como estaba}
					    */
						// CR25996 UMTS - agonz - 16/07/2009
										
						
					    if(peticionDelegate.esGrupoUmts(nroPet) ){
					        
					    	
					    	if (peticionDelegate.validaAltaDuoUmts(act.getNumeroPeticion(),act.getPsOk().iterator())){
					    		
					    		//Mando el ps de BA
				    	 		
				    	 		Iterator iterTemp = act.getPsOk().iterator();
						        while(iterTemp.hasNext()){
									
									PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
									if(psTemp.getOpComTipo()==null)
										continue;
									if(!psTemp.getOpComTipo().equals("A") && !psTemp.getOpComTipo().equals("B")&&!psTemp.getOpComTipo().equals("BCP")&&!psTemp.getOpComTipo().equals("ACP") )
										continue;
//									REQ BA NAKED adicion familia PC naked
									if (psTemp.getFaPsId().intValue()!= ComunInterfaces.familiaPcBA ||psTemp.getFaPsId().intValue()!= ComunInterfaces.familiaPcPsBANaked)
										continue;
									
									//TODO: 29012010 - RETA - Defecto 32462
									if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
										log.debug("Inicio Actividad Configurar Internet [" + nroPet + "]");
										delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,false,act.getIdActividadFlujo());
//										act.setObservacion("Se envia el mensaje de Configuracion Internet UMTS DUO.");
									}else{
										if (psTemp.getOpComRevId()!=null && psTemp.getOpComRevId().longValue()>-1){
											delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,true,act.getIdActividadFlujo());
											act.setObservacion("Se envia reversa del mensaje de Configuracion Internet UMTS DUO.");
										}else{
											act.setObservacion("No hay OC para la reversa. No se envia mensaje de reversa.");
											act.setRealizarTerminoInmediato(true);
										}
									}
									//End TODO
									break;
								}
				    	 	}else{
				    	 		Iterator iterTemp = act.getPsOk().iterator();
						        while(iterTemp.hasNext()){
									
									PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
									if(psTemp.getOpComTipo()==null)
										continue;
									if(!psTemp.getOpComTipo().equals("A") && !psTemp.getOpComTipo().equals("B")&&!psTemp.getOpComTipo().equals("BCP")&&!psTemp.getOpComTipo().equals("ACP") )
										continue;
									
									//TODO: 22012010 - RETA - Ajuste para solo enviar las PS UMTS en el mensaje tr-013
									try{
										Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
										Producto_servicioLocal producto_servicioLocal =null;
																				
										producto_servicioLocal=(Producto_servicioLocal) producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psTemp.getPsId()));
																			
										if (!producto_servicioLocal.getPs_nombre().equals("UMTS"))
											continue;
									}catch(FinderException e){
										e.printStackTrace();
										log.error("Error en la consulta: "	+ e);
									}
									//End TODO
									
									//TODO: 29012010 - RETA - Defecto 32462
									if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
										log.debug("Inicio Actividad Configurar Internet UMTS [" + nroPet + "]");
										delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,false,act.getIdActividadFlujo());
//										act.setObservacion("Se envia el mensaje de Configuracion Internet UMTS.");
									}else{
										if (psTemp.getOpComRevId()!=null && psTemp.getOpComRevId().longValue()>-1){
											delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,true,act.getIdActividadFlujo());
//											act.setObservacion("Se envia reversa del mensaje de Configuracion Internet UMTS.");
										}else{
											act.setObservacion("No hay OC para la reversa. No se envia mensaje de reversa.");
											act.setRealizarTerminoInmediato(true);
										}
									}
									//End TODO
									break;
								}
				    	 	}
					    	
					        
					    
					    }else{
					    
						    if(act.getPsOk().size()>1){	log.debug("act.getPsOk().size()>1");
							// la unica posibilidad que entre aki es para los cambios producto.
							// pork dos peses invocan la actividad.
										
							Iterator iterTemp = act.getPsOk().iterator();
						
							if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
								while(iterTemp.hasNext()){
									
									PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
									if(psTemp.getOpComTipo()==null)
										continue;
									if(!psTemp.getOpComTipo().equals("A") && !psTemp.getOpComTipo().equals("ACP"))
										continue;
									log.debug("Inicio Actividad Configurar Internet [" + nroPet + "]");
									delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),psTemp,false,act.getIdActividadFlujo());
//									act.setObservacion("Se envia el mensaje de Configuracion Internet.");
								}
								
							}else{
						
								//Aqui le paso al metodo de configurar internet 
								//los ps/operaciones de alta y baja
								//para la reversa del traslado.	
								log.debug("Estoy en reversa !!!");
							
								Long psBaja=null;
								Long opBaja=null;
								Long opAlta=null;
								Long psAlta=null;
							
								while(iterTemp.hasNext()){
									
									PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
															
									if(psTemp.getOpComTipo()==null){
										log.debug("OpComTipo Null");
										continue;
									}
								
									if(psTemp.getOpComTipo().equals("BCP")){
										
										psBaja=psTemp.getPsId();
										opBaja=psTemp.getOpComId();
									}
									
									if(psTemp.getOpComTipo().equals("ACP")){
										psAlta=psTemp.getPsId();
										opAlta=psTemp.getOpComId();
									}
								
									if(psBaja==null || opAlta==null || psAlta==null || opBaja==null)
										continue;
									
									PsVsOcVO nuevo=new PsVsOcVO();
								
									nuevo.setPsId(psBaja);
									nuevo.setOpComId(opBaja);
									nuevo.setOpComRevId(opAlta);
									nuevo.setOpComTipo("B");
								
									nuevo.setSacarFatherEmAlta(true);
									nuevo.setPsIdAlta(psAlta);
									nuevo.setOpCoAlta(opAlta);	
								
									log.debug("Inicio Actividad Reversa de Configurar Internet [" + nroPet + "]");
									delegate.solicitudConfiguracionBA(nroPet.toString(),act.getCodigoActividad(),nuevo,true,act.getIdActividadFlujo());
									act.setObservacion("Se envia el mensaje de Reversa Configuracion Internet.");
								
									log.debug("Fin Actividad Configurar Internet [" + nroPet + "]");
									return;
								}
//								act.setObservacion("No existe informacion Coherente para Realizar Actividad.");
								act.setRealizarTerminoInmediato(true);
							}
						}
					}
				}
			}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configurar Internet",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet", e);
		} catch (NamingException e) {
			log.warn("Error en Actividad Configurar Internet",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet", e);
		}
		

		log.debug("Fin Actividad Configurar Internet [" + act.getNumeroPeticion() + "]");
		
	}

	// CR19465 - ana santos  - inicio
	private boolean trasladoIgualNumero(ActividadEJBDTO act){
		RecursosInterfaces recSTB;
		boolean cambioNro = false;
		try {
			recSTB = new RecursosDelegate();
			InformacionTecnicaDTO infTec=recSTB.obtenerRecursosTecnicos(act.getNumeroPeticion());
			
			PeticionKey pkey = new PeticionKey(act.getNumeroPeticion());
			PeticionLocalHome pLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal plocal = pLocalHome.findByPrimaryKey(pkey);
			
			//verifico si se cambió el numero
			String lineaExistente = plocal.getIdentificadorOriLinea();;
			if(infTec.LineaNueva!=null && lineaExistente != null){		
				if(infTec.LineaNueva.getTelefono().equals(lineaExistente)){
					cambioNro = true;
				}
			}
		}catch(ATiempoAppEx e){
			log.warn("Error en Actividad Cambio Numero BA",e);
		}catch (NamingException e){
			log.warn("Error en Actividad Cambio Numero BA",e);
		}catch (FinderException e){
			log.warn("Error en Actividad Cambio Numero BA",e);
		}
		return cambioNro;		
	}
//	 CR19465 - ana santos  - fin	
	// Defecto 21348 - Juan Pereyra - 19-Dic-2008 - Inicio
	private boolean esBaja(ActividadEJBDTO act) {
		Iterator iterTemp = act.getPsOk().iterator();
		PsVsOcVO psTemp;
		boolean baja = false;
		while(iterTemp.hasNext()){
			
			 psTemp= (PsVsOcVO) iterTemp.next();
			
			if(psTemp.getOpComTipo() == null) {
				continue;
			}
			if(psTemp.getOpComTipo().equals("B")){
				baja = true;
				break;
			}
		}
		return baja;
	}
	// Defecto 21348 - Juan Pereyra - 19-Dic-2008 - FIN


	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba))
		{//La seteo solo en los flujos que conocen esa variable
			//Para que no haga el obtener configuracion BA Terra
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_recursos_ba,"S");
		}
//		AT-1524 Reversa para Configurar Internet 08/12/2008 
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals("18")&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){		
			try {
				//llamar al metodo
				PeticionesInterfaces pI = new PeticionesDelegate();
				pI.actualizarEstadoPS(act.getNumeroPeticion(),act.getIdActividadFlujo(),act.getActividadBD().getIdActividad());
			}catch (ATiempoAppEx e) {
				log.warn("Error en Actividad Configurar Internet",e);
				throw new TnProcesoExcepcion("Error en Actividad Configurar Internet", e);
			}	
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
