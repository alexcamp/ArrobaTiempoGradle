package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_ACSLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR137S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AAutoInstalacion
 */
public class AAutoInstalacionBean
	extends
		co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB
	implements
		javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}

	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {	
		System.out.println("ESTAMOS EN AUTO INSTALACION!");
		
		if(!saltarActividad(act)){
		
			Long idPeticion=act.getNumeroPeticion();
			try{
				PeticionesDelegate pDelegate = new PeticionesDelegate();
				RecursosBADelegate recursosBADelegate = new RecursosBADelegate();

				Mensaje_ACSLocal mensaje_ACSLocal=pDelegate.recuperaUltimoMensajeACS(idPeticion);
				String mensajeOk=pDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.MENSAJE_OK_ACS);
				
				if(mensaje_ACSLocal!=null){
					TR137S tr137s=(TR137S)XMLUtilities.unmarshall(mensaje_ACSLocal.getXml());
					if(tr137s!=null&&tr137s.getErrorDescription().equals(mensajeOk)){
						log.debug("Ya existe un mensaje de OK para el m�dem de �sta petici�n, se inhibir� la actividad Auto Instalaci�n.");
						act.setRealizarTerminoInmediato(true);
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_auto_inst, "N");
						Date hoy=new Date();
						recursosBADelegate.grabarFechaAutoInst(idPeticion,new Fecha(hoy.toString(),"dd/MM/yyyy HH:mm"));
					}
				}else{
					recursosBADelegate.enviarSMSAutoInstalacion(idPeticion,"Automatico");
				}
			}
			catch(FechaException e){
				log.debug("Error en getInfoPresentacion() al guardar fecha de auto instalaci�n: "+e.toString());
			}
			catch(ATiempoAppEx e){
				log.debug("Error en getInfoPresentacion() de EntregarKitAutoInstalacionEjecutor: "+e.toString()); 
			}
		}
	}

	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
	}
	
	/**
	 * Req 3709
	 * @param act
	 * @return
	 */
	private boolean saltarActividad(ActividadEJBDTO act){
		boolean saltarActividad = false;
		Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
		RecursosBADelegate recursosBADelegate = null;
		PeticionesDelegate peticionesDelegate = null;
		try{
			peticionesDelegate=new PeticionesDelegate();
			Long idActAutoInstalacion=new Long(peticionesDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.ID_ACT_AUTO_INSTALACION));
			Long idActPGACS=new Long(peticionesDelegate.recuperarParametroFromPropertiesBD(ComunInterfaces.ID_ACT_PGACS));
			
			Long idActPGI=new Long(ComunInterfaces.idActividadPGI);
			Long idActPGC=new Long(ComunInterfaces.idActividadPGC);
			Long idActPGF=new Long(ComunInterfaces.idActividadPGF);
			
			Long idActPGIBA=new Long(ComunInterfaces.idAct_Solucion_Tecnica_BA);
			Long idActPGIDTH=new Long(ComunInterfaces.idAct_Solucion_Tecnica_DTH);
			Long idActPGISTB=new Long(ComunInterfaces.idAct_Solucion_Tecnica_STB);
			
			ActividadLocalHome actividadLocalHome = (ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ActividadLocal actividadLocalAutoInst = actividadLocalHome.findByCodigoActividadIdAplicacion(ComunInterfaces.COD_ACT_AUTO_INSTALACION,idAplicacion);
			ActividadKey actividadKeyAutoInst = (ActividadKey)actividadLocalAutoInst.getPrimaryKey();	
			
			
			ActividadLocal actividadLocalPGACS = actividadLocalHome.findByCodigoActividadIdAplicacion(ComunInterfaces.COD_ACT_PGACS,idAplicacion);
			ActividadKey actividadKeyPGACS = (ActividadKey)actividadLocalPGACS.getPrimaryKey();
			
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			
			
			Collection bintegradaLocalList=bintegradaLocalHome.findByIdAplicacionNroPeticion(idAplicacion,act.getNumeroPeticion());
			
			
			Iterator bintegradaLocalListIt=null;
			int numeroAutoInstalaciones=0;
			boolean pasoPorPGACS=false;
			for(bintegradaLocalListIt=bintegradaLocalList.iterator();bintegradaLocalListIt.hasNext();){
				BintegradaLocal bintegradaLocal = (BintegradaLocal)bintegradaLocalListIt.next();
				ActividadLocal actividadLocal = bintegradaLocal.getFk_bi_act();
				ActividadKey actividadKey = (ActividadKey)actividadLocal.getPrimaryKey();
				Long acId=actividadKey.act_id;
		
				if(acId.equals(idActAutoInstalacion)){
					numeroAutoInstalaciones++;
				}
				if(acId.equals(idActPGACS)){
					pasoPorPGACS=true;
				}
			}
			
			
			
			BintegradaLocal bintegradaLocalLastDate=bintegradaLocalHome.findLastDateByPetiNum(act.getNumeroPeticion());
			ActividadLocal actividadLocalLastDate =bintegradaLocalLastDate.getFk_bi_act();
			ActividadKey actividadKeyLastDate = (ActividadKey)actividadLocalLastDate.getPrimaryKey();
			Long acIdLastDate=actividadKeyLastDate.act_id;
			
			//DAVID: Si el �ltimo registro de actividad en bandeja integrada es una bandeja de gesti�n y ya previamente se hab�a pasado por
			//PGACS, entonces se vuelve a PGACS
			if((acIdLastDate.equals(idActPGI)||acIdLastDate.equals(idActPGC)||acIdLastDate.equals(idActPGF)
					||acIdLastDate.equals(idActPGIBA)||acIdLastDate.equals(idActPGIDTH)
					||acIdLastDate.equals(idActPGISTB))&&pasoPorPGACS){
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGACS"));
				act.setObservacion("Se inhibe Auto instalaci�n y vuelve a PGACS porque viene de una plataforma de gesti�n." );
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_auto_inst, "N");
				act.setRealizarTerminoInmediato(true);
				saltarActividad=true;
			}
			//DAVID: Si el �ltimo registro en BI no es una bandeja de gesti�n, entonces se eval�a si ya se pas� por PGACS y por auto instalaci�n al menos una vez,
			//lo que indica que se ha pasado por PGACS y se est� entrando a Auto instalaci�n despu�s de dar continuar flujo en PGACS, por lo que se inhibe Auto instalaci�n.
			else{				
				if(pasoPorPGACS && numeroAutoInstalaciones>=1){
					saltarActividad = true;
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_auto_inst, "N");
					act.setObservacion("Se inhibe Auto instalaci�n porque viene de PGACS." );
					log.debug("Se salta la actividad auto instalacion porque viene de PGACS");
					act.setRealizarTerminoInmediato(true);
				}
			}//DAVID: si no se cumple ninguno de los dos casos anteriores, no se inhibe la actividad Auto instalaci�n.		
			
			
			return saltarActividad;
		}catch(ATiempoAppEx e){
			log.debug("Error al recuperar datos mediante recurso Delegate:"+act.getNumeroPeticion()+"    "+e.toString());
			return saltarActividad;
		}catch(FinderException e){
			log.debug("No hay registros para la bandeja integrada en la actividad autoinstalaci�n o PGACS para la petici�n:"+act.getNumeroPeticion()+"    "+e.toString());
			return saltarActividad;
		}catch(NamingException e){
			log.debug("");
			return saltarActividad;
		}
	}
}
