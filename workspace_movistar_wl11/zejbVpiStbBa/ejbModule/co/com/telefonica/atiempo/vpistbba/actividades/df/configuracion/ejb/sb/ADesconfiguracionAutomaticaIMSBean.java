package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.control.Password;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ADesconfiguracionAutomaticaIMS
 */
public class ADesconfiguracionAutomaticaIMSBean extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {

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
		    Recursos_linea_basicaLocal recursos_linea_basicaLocal;
		    Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
			//Se realiza la validacion en el campo rec_fttc_asg para saber si es FTTC o no, si viene en S significa q hay campos FTTC y se debe continuar la actividad, si viene N debe inhibir la activdad
			log.debug("-Comienza Validacion FTTC");
			String mensaje="";
			boolean hayPC = false;
			RecursosDelegate recursos = new RecursosDelegate();
			

			//se trae de la tabla propertiesBD la longitud del password para IMS NAKED
			Properties_BDLocalHome propertiesBDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesDBLocalPassword = propertiesBDLocalHome.findByPrimaryKey("FTTC_LEN_PWD");
			

			PeticionLocalHome petHome = (PeticionLocalHome) HomeFactory.getHome( PeticionLocalHome.JNDI_NAME);
			PeticionLocal petLocal = petHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			
//			intanciamos la talba producto_servicio
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			//guardamos los ps en de la ptecion en un arreglo
			PeticionesInterfaces peticionesInterface = new PeticionesDelegate();
			Collection ps = peticionesInterface.listaDePsDelaActividadEstadoOKFinal(act.getNumeroPeticion(), act.getIdActividadFlujo());
			String operacionComercial="";
			//recorremos el arreglo
			RecursosDelegate recursosDelegate = new RecursosDelegate();
			//ba naked se crea variable que definira si hay voip naked dodne hara ejecutar la actividad conf IMS sin necesidad de campos FTTC
			boolean hayVoipNaked=false;
			for (Iterator iter = ps.iterator(); iter.hasNext();) {
				PsVsOcVO psVsOcVO = (PsVsOcVO) iter.next();
				//Ba naked se agrega validacion para cuando halla familia VOIP no valide campos FTTC si no se ejecuter la actividad directamente
				if(psVsOcVO.getFaPsId().intValue()==ComunInterfaces.familiaBandaAnchaNaked
						&& (psVsOcVO.getOpComTipo().equals("BCP") || psVsOcVO.getOpComTipo().equals("B")
								|| psVsOcVO.getOpComId().intValue() == ComunInterfaces.opCoSuspencioAP)){
					log.debug("Hay familia PS Voz IP para la peticion "+act.getNumeroPeticion()+ " con la operacion comercial "+psVsOcVO.getOpComTipo());
					operacionComercial = psVsOcVO.getOpComTipo();
					hayVoipNaked=true;
				}
				
				if(psVsOcVO.getFaPsId().intValue()==ComunInterfaces.familiaBandaAnchaNaked &&
						psVsOcVO.getOpComId().longValue() == ComunInterfaces.OPCO_CAMBIO_NUMERO){
					log.debug("Hay familia PS Voz IP para la peticion "+act.getNumeroPeticion()+ " con la operacion comercial de cambio de numero");
					operacionComercial = "B";
					hayVoipNaked=true;
				}
				if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaPcLinea
						&& (psVsOcVO.getOpComTipo().equals("BCP") || psVsOcVO.getOpComTipo().equals("B"))){
					operacionComercial = psVsOcVO.getOpComTipo();
					break;
				}else{
					if(psVsOcVO.getFaPsId().intValue()== ComunInterfaces.familiaLinea
							&& (psVsOcVO.getOpComTipo().equals("BCP") || psVsOcVO.getOpComTipo().equals("B"))){
						operacionComercial = psVsOcVO.getOpComTipo();
					}
				}
			}
				
			if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				// DCARDENA 26/02/2015 se agrega validacion para cauando halla reversa y se halla inhibido la actividad de Desconfigurar IMS anterioirmente, 
				//tambien se inhiba la actividad de (Descconfigurar IMS (REV))
				  Bitacora_peticionLocalHome bitacora_peticionLocalHome = (Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
				  Collection  bitacorasDeLaPeticion=bitacora_peticionLocalHome.findbyNumeroPeticion(act.getNumeroPeticion());
					boolean seinhibeReversa=false;
					for(Iterator iterator2=bitacorasDeLaPeticion.iterator();iterator2.hasNext();)
					{
						log.debug("se valida si de sebe inhibir la reversa");
						Bitacora_peticionLocal bitacora_peticionLocal =(Bitacora_peticionLocal) iterator2.next();
						//se consulta si la actividad en la q estoy anterioirmente se inhibio o se ejecuto
						
						if(bitacora_peticionLocal.getFk_acti_2_bipe().getAct_codigo().equals(act.getCodigoActividad())
								&& bitacora_peticionLocal.getBipe_observacion().startsWith("Se inhibe"))
								{
							log.debug("se inhibe la reversa");
							seinhibeReversa=true;
							break;
						}
					}
//				se agrega validacion si se debe inhivir la reversa de la actividad segun la validacion anterior
				if(seinhibeReversa){
					// codigo que inhibe la reversa
					log.debug("--se inhibe la actividad de Reversa ya que no se Configuro nada en IMS");
					act.setObservacion("Se inhibe la actividad de Reversa ya que no Configuro nada en IMS");					
					act.setRealizarTerminoInmediato(true);
					return;
				}else{
				log.debug("-Actividad en reversa "+act.getCodigoActividad());
				if(operacionComercial.equals("B"))
					recursos.desconfiguracionAutIMS(act, "B");
				else
					recursos.desconfiguracionAutIMS(act, "BCP");
				return;
				}
			}

			//se valida si hay voip naked para mandar a configurara la voz ip
			if (hayVoipNaked){
				log.debug("-Se envia mensaje Configuracion automatica IMS por VOIP NAKED");
				Password generarPass = new  Password();
				//se genera el password y se le envia la longitud
				int longitud= Integer.parseInt(propertiesDBLocalPassword.getValor());
				recursos_linea_basicaLocal.setPassword_fttc(generarPass.RandomString(longitud));
//				recursos.configuracionAutIMS(act, operacionComercial);
				recursos.desconfiguracionAutIMS(act, operacionComercial);
				return ;
			}
				
			if(petLocal.getTica_id().equals("B") && recursos_linea_basicaLocal.getRec_fttc_asg() != null
			&& recursos_linea_basicaLocal.getRec_fttc_asg() != null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S")){
				log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica IMS");
				recursosDelegate.desconfiguracionAutIMS(act, operacionComercial);
			}else{
				
				if((petLocal.getTica_id().equals("P") || (petLocal.getTica_id().equals("T")))&&
						!recursosDelegate.recursosIgualesFTTC(recursos_linea_basicaLocal)
						&& recursos_linea_basicaLocal.getRec_fttc_ant() != null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S")){
					log.debug("-Se envia mensaje Configuracion/Desconfiguracion automatica IMS");
					recursosDelegate.desconfiguracionAutIMS(act, operacionComercial);
				}else{
					
					if(operacionComercial.equals(ComunInterfaces.opCoTipoBajaCambioProd)&&((recursos_linea_basicaLocal.getRec_fttc_ant() != null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S"))||(recursos_linea_basicaLocal.getRec_fttc_asg()!= null&& recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))))
					{
						log.debug("-Se envia mensaje Configuracion automatica IMS");
						recursos.desconfiguracionAutIMS(act, operacionComercial);
					}else{
						if(recursosDelegate.recursosIgualesFTTC(recursos_linea_basicaLocal))
						{
						log.debug("--se inhibe la activdad porque los equipos anteriores y asignados son iguales");
						act.setObservacion("Se inhibe la actividad porque los equipos anteriores y asignados son iguales");					
							
						}else{
						log.debug("--se inhibe la activdad ya que no se requiere no Desconfigurar nada en IMS");
						act.setObservacion("Se inhibe la actividad ya que no se requiere no Desconfigurar nada en IMS");					
						
						}
						act.setRealizarTerminoInmediato(true);	
					}			
				}
			}
		//} catch (ATiempoAppEx e){
		//	log.warn("Error en Actividad Desconfiguracion Automatica IMS",e);
		//	throw new TnProcesoExcepcion("Error en Actividad Desconfiguracion Automatica IMS", e);
		} catch (NamingException e1) {
			log.warn("Error en Actividad Desconfiguracion Automatica IMS",e1);
			throw new TnProcesoExcepcion("Error en Actividad Desconfiguracion Automatica IMS", e1);
		} catch (FinderException e) {
			log.warn("Error en Actividad Desconfiguracion Automatica IMS",e);
			throw new TnProcesoExcepcion("Error en Actividad Desconfiguracion Automatica IMS", e);
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