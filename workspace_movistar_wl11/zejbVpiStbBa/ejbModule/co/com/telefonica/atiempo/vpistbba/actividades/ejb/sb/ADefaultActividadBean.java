package co.com.telefonica.atiempo.vpistbba.actividades.ejb.sb;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.Operacion_comercialKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioDelegate;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.DecoModemPeticionLocalHome;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ADefaultActividad
 */

//TODO: La actividad por defecto es una actividad Manual
public class ADefaultActividadBean
	extends ActividadManualEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		try {
//			  Req FTTC se agrega la consulta a la tabla recursos_linea_baqsica para sabers si la peticion contiene campos de tipo FTTC para que se inhiba la actividad
		    Recursos_linea_basicaLocal recursos_linea_basicaLocal = null;
		    Recursos_linea_basicaLocalHome recursos_linea_basicaLocalHome = (Recursos_linea_basicaLocalHome) HomeFactory.getHome( Recursos_linea_basicaLocalHome.JNDI_NAME);
			recursos_linea_basicaLocal = (Recursos_linea_basicaLocal) recursos_linea_basicaLocalHome.findByPeticion(act.getNumeroPeticion());
			//Se realiza la validacion en el campo rec_fttc_asg para saber si es FTTC o no si viene en S significa q hay campos FTTC y se debe inhibir la actividad, si viene N continua normalmente con la activdad
			log.debug("---Comienza Validacion FTTC");
			String asignado="" ;
			
				asignado  = recursos_linea_basicaLocal.getRec_fttc_asg();
			if(asignado == null){
				
				log.debug("--No contiene Equipos FTTC Continua la actividad");
				RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
				AgendaServicioDelegate agendaServicio = new AgendaServicioDelegate();
				TOADelegate toaDelegate = new TOADelegate();
				PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
				PeticionLocal peticionLocal  = peticionLocalHome.findByPrimaryKey(peticionKey);
				LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
			    Peticion_atisLocal peticion_atisLocal = peticionLocal.getFk_01_pet_atis();
			    Peticion_atisKey peticion_atisKey = (Peticion_atisKey)peticion_atisLocal.getPrimaryKey();
			    
				//REQ CAMBIO NUMERO POR TRANSFERENCIA TECNICA @dcardena
				//for para recorrer llos pss y obtener la operacion comercial para validar si es de tipo cambio numero por transferencia tecnica
				int opComercial=0;
				Collection producto_servicio_peticionArray = peticionLocal.getProducto_servicio_peticion();		
				boolean esCambioNumeroTransfTecnica=false;
				for(Iterator iter = producto_servicio_peticionArray.iterator();iter.hasNext();){
					Producto_servicio_peticionLocal producto_servicio_peticionLocal= (Producto_servicio_peticionLocal) iter.next();
					Producto_servicioKey productoServicoKey=(Producto_servicioKey) producto_servicio_peticionLocal.getProducto_servicio().getPrimaryKey();
					Operacion_comercialKey operacion_comercialKey=(Operacion_comercialKey) producto_servicio_peticionLocal.getOperacion_comercial().getPrimaryKey();
					opComercial = operacion_comercialKey.opco_id.intValue();
					//se valida si la operacion comercial es cambio numero por transferencia tecnica
					if(opComercial==ComunInterfaces.OcCambNumTransfTec){
						esCambioNumeroTransfTecnica=true;
						log.debug("la peiicion "+act.getNumeroPeticion()+" es de tipo cambio numero por transferencia tecnica en Desconfigurar STB");
						break;
					}
				}
				//se valida si el boolean esta en true de cambio numero por transferencia tecnica
				if(esCambioNumeroTransfTecnica&& act.getIdActividadFlujo().intValue() == Integer.parseInt(ComunInterfaces.ACT_DESCONFIG_STB)){
					//se ejecuta la funcion psCambioNumeroTransferenciaTecncia la cual obtiene los ps parametricos de cambio numero por transferencia tecnica
					//(psCambioNumeroTransferenciaTecncia(peticionKey.peti_numero.toString());
					act.setObservacion("Se aprovisionarán los siguientes productos PS 2454 - Desvio llamada cambio de numero y PS 101 - Bloqueo, sobre el número anterior");
				}
				//fin REQ CAMBIO NUMERO POR TRANSFERENCIA TECNICA @dcardena
				
				
			    
			    
			    
				boolean esAgendaSc = recursosBADelegate.esAgendaSC( peticion_atisKey.cod_pet_cd );
				if( esAgendaSc && act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_PGACS"))){
					Calendar calendar = Calendar.getInstance();
					Date date = calendar.getTime();
					Timestamp currentTimestamp = new Timestamp(date.getTime());
//					recursosBADelegate.creacionActuacionAgendaSC( act.getNumeroPeticion(), currentTimestamp, "esPGACS", act.getCodigoActividad(), act);
					if(localidadLocal.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA)
						toaDelegate.crearActuacionTOA(act);
					else
						agendaServicio.enviarCreacionActuacion( act.getNumeroPeticion(), currentTimestamp, "esPGACS", act.getCodigoActividad(), act);
				}
			}else{
				
			
			if(!asignado.equals("S")){
			
			log.debug("--No contiene Equipos FTTC Continua la actividad");
			RecursosBADelegate recursosBADelegate = new RecursosBADelegate();
			AgendaServicioDelegate agendaServicio = new AgendaServicioDelegate();
			TOADelegate toaDelegate = new TOADelegate();
			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionKey peticionKey = new PeticionKey(act.getNumeroPeticion());
			PeticionLocal peticionLocal  = peticionLocalHome.findByPrimaryKey(peticionKey);
			LocalidadLocal localidadLocal = peticionLocal.getFk_01_localidad();
		    Peticion_atisLocal peticion_atisLocal = peticionLocal.getFk_01_pet_atis();
		    Peticion_atisKey peticion_atisKey = (Peticion_atisKey)peticion_atisLocal.getPrimaryKey();
		    
			boolean esAgendaSc = recursosBADelegate.esAgendaSC( peticion_atisKey.cod_pet_cd );
			if( esAgendaSc && act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_PGACS"))){
				Calendar calendar = Calendar.getInstance();
				Date date = calendar.getTime();
				Timestamp currentTimestamp = new Timestamp(date.getTime());
//				recursosBADelegate.creacionActuacionAgendaSC( act.getNumeroPeticion(), currentTimestamp, "esPGACS", act.getCodigoActividad(), act);
				if(localidadLocal.getLocalidad_toa().intValue() == ComunInterfaces.LOCALIDAD_TOA)
					toaDelegate.crearActuacionTOA(act);
				else
					agendaServicio.enviarCreacionActuacion( act.getNumeroPeticion(), currentTimestamp, "esPGACS", act.getCodigoActividad(), act);
			}	
		}else{
			if(!act.getCodigoActividad().equals("Director de Flujos.PGI.Solucion_y_Soporte_Tecnica") 
					&& !act.getCodigoActividad().equals("Director de Flujos.PGC.Pendiente_Solucion_Comercial")
					&& !act.getCodigoActividad().equals("Director de Flujos.PGF.Pendiente_Gestion_Fraude")
					&& !act.getCodigoActividad().equals("Director de Flujos.Instalacion.Control_Instalacion")
					&& !act.getCodigoActividad().equals(ComunInterfaces.ACT_CTRL_INSTALACION_TOA)// REQ TOA FASE III
					&& act.getIdActividadFlujo().intValue() != Integer.parseInt(ComunInterfaces.ACT_CONFIG_STB)
					&& act.getIdActividadFlujo().intValue() != Integer.parseInt(ComunInterfaces.ACT_DESCONFIG_STB))
			{				
			log.debug("---Contiene Equipos FTTC, se inhibe la actividad");
			act.setObservacion("Se inhibe la actividad por que existe configuracion para FTTC");					
			act.setRealizarTerminoInmediato(true);	
		
			}
			if(act.getCodigoActividad().equals(ComunInterfaces.ACT_CONTROL_DESINSTALAR_CRE)|| act.getCodigoActividad().equals(ComunInterfaces.ACT_CONTROL__DESINSTALAR_MTZ)){
				DecoModemPeticionLocalHome home = (DecoModemPeticionLocalHome) HomeFactory.getHome(DecoModemPeticionLocalHome.JNDI_NAME);
				Collection local = home.findByPeticion(act.getNumeroPeticion().toString());
				for(Iterator listaPet = local.iterator();listaPet.hasNext();){
					DecoModemPeticionLocal petLocal = (DecoModemPeticionLocal) listaPet.next();
					petLocal.setId_act(new Long(act.getIdActividadFlujo().intValue()));
					petLocal.setEstadopet(new Long(1));
				}
			}
		}
		}
		}catch (ATiempoAppEx e) {
			log.debug("Error al crear el delegate en ADefaultActividadBean ");
		} catch (NamingException ne) {
			log.debug("Error Naming e");
		} catch (FinderException e) {
			log.debug("Errro FinderException ");
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
		// adocarmo - 02-09-09 - inicio	
		// Si desde PGI estoy derivando a PGC seteo la variable PGI_OK al valor por defecto
		if (act.getCodigoActividad().equals(VpistbbaConfig.getVariable("COD_ACTIVIDAD_PGI")) && act.getDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza).equals(VpistbbaConfig.getVariable("IDPGC"))) {		
			act.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.PGI.pgi_ok,"X");
			log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SETEO PGI_OK CON X");
		}
		// adocarmo - 02-09-09 - fin
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadManualEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}
}