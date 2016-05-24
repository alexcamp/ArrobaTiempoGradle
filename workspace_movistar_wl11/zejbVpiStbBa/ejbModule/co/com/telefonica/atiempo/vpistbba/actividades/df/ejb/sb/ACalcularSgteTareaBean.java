package co.com.telefonica.atiempo.vpistbba.actividades.df.ejb.sb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.vpistbba.actividades.ObtenerSgteTarea2;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.singleton.PropertiesActividades;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ACalcularSgteTarea
 */
public class ACalcularSgteTareaBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 * 
	 */	
	private Peticion_flujoLocalHome ejbPeticionFlujoHome = null;
	private ActividadLocalHome ejbActividadHome=null;
	private Actividad_flujoLocalHome ejbActividadFlujoHome=null;
	private Operacion_comercialLocalHome ejbOperacionComercialHome=null;
	private PeticionLocalHome ejbPeticionHome=null;
	private DBManager manager;	 
	 
	private final String IDPGI=VpistbbaConfig.getVariable("IDPGI");
	private final String IDPGC=VpistbbaConfig.getVariable("IDPGC");
	private final String IDPGF=VpistbbaConfig.getVariable("IDPGF");
	private final String IDPGACS=VpistbbaConfig.getVariable("IDPGACS");
	
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("INICIO AUTOMATICO, CalcularSgteTarea");
		boolean hayGestionReversa=false;
			if((act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb).equals("CONFSTB")||
					act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb).equals("DESCSTB"))
					&&act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S"))
			{
				log.debug(" d "+act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb)+ " "+ act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa));
				hayGestionReversa=true;
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primera_reversa,"");
			}
				
		//inicializo los subflujos a "N"
		this.inicializa(act);
		

		//Identifica las siguientes tareas a realizar por la peticion

		try {
//			Cuando se genera el Flujo en la actividad GenerarFlujoPeticion, se pobla la tabla PeticionFlujo con las actividades_flujo que debe hacer
//			Además de agregar las actividades, les asocia el orden. Así sabe cual o cuales(actividades paralelas) debe hacer. El orden de actividad_flujo
//			en que va lo guarda en una variable del Workflow. La variable FLUJ_ORDEN.
			Integer ordenFlujo;
			try {
				ordenFlujo = new Integer(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden));
				if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok).equals("AutoInstalacion")){
//					ordenFlujo = new Integer (ordenFlujo.intValue() + 1);
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"");
				}
					
			} catch (NumberFormatException e) {
				//TODO: ¿porque el orden flujo e inicializa en -1?
				ordenFlujo = new Integer(-1);
			}
			

			List listaDatos = null;
			List listaMismoOrden = null;
			List listaPendientes = new ArrayList();
			
			//ID PGI =18 , ID PGC = 19


//			Seteo la variable para la reversa cuando se lanza la cancelacion desde la PGI o PGC
			if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGI.pgi_ok) && act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGI.pgi_ok).equals("C")){
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa,"S");
			}
			if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGC.pgc_ok) && act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGC.pgc_ok).equals("C")){
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa,"S");
			}
			//Seteo la variable para la reversa cuando se lanza la cancelacion desde la PGF
			if (act.containsKeyDato(DATOS_ATVPISTBBA.act_ok) && act.getDato(DATOS_ATVPISTBBA.act_ok).equals("C")){
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa,"S");
			}
			
			boolean isWF = this.validarSiguienteTarea(act, ordenFlujo);
			
			//No se envìa a WF
			if(!isWF)
				return;

			if (!act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) || act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("")) {

				if (!act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) || act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals("")) {
					/*  Viene alguna actividad con ESTADO=3 a pesar de venir FLUJ_ACT_SIG_FORZA<>18 o 19
					 *  ("Alguna actividad" digo: para casos de paralelismo)
					 */
					 //Si no hay Quiebre se deja esta variable en N
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre,"N");					
					
						log.debug("ORDEN EN EL QUE ESTOY =>"+ordenFlujo);

						ordenFlujo = new Integer( ordenFlujo.intValue()+1 );
						log.debug("No viene el flujo en reversa y no fuerzan a la siguiente tarea [" + ordenFlujo + "]");
	
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
	
						listaDatos = this.getDatosPeticion(act.getNumeroPeticion(), ordenFlujo);
	
						if (listaDatos.size() == 0) {
							//YA NO QUEDAN MAS TAREAS EN FLUJO POSITIVO
							log.debug("No quedan mas tareas en el flujo, se verifica si se hace reversa");
							//reviso que no haya ningun ps con estado no OK	
							boolean hayPsNoOk = false;					
							PeticionesInterfaces pI= new PeticionesDelegate();
							ArrayList psPet=pI.listaDePsDePeticion(act.getNumeroPeticion());
							for(Iterator iter=psPet.iterator();iter.hasNext();){
								PsVsOcVO psVO = (PsVsOcVO) iter.next();
								if(!psVO.isOk()){
									log.debug("Hay un ps no ok, se hace reversa");
									act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa,"S");
									hayPsNoOk=true;
									break;
								}
									
							}
							if (!hayPsNoOk){
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "N");
								act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
							}
						} else {
							log.debug("Se marca el paso por una actividad");
							this.setDatosActividades(listaDatos,act);
						}
//					}
				} else {
					//SE VA A UNA ACTIVIDAD EN FORMA FORZOSA
					log.debug("No viene el flujo en reversa, pero se forzara a la siguiente tarea");
					if (!(IDPGI).equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)) && !(IDPGC).equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))
							&& !(IDPGF).equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))&& !(IDPGACS).equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))) {
						log.debug("La variable FLUJ_ACT_SIG_FORZA tiene valor: " + act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza));
						

						//TODO: se obtendrá el siguiente valor de una secuencia para peticion_flujo.
						manager= new DBManager();
						manager.setDataSource(DBManager.JDBC_VPISTBBA);
						Integer pefl_id=new Integer(manager.seqNextValInteger("ATIEMPO.CORRELATIVO_PETICION_FLUJO")+1);
						PeticionLocal peticionLocal=ejbPeticionHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
						
						Peticion_flujoLocalHome petiFlujoHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
						Collection petiFlujo = petiFlujoHome.findByIdPeticionOrdenado(act.getNumeroPeticion());
						Operacion_comercialLocalHome operacionComercialHome=(Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);;
						Long prse_id = null;
						Operacion_comercialLocal operacion_comercialLocal = null;
						for(Iterator iter = petiFlujo.iterator();iter.hasNext();){
							Peticion_flujoLocal petiFlujoLocal = (Peticion_flujoLocal)iter.next();
							if(petiFlujoLocal.getPefl_orden().intValue()>ordenFlujo.intValue()){
								petiFlujoLocal.setPefl_orden(new Integer (petiFlujoLocal.getPefl_orden().intValue()+1));
							}else{
								prse_id = petiFlujoLocal.getPrse_id();
								operacion_comercialLocal = petiFlujoLocal.getFk_opco_2_pefl();
							}
								
						}
						ordenFlujo = new Integer(ordenFlujo.intValue() + 1); 
						Actividad_flujoLocal actividad_flujoLocal=ejbActividadFlujoHome.findByPrimaryKey(new Actividad_flujoKey(new Integer(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))));
						
						Integer opcom=null;
						ejbPeticionFlujoHome.create(pefl_id,ordenFlujo,prse_id,null,peticionLocal,actividad_flujoLocal,operacion_comercialLocal);
						Collection newActivity = ejbPeticionFlujoHome.findActividadesByOrden(act.getNumeroPeticion(),ordenFlujo);
						for(Iterator iter = newActivity.iterator();iter.hasNext();){
							Peticion_flujoLocal petiFlujoLocal = (Peticion_flujoLocal)iter.next();
							petiFlujoLocal.setPrse_id(prse_id);
						}
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
					}
					else {
						log.debug("La variable FLUJ_ACT_SIG_FORZA tiene valor:" + act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza));
						if (!act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre) || !"S".equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre))){
							ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
						}
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre,"S");
					}
					
					
					
					String llave = PropertiesActividades.getVariable(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza));
					if ((IDPGF).equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))){						
						log.debug("ESTOY SETEANDO EN PlatGestFraude LA LLAVE:" + llave);
						act.setDato(llave, "PlatGestFraude");
					}else{ 
						if(!ComunInterfaces.ACT_CONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))
								&& !ComunInterfaces.ACT_DESCONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))){
							log.debug("ESTOY SETEANDO EN S LA LLAVE:" + llave);
							act.setDato(llave, "S");
						}else{
							if(ComunInterfaces.ACT_CONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))){
								log.debug("ESTOY SETEANDO EN CONFSTB LA LLAVE:" + llave);
								act.setDato(llave, "CONFSTB");
							}else{
								log.debug("ESTOY SETEANDO EN CONFSTB LA LLAVE:" + llave);
								act.setDato(llave, "DESCSTB");
							}
								
						}
						
					}					
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "");
				}
			} else {			
				if (act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primera_reversa).equals("")) {
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primera_reversa, "S");
					//Se setea la peticion como Cancelada
					PeticionLocal peticionLocal=ejbPeticionHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
					peticionLocal.setEspe_id(new Integer(ComunInterfaces.estadoPeticionCancelada));
				}
				//Se va Quiebre en Reversa
				if (ComunInterfaces.ACT_DESCONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)) ||ComunInterfaces.ACT_CONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)) ||(IDPGI).equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza)) || (IDPGC).equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))) {
					log.debug("La variable FLUJ_ACT_SIG_FORZA tiene valor:" + act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza));
					if (!act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre) || !"S".equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre))){
						log.debug("Le sumo 1 al orden");
						ordenFlujo = new Integer(ordenFlujo.intValue() + 1);
					}
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre,"S");
					String llave = PropertiesActividades.getVariable(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza));
					
					if(!ComunInterfaces.ACT_CONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))
							&& !ComunInterfaces.ACT_DESCONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))){
						log.debug("ESTOY SETEANDO EN S LA LLAVE:" + llave);
						act.setDato(llave, "S");
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "");
					}else{
						if(ComunInterfaces.ACT_CONFIG_STB.equals(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza))){
							log.debug("ESTOY SETEANDO EN CONFSTB LA LLAVE:" + llave);
							act.setDato(llave, "CONFSTB");
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "");
						}else{
							log.debug("ESTOY SETEANDO EN CONFSTB LA LLAVE:" + llave);
							act.setDato(llave, "DESCSTB");
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "");
						}
							
					}
										
				}else{
					act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre,"N");
					// La reversa tiene que seguir aunque tenga actividades que no esten hechas.
					listaDatos = new ArrayList();
					log.debug("Orden Flujo:... "+ordenFlujo.toString());
					int contador=0;
					while (listaDatos.size()<1 && ordenFlujo.intValue()>-1){
						contador++;
						if(hayGestionReversa){
							ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
							listaDatos = this.getDatosPeticionReversa(act.getNumeroPeticion(), ordenFlujo);
							log.debug("se continua desde gestion Abonados se debe continuar a la siguiente actividad "+ordenFlujo.toString());
							
							for (int i = 0; i < listaDatos.size(); i++) {
								// dato es el código de la actividad macro (subflujo) y llave es el nombre de la variable que se usará para
								// entrar a dicha actividad macro (por ejemplo dato=52 y llave=FLUJ_MULT_CONF_INT) 			
								String dato = listaDatos.get(i).toString();
//								if que no de deja llamar la activdad gestion abonados cuando esta grabada en un flujo 
								if((dato.equals(ComunInterfaces.ACT_CONFIG_STB)
										|| dato.equals(ComunInterfaces.ACT_CONFIG_STB))
										&& act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){ 
									log.debug("hay activdad gestion abonados en el flujo la salto actividad");
									ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
									listaDatos = this.getDatosPeticionReversa(act.getNumeroPeticion(), ordenFlujo);
									act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primera_reversa,"");
									break;
								}
							}
							ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
							hayGestionReversa=false;
							log.debug("menos 1 flujo que se va a validar " +ordenFlujo);
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primera_reversa,"");
						}else{
							listaDatos = this.getDatosPeticionReversa(act.getNumeroPeticion(), ordenFlujo);
							
							for (int i = 0; i < listaDatos.size(); i++) {
								// dato es el código de la actividad macro (subflujo) y llave es el nombre de la variable que se usará para
								// entrar a dicha actividad macro (por ejemplo dato=52 y llave=FLUJ_MULT_CONF_INT) 			
								String dato = listaDatos.get(i).toString();
//								if que no de deja llamar la activdad gestion abonados cuando esta grabada en un flujo 
								if((dato.equals(ComunInterfaces.ACT_CONFIG_STB)
										|| dato.equals(ComunInterfaces.ACT_CONFIG_STB))
										&& act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")){ 
									log.debug("hay activdad gestion abonados en el flujo la salto actividad");
									ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
									listaDatos = this.getDatosPeticionReversa(act.getNumeroPeticion(), ordenFlujo);
									act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primera_reversa,"");
									break;
								}
							}
//							cuando se reversa el flujo se resta dos veces y se salta activdades en el flujo
							//se mapea estado de peticion cancelada ya que esta se estaba cambiando a otro estado y las peticiones no quedaban como canceladas
							if(contador<=1){
							ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
							act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primera_reversa,"S");
							PeticionLocal peticionLocal=ejbPeticionHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
							peticionLocal.setEspe_id(new Integer(ComunInterfaces.estadoPeticionCancelada));
							}
							log.debug(" nuevo Orden Flujo "+ordenFlujo.toString()+ " "+act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_conf_stb));
							}
					}
				
					if (listaDatos.size() > 0) {
						log.debug("Existen Actividades en Reversa: "+listaDatos.size());
						this.setDatosActividades(listaDatos,act);
//						ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
						
					} else {
						//YA NO SE PUEDE RETROCEDER MAS
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "N");
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
					}					
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "S");
			
			throw new TnProcesoExcepcion(
				e.getClass().getName()
					+ " en : "
					+ this.getClass().getName()
					+ ".onInicioAutomatico()"
					+ e.getMessage());
		}

		log.debug("FIN INICIO AUTOMATICO, CalcularSgteTarea");		
	}


	/**
	 * @param act
	 * @param ordenFlujo
	 * @return
	 */
	private boolean validarSiguienteTarea(ActividadEJBDTO act, Integer ordenFlujo) {
		// TODO Apéndice de método generado automáticamente
		Peticion_flujoLocalHome peticionFlujoLocalHome;
		try {
			// Se obtiene la siguiente actividad
			if(ordenFlujo == null)
				ordenFlujo = new Integer(-1);
			
			Integer ordenFlujoNew = null;
			if(act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) 
						&& act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("S")
						&& ordenFlujo.intValue() > -1){
				ordenFlujoNew = new Integer (ordenFlujo.intValue() - 1);
			}else{
				//se agrega validacion de continuar flujo en las badejas ya que si se continua flujo suma +1 en el flujo y se continua la proxima actividad y no se reintenta la activdad
				if ((act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGI.pgi_ok) && act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGI.pgi_ok).equals("S"))
						||(act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGC.pgc_ok) && act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGC.pgc_ok).equals("S"))
						||(act.containsKeyDato(DATOS_ATVPISTBBA.act_ok) && act.getDato(DATOS_ATVPISTBBA.act_ok).equals("S")))
					{
						
						ordenFlujoNew= ordenFlujo;
						//SE REINICIA VARIBLE A X POR QUE CUANDO SE CALCULA UNA NUEVA ACTIVIDAD SE CIERRA
						if(act.containsKeyDato(DATOS_ATVPISTBBA.act_ok) && act.getDato(DATOS_ATVPISTBBA.act_ok).equals("S")){
							act.setDato(DATOS_ATVPISTBBA.act_ok,"X");
						}
						log.debug("se continua el flujo desde las bandejas PGI,PGC o PGF "+ordenFlujoNew);
					}else{
						ordenFlujoNew = new Integer (ordenFlujo.intValue() + 1);
					}
			}
			log.debug("Se evelua si es una actividad de WF para el orden "+ordenFlujoNew);
			peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticionFlujoList = peticionFlujoLocalHome.findActividadesByOrden(act.getNumeroPeticion(), ordenFlujoNew);
			if (!act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) || act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals("")) {
				for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
	    			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
	    			log.debug("Se valida la descripciòn con : "+peticionFlujoLocal.getFk_acti_2_pefl().getActi_descripcion());
	    			//Este proceso es temporal
	    			log.debug("Actividad Evaluada: "+peticionFlujoLocal.getFk_acti_2_pefl().getActi_descripcion());
	    			if((peticionFlujoLocal.getFk_acti_2_pefl().getActi_descripcion().equals(ComunInterfaces.ACT_MOVISTAR_PLAY)
	    					//Se agrega la actividad configurar acs
	    					||peticionFlujoLocal.getFk_acti_2_pefl().getActi_codigo().equals(ComunInterfaces.ACT_CONF_ACS)
							||peticionFlujoLocal.getFk_acti_2_pefl().getActi_codigo().equals(ComunInterfaces.ACT_SXRX_IMS)
							|| peticionFlujoLocal.getFk_acti_2_pefl().getActi_descripcion().equals(ComunInterfaces.ACT_INSTALAR_FLUJO)
							|| peticionFlujoLocal.getFk_acti_2_pefl().getActi_descripcion().equals(ComunInterfaces.ACT_INSTALAR_TOA_FLUJO))
	    					&&(peticionFlujoLocal.getPefl_estado()== null || !(peticionFlujoLocal.getPefl_estado().equals("OK")))){
	    				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre,"N");
	    				new ObtenerSgteTarea2(act, ordenFlujoNew);
	    				act.setRealizarTerminoInmediato(false);
	    				return false;
	    			
	    			//se grega validacion para reversa ya que lactivdidad de toa en reversa no se estaba ejecutando
	    			}
			}
			
			}
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			return true;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			return true;
		}
		
		return true;
	}


	private void inicializa(ActividadEJBDTO act) {

		Map datos = PropertiesActividades.getProperties();

		for (Iterator iterator = datos.keySet().iterator(); iterator.hasNext();) {
			String element = (String) iterator.next();
			String fluj_mult = (String) datos.get(element);
//			Correccion para no enviar variables que el flujo antiguo no conoce
			if (act.containsKeyDato(fluj_mult))
			{
					act.setDato(fluj_mult, "N");

			}
			if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra))
			{
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.CONFIGURACION_TERRA.control_terra,"N");
			}	
		}
		if (!act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion) || !act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion).equals("S")){
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "N");	
		}
		//Inicializacion variables Instalacion.Si no tienen valor, se les setea uno por defecto: X
		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "N");
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok))
		{
			if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok).equals("")){
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"X");
			}
		}else{
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"X");
		}
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok))
		{
			if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok).equals("")){
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"X");
			}
		}else{
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"X");
		}	
		if (act.containsKeyDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok))
		{
			if(act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok).equals("")){
				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"X");
			}
		}else{
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"X");
		}			
//		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.INSTALACION.inst_ok,"N");
//		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION.desinst_ok,"N");		
//		act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.DESINSTALACION_DTH.desinst_dth_ok,"N");
	}	
	
	private void setDatosActividades(List listaDatos,ActividadEJBDTO act) {

		for (int i = 0; i < listaDatos.size(); i++) {
			// dato es el código de la actividad macro (subflujo) y llave es el nombre de la variable que se usará para
			// entrar a dicha actividad macro (por ejemplo dato=52 y llave=FLUJ_MULT_CONF_INT) 			
			String dato = listaDatos.get(i).toString();
			String llave = PropertiesActividades.getVariable(dato);
			//act.setDato(llave, "S");
			// Llave es la variable de WF, usada en Director de Flujo para determinar a que subflujo entrar (Configuracion Terra, Configuracion Aula, etc)
				this.setVariableActividad( Integer.parseInt(dato), act, llave);
		}
	}
	
	//Metodo encargado de Setear el valor para que pase a la siguiente actividad 
	//El valor del dato se saca del properties actividadesVpiStbBaWF 
	//Lo que se carga en act.setDato(llave, "ConfSI") es el valor del WF
	//para no romper el resto de  las peticiones por defecto pone S
	private void setVariableActividad(int dato,ActividadEJBDTO act, String llave) {

			switch (dato) {
				case 28 :
					act.setDato(llave, "ConfSI");
					break;
				case 29 :
					act.setDato(llave, "ObtCorreoSI");
					break;
				case 30 :
					act.setDato(llave, "InfoResInsSI");
					break;
				case 31 :
					act.setDato(llave, "SuspReco");
					break;
				case 50 :
					act.setDato(llave, "CierrePrimarioTV");
					break;
				case 51 :
					act.setDato(llave, "CierrePrimarioS");
					break;
				case 32 :
					act.setDato(llave, "DesconSigres");
					break;
				case 33 :
				//se carga cuando debe instancia la actividad de Configurar internet para Traslado
					act.setDato(llave, "ConfIntTra");
					break;
				case 34 :
				//se carga cuando debe instancia la actividad de Configurar internet para Traslado
					act.setDato(llave, "ModIdenOpe");
					break;
				case 35 :
					act.setDato(llave, "ObtConfBASI");
					break;
				case 52 :
					act.setDato(llave, "ConfAula");
					break;
				case 53 :
					act.setDato(llave, "ConfTelNau");
					break;
				case 60 :
					//se carga cuando debe instancia la actividad Actualizar Inventario PE					
					act.setDato(llave, "AcInvBaPe");
					break;
				case 61 :
					//se carga cuando debe instancia la actividad Actualizar Inventario PE					
					act.setDato(llave, "CreaODS");	
					break;
				//TODO: RETA 16/09/2009 - Se adiciona para la tarea de financiar equipo
				case 70 :
					act.setDato(llave, "ObtenerInventarioEquipo");
					break;					
				case 71 :
					act.setDato(llave, "ActualizarEquipo");
					break;
				case 72 :
					act.setDato(llave, "AltaAbonadoRI");
					break;
				case 73 :
					act.setDato(llave, "AltaPeriodRI");
					break;
				case 74:
					act.setDato(llave, "BajaAbonadoRI");
					break;						
				case 75:
					act.setDato(llave, "CambioNumeroRI");
					break;
				case 76:
					act.setDato(llave, "CambioPrepagoRI");
					break;
				case 77:
					act.setDato(llave, "BajaPeriodRI");
					break;
				// CR 27638 - Se setan las 2 nuevas actividades
				case 78:
					act.setDato(llave, "AltaClaveRI");
					break;
				case 79:
					act.setDato(llave, "DesconfClaveRI");
					break;
				case 80 :
				    act.setDato(llave, "ConfUMTS");
				    break;
				case 81 :
				    act.setDato(llave, "DesconfUMTS");
				    break;
					//CR24918 - TV Masivo - PCawen
				case 90 :
                    act.setDato(llave, "DesinstDTHCRE");
                    break;                                                  
				case 91 :
                    act.setDato(llave, "DesinstCRE");
                    break;                                                              
				case 92:
                    act.setDato(llave, "DesinstalMasivo");
                    break;
				case 93:
                    act.setDato(llave, "ConsTVMasivo");
                    break;
				case 94:
                    act.setDato(llave, "InstaMasivo");
                    break;
				case 95:
				    act.setDato(llave, "Financiar");
				    break;
				case 96:
				    act.setDato(llave, "InstEquipo");
				    break;							
				case 97:
				    act.setDato(llave, "AsistCliente");
				    break;
				case 98:
				    act.setDato(llave, "AsistClienteRemota");
				    break;
				// idrincon 18/08/2010 Req 1060     
				case 100:
					act.setDato(llave, "DesInstEquipo");
					break;
				// end idrincon 18/08/2010
				case 101:
				    act.setDato(llave, "ObtConfBASISVA");
				    break;
				case 102:
				    act.setDato(llave, "ConfWebFilter");
				    break;
				case 103:
				    act.setDato(llave, "ConfIntOptenet");
				    break;
				case 104:
					act.setDato(llave, "ConfTroncalSip");
					break;
				case 105:
					act.setDato(llave, "ConfTerabox");
					break;
				case 106:
					act.setDato(llave, "AlistKitAutoInst");
					break;
				case 107:
					act.setDato(llave, "EntKitAutoInst");
					break;
				case 108:
					act.setDato(llave, "AutoInstalacion");
					break;
				case 110:
					act.setDato(llave, "DesconfModem");
					break;
				//Venta de equipos 5565
				case 111:
					act.setDato(llave, "Generar_Factura");
					break;
				/*Req 6895 - Interfaz Atiempo - MM SAP*/
				case 113:
					act.setDato(llave, "Enviar_Equipo_SAP");
					break;
				/*Req 5606 - Internet Movil*/
				case 114:
					act.setDato(llave, "Config_Int_Movil");
					break;					
				case 115:
					act.setDato(llave, "Legali_Int_Movil");
					break;
					/*Req  - TV Sola*/
				case 116:
					act.setDato(llave, "Generar_Recibo");
					break;					
				case 117:
					act.setDato(llave, "Conf_Pago_Recibo");
					break;
				/*Req 8595 - Venta Minoristas SAP*/
				case 118:
					act.setDato(llave, "Vista_Global");
					break;
				case 119:
					act.setDato(llave, "Config_Terra_Sonora");
					break;
				case 120:
					act.setDato(llave, "Config_Terra_SiteBuilder");
					break;
				case 121:
					act.setDato(llave, "Notificacion_SAP");
					break;
				case 122:
					act.setDato(llave, "Config_Terra_Antivirus");
					break;
				case 123:
					act.setDato(llave, "Valida_Actuacion_ASC");
					break;
				case 124:
					act.setDato(llave, "instalacion_equipos");
					break;
				case 125:
					act.setDato(llave, "Conf_Presencia_Digital");
					break;
				case 126:
					act.setDato(llave, "Conf_PdVA");
					break;
				case 127:
					act.setDato(llave, "Conf_Ext_PDVA");//Actifvidad manual de extensiones PdVA
					break;
				case 128:
					act.setDato(llave, "Conf_BandejaTelf");//Actifvidad manual de extensiones PdVA
					break;
				case 129:
					act.setDato(llave, "ConfTeraboxMail");//Actifvidad Envio Correo Terabox
					break;
				case 131:
					act.setDato(llave, "Conf_Mediacion_Movil");//Actifvidad Configuracion Mediacion Movil
					break;
				case 132:
					act.setDato(llave, "Recarga_Fija_Movil");//Actifvidad Recarga Fija Movil
					break;
				case 133:
					act.setDato(llave, "Conf_Paquete_Movil");//Actifvidad Configuracion Paquete Movil
					break;
				case 134:
					act.setDato(llave, "Conf_Cliente_ZTE");//Actividad cliente ZTE
					break;
				case 135:
					act.setDato(llave, "Consulta_Estado_DTH");//Actividad Consulta Estado DTH
					break;
				case 136:
					act.setDato(llave, "DesinstMotorizado");//Actifvidad Configuracion STB MSAN
					break;
				case 137:
					act.setDato(llave, "CONF_AUT_IMS");//Actifvidad Configuracion STB IMS
					break;
				case 138:
					act.setDato(llave, "CONF_AUT_MSAN");//Actifvidad Configuracion STB MSAN
					break;
				case 139:
					act.setDato(llave, "DESCONF_AUT_IMS");//Actifvidad Configuracion STB MSAN
					break;
				case 140:
					act.setDato(llave, "DESCONF_AUT_MSAN");//Actifvidad Configuracion STB MSAN
					break;
				case 141:
					act.setDato(llave, "CONFSTB");//Actifvidad Gestiòn Abonados
					break;
				case 142:
					act.setDato(llave, "DESCSTB");//Actifvidad Gestiòn Abonados
					break;
				case 143:
					act.setDato(llave, "Vel_Adicional_TMP");//Actifvidad velocidad adicional
					break;
				case 144:
					act.setDato(llave, "CONF_NAPSTER");//Actividad Configurar Napster dcardena 
					break;
				case 145:
					act.setDato(llave, "ASIST_REMOTA");//Actifvidad Bandeja Asistencia Remota
					break;	
				//End 
			default :
					act.setDato(llave, "S");
					break;
			}
		}
	
	
	
	private List getDatosPeticion(Long idPeticion,Integer orden) throws NamingException, FinderException {
		List datos = new ArrayList();
		
		// Llamada al Entity
		Peticion_flujoLocalHome tareaHome=(Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
		//Consulta al entity Peticion Flujo
		Collection tareas=(Collection) tareaHome.findActividadesByOrden(idPeticion,orden);
		
		for (Iterator iter = tareas.iterator(); iter.hasNext();) {
			Peticion_flujoLocal tarea = (Peticion_flujoLocal) iter.next();
			if ( tarea.getPefl_estado()== null || !(tarea.getPefl_estado().equals("OK")) ){
				//LE PASA LOS ID DE LAS ACTIVIDAD A SEGUIR
				Actividad_flujoKey afk= (Actividad_flujoKey) tarea.getFk_acti_2_pefl().getPrimaryKey();
				datos.add(afk.acti_id);	
				log.debug("Actividad_flujoKey Codigo A Seguir" + afk.acti_id);
			}
		}
		return datos;
	}
	
	private List getDatosPeticionReversa(Long idPeticion,Integer orden) {
		
		List datos = new ArrayList();
		Peticion_flujoLocalHome tareaHome = null;
		Collection tareas = null;
		 
		try {
			tareaHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			tareas = (Collection) tareaHome.findActividadesByOrden(idPeticion, orden);
			for (Iterator iter = tareas.iterator(); iter.hasNext();) {
				Peticion_flujoLocal tarea = (Peticion_flujoLocal) iter.next();
				if ( tarea.getPefl_estado()!= null && (tarea.getPefl_estado().equals("OK")) ){
					//LE PASA LOS ID DE LAS ACTIVIDAD A SEGUIR
					Actividad_flujoKey afk= (Actividad_flujoKey) tarea.getFk_acti_2_pefl().getPrimaryKey();
					datos.add(afk.acti_id);
				}
			}
		} catch (NamingException e) {
			log.error("NamingException Reversa [" + idPeticion + "," + orden + "]");
		} catch (FinderException e1) {
			log.error("FinderException Reversa [" + idPeticion + "," + orden + "]");
		}
		
		
		return datos;
	}
	
//	private List getActividadesByOrden (Long idPeticion, Integer orden) {
//		List actList = new ArrayList();
//		Map actMap = new HashMap();
//		PeticionFlujoDTO peflDto = null;
//		
//		try {
//			Peticion_flujoLocalHome pfHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
//			Collection col = pfHome.findActividadesByOrden(idPeticion, orden);
//
//			for ( Iterator iter = col.iterator(); iter.hasNext(); ) {
//				peflDto = new PeticionFlujoDTO();
//				Peticion_flujoLocal pfLocal = (Peticion_flujoLocal)iter.next();
//				Actividad_flujoKey afk= (Actividad_flujoKey) pfLocal.getFk_acti_2_pefl().getPrimaryKey();
//				peflDto.setIdActividad( afk.acti_id );
//				peflDto.setEstado( pfLocal.getPefl_estado() );
//				actList.add(peflDto);
//			}
//		} catch (NamingException e) {
//			log.error("NamingException [" + idPeticion + "," + orden + "]");
//		} catch (FinderException e1) {
//			log.error("FinderException [" + idPeticion + "," + orden + "]");
//		}
//		
//		return actList;
//	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Esta actividad Termina de Inmediato al finalizar su Logica de inicio
		act.setRealizarTerminoInmediato(true);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.actividades.ActividadEJB#ejbCreate()
	 */
	public void ejbCreate() throws CreateException
	{	
		super.ejbCreate();
		try
		{
			ejbPeticionFlujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			ejbActividadHome=(ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
			ejbOperacionComercialHome=(Operacion_comercialLocalHome) HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
			ejbPeticionHome=(PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			ejbActividadFlujoHome=(Actividad_flujoLocalHome)HomeFactory.getHome(Actividad_flujoLocalHome.JNDI_NAME);
			manager=new DBManager();
			manager.setDataSource(DBManager.JDBC_VPISTBBA);
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}						
}