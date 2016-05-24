package co.com.telefonica.atiempo.vpistbba.actividades.df.configuracion.ejb.naked.sb;

import java.util.Collection;
import java.util.Iterator;

import co.com.atiempo.dto.ModemVpiDTO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.ModemKey;
import co.com.telefonica.atiempo.ejb.eb.ModemLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.ACSServicioDelegate;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: AConfigurarACS
 */
public class AConfigurarACSBean  extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB{

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		try{
		//se instancia la el home del bean de entidad peticion
		PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		PeticionLocal  peticionLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(act.getNumeroPeticion()));
		//instancio el nuevo bean de acs
		ACSServicioDelegate aCSServicioDelegate = new ACSServicioDelegate();
		ModemVpiDTO modemVpiDTO =new ModemVpiDTO();
		//obtengo el modem de la peticion del cliente
		Collection modems = peticionLocal.getModem();
		//se valida si hay algun modem asociado si no hay inhibo la activdad
		if (modems.size() ==0){
			log.debug("INFO: No existen Modem Asociados a la Peticion "+act.getNumeroPeticion());
			log.debug("se inhibe la actividad ya que no hay modems Asociados a la peticion "+act.getNumeroPeticion());
			act.setObservacion("se inhibe la actividad ya que no hay modems Asociados a la peticion");					
			act.setRealizarTerminoInmediato(true);
			return;
		}
	//	se recorre el arreglo de modems
		boolean informaACS = true;
		for(Iterator iter = modems.iterator();iter.hasNext();){
			//almaceno el modem en un local para obtener los datos
			ModemLocal modemLocal = (ModemLocal) iter.next();
			//valida si el modem ya fue autoconfiguardo en instalar para inhibir la actividad
			if(modemLocal.getAccion().intValue() == ComunInterfaces.accionModemConfigurado){
				//se inhibe la activada
				log.debug("se inhibe la actividad por que el modem ya fue Autoconfigurado"+act.getNumeroPeticion());
				act.setObservacion("se inhibe la actividad por que el modem ya fue Autoconfigurado");
				act.setRealizarTerminoInmediato(true);
				return;
			}else{
				if(modemLocal.getAccion().intValue() == ComunInterfaces.accionModemNoConfigurado){
					modemVpiDTO = CargarInfoModem(modemLocal,act);
					// solucion de id actividad naked
					aCSServicioDelegate.enviarAutoConfiguracion(modemVpiDTO,act.getCodigoActividad().toString(),"");
				}
			}
		}
		}catch(Exception e){
			log.debug("AConfigurarACSBean.onInicioActividadVPI: " + e);
		}
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		// TODO Apéndice de método generado automáticamente
		
	}
	private ModemVpiDTO CargarInfoModem(ModemLocal modem,ActividadEJBDTO act){
		ModemVpiDTO modemDTO = new ModemVpiDTO();
		try{
	    modemDTO.setPeti_numero(act.getNumeroPeticion());
		modemDTO.setMarca(modem.getModem_marca());
		ModemKey modemKey = (ModemKey) modem.getPrimaryKey();
		modemDTO.setSerial(modemKey.serial.toString());
		
		//Se adiciona este cambio para tener en cuenta el codigo de material y el modelo del modem
		modemDTO.setModelo(modem.getModelo());
		modemDTO.setCodMaterial(modem.getCodigo_material());
			modemDTO.setTipo(modem.getTipo().shortValue());
		InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
		InformacionTecnicaDTO informacionTecnicaDTO = infoComunColombiaBI.obtenerInformacionTecnica(act.getNumeroPeticion());
		Long telefono = null;
		
		if (informacionTecnicaDTO.LineaNueva.getTelefono()!= null && informacionTecnicaDTO.LineaNueva.getTelefono().length()>0){
			modemDTO.setTelefono(new Long(informacionTecnicaDTO.LineaNueva.getTelefono()));
		}else{
			modemDTO.setTelefono(new Long("0"));
		}
		
		modemDTO.setAccion(new Integer(ComunInterfaces.accionModemNoAction).shortValue());
		
		
		/*RQ.8595 - mfmendez*/
		modemDTO.setPostingDateSAP(modem.getFec_cont_sap());
		modemDTO.setMoveTypeSAP(modem.getClase_mov_sap());
		modemDTO.setMaterialCodeSAP(Integer.toString(modem.getPos_doc_sap()));
		modemDTO.setMaterialSAP(modem.getNum_material_sap());
		modemDTO.setPlantSAP(modem.getCentro_sap());
		modemDTO.setStorageSAP(modem.getAlmacen_sap());
		modemDTO.setBatchCodeSAP(modem.getCod_lote_sap());
		modemDTO.setMeasurementUnitSAP(modem.getUnd_medida_sap());
		modemDTO.setCostCenterSAP(modem.getCentr_cost_sap());
		modemDTO.setFuncAreaLongSAP(modem.getArea_func_sap());
		modemDTO.setPepElementSAP(modem.getElement_pep_sap());
		modemDTO.setFlagMatSAP(modem.getFlag_mat_sap());
		
	}catch(Exception e){
		log.debug("AConfigurarACSBean.CargarInfoModem: " + e);
	}
	return modemDTO;
}
}