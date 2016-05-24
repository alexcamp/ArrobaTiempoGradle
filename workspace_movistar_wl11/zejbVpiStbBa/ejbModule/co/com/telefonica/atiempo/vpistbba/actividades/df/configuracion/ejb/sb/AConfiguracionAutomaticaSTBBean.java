package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
/**
 * Bean implementation class for Enterprise Bean: AConfigurarAula365
 */
public class AConfiguracionAutomaticaSTBBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements javax.ejb.SessionBean {
		
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		try{		
			
			PeticionesInterfaces pI = new PeticionesDelegate();
		   	boolean usaGranite = pI.usaGranite(act.getNumeroPeticion());
			
		   	
		   	boolean soportaConfAutomatica = pI.centralSoportaConfAutomatica(act.getNumeroPeticion());
		   	
		   	boolean trasladoConOrigenyDestinoIguales = pI.esTrasladoConIgualOrigenYDestino(act.getNumeroPeticion());
		   		   	
		   	boolean trasladoConIgualLen = pI.esTrasladoConIgualLen(act.getNumeroPeticion());
		   
		   	boolean vaAGranite = usaGranite && soportaConfAutomatica;
//		  Req FTTC se agrega la consulta a la tabla recursos_linea_baqsica para sabews si la peticion contiene campos de tipo FTTC para que se inhiba la actividad
		    Recursos_linea_basicaLocal recursos_linea_basicaLocal;
		    Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
			//Se realiza la validacion en el campo rec_fttc_asg para saber si es FTTC o no si viene en S significa q hay campos FTTC y se debe inhibir la actividad, si viene N continua normalmente con la activdad
			boolean hayPC=false;

//			intanciamos la talba producto_servicio
			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			//guardamos los ps en de la ptecion en un arreglo
			Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
			String operacionComercial="";
			String operacionNombre="";
			//recorremos el arreglo
			boolean cambProd=false;
			if(ps!=null && ps.size()>0){
					for (Iterator iter = ps.iterator(); iter.hasNext();) {
					
						//guardamos en un iterator los ps
			
					Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
					Operacion_comercialKey operacion_comercialKey= (Operacion_comercialKey) element.getOperacion_comercial().getPrimaryKey();
					operacionNombre = element.getOperacion_comercial().getOpco_tipo().toString();
//					se valida si hay acp o bcp en los ps para validar los campos anterioires
					if(!cambProd&&operacionNombre.equals("ACP")|| !cambProd&&operacionNombre.equals("BCP"))
					{
						log.debug("Solo hay ACP y BCP");
						cambProd=true;
					}else{
						log.debug("no hay cambio producto");
						cambProd=false;
					}
//					validamos si la familia del ps es 1
					if(element.getFamiliaKey().faps_id.intValue() == 300)
					{
						operacionComercial=operacion_comercialKey.opco_id.toString();
						log.debug("Hay PC Familia 300");
						log.debug("operacion comercial "+operacionComercial);
						hayPC=true;
						break;
					}else{
					operacionComercial=operacion_comercialKey.opco_id.toString();
					log.debug("operacion comercial "+operacionComercial);
					}
					
			}
			}
			boolean baja=false;

			
			
			if(operacionComercial.equals("6")&&(!hayPC))
			{
				log.debug("Solo hay baja SVA");
				baja=true;
			}else{
				log.debug("Hay PC");
				baja=false;
			}
			
			log.debug("---Comienza Validacion FTTC");
			// se elimina validacion de baja ya que afecta la funcionalidad con las bajas de svas solas
			//if(	recursos_linea_basicaLocal.getRec_fttc_asg()!=null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S")||baja){
			if(	(recursos_linea_basicaLocal.getRec_fttc_asg()!=null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S"))||
					(recursos_linea_basicaLocal.getRec_fttc_ant()!=null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S")&&baja)||
					(recursos_linea_basicaLocal.getRec_fttc_ant()!=null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S")&&cambProd)){
				log.debug("---Contiene Equipos FTTC, se inhibe la actividad");
				act.setObservacion("Se inhibe la actividad porque existen equipos FTTC a Configurar");					
				act.setRealizarTerminoInmediato(true);	
			
			}else{
		   	
		   	log.debug("---No contiene Equipos FTTC Continua la actividad");
			boolean soportaEOC = pI.centralSoportaConfAutomaticaEOC(act.getNumeroPeticion());
		
		   	if (usaGranite && soportaConfAutomatica) {
		   		RecursosInterfaces rBAI= new RecursosDelegate();
		   		if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
					
		   			log.debug("Inicio Actividad Configuracion Automatica STB [" + act.getNumeroPeticion() + "]");
					
					if (trasladoConOrigenyDestinoIguales) {
						if(trasladoConIgualLen){
							act.setObservacion("No se envia el mensaje de Configuracion/Desconfiguración Automatica STB ya que se trata de un traslado con numeros origen y destino iguales, o con Len origen y destino iguales ");					
							act.setRealizarTerminoInmediato(true);	
						}else{//Es cambio de len sin cambio de número
							//Cambio el tica para que se realice la baja
							Long idPeticion = act.getNumeroPeticion();
							PeticionLocalHome peticionLocalHome;
							PeticionLocal  peticionLocal = null;
							try {
								peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
								peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(idPeticion));
								if(peticionLocal != null){
									peticionLocal.setTica_id("B");
								}
							} catch (NamingException e1) {
								log.warn("Error en Actividad Configuracion Automatica STB",e1);
								throw new TnProcesoExcepcion("Error en Actividad Configurcion Automatica STB", e1);
							} catch (FinderException e) {
								log.warn("Error en Actividad Configuracion Automatica STB",e);
								throw new TnProcesoExcepcion("Error en Actividad Configurcion Automatica STB", e);
							}
							
							//Se envia la desconfiguracion. en la actividad que recibe la respuesta de la tr511s se
							//hace la configuración.
							boolean resp =rBAI.enviaDesconfigurarConfiguracionAutomaticaSTB(act);
							if (resp){
								act.setObservacion("Se envia el mensaje de Desconfiguración Automatica STB. Solo cambio de len");	
								//Si se envia la desconfiguracion se deja para hacer el alta
								peticionLocal.setTica_id("A");
							}else{
								act.setObservacion("No se envia el mensaje de Desconfiguración Automatica STB. Revise si no hay PSs, si no hay tel del abonado, si hay mas de 1 PS con famila");					
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_DESCONFIG_STB);
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_desconf_stb, "DESCSTB");
								act.setRealizarTerminoInmediato(true);	
								return;
							}
						}
					}
					else {
						boolean resp =rBAI.enviaConfiguracionAutomaticaSTB(act);
						if (resp){
							act.setObservacion("Se envia el mensaje de Configuracion/Desconfiguración Automatica STB.");	
						}else{
							act.setObservacion("No se envia el mensaje de Configuracion/Desconfiguración Automatica STB. Revise si no hay PSs, si no hay tel del abonado, si hay mas de 1 PS con famila");					
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
							act.setRealizarTerminoInmediato(true);		
						}
		   			}
		   		}

		   		else{
					log.debug("Inicio Actividad Reversa de Configuracion Automatica STB [" + act.getNumeroPeticion() + "]");
					if (trasladoConOrigenyDestinoIguales) {
						act.setObservacion("No se envia el mensaje de Desconfigurar Configuracion Automatica STB ya que se trata de un traslado con numeros origen y destino iguales ");					
						act.setRealizarTerminoInmediato(true);								
					}else {
						//
						int resp=rBAI.enviaReversaConfiguracionAutomaticaSTB(act);
						log.debug("resp .."+resp);
						if (resp == 2){
							//enviaReversaConfiguracionAutomaticaSTB retorna 2, se debe enviar la Reversa de Configurar
							act.setObservacion("Se envia el mensaje de Desconfigurar Configuracion Automatica STB.");	
						}else{
							if(resp == 1){
								//enviaReversaConfiguracionAutomaticaSTB retorno 1, se debe enviar la Reversa y se termina la actividad con error 
								act.setObservacion("No se envia el mensaje de Desconfigurar Configuracion Automatica STB. Revise si no hay PSs, si no hay tel del abonado, si hay mas de 1 PS con famila");					
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
								act.setRealizarTerminoInmediato(true);
							}else{
								//enviaReversaConfiguracionAutomaticaSTB retorno 3, la reversa no se hace y se debe terminar OK
								act.setRealizarTerminoInmediato(true);
								act.setObservacion("No se envia el mensaje de Desconfigurar Configuracion Automatica STB no es necesario hacer reversa.");	
							}			
						}
					}					
		   		}
		   	}
		   	// si no es granite o es granite pero no soporta conf automatica
		   	else {
		   		//Se envia configuraciòn para EOC
		   		log.debug("Se realiza actividad para usaGranite:"+usaGranite+" y soportaEOC:"+soportaEOC);
		   		if(!usaGranite && soportaEOC){
		   			RecursosInterfaces rBAI= new RecursosDelegate();
		   			rBAI.ConfiguracionAutomaticaEOC(act);
		   		}else{
					act.setGrabaEnBitacora(false); // creo que a este seteo no se le da corte, pero la idea es que no imprima en bitacora si no es granite
					// Si la peticion no es de Granite termina y va  aconf manual sin grabar en bitacora
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_CONFIG_STB);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb, "CONFSTB");
					act.setRealizarTerminoInmediato(true);		   		
				}
		   	}
			}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configuracion Automatica STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurcion Automatica STB", e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("Error en Actividad Configuracion Automatica STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurcion Automatica STB", e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.warn("Error en Actividad Configuracion Automatica STB",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurcion Automatica STB", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		
	}

}
