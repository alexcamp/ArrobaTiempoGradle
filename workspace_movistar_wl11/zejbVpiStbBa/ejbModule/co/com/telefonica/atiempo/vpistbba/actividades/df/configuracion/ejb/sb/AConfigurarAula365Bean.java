package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.sb;

import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocal;
import co.com.telefonica.atiempo.ejb.eb.Grpe_PsLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarAula365
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class AConfigurarAula365Bean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements javax.ejb.SessionBean {
		
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		try{
								
			Iterator iterTemp = act.getPsOk().iterator(); //Debe venir al menos un ps
			//PsVsOcVO psTemp= (PsVsOcVO) iterTemp.next();
			PsVsOcVO psTemp= null;
			
			Long grupoDescuentoAula =new Long(VpistbbaConfig.getVariable("GRUPO_DESCUENTO_AULA"));
			
			log.debug("GRUPO DESCUENTO AULA: " + grupoDescuentoAula);
			
			Grpe_PsLocalHome grpeLocalHome = null;
			if(grpeLocalHome==null)
				try {
					grpeLocalHome=(Grpe_PsLocalHome) HomeFactory.getHome(Grpe_PsLocalHome.JNDI_NAME);
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			Grpe_PsLocal grPsLocal = null;

			Long licenseType = null;
			PsVsOcVO psPrim = null;
			boolean estaEnGrupo = false;
			//while (!estaEnGrupo && iterTemp.hasNext()) {
			while (iterTemp.hasNext()) {

				psTemp = (PsVsOcVO) iterTemp.next();
				
				log.debug("PSTEMP: " + psTemp.getPsId());
				
				try {
					grPsLocal = grpeLocalHome.findGrupoPs(grupoDescuentoAula,psTemp.getPsId());					
					estaEnGrupo = (grPsLocal != null);
					licenseType = psTemp.getPsId();
					log.debug("ESTA EN GRUPO?: " + estaEnGrupo);
					log.debug("PS " + psTemp.getPsId() + " esta en el grupo " + grupoDescuentoAula);	
					
					//Raúl Triviño: Corrección defecto para pasar sin problemas en aula 
					if (psPrim == null)
						psPrim = psTemp;
					//End
				} catch (FinderException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					psPrim = psTemp;
					log.debug("PS " + psPrim.getPsId() + " no esta en el grupo " + grupoDescuentoAula);
				}
			}
			
			if (!estaEnGrupo) {
				psTemp.setPsId(null);
				log.debug("Ningun ID-PS VA A COMPETIR: " + psTemp.getPsId());
			}
					
			RecursosBAInterfaces rBAI= new RecursosBADelegate();
			if(!act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){
				log.debug("Inicio Actividad Configurar Aula365 [" + act.getNumeroPeticion() + "]");
				
				log.debug("ID-PS QUE VA A COMPETIR: " + psTemp.getPsId());
				
				
//				Asumo que pueden venir un único PS 895 o 2 Ps,uno primario (895) y otro de descuento (905) invoco a enviaConfiguracionAula365 con dicho ps, habiéndole seteado el campo psId en null
//				Si vienen dos PS: 895 y 905, invoco a enviaConfiguracionAula365 con el ps primario (el 805) y seteando en licenseType valor del psId del ps de descuento (905).
//				Si viene solo un PS, el 895, invoco a enviaConfiguracionAula365 con el ps primario (el 805) y seteando en licenseType en null.
				//boolean resp=rBAI.enviaConfiguracionAula365(act.getNumeroPeticion(),act,psTemp,false);
				boolean resp=rBAI.enviaConfiguracionAula365(act.getNumeroPeticion(),act,psPrim,licenseType,false);
				if (resp){
					act.setObservacion("Se envia el mensaje de Configuracion Aula365.");	
				}else{
					act.setObservacion("No se envia el mensaje de Configuracion Aula365. Faltan datos. Revise Service Reference ID(CDS), Direccion Electronica(CDS) y Usuario de Acceso Nuevo(ADSL)");					
					//Se deriva a Control Aula365 para que se haga la activacion en forma manual.
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_AULA.control_aula, "AulaS");
					act.setRealizarTerminoInmediato(true);
				}
			}else{
				log.debug("Inicio Actividad Reversa de Configurar Internet [" + act.getNumeroPeticion() + "]");
				//rBAI.enviaConfiguracionAula365(act.getNumeroPeticion(),act,psTemp,true);
				rBAI.enviaConfiguracionAula365(act.getNumeroPeticion(),act,psPrim,licenseType,true);
				//Si tiene OC de reversa hago la reversa, si no tiene OC de Reversa asignada, no hay reversa y se termina la actividad.
				if (psTemp.getOpComRevId()!=null && psTemp.getOpComRevId().longValue()>-1){
					//rBAI.enviaConfiguracionAula365(act.getNumeroPeticion(),act,psTemp,true);
					rBAI.enviaConfiguracionAula365(act.getNumeroPeticion(),act,psPrim,licenseType,true);
					act.setObservacion("Se envia el mensaje de Reversa Configuracion Terra.");
				}
				else{
					act.setObservacion("No hay OC para la reversa. No se envia mensaje de reversa.");
					//Se deriva a Control Aula365 para que se haga la desactivacion.
					// Juan puso AulaN
//					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_AULA.control_aula, "AulaN");
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_AULA.control_aula, "AulaS");
					// CUANDO ENTIENDA LO QUE HACE DESCOMENTO LA LINEA SIGUIENTE Y BORRO LA ANTERIOR
					//act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_AULA365.control_aula365, "S");
					act.setRealizarTerminoInmediato(true);
				}
			}
		} catch (ATiempoAppEx e){
			log.warn("Error en Actividad Configurar Aula365",e);
			throw new TnProcesoExcepcion("Error en Actividad Configurar Aula365", e);
		}
//		} catch (NamingException e) {
//			log.warn("Error en Actividad Configurar Internet",e);
//			throw new TnProcesoExcepcion("Error en Actividad Configurar Internet", e);
//		}		
	}
	
	
	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {

		
	}

}
