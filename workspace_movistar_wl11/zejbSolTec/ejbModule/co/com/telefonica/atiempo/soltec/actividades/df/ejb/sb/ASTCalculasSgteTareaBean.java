package co.com.telefonica.atiempo.soltec.actividades.df.ejb.sb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocalHome;
import co.com.telefonica.atiempo.soltec.dto.PeticionFlujoSTDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Actividad_flujoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Operacion_comercial_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.singleton.PropertiesActividadesST;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ASTCalculasSgteTarea
 */
public class ASTCalculasSgteTareaBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB{

		private Peticion_flujoLocalHome ejbPeticionFlujoHome = null;
		private ActividadLocalHome ejbActividadHome=null;
		private Actividad_flujoLocalHome ejbActividadFlujoHome=null;
		private Operacion_comercial_stLocalHome ejbOperacionComercialHome=null;
		private Peticion_stLocalHome ejbPeticionHome=null;
		private DBManager manager;	
	 
	
		public void ejbCreate() throws CreateException
		{	
			super.ejbCreate();
			try
			{
				ejbPeticionFlujoHome=(Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				ejbActividadHome=(ActividadLocalHome) HomeFactory.getHome(ActividadLocalHome.JNDI_NAME);
				ejbOperacionComercialHome=(Operacion_comercial_stLocalHome) HomeFactory.getHome(Operacion_comercial_stLocalHome.JNDI_NAME);
				ejbPeticionHome=(Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				ejbActividadFlujoHome=(Actividad_flujoLocalHome)HomeFactory.getHome(Actividad_flujoLocalHome.JNDI_NAME);
				manager=new DBManager();
				manager.setDataSource(DBManager.JDBC_VPISTBBA);
			} catch (NamingException e)
			{
				e.printStackTrace();
				throw new CreateException(e.getMessage());
			}
		}
		
		private void inicializa(ActividadEJBDTO act) {

			Map datos = PropertiesActividadesST.getProperties();

			for (Iterator iterator = datos.keySet().iterator(); iterator.hasNext();) {
				String element = (String) iterator.next();
				act.setDato((String) datos.get(element), "N");
			}
			if (!act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion) || !act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion).equals("S")){
				act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "N");	
			}
			act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "N");
		
		

		}	
	
		private void setDatosActividades(ActividadEJBDTO act,List listaDatos) {

			for (int i = 0; i < listaDatos.size(); i++) {
				String dato = listaDatos.get(i).toString();
				String llave = PropertiesActividadesST.getVariable(dato);
				//act.setDato(llave, "S");
				this.setVariableActividad( Integer.parseInt(dato), act, llave);
			}
		}
	
//		Metodo encargado de Setear el valor para que pase a la siguiente actividad el valor del dato se saca del properties actividadesVpiStbBaWF lo que se carga en act.setDato(llave, "ConfSI") es el valor del WF
		  //para no romper el resto de  las peticiones por defecto pone S
		  private void setVariableActividad(int dato,ActividadEJBDTO act, String llave) {

				  switch (dato) {
					  case 10 :
						  act.setDato(llave, "RecuInfSI");
						  break;
					  case 11 :
						  act.setDato(llave, "ObtCorreoSI");						  
						  break;
					  case 12 :
						  act.setDato(llave, "RecuInfEquipo");					  
						  break;
					  case 13 :
						  act.setDato(llave, "ActInvEquipo");					  
						  break;
						  //David, req 1235 Asistencia ST
					  case 14 :
						  act.setDato(llave, "ACST");					  
						  break;
						  //Raúl: Venta de equipos, req 3947
					  case 15 :
						  act.setDato(llave, "VE");					  
						  break;
						  //Raúl: AVerias masivas
					  case 16 :
					  	  act.setDato(llave, "AM");
					  	break;
					  /*mfmendez - RQ-SAP*/
					  case 17 :					  	 
					  	  act.setDato(llave, "Vista_Global_ST");
					  	  break;
					  case 18 :					  	 
					  	  act.setDato(llave, "Notificacion_SAP_ST");
					  	  break;
					  /*FIN mfmendez - RQ-SAP*/
					  case 19 :					  	 
					  	  act.setDato(llave, "IncidenciaAplicateca");
					  	  break;
					  case 20 :					  	 
					  	  act.setDato(llave, "Conf_Presencia_Digital_ST");
					  	  break;
					  	  
					  //@idrincon req 5935_ajuste_flujoit_apcsc
					  //case 16:
					  //	  act.setDato(llave, "SOL_REC_ST");
					  //	  break;
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
					Actividad_flujoKey afk= (Actividad_flujoKey) tarea.getActividad_flujo().getPrimaryKey();
					datos.add(afk.acti_id);	
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
						Actividad_flujoKey afk= (Actividad_flujoKey) tarea.getActividad_flujo().getPrimaryKey();
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
	
		private List getActividadesByOrden (Long idPeticion, Integer orden) {
			List actList = new ArrayList();
			Map actMap = new HashMap();
			PeticionFlujoSTDTO peflDto = null;
		
			try {
				Peticion_flujoLocalHome pfHome = (Peticion_flujoLocalHome) HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
				Collection col = pfHome.findActividadesByOrden(idPeticion, orden);

				for ( Iterator iter = col.iterator(); iter.hasNext(); ) {
					peflDto = new PeticionFlujoSTDTO();
					Peticion_flujoLocal pfLocal = (Peticion_flujoLocal)iter.next();
					Actividad_flujoKey afk= (Actividad_flujoKey) pfLocal.getActividad_flujo().getPrimaryKey();
					peflDto.setIdActividad( afk.acti_id );
					peflDto.setEstado( pfLocal.getPefl_estado() );
					actList.add(peflDto);
				}
			} catch (NamingException e) {
				log.error("NamingException [" + idPeticion + "," + orden + "]");
			} catch (FinderException e1) {
				log.error("FinderException [" + idPeticion + "," + orden + "]");
			}
		
			return actList;
		}		
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Esta actividad Termina de Inmediato al finalizar su Logica de inicio
		act.setRealizarTerminoInmediato(true);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("INICIO AUTOMATICO, CalcularSgteTarea ST");

		//inicializo los subflujos a "N"
		this.inicializa(act);

		//Identifica las siguientes tareas a realizar por la peticion

		try {
//			Cuando se genera el Flujo en la actividad GenerarFlujoPeticion, se pobla la tabla PeticionFlujo con las actividades_flujo que debe hacer
//			Además de agregar las actividades, les asocia el orden. Así sabe cual o cuales(actividades paralelas) debe hacer. El orden de actividad_flujo
//			en que va lo guarda en una variable del Workflow. La variable FLUJ_ORDEN.
			Integer ordenFlujo;
			try {
				ordenFlujo = new Integer(act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden));
				
				boolean isWF = this.validarSiguienteTarea(act, new Integer (ordenFlujo.intValue()+1));
				//No se envìa a WF
				if(!isWF)
					return;
			} catch (NumberFormatException e) {
				//TODO: ¿porque el orden flujo e inicializa en -1?
				ordenFlujo = new Integer(-1);
			}
			

			List listaDatos = null;
			List listaMismoOrden = null;
			List listaPendientes = new ArrayList();
			
			//No Hay PGI  ni quiebres

			if (!act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa) || act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_reversa).equals("")) {

				if (!act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza) || act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals("")) {
					/*  Viene alguna actividad con ESTADO=3 a pesar de venir FLUJ_ACT_SIG_FORZA<>18 o 19
					 *  ("Alguna actividad" digo: para casos de paralelismo)
					 *  Es para enviar a PGI o forzar a una actividad. Esto no va por ahora en ST pero se mantiene en caso de que a futuro se incorpore.
					 */
					
					if ( log.isDebugEnabled() )
						log.debug("ORDEN EN EL QUE ESTOY =>"+ordenFlujo);

					boolean envioPgi = false;
//					boolean actPend = false;
					PeticionFlujoSTDTO peflDto = null;
					listaMismoOrden = this.getActividadesByOrden(act.getNumeroPeticion(), ordenFlujo);
					
					// Reviso todas las del Orden Actual...
					// 1.- Puede haber 1 en NULL, entonces se "saltó" y se vuelve a hacer.
					// 2.- Pudo ser enviada a PGI, caso del Paralelismo. 
					for (int i=0;i<listaMismoOrden.size();i++) {
						peflDto = (PeticionFlujoSTDTO)listaMismoOrden.get(i);
						if ( peflDto == null )
							continue;
						if ( peflDto.getEstado() == null ) { // Esta Pendiente
					
						}

					}

//				
					if (envioPgi) {
//						En ST no hay PGI.. todavia						
						if ( log.isDebugEnabled() )
							log.debug("Envío a PGI. FLUJ_ACT_SIG_FORZA no viene seteado, pero Estado es ["+peflDto.getEstado()+"]");
					
					} else {
						ordenFlujo = new Integer( ordenFlujo.intValue()+1 );
						if ( log.isDebugEnabled() )
							log.debug("No viene el flujo en reversa y no fuerzan a la siguiente tarea [" + ordenFlujo + "]");
	
						act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
	
						listaDatos = this.getDatosPeticion(act.getNumeroPeticion(), ordenFlujo);
	
						if (listaDatos.size() == 0) {
							//YA NO QUEDAN MAS TAREAS
							act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "N");
							act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
						} else {
							this.setDatosActividades(act,listaDatos);
						}
					}
				} else {
					//SE VA A UNA ACTIVIDAD EN FORMA FORZOSA
					if ( log.isDebugEnabled() )
						log.debug("No viene el flujo en reversa, pero se forzara a la siguiente tarea");

					//Raúl Triviño: Req 5935_Ajuste_Flujo_IT_Cons_Rec_APSC					
					if (!act.containsKeyDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_REC_ST.solucion_rec_st) 
							|| !("SOL_REC_ST").equals(act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_REC_ST.solucion_rec_st))){
						act.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_REC_ST.solucion_rec_st,"SOL_REC_ST");
					}else{
						log.debug("La variable FLUJ_ACT_SIG_FORZA tiene valor:" + act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza));
					
						if (ordenFlujo.intValue() > 0){
							ordenFlujo = new Integer(ordenFlujo.intValue() - 1);
						}
						
						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
						
						listaDatos = this.getDatosPeticion(act.getNumeroPeticion(), ordenFlujo);
						this.setDatosActividades(act,listaDatos);

						act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, "");
						act.setDato( DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.SOLUCION_REC_ST.solucion_rec_st,"");
					}
					//End 
				}
			} else {
				//TODO VMM 06-03-2007 Revisar Funcionamiento de Reversa				
				if (act.getDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.primera_reversa).equals("")) {
					ordenFlujo = new Integer(0);
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.primera_reversa, "N");
				}
				
				listaDatos = this.getDatosPeticionReversa(act.getNumeroPeticion(), ordenFlujo);
				
				if (listaDatos.size() != 0) {
					if ( (log.isDebugEnabled() ))
						log.debug("Existen Actividades en Reversa: "+listaDatos.size());
					this.setDatosActividades(act,listaDatos);
					ordenFlujo = new Integer(ordenFlujo.intValue() + 1);
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_orden, ordenFlujo.toString());
				} else {
					//YA NO SE PUEDE RETROCEDER MAS
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "N");
					act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_mult_cierre_peticion, "S");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			act.setDato(DATOS_ATSTSTBBA.DIRECTOR_DE_FLUJOS.fluj_cierre, "S");
			
			throw new TnProcesoExcepcion(
				e.getClass().getName()
					+ " en : "
					+ this.getClass().getName()
					+ ".onInicioAutomatico()"
					+ e.getMessage());
		}

		log.debug("FIN INICIO AUTOMATICO, CalcularSgteTarea ST");		

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
			peticionFlujoLocalHome = (Peticion_flujoLocalHome)HomeFactory.getHome(Peticion_flujoLocalHome.JNDI_NAME);
			Collection peticionFlujoList = peticionFlujoLocalHome.findActividadesByOrden(act.getNumeroPeticion(), ordenFlujo);
			
			for (Iterator peticionFlujoIter = peticionFlujoList.iterator(); peticionFlujoIter.hasNext();){
    			Peticion_flujoLocal peticionFlujoLocal = (Peticion_flujoLocal)peticionFlujoIter.next();
    			log.debug("Se valida la descripciòn con : "+peticionFlujoLocal.getActividad_flujo().getActi_descripcion());
    			//Este proceso es temporal
    			if((peticionFlujoLocal.getActividad_flujo().getActi_descripcion().equals(ComunInterfaces.ACT_MOVISTAR_PLAY)
    					|| peticionFlujoLocal.getActividad_flujo().getActi_descripcion().equals(ComunInterfaces.ACT_INSTALAR_FLUJO)
						|| peticionFlujoLocal.getActividad_flujo().getActi_descripcion().equals(ComunInterfaces.ACT_INSTALAR_TOA)
    					&&(peticionFlujoLocal.getPefl_estado()== null || !(peticionFlujoLocal.getPefl_estado().equals("OK"))))){
    				act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.primer_quiebre,"N");
    				new ObtenerSgteTarea(act, ordenFlujo);
    				act.setRealizarTerminoInmediato(false);
    				return false;
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

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}

}
