package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: AConfiguracionAutomaticaMSAN
 */
public class AConfiguracionAutomaticaMSANBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		try{
						
//			Req FTTC se agrega la consulta a la tabla recursos_linea_baqsica para sabews si la peticion contiene campos de tipo FTTC para que se inhiba la actividad
			PeticionLocalHome petHome = (PeticionLocalHome) HomeFactory.getHome( PeticionLocalHome.JNDI_NAME);
			PeticionLocal petLocal = petHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
		    Recursos_linea_basicaLocal recursos_linea_basicaLocal;
		    Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
			
			//Se realiza la validacion en el campo rec_fttc_asg para saber si es FTTC o no, si viene en S significa q hay campos FTTC y se debe continuar la actividad, si viene N debe inhibir la activdad
			log.debug("-Comienza Validacion FTTC");
			String mensaje="";
			boolean hayPC = false;
			boolean isprecableada = false;
//			intanciamos la talba producto_servicio//	
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			//guardamos los ps en de la ptecion en un arreglo
			PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
			Collection ps = peticionesInterface.listaDePsDelaActividadEstadoFinal(act.getNumeroPeticion(), act.getIdActividadFlujo());
			String operacionComercial="";
			//recorremos el arreglo
			if(ps!=null && ps.size()>0){
					for (Iterator iter = ps.iterator(); iter.hasNext();) {
					
						//guardamos en un iterator los ps
						PsVsOcVO psTemp= (PsVsOcVO) iter.next();
						Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
						Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(psTemp.getPsId()));
						//validamos si la familia del ps es 1
							if(psTemp.getFaPsId().intValue() == 300)
							{
								operacionComercial=psTemp.getOpComTipo();
								log.debug("Hay PC Familia 300");
								log.debug("operacion comercial "+operacionComercial);
								if (producto_servicioLocal.getPs_nombre().equals("LINEA PRECABLEADA"))
									isprecableada= true;
								hayPC=true;
								break;
							}
						operacionComercial=psTemp.getOpComTipo();
						log.debug("operacion comercial "+operacionComercial);
						
					}
					
			}

			if(!hayPC)
			{
				if(act.getCodigoActividad().equals("Director de Flujos.CONF_AUT_MSAN.CONF_AUT_MSAN"))
				{
					log.debug("--La peticion no contiene PS de familia 300 se inhibe la activdad");
					act.setObservacion("Se inhibe la actividad porque no existen PC's a Configurar");	
				}else 
				{
					log.debug("--La peticion no contiene PS de familia 300 se inhibe la activdad");
					act.setObservacion("Se inhibe la actividad porque no existen PC's a Configurar");	
				}
								
				act.setRealizarTerminoInmediato(true);	
			}else{
				RecursosDelegate recursosDelegate = new RecursosDelegate();
				
				if(act.getCodigoActividad().equals("Director de Flujos.DESCONF_AUT_MSAN.DESCONF_AUT_MSAN")){
					if(isprecableada){
						log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica MSAN");
						recursosDelegate.desconfiguracionAutMSAN(act);
						return;
					}
					
					if(petLocal.getTica_id().equals("B") && recursos_linea_basicaLocal.getRec_fttc_asg() != null
							&& recursos_linea_basicaLocal.getRec_fttc_asg().equals("S") 
							&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
					{
						log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica MSAN");
						recursosDelegate.desconfiguracionAutMSAN(act);
					}else{
						if((petLocal.getTica_id().equals("P") || (petLocal.getTica_id().equals("T")))&&
								!recursosDelegate.recursosIgualesFTTC(recursos_linea_basicaLocal)
								&&!operacionComercial.equals("ACP") && !operacionComercial.equals("BCP")
								&& recursos_linea_basicaLocal.getRec_fttc_ant() != null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S")
								&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
							log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica MSAN");
							recursosDelegate.desconfiguracionAutMSAN(act);
						}else{
							if(recursosDelegate.recursosIgualesFTTC(recursos_linea_basicaLocal)&& !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
							{
								log.debug("--Los equipos anteriores y asignados son iguales, Se inhibe la activdad");
								act.setObservacion("Los equipos anteriores y asignados son iguales, Se inhibe la activdad");
								act.setRealizarTerminoInmediato(true);
							}else{
								if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
									log.debug("-Se inhibe por que no cumple los parametros de FTTC");
									act.setObservacion("Se inhibe por que no cumple los parametros de FTTC");
									act.setRealizarTerminoInmediato(true);
								}
								if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
										// DCARDENA 26/02/2015 se agrega validacion para cauando halla reversa y se halla inhibido la actividad de configurar msan anterioirmente, 
											//tambien se inhiba la actividad de (configurar msan (REV))
									boolean seinhibeReversa=false;		
									try{  
										
										Mensaje_estado_lineaLocalHome mensaje_estado_lineaLocalHome =(Mensaje_estado_lineaLocalHome) HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
										Collection mensaje_estado_lineaLocal =mensaje_estado_lineaLocalHome.findByPetiActi(act.getNumeroPeticion(),"Director de Flujos.DESCONF_AUT_MSAN.DESCONF_AUT_MSAN");
										seinhibeReversa=false;
										log.debug("hay mensajes enviados hacia MSA en Mensaje_estado_lineaLocal"+act.getNumeroPeticion());
										
										}catch (Exception e){
											log.debug("no hay registro de mensajes enviados hacia MSA en Mensaje_estado_lineaLocal"+act.getNumeroPeticion());
											seinhibeReversa=true;
										}
//											  Bitacora_peticionLocalHome bitacora_peticionLocalHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
//											  Collection  bitacorasDeLaPeticion=bitacora_peticionLocalHome.findbyNumeroPeticion(act.getNumeroPeticion());
//												
//												for(Iterator iterator2=bitacorasDeLaPeticion.iterator();iterator2.hasNext();)
//												{
//													log.debug("se valida si de sebe inhibir la reversa");
//													Bitacora_peticionLocal bitacora_peticionLocal =(Bitacora_peticionLocal) iterator2.next();
//													//se consulta si la actividad en la q estoy anterioirmente se inhibio o se ejecuto
//													if(bitacora_peticionLocal.getFk_acti_2_bipe().getAct_codigo().equals(act.getCodigoActividad())
//															&& bitacora_peticionLocal.getBipe_observacion().startsWith("Se inhibe"))
//													{
//														log.debug("se inhibe la reversa");
//														seinhibeReversa=true;
//														break;
//													}
//												}
											//se agrega validacion si se debe inhivir la reversa de la actividad segun la validacion anterior
											if(seinhibeReversa){
												// codigo que inhibe la reversa
												log.debug("--se inhibe la actividad de Reversa ya que no se Configuro nada en MSAN");
												act.setObservacion("Se inhibe la actividad de Reversa ya que no Configuro nada en MSAN");					
												act.setRealizarTerminoInmediato(true);
												return;
											}else{
											log.debug("-Actividad en reversa "+act.getCodigoActividad());
												recursosDelegate.configuracionAutMSAN(act);
											return;
										//	}
									}
								}
							}
							
						}
					}
					
				}else{
					if(isprecableada){
						log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica MSAN");
						recursosDelegate.configuracionAutMSAN(act);
						return;
					}
					if((!recursosDelegate.recursosIgualesFTTC(recursos_linea_basicaLocal)|| petLocal.getTica_id().equals("A"))
							&&!operacionComercial.equals("ACP") && !operacionComercial.equals("BCP")
					&& recursos_linea_basicaLocal.getRec_fttc_asg() != null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S")
							&& !petLocal.getTica_id().equals("B")){
						if (!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")) {
						log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica MSAN");
						recursosDelegate.configuracionAutMSAN(act);
							
						} else {
							if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
								// DCARDENA 26/02/2015 se agrega validacion para cauando halla reversa y se halla inhibido la actividad de configurar msan anterioirmente, 
									//tambien se inhiba la actividad de (configurar msan (REV))
								boolean seinhibeReversa=false;		
								try{  
									log.debug("se valida si de sebe inhibir la reversa peticion"+act.getNumeroPeticion());
									Mensaje_estado_lineaLocalHome mensaje_estado_lineaLocalHome =(Mensaje_estado_lineaLocalHome) HomeFactory.getHome(Mensaje_estado_lineaLocalHome.JNDI_NAME);
									Collection mensaje_estado_lineaLocal =mensaje_estado_lineaLocalHome.findByPetiActi(act.getNumeroPeticion(),"Director de Flujos.CONF_AUT_MSAN.CONF_AUT_MSAN");
									seinhibeReversa=false;
									log.debug("hay mensajes enviados hacia MSA en Mensaje_estado_lineaLocal"+act.getNumeroPeticion());
									
									}catch (Exception e){
										log.debug("no hay registro de mensajes enviados hacia MSA en Mensaje_estado_lineaLocal"+act.getNumeroPeticion());
										seinhibeReversa=true;
									}

//									  Bitacora_peticionLocalHome bitacora_peticionLocalHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
//									  Collection  bitacorasDeLaPeticion=bitacora_peticionLocalHome.findbyNumeroPeticion(act.getNumeroPeticion());
//										boolean seinhibeReversa=false;
//										for(Iterator iterator2=bitacorasDeLaPeticion.iterator();iterator2.hasNext();)
//										{
//											log.debug("se valida si de sebe inhibir la reversa");
//											Bitacora_peticionLocal bitacora_peticionLocal =(Bitacora_peticionLocal) iterator2.next();
//											//se consulta si la actividad en la q estoy anterioirmente se inhibio o se ejecuto
//											if(bitacora_peticionLocal.getFk_acti_2_bipe().getAct_codigo().equals(act.getCodigoActividad())
//													&& bitacora_peticionLocal.getBipe_observacion().startsWith("Se inhibe"))
//											{
//												log.debug("se inhibe la reversa");
//												seinhibeReversa=true;
//												break;
//											}
//										}
									//se agrega validacion si se debe inhivir la reversa de la actividad segun la validacion anterior
									if(seinhibeReversa){
										// codigo que inhibe la reversa
										log.debug("--se inhibe la actividad de Reversa ya que no se Configuro nada en MSAN");
										act.setObservacion("Se inhibe la actividad de Reversa ya que no Configuro nada en MSAN");					
										act.setRealizarTerminoInmediato(true);
										return;
									}else{
									log.debug("-Actividad en reversa "+act.getCodigoActividad());
										recursosDelegate.desconfiguracionAutMSAN(act);
									return;
								//	}
							}
						}
						}
					}else {
						
						if(recursosDelegate.recursosIgualesFTTC(recursos_linea_basicaLocal))
						{
							log.debug("--Los equipos anteriores y asignados son iguales, se inhibe la activdad");
							act.setObservacion("Los equipos anteriores y asignados son iguales, se inhibe la activdad");	
							act.setRealizarTerminoInmediato(true);
						}else{
							if((operacionComercial.equals("ACP") || operacionComercial.equals("BCP"))
							&& recursos_linea_basicaLocal.getRec_fttc_asg() != null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S")
									&& !petLocal.getTica_id().equals("B")){
								log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica MSAN");
								recursosDelegate.configuracionAutMSAN(act);
							}else{
								log.debug("--se inhibe la actividad porque no hay recursos FTTC a Configurar");
								act.setObservacion("se inhibe la actividad porque no hay recursos FTTC a Configurar");					
								act.setRealizarTerminoInmediato(true);	
							}
						}
					}
				}
			}
			
		} catch (NamingException e1) {
			log.warn("Error en Actividad Configuracion/Desconfiguracion Automatica MSAN",e1);
			throw new TnProcesoExcepcion("Error en Actividad Configurcion/Desconfiguracion Automatica MSAN", e1);
		} catch (FinderException e) {
			log.warn("Error en Actividad Configuracion/Desconfiguracion Automatica MSAN",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurcion/Desconfiguracion Automatica MSAN", e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}
	
}