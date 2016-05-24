package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
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

/**
 * Bean implementation class for Enterprise Bean: ADesconfiguracionAutomaticaSTB
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ADesconfiguracionAutomaticaSTBBean
extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB implements javax.ejb.SessionBean {
	
/* (non-Javadoc)
 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
 */
protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	// TODO Auto-generated method stub
	
}

protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

	try{
		log.debug("Actividad ADesconfiguracion Automatica STB ");
		PeticionesInterfaces pI = new PeticionesDelegate();
	   	boolean usaGranite = pI.usaGranite(act.getNumeroPeticion());			
	   	RecursosInterfaces rBAI= new RecursosDelegate();
	   	boolean soportaConfAutomatica = pI.centralSoportaConfAutomatica(act.getNumeroPeticion());
		boolean soportaEOC = false;
		boolean cambioNumero = false;
	   	
	   	// Granite - adocarmo - inicio
	   	boolean trasladoConOrigenyDestinoIguales = pI.esTrasladoConIgualOrigenYDestino(act.getNumeroPeticion());
		//Req FTTC se agrega la consulta a la tabla recursos_linea_baqsica para sabews si la peticion contiene campos de tipo FTTC para que se inhiba la actividad
	    Recursos_linea_basicaLocal recursos_linea_basicaLocal;
	    Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
		recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
		//Se realiza la validacion en el campo rec_fttc_asg para saber si es FTTC o no si viene en S significa q hay campos FTTC y se debe inhibir la actividad, si viene N continua normalmente con la activdad
		log.debug("---Comienza Validacion FTTC");
		
		
//		intanciamos la talba producto_servicio
		Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome = (Producto_servicio_peticionLocalHome) HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
		//guardamos los ps en de la ptecion en un arreglo
		Collection ps = producto_servicio_peticionLocalHome.findAllByPetiNumero(act.getNumeroPeticion());
		String operacionComercial="";
		//recorremos el arreglo
		if(ps!=null && ps.size()>0){
				for (Iterator iter = ps.iterator(); iter.hasNext();) {
				
					//guardamos en un iterator los ps
		
				Producto_servicio_peticionLocal element = (Producto_servicio_peticionLocal) iter.next();
				Operacion_comercialKey operacion_comercialKey= (Operacion_comercialKey) element.getOperacion_comercial().getPrimaryKey();
					operacionComercial=operacion_comercialKey.opco_id.toString();
					log.debug("operacion comercial "+operacionComercial);
					break;
				}
				
		}
		boolean asignado=false;
		
		if(operacionComercial.equals("6"))
		{
			asignado=true;
		}else if(operacionComercial.equals("7"))
		{
			asignado=true;
		}else{
			asignado=false;
		}
		
		if((recursos_linea_basicaLocal.getRec_fttc_asg()!=null && recursos_linea_basicaLocal.getRec_fttc_asg().equals("S")&&asignado)||(recursos_linea_basicaLocal.getRec_fttc_ant()!=null && recursos_linea_basicaLocal.getRec_fttc_ant().equals("S")&&!asignado))
		{
		log.debug("---Contiene Equipos FTTC, se inhibe la actividad");
		act.setObservacion("Se inhibe la actividad porque existen equipos FTTC a Desconfigurar");					
		act.setRealizarTerminoInmediato(true);	
		
	}else{
	   	
		log.debug("---No contiene Equipos FTTC Continua la actividad");
	   	if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
	   	
		   	log.debug("ES TRASLADO CON ORIGEN Y DESTINO IGUALES?: " + trasladoConOrigenyDestinoIguales);
		   	
		   	// Granite - adocarmo - fin	   				  
		   	
		   	boolean traslado = false;
		   	
		   	traslado = rBAI.tieneTrasladoLB(act.getNumeroPeticion());
		   	cambioNumero = rBAI.tieneCambioNumeroLB(act.getNumeroPeticion());

		   	// Si es una peticion que va por Granite y la central soporta configuracion automatica envio el mensaje
		   	// La pregunta de recursos_linea_basicaLocal.getCentralSoportaConfAutomatica() podría ir en un método dentro de PeticionesServicesBean al igual que el metodo usaGranite()

		   	if (usaGranite && soportaConfAutomatica) {
		   		if(!rBAI.tienePcLinea(act.getNumeroPeticion())){
					act.setObservacion("No se envia el mensaje de Desconfiguracion Automatica STB.");		
					act.setRealizarTerminoInmediato(true);
		   		}else{
				   	// Granite - adocarmo - inicio
					if (trasladoConOrigenyDestinoIguales) {
						act.setObservacion("No se envia el mensaje de Desconfiguracion Automatica STB ya que se trata de un traslado con numeros origen y destino iguales ");					
						act.setRealizarTerminoInmediato(true);								
					}else {
		   		if(traslado || cambioNumero){
						log.debug("Inicio Actividad Desconfiguracion Automatica STB [" + act.getNumeroPeticion() + "]");
						//AT-2086 Correccion del caso de Cambio de Numero sin necesidad de Cruzada
						if (rBAI.tieneCambioNumeroLB(act.getNumeroPeticion())&& !rBAI.esCruzada(act.getNumeroPeticion())){
							//si es cambio de numero y ademas NO hay cruzada NO Se ejecuta la Desconfiguracion STB
							act.setObservacion("No se envia el mensaje de Desconfiguracion Automatica STB. No hay necesidad de cruzada");		
							act.setRealizarTerminoInmediato(true);
						}else{
													
							boolean resp=rBAI.enviaDesconfigurarConfiguracionAutomaticaSTB(act);
							if (resp){
								act.setObservacion("Se envia el mensaje de Desconfiguracion Automatica STB.");	
							}else{
								act.setObservacion("No se envia el mensaje de Desconfiguracion Automatica STB. Faltan datos. Revise Service Reference ID(CDS), Direccion Electronica(CDS) y Usuario de Acceso Nuevo(ADSL)");					
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_DESCONFIG_STB);
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_desconf_stb, "DESCSTB");
								act.setRealizarTerminoInmediato(true);		
							}
						}
		   		}else{
		   			act.setObservacion("No se envia el mensaje de Desconfiguracion Automatica STB ya que no se trata de un traslado o cambio de Numero");					
					act.setRealizarTerminoInmediato(true);	
		   		
		   		}
				}
				}
			   	// Granite - adocarmo - fin
	
		   	}else {
		   		
		   		cambioNumero = rBAI.tieneCambioNumeroLB(act.getNumeroPeticion());
		   		
		   		if(cambioNumero ||(traslado && !trasladoConOrigenyDestinoIguales) ){
		   			
		   			if(recursos_linea_basicaLocal.getCentral_ant() != null)
		   				soportaEOC = pI.centralAntSoportaConfAutomaticaEOC(recursos_linea_basicaLocal.getCentral_ant());
		   		}
		   		log.debug("usaGranite = "+usaGranite+" soporta EOC = "+soportaEOC);
		   		if(!usaGranite && soportaEOC){
		   			rBAI.ConfiguracionAutomaticaEOC(act);
		   		}else{
		   		if(traslado){
					act.setGrabaEnBitacora(false);
					// Si la peticion no es de Granite termina y va  aconf manual sin grabar en bitacora
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, ComunInterfaces.ACT_DESCONFIG_STB);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_desconf_stb, "DESCSTB");
					act.setRealizarTerminoInmediato(true);
		   		}else{
		   			act.setObservacion("Es cambio de Numero no se envia a Desconfiguracion manual STB");					
					act.setRealizarTerminoInmediato(true);
		   		}
		   	}
	   	}
	   	}else{
	   		act.setObservacion("En reversa no es necesario ejecutar la actividad");
	   		act.setRealizarTerminoInmediato(true);
	   	
	   	}
	}
	} catch (ATiempoAppEx e){
		log.warn("Error en Actividad Desconfiguracion Automatica STB",e);
		throw new TnProcesoExcepcion("Error en Actividad Desconfigurcion Automatica STB", e);
	} catch (NamingException e) {
		// TODO Bloque catch generado automáticamente
		log.warn("Error en Actividad Desconfiguracion Automatica STB",e);
		throw new TnProcesoExcepcion("Error en Actividad Desconfigurcion Automatica STB", e);
	} catch (FinderException e) {
		// TODO Bloque catch generado automáticamente
		log.warn("Error en Actividad Desconfiguracion Automatica STB",e);
		throw new TnProcesoExcepcion("Error en Actividad Desconfigurcion Automatica STB", e);
	}
}

/* (non-Javadoc)
 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
 */
protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

	
}
}
