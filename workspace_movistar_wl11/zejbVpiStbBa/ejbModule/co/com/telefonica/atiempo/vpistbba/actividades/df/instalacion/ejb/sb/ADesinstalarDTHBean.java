package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InfoPostVentaTV;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;

/**
 * Bean implementation class for Enterprise Bean: ADesinstalarDTH
 */
public class ADesinstalarDTHBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		act.setGrabarWfInstancia(true);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
			// Resultado merge Altamira con UMTS, priorizo cambio en UMTS - guido - 21/Oct/2009
			try {
				boolean esCRE = false;
				RecursosTVDelegate delegate=new RecursosTVDelegate();
				InfoPostVentaTV infoPostVentaTV=delegate.recuperaInfoTvPostVenta(act.getNumeroPeticion());
				if(infoPostVentaTV.isEsBajaCompleta()){
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");
					log.debug("Es Baja Completa TV se cierra la actividad sin realizar trabajos");
					//TODO: 12022010 - RETA - Se elimina para que asi se guarde en la tabla de instancias actividad más adelante
					act.setRealizarTerminoInmediato(true);
					//End TODO
				} else {
					//CR24918 - TV Masivo - PCawen
					//TODO Si tiene mas de 10 decos es actividad masiva => inhibo actividad 
					Long nroPeticion = act.getNumeroPeticion();
					int decosAlta = 0;
					int decosBaja = 0;
					decosAlta = getNroDecos(nroPeticion)[0];
					decosBaja = getNroDecos(nroPeticion)[1];
					if(decosBaja > ComunInterfaces.NRO_DECOS_MASIVO){
					 	log.debug("Seteamos RealizarTerminoInmediato en true!");
						act.setRealizarTerminoInmediato(true);
						act.setObservacion("Se inhibe la actividad de DesinstalarDTH por ser de tipo Masivo");
			//			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok, "N");
						log.debug("Se finaliza la Actividad DesinstalarDTH[" + nroPeticion + "]");
					 }else{
					 	PeticionesInterfaces pI= new PeticionesDelegate();
					 	esCRE = pI.getPeti_CRE(act.getNumeroPeticion());		
						//Si peticion contiene ps de pdti o ps de linea basica inhibo actividad
						//if (petiId.indexOf("PDTI") > -1) {
						if(esCRE){
							act.setRealizarTerminoInmediato(true);
							act.setObservacion("Se inhibe la actividad de Desinstalar ya que la petición contiene un ps que requiere no ir a la casa del cliente");
						
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");
							log.debug("Inhibo DesinstalarCRE ya que la petición para por otra actividad que implica ir a la casa del cliente");				
							return;
						}
					 }
					
				}
		} catch (ATiempoAppEx e) {
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad ADesinstalarDTHBean",e);
		}
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {	
		
		Long nroPeticion = act.getNumeroPeticion();
		int decosAlta = 0;
		int decosBaja = 0;
		decosAlta = getNroDecos(nroPeticion)[0];
		decosBaja = getNroDecos(nroPeticion)[1];
		
		//this.noTerminar=true; setear esto para no hacer el termino de la actividad y ejecutar solo la logica
		log.debug("Entrando a onTerminoActividadVPI");
		
		try{
			Bitacora_peticionLocalHome bitacora_peticionLocalHome=(Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
			Long idAct=actividadSessionLocal.getIdActividad(act.getCodigoActividad(),new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			RecursosTVDelegate delegate=new RecursosTVDelegate();
			InfoPostVentaTV infoPostVentaTV=delegate.recuperaInfoTvPostVenta(act.getNumeroPeticion());

			//TODO: 07/10/2009 - RETA - Ajuste para req. 00029579
			if((infoPostVentaTV.isEsBajaDeco() || infoPostVentaTV.isEsBajaCompleta()) && (!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok).equals("N"))){
				log.debug("Es baja de DEco");
				PeticionLocalHome home=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionLocal local=home.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
				if(local.getPeti_causal_baja()==null)//no he enviado el mensaje
				{
					log.debug("Envio el mensaje tr017e");
					delegate.enviaConfiguracionServiciosTVAuto(act.getNumeroPeticion().longValue(),act.getCodigoActividad(),act.getIdActividadFlujo());
					PeticionesDelegate delegate2=new PeticionesDelegate();
					delegate2.setearFechaBajaPs(act.getNumeroPeticion());
					local.setPeti_causal_baja(ComunInterfaces.desconfiguracionEspera);
					act.setNoTerminar(true);
					this.despublicar(act);
				}else//tengo respuesta positiva o negativa.
				{
					log.debug("Tengo respuesta debo terminar la vaina, para peticion:"+act.getNumeroPeticion());
					local.setPeti_causal_baja(null);
//					act.setRealizarTerminoInmediato(true);
//					act.setNoTerminar(false);
					try{
						Bitacora_peticionLocal bi=bitacora_peticionLocalHome.findByPeticionActividad(act.getNumeroPeticion(),idAct);
						bi.setBipe_fecha_fin(new Fecha().getTimestamp());
						bi.setBipe_observacion("Ya se ha enviado el mensaje de configuracion/desconfiguracion DTH.");
					}catch(FinderException fe){
						log.debug("No se encontro el registro en bitacora. Nro peticion : "+ act.getNumeroPeticion()+" y idAct: "+idAct+"  "+ fe);
					}
				}
			}
			else{
//				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");
				log.debug("No es baja de deco tiene que terminar la actividad");
//				act.setRealizarTerminoInmediato(true);
//				act.setNoTerminar(false);
				try{
					Bitacora_peticionLocal bi=bitacora_peticionLocalHome.findByPeticionActividad(act.getNumeroPeticion(),idAct);
					bi.setBipe_fecha_fin(new Fecha().getTimestamp());
				}catch(FinderException fe){
					log.debug("No se encontro el registro en bitacora. Nro peticion : "+ act.getNumeroPeticion()+" y idAct: "+idAct+"  "+ fe);
				}
				
				if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok)) {
					if (act.isRealizarTerminoInmediato() && !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok).equals("S")){
						// para que no instancie Control de Desintalacion DTH
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");
					}
				}else{
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");
				}
				
				/*
				//Para que no haga el Instalar Visita si es que hizo el instalar normal
				if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok))
				{//Para que no haga el Instalar Visita si es que hizo el instalar normal
					if (!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok).equals("S")){
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
					}
				}else{//Si no existe.. la seteo en N
					//Para que no haga el Instalar Visita si es que hizo el instalar normal
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
				}
				//El instalar visita solo lo hace si la variable inst_ok está en blanco*/

			}
		}catch (ATiempoAppEx e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad ADesinstalarDTHBean",e);
		}catch (NamingException e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad ADesinstalarDTHBean",e);
		}catch (FinderException e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad ADesinstalarDTHBean",e);
		}catch (CreateException e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad ADesinstalarDTHBean",e);
		}catch (NumberFormatException e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad ADesinstalarDTHBean",e);
		}catch (Exception e){
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad ADesinstalarDTHBean",e);
		}		
	}
	
	
	private int[] getNroDecos(Long nroPeticion) {
		int[] cantidadDeco = new int[2];
		int decosAlta = 0;
		int decosBaja = 0;
		try{
			InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(nroPeticion);
			
			for (int i=0; listadoPS!=null && i<listadoPS.size(); i++) {
				ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
				if ( psDto.getIdFaps().intValue()== ComunInterfaces.familiaDecoTV || psDto.getIdFaps().intValue()== ComunInterfaces.familiaDecoPVRTV ) {
					if ( "A".equals(psDto.getTipoOperacionComercial()) )
						++decosAlta;
					else if ( "B".equals(psDto.getTipoOperacionComercial()) )
						++decosBaja;
				}
				else if(psDto.getIdFaps().intValue()==ComunInterfaces.familiaPcTV){
					if ( "A".equals(psDto.getTipoOperacionComercial()) )
						++decosAlta;
				}
			}
		}catch (Exception e){
	 		log.error("Error al obtener la cantidad de decos");
	 	}
		cantidadDeco[0] = decosAlta;
		cantidadDeco[1] = decosBaja;
	 	return cantidadDeco;
	}

}
