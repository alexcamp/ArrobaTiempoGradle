package co.com.telefonica.atiempo.vpistbba.actividades.df.instalacion.ejb.sb;

import javax.ejb.FinderException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Bitacora_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
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

/**
 * Bean implementation class for Enterprise Bean: ADesinstalarDTH
 */
public class ADesinstalarCREDTHBean
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
		 
		PeticionLocalHome home = null;
		try {
			home = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionLocal peticion=home.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
			PeticionesInterfaces pI= new PeticionesDelegate();
			
			String petiId = pI.getPeti_id_instancia_con_pdti(act.getNumeroPeticion());
			boolean esCRE = pI.getPeti_CRE(act.getNumeroPeticion());

			if(!esCRE){
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe la actividad de DesisntalarCRE ya que la petición contiene un ps que requiere ir a la casa del cliente");
				
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok, "N");
				log.debug("Inhibo DesinstalarCRE ya que la petición para por otra actividad que implica ir a la casa del cliente");				
				return;
			}
			
			//Si peticion contiene ps de pdti o ps de linea basica inhibo actividad
			if (petiId.indexOf("PDTI") > -1) {				
				log.debug("Inhibo DesinstalarCREDTH ya que la petición pasa por otra actividad que implica ir a la casa del cliente");
				act.setRealizarTerminoInmediato(true);
				act.setObservacion("Se inhibe la actividad de DesisntalaCREDTH ya que la petición contiene un ps que requiere ir a la casa del cliente");
				
				// Seteamos la variable DESINST_DTH_OK para evitar que entre a CONTROL DE DESINSTALACION
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");				
			}
			
		} catch (Exception e) {
			log.debug("Error en Actividad Desinstalar CRE DTH " + e.getMessage());
		} 
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {		

		try
		{
			Bitacora_peticionLocalHome bitacora_peticionLocalHome=(Bitacora_peticionLocalHome) HomeFactory.getHome(Bitacora_peticionLocalHome.JNDI_NAME);
			ActividadSessionLocalHome actividadSessionLocalHome=(ActividadSessionLocalHome) HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
			ActividadSessionLocal actividadSessionLocal=actividadSessionLocalHome.create();
			Long idAct=actividadSessionLocal.getIdActividad(act.getCodigoActividad(),new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID)));
			RecursosTVDelegate delegate=new RecursosTVDelegate();
			InfoPostVentaTV infoPostVentaTV=delegate.recuperaInfoTvPostVenta(act.getNumeroPeticion());
			if((infoPostVentaTV.isEsBajaDeco() && !infoPostVentaTV.isEsBajaCompleta()) && (!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok).equals("N")) )
			{
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
				}
				else//tengo respuesta positiva o negativa.
				{
					log.debug("Tengo respuesta debo terminar la vaina, para peticion:"+act.getNumeroPeticion());
					local.setPeti_causal_baja(null);
//					act.setRealizarTerminoInmediato(true);
//					act.setNoTerminar(false);
					try
					{						
						Bitacora_peticionLocal bi=bitacora_peticionLocalHome.findByPeticionActividad(act.getNumeroPeticion(),idAct);
						bi.setBipe_fecha_fin(new Fecha().getTimestamp());
						bi.setBipe_observacion("Ya se ha enviado el mensaje de configuracion/desconfiguracion DTH.");
					}
					catch(FinderException fe)
					{
					    log.debug("No se encontro el registro en bitacora. Nro peticion : "+ act.getNumeroPeticion()+" y idAct: "+idAct+"  "+ fe);						
					}
				}
			}
			else
			{
				log.debug("No es baja de deco tiene que terminar la actividad");
//				act.setRealizarTerminoInmediato(true);
//				act.setNoTerminar(false);
				try
				{
					//si aun no habia inhibido la actividad...
					if (!act.isRealizarTerminoInmediato()) {						
						Bitacora_peticionLocal bi=bitacora_peticionLocalHome.findByPeticionActividad(act.getNumeroPeticion(),idAct);
						bi.setBipe_fecha_fin(new Fecha().getTimestamp());						
					}	
				}
				catch(FinderException fe)
				{
				    log.debug("No se encontro el registro en bitacora. Nro peticion : "+ act.getNumeroPeticion()+" y idAct: "+idAct+"  "+ fe);					
				}
			}
			
			if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok)) {
				if (act.isRealizarTerminoInmediato() && !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok).equals("S")){
					
					// para que no instancie Control de Desintalacion CRE DTH
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"N");
				}
			}else{
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"N");
			}
			
		}
		catch (Exception e)
		{
			log.debug(e);
			throw new TnProcesoExcepcion("Error en Actividad Desinstalar CRE DTH",e);
		}		
	}

}
