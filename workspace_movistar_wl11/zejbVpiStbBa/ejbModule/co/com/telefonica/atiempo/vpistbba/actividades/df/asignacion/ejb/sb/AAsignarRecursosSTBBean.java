package co.com.telefonica.atiempo.vpistbba.actividades.df.asignacion.ejb.sb;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AAsignarRecursosSTB
 */
public class AAsignarRecursosSTBBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		try {
////---------------------arreglo direcciones 
//			RecursosDelegate recursosDirec = new RecursosDelegate();
//			PeticionKey peticionKeyd = new PeticionKey(act.getNumeroPeticion());
//			PeticionLocalHome peticionLocalHomed = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//			PeticionLocal peticionLocalD = peticionLocalHomed.findByPrimaryKey(peticionKeyd);
//			Collection producto_servicio_peticionArray = peticionLocalD.getProducto_servicio_peticion();	
//			Long numeroAtis=(new Long (0));
//			Collection s=peticionLocalD.getFk_01_pet_atis().getAgrupacion_atis();
//			for(Iterator iter3 = s.iterator();iter3.hasNext();)
//			{
//				Agrupacion_atisLocal agrupacion_atisLocal= (Agrupacion_atisLocal) iter3.next();
//				Agrupacion_atisKey ak=(Agrupacion_atisKey)	agrupacion_atisLocal.getPrimaryKey();
//				numeroAtis=ak.fk_pet_agrupacion_cod_pet_cd;
//			}
//			
//			recursosDirec.actualizarDireccionAtis(numeroAtis,act.getNumeroPeticion());
////-----------------------------FIN
			
			PeticionesInterfaces pI = new PeticionesDelegate();
		   	
		   	if(!debeEnviarDesconfiguracion(act.getNumeroPeticion())){
		   		act.setObservacion("No Se Envia Asignacion de Recursos STB, Para Granite no es Necesario");
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");
				act.setRealizarTerminoInmediato(true);
		   		return ;
		   		
		   	}
		   	boolean esGranite = pI.usaGranite(act.getNumeroPeticion());
		   	boolean flagEnvioGranite = false;  //Se utiliza para validar cuando tiene operacion comercial de Cambio de
//		  CR-14657 Granite - agonz - preguntamos si la asignación de recursos se hace con Granite o no.
			if(esGranite){
				
				//Valida que la Peticion tenga un PS con la operacion comercial 106 (Alta por Cambio de Titular
				//y con esto inhibir la Actividad de Asignacion de REcursos contra Granite
				Iterator iterador = act.getPsOk().iterator();
				while(iterador.hasNext()){
					PsVsOcVO psTemp= (PsVsOcVO) iterador.next();
					if (psTemp.getFaPsId().intValue() == ComunInterfaces.familiaPcLinea && psTemp.getOpComId().intValue() == ComunInterfaces.opCoCambioTitular){
						flagEnvioGranite = true;
						break;
					}
				}
				if (flagEnvioGranite == false){
					//Actividad Asignacion de recursos con Granite
					if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
						log.debug("Inicio Actividad Asignacion Recursos con Granite[" + act.getNumeroPeticion() + "]");
						RecursosInterfaces recursosInterfaces = new RecursosDelegate();
						recursosInterfaces.enviarRecursoGraniteSTB(act.getNumeroPeticion(),act.getCodigoActividad(),act.getIdActividadFlujo());
					}else{
						
						//TODO:Caso en la reversa para Granite 
						log.debug("Inicio Actividad Reversa de Asignacion Recursos con Granite [" + act.getNumeroPeticion() + "]");
						RecursosInterfaces recursosInterfaces = new RecursosDelegate();
						recursosInterfaces.envioLiberacionRecursosGranite(act.getNumeroPeticion(),act.getCodigoActividad(), act.getIdActividadFlujo());
						act.setObservacion("Se envio la liberacion de Recursos");
					}
				}else{
					log.debug("No Se Envia Asignacion de Recursos STB, Para Granite no es Necesario [" + act.getNumeroPeticion() + "]");
					act.setObservacion("No Se Envia Asignacion de Recursos STB, Para Granite no es Necesario");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.ASIGNACION_RECURSOS.asig_manual_stb, "N");
					act.setRealizarTerminoInmediato(true);
				}
			}else{
				if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
					log.debug("Inicio Actividad Asignacion Recursos [" + act.getNumeroPeticion() + "]");
					RecursosInterfaces recursosInterfaces = new RecursosDelegate();
					recursosInterfaces.enviarRecursoSTB(act.getNumeroPeticion(),act.getCodigoActividad(),act.getIdActividadFlujo());
				
				}else{
					log.debug("Inicio Actividad Reversa de Asignacion Recursos [" + act.getNumeroPeticion() + "]");
					RecursosInterfaces recursosInterfaces = new RecursosDelegate();
					recursosInterfaces.envioLiberacionRecursos(act.getNumeroPeticion(),act.getCodigoActividad(), act.getIdActividadFlujo());
					act.setObservacion("Se envio la liberacion de Recursos");
				}
			}
		} catch (Exception e) {
			log.warn("Error en Actividad Asig Rec",e);
			throw new TnProcesoExcepcion("Error en Actividad Asignacion Recursos", e);
		}
		

		log.debug("Fin Actividad Asignacion Recursos [" + act.getNumeroPeticion() + "]");
		
	}

	/**
	 * Valida que no se debe enviar la desconfiguracion a aprovisionamiento 
	 * @param numeroPeticion
	 * @return
	 * @throws FinderException
	 * @throws NamingException
	 */
	private boolean debeEnviarDesconfiguracion(Long numeroPeticion) throws FinderException, NamingException {
		PeticionKey peticionKey = new PeticionKey(numeroPeticion);
		PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		
		PeticionLocal peticionLocal = peticionLocalHome.findByPrimaryKey(peticionKey);
		
		if(peticionLocal.getTica_id().equals(ComunInterfaces.opCoTipoBaja)){
			for(Iterator iterator=peticionLocal.getProducto_servicio_peticion().iterator();iterator.hasNext();){
				Producto_servicio_peticionLocal producto_servicio_peticionLocal=(Producto_servicio_peticionLocal) iterator.next();
				Subpeticion_atisLocal subpeticion_atisLocal=producto_servicio_peticionLocal.getFk_01_subp_atis();
				Agrupacion_atisLocal agAtis=subpeticion_atisLocal.getFk_agru_sub();
				if(agAtis.getTip_pro_cmr_cd().equals(ComunInterfaces.tipoAgrupacionLinea) &&
					(agAtis.getSbt_pro_cmr_cd().equals(ComunInterfaces.tipoSubAgrupacionAltaPVAExistente)|| 
					agAtis.getSbt_pro_cmr_cd().equals(ComunInterfaces.tipoSubAgrupacionAltaSTBExistente))){
					log.debug("La operacion es baja y subcategoria es "+agAtis.getSbt_pro_cmr_cd()+" por eso no se envia al tr");
					return false;
				}
			}
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
//comentado 27/02/2015-------se comenta por que siempre iba con flag N--------------------
		//boolean hayFTTC =validaFTTC(act.getNumeroPeticion());
		//boolean hayAlta = validaALta(act.getNumeroPeticion());
		//boolean hayBaja = validaBaja(act.getNumeroPeticion());
		//if(!act.isRealizarTerminoInmediato()&&hayAlta)
		//{
		//	act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"S");
		//}else{
		//	if(hayBaja)
		//	{		
		//		  act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"N");
		//	}else{
//--comentado 27/02/2015-----------------FIN--------------------------------------------------
//		if ((!act.isRealizarTerminoInmediato()&&!hayFTTC)){
//            act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"S");
//		}else{
//--comentado 27/02/2015-----//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"N"); se comenta por el mismo error del flag N
//		}
			//}
		//}
//--se agrega el funcionamiento anterior 27/02/2015
//		
//validacion para ejecutar obtener puntos de red stb
//		if (!act.isRealizarTerminoInmediato()){
//			//se agrega flag en N ya que si hay un traslado se debe obtener los recursos que tiene el cliente para desistalar OBtener puntos de Red STB
//			if(validaTraslado(act.getNumeroPeticion())){
//				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"N");	
//			}else{
//				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"S");
//			}
//		}else{
//            act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"N");
//		}
		
		if (!act.isRealizarTerminoInmediato()){
            act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"S");
		}else{
            act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.hay_asignacion_recursos,"N");
		}
	}
	//funcion que valida si en la asignacion de recursos se registraron en BD Recursos FTTC ASG
	protected boolean validaFTTC(Long petiNumero){
//		Req FTTC se agrega la consulta a la tabla recursos_linea_baqsica para sabews si la peticion contiene campos de tipo FTTC para que se inhiba la actividad
	    Recursos_linea_basicaLocal recursos_linea_basicaLocal;
	    Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome;
		try {
			recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
		
			recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(petiNumero);
		
		//Se realiza la validacion en el campo rec_fttc_asg para saber si es FTTC o no si viene en S significa q hay campos FTTC y se debe inhibir la actividad, si viene N continua normalmente con la activdad
		log.debug("Se valida si en la asignacion de recursos hay recursos FTTC");
		
		if(recursos_linea_basicaLocal.getRec_fttc_asg()!=null && recursos_linea_basicaLocal.getRec_fttc_asg()=="S")
		{
			return true;
		}else{
			return false;
		}
		
		} catch (FinderException e1) {
			return false;
		
		} catch (NamingException e) {
			return false;
		}	
	}

	//funcion que valida si en la asignacion de recursos se registraron en BD Recursos FTTC ASG
	protected boolean validaTraslado(Long petiNumero){
		try{
		PeticionLocalHome petiHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		PeticionLocal petiLocal = petiHome.findByPrimaryKey(new PeticionKey (petiNumero));
		Collection psp= petiLocal.getProducto_servicio_peticion();
		
		Producto_servicio_peticionLocal producto_servicio_peticionLocal=null;
		
		for(Iterator iter = psp.iterator();iter.hasNext();)
		{
			producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
			Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
			Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
			if(petiLocal.getTica_id().equals("P")&&producto_servicio_peticionLocal.getOperacion_comercial().getOpco_tras().equals(ComunInterfaces.opCoTras_Traslado)){
				return true;
			}
		}
		
			
		} catch (FinderException e1) {
			return false;
		
		} catch (NamingException e) {
			return false;
		}

		return false;
	}
	

//	funcion que valida si en la asignacion de recursos se registraron en BD Recursos FTTC ASG
	protected boolean validaBaja(Long petiNumero){
		try{
			PeticionLocalHome petiHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal petiLocal = petiHome.findByPrimaryKey(new PeticionKey (petiNumero));
			
			if(petiLocal.getTica_id().equals("B")){
				return true;
			}
				
			} catch (FinderException e1) {
				return false;
			
			} catch (NamingException e) {
				return false;
			}

			return false;
				
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
