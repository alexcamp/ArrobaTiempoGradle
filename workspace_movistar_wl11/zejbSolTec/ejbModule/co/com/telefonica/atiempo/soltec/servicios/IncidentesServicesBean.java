package co.com.telefonica.atiempo.soltec.servicios;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.CentralKey;
import co.com.telefonica.atiempo.ejb.eb.CentralLocal;
import co.com.telefonica.atiempo.ejb.eb.CentralLocalHome;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocal;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocal;
import co.com.telefonica.atiempo.ejb.eb.Properties_BDLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR005E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR005S;
import co.com.telefonica.atiempo.soltec.actividades.factory.ActividadFactorySTEJB;
import co.com.telefonica.atiempo.soltec.dto.CierrePeticionDTO;
import co.com.telefonica.atiempo.soltec.dto.Peticion_stDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierreLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierre_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_cierre_peticionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.ControlvisitaLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Operacion_comercial_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Operacion_comercial_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Operacion_comercial_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stLocalHome;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.soltec.mensajeria.salida.CierreIncidenteATISMQ;
import co.com.telefonica.atiempo.soltec.reglas.ReglasDelegate;
import co.com.telefonica.atiempo.soltec.reglas.ReglasInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.STConfig;
import com.telefonica_chile.atiempo.utiles.Utiles;
import com.telefonica_chile.bandeja.ejbutiles.UtilesWeb;
import com.telefonica_chile.comun.actividad.dto.ActividadDTO;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocal;
import com.telefonica_chile.comun.actividad.session.ActividadSessionLocalHome;

/**
 * Bean implementation class for Enterprise Bean: IncidentesServices
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class IncidentesServicesBean extends SessionBeanAdapter implements SessionBean,IncidentesInterfaces {

	private Logger log=LoggerFactory.getLogger(IncidentesServicesBean.class);
	//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
//	private Peticion_stLocalHome incidenteATISHome;
//	private Codigo_cierreLocalHome codigoCierreLocalHome;
//	private LocalidadLocalHome localidadLocalHome;
//	private DepartamentoLocalHome departamentoLocalHome;
//	private Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome;
//	private Producto_servicio_stLocalHome producto_servicio_stLocalHome;
//	private Operacion_comercial_stLocalHome operacion_comercial_stLocalHome;
//	private Producto_servicioLocalHome producto_servicioHome;
//	private ControlvisitaLocalHome controlvisitaHome;
//	private Tecnico_peticionLocalHome tecnicoPeticionHome;

	private DBManager dbSeq ;
	

	public IncidentesServicesBean() throws ATiempoAppEx
	{
	}
	
	public void setSessionContext (SessionContext ctx)
		throws EJBException, RemoteException
		{
			super.setSessionContext (ctx) ;
			// Creacion de DataSource
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC);
			buscaHome();
		}
		
	/*
	 * Metodo Generador de Home
	 */
	private void buscaHome (){
//	//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
//		try {
//	
//		//	Creacion de los Home
//		incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
//
//		localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
//		departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
//
//		codigoCierreLocalHome=(Codigo_cierreLocalHome)HomeFactory.getHome(Codigo_cierreLocalHome.JNDI_NAME);
//
//		producto_servicio_peticionLocalHome=(Producto_servicio_peticionLocalHome)HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
//		producto_servicio_stLocalHome=(Producto_servicio_stLocalHome)HomeFactory.getHome(Producto_servicio_stLocalHome.JNDI_NAME);
//		
//		operacion_comercial_stLocalHome=(Operacion_comercial_stLocalHome)HomeFactory.getHome(Operacion_comercial_stLocalHome.JNDI_NAME);
//		
//		producto_servicioHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
//		
//		controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
//		
//		} catch (NamingException e) {
//			log.error(" Creacion de Local Home Nulls",e);
//		}
		}


	/**
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.business.PeticionesServicesBusinessInterface#obtenerPeticionATIS(java.lang.String)
	 */
//	public TR005S obtenerIncidenteATIS(Long numeroIncidenteAtis) throws ATiempoAppEx
//	{
//		try
//		{
//			Peticion_stLocal incidenteAtis = incidenteATISHome.findByPrimaryKey( new Peticion_stKey(numeroIncidenteAtis) );
//			// convierto del entity a un DTO
//			TR005S tr005s = IncidenteATISConvertidor.getDTO(incidenteAtis);
//			return tr005s;
//		} catch (FinderException e) {
//			log.error("FinderException. " + e.getMessage(), e);
//			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
//		}
//	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.business.PeticionesServicesBusinessInterface#obtenerPeticionATIS(java.lang.String)
	 */
	public boolean existeIncidenteATIS(Long numeroIncidente) throws ATiempoAppEx {

		try {
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticionAtis = incidenteATISHome.findByPrimaryKey(new Peticion_stKey(numeroIncidente));
			return true;
		} catch (FinderException e) {
			return false;
		}catch (NamingException e)
		{
			log.error(" Creacion de Local Home Nulls",e);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
		}

	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.peticiones.business.PeticionesServicesBusinessInterface#salvarPeticionATIS(co.com.telefonica.atiempo.vpistbba.dto.PeticionAtisDTO)
	 */
	public Peticion_stLocal salvarIncidenteATIS(TR005S incidenteAtisDTO, boolean masiva) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			log.debug("Procesando Ingreso de Incidente: "+incidenteAtisDTO.getBreakdownNumber());
			Peticion_stLocal incidente=(Peticion_stLocal)incidenteATISHome.create(new Long(incidenteAtisDTO.getBreakdownNumber()),new Integer (1));			

			this.salvarIncidenteATiempo(incidente,incidenteAtisDTO, masiva);
			
			Long codavecd=((Peticion_stKey) incidente.getPrimaryKey()).cod_ave_cd;
			log.debug(""+codavecd+" Ok");

			
			//TODO parametrizar correctamente el ID de producto_servicio_st
			int psid=1;
			  //String idpro=incidente.getIde_pro_cmr_cd();
			Long idPs = incidente.getCod_pro_ser_cd();
			  
			log.debug("Prod_Serv:" + idPs);
			Producto_servicioLocalHome producto_servicioLocalHome = (Producto_servicioLocalHome) HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
			Producto_servicioLocal producto_servicioLocal = producto_servicioLocalHome.findByPrimaryKey(new Producto_servicioKey(idPs));
			  
			Familia_producto_servicioKey familiProKey = (Familia_producto_servicioKey) producto_servicioLocal.getFamilia_producto_servicio().getPrimaryKey();
			log.debug("Validacion de Registros de Incidentes de PS");
			if (familiProKey.faps_id.intValue() == familiaPcLinea || familiProKey.faps_id.intValue() == familiaIP || familiProKey.faps_id.intValue() == familiaLinea){
				psid = 1;
				incidente.setIde_pro_cmr_cd("L"); //Truco para saber bien las familias en las pantallas
				log.debug("Obtencion de PS de Linea");
//				TODO PVR validar se agrego  familia
			}else if(familiProKey.faps_id.intValue() == familiaPcTV || familiProKey.faps_id.intValue() == familiaTV || familiProKey.faps_id.intValue() == familiaDecoTV 
					|| familiProKey.faps_id.intValue() == familiaDecoPVRTV || familiProKey.faps_id.intValue() == familiaTematicoTV || familiProKey.faps_id.intValue() == familiaPaqueteTV
					|| familiProKey.faps_id.intValue() ==  familiaDecoHDTV){
				psid = 3;
				incidente.setIde_pro_cmr_cd("TV");//Truco para saber bien las familias en las pantallas
				log.debug("Obtencion de PS de TV");
				//REQ BA NAKED adicion familia PC y PS naked
			}else if(familiProKey.faps_id.intValue() == familiaPcPsBANaked || familiProKey.faps_id.intValue() == familiaBandaAnchaNaked || familiProKey.faps_id.intValue() == familiaPcBANaked || familiProKey.faps_id.intValue() == familiaPcBA || familiProKey.faps_id.intValue() == familiaBandaAncha || familiProKey.faps_id.intValue() == familiaIC){
				psid = 2;
				incidente.setIde_pro_cmr_cd("BA");//Truco para saber bien las familias en las pantallas
				log.debug("Obtencion de PS de  Banda Ancha");
			}
			//David, req 1235 Asistencia ST
			else if(familiProKey.faps_id.intValue() == familiaAsistencia){
				psid = idPs.intValue();
				incidente.setIde_pro_cmr_cd("AS");//Truco para saber bien las familias en las pantallas
				log.debug("Obtencion de PS de  Asistencia");
			}
			//	Fin: David, req 1235 Asistencia ST
			//Ra�l: req incidencias ventas de equipos	
			else if(familiProKey.faps_id.intValue() == familiaIntEquipado){
				psid = 4;
				incidente.setIde_pro_cmr_cd("IT");
				log.debug("Obtencion de PS de  Venta de equipos");
			}
			else if(familiProKey.faps_id.intValue() == familiaAplicateca){
				psid = 5;
				incidente.setIde_pro_cmr_cd("AP");
				log.debug("Obtencion de PS de Aplicateca");
			}else if(familiProKey.faps_id.intValue() == familiaPresenciaDigital){
				psid = 6;
				incidente.setIde_pro_cmr_cd("PD");
				log.debug("Obtencion de PS de Aplicateca");
			}
			else{
				throw new ATiempoAppEx("Error : En el Registro de Incidente, el PS no tiene Familia Asignada");
				
			}
		  
			//creamos producto_servicio_peticion
			log.debug("buscamos producto_servicio_peticion");
			Operacion_comercial_stLocalHome operacion_comercial_stLocalHome=(Operacion_comercial_stLocalHome)HomeFactory.getHome(Operacion_comercial_stLocalHome.JNDI_NAME);
			//TODO parametrizar "1": Operacion comercial
			Operacion_comercial_stLocal ocLocal=(Operacion_comercial_stLocal)operacion_comercial_stLocalHome.findByPrimaryKey(new Operacion_comercial_stKey(new Long(1)));
			
			log.debug("Operacion comercial: "+ ocLocal.getOpco_nombre());
			
			Producto_servicio_stLocalHome producto_servicio_stLocalHome=(Producto_servicio_stLocalHome)HomeFactory.getHome(Producto_servicio_stLocalHome.JNDI_NAME);
			Producto_servicio_stLocal psLocal=(Producto_servicio_stLocal)producto_servicio_stLocalHome.findByPrimaryKey(new Producto_servicio_stKey(new Long(psid)));
			
			log.debug("buscamos producto_st "+psLocal.getPs_nombre());
			
			log.debug("Creamos producto servicio peticion ");

			Long correlativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_PRODUCTO_SERVICIO_PETICION"));

			Producto_servicio_peticionLocalHome producto_servicio_peticionLocalHome=(Producto_servicio_peticionLocalHome)HomeFactory.getHome(Producto_servicio_peticionLocalHome.JNDI_NAME);
			Producto_servicio_peticionLocal pspLocal=(Producto_servicio_peticionLocal)producto_servicio_peticionLocalHome.create(codavecd,correlativo,ocLocal,psLocal);
			
			return incidente;
			
		}catch (CreateException e)
		{
			log.error("CreateException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, e);
		} catch (FinderException e)
		{
			log.error("FinderException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
		} catch (NamingException e)
		{
			log.error("NamingException:",e);
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, e);
		} catch (Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}			
	}
	

	/**
	 * @param superPeticionDto
	 */
	private void salvarIncidenteATiempo(Peticion_stLocal incidenteLocal, TR005S incidenteAtisDTO, boolean masiva) throws CreateException, FinderException, NamingException, ATiempoAppEx
	{

		incidenteLocal.setCod_cli_cd(new Long(incidenteAtisDTO.getClientCode()));
		incidenteLocal.setPro_ser_cto_cd(new Long(incidenteAtisDTO.getContractedProductServiceCode()));
		incidenteLocal.setIde_pro_cmr_cd(incidenteAtisDTO.getComercialProductIdentification());
		log.debug("Num Ide Num:" + incidenteAtisDTO.getComercialProductIdentificationNumber());
		incidenteLocal.setNum_ide_nu(incidenteAtisDTO.getComercialProductIdentificationNumber());
		incidenteLocal.setNum_ide_nu_tv(incidenteAtisDTO.getComercialProductIdentificationNumberTV());
		incidenteLocal.setCod_apt_ave_cd(incidenteAtisDTO.getBreakdownOpenCode());
		incidenteLocal.setCod_cie_ave_cd(incidenteAtisDTO.getBreakdownCloseCode());
		incidenteLocal.setStm_ave_mas_cd(incidenteAtisDTO.getBreakdownSymptomCode());
		incidenteLocal.setRpt_pru_ave_cd(incidenteAtisDTO.getBreakdownTestResponseCode());
		incidenteLocal.setObs_ave_ds(incidenteAtisDTO.getBreakdownObservation());
		incidenteLocal.setCod_are_ges_cd(new Long(incidenteAtisDTO.getManagementAreaCode()));
		if (!codigoEstadoAveriaTerminada.equals("" + incidenteAtisDTO.getBreakdownStatusCode())){
			incidenteLocal.setCod_est_ave_cd("A");
		}else{
			incidenteLocal.setCod_est_ave_cd(""+incidenteAtisDTO.getBreakdownStatusCode());
		}
		incidenteLocal.setCod_pet_cd(new Long(incidenteAtisDTO.getRequestNumber()));
		incidenteLocal.setCod_ave_mas_cd(new Long(incidenteAtisDTO.getMassiveBreakdownCode()));
		incidenteLocal.setCod_ave_ori_sn(new Long(incidenteAtisDTO.getBreakdownOriginalCode()));
		
		if (incidenteAtisDTO.getClimantDocumentType() != null && incidenteAtisDTO.getClimantDocumentType().length()>0)
			incidenteLocal.setTip_doc_rte_cd(incidenteAtisDTO.getClimantDocumentType());
		else
			incidenteLocal.setTip_doc_rte_cd("CC");
		
		if (incidenteAtisDTO.getClimantDocumentNumber() != null && incidenteAtisDTO.getClimantDocumentNumber().length()>0)
			incidenteLocal.setNum_doc_rte_nu(incidenteAtisDTO.getClimantDocumentNumber());
		else
			incidenteLocal.setNum_doc_rte_nu("0");

		incidenteLocal.setCct_doc_rte_cd(incidenteAtisDTO.getClimantDocumentVerification());
		incidenteLocal.setNom_rte_sn(incidenteAtisDTO.getClimantName());
		incidenteLocal.setPri_ape_rte_sn(incidenteAtisDTO.getClimantFirstLastname());
		incidenteLocal.setSeg_ape_rte_sn(incidenteAtisDTO.getClimantSecondLastname());
		incidenteLocal.setCod_rel_cli_cd(incidenteAtisDTO.getClientRelationshipCode());
		incidenteLocal.setRel_cli_otr_ds(incidenteAtisDTO.getClimantRelationshipOtherDescription());
		incidenteLocal.setFrm_frm_ntf_cd(""+incidenteAtisDTO.getNotificationWayCode());
		incidenteLocal.setNom_psn_cot_no(incidenteAtisDTO.getContactName());
		incidenteLocal.setTel_cot_ds(incidenteAtisDTO.getContactPhone());
		incidenteLocal.setSeg_psn_cot_sn(""+incidenteAtisDTO.getContactName2());
		incidenteLocal.setSeg_tel_cot_sn(""+incidenteAtisDTO.getContactPhone2());
		incidenteLocal.setTip_mdi_cmc_cd(""+incidenteAtisDTO.getCommunicationMediaCode());
		incidenteLocal.setCod_ctz_cd(incidenteAtisDTO.getCategoryCode());
//		incidenteLocal.setCod_pra_ave_cd(""+incidenteAtisDTO.getBreakdownPriorityCode());//TODO Modificar prioridar
		incidenteLocal.setPzo_max_rsl_nu(new Long(incidenteAtisDTO.getMaxResolutionTerm()));
		incidenteLocal.setCan_dia_ala_nu(new Long(incidenteAtisDTO.getDaysToAlarm()));
		incidenteLocal.setInd_env_rpt_in(""+incidenteAtisDTO.getAnswerIndicator());
		incidenteLocal.setInd_cob_vst_in(""+incidenteAtisDTO.getCollectIndicator());
		incidenteLocal.setInd_apz_in(""+incidenteAtisDTO.getDelayIndicator());
		incidenteLocal.setInd_ave_rei_in(""+incidenteAtisDTO.getBreakdownReiterateIndicator());
		incidenteLocal.setInd_dft_emo_in(""+incidenteAtisDTO.getExtremeDefectIndicator());
		incidenteLocal.setAju_flt_ser_in(""+incidenteAtisDTO.getLackOfServiceIndicator());
		incidenteLocal.setInd_imp_mtl_in(""+incidenteAtisDTO.getMaterialAmountIndicator());
		incidenteLocal.setInd_ser_efe_in(""+incidenteAtisDTO.getCompletedServiceIndicator());
		incidenteLocal.setInd_equ_atc_in(""+incidenteAtisDTO.getUpdatedEquipmentIndicator());
		incidenteLocal.setInd_age_cit_in(""+incidenteAtisDTO.getMakeAppoitmentIndicator());
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy"); 
		incidenteLocal.setFec_cit_ff(simpleDateFormat.format(incidenteAtisDTO.getAppoitmentDate().toDate()));
		
		
//		incidenteLocal.setTim_fec_cps_ts(new Timestamp(incidenteAtisDTO.getBreakdownCommitmentDate().getTime()));//Fecha compromiso
		
		
		incidenteLocal.setFec_cie_ave_ts(new Timestamp(incidenteAtisDTO.getBreakdownCloseDate().getTime()));
		incidenteLocal.setNum_seg_lin_ds(incidenteAtisDTO.getSecondLineNumber());
		incidenteLocal.setOri_alt_icd_in(""+incidenteAtisDTO.getBreakdownSource());
		incidenteLocal.setMot_ccl_icd_cd(incidenteAtisDTO.getBreakdownCancelationCode());
		incidenteLocal.setDsc_rpt_pru_ds(incidenteAtisDTO.getBreakdownTestDescription());
		incidenteLocal.setUsr_alt_no(incidenteAtisDTO.getEmittingUser());
		incidenteLocal.setUsr_ult_mod_no(incidenteAtisDTO.getLastModificationUser());
		incidenteLocal.setIte_alt_sis_ts(new Timestamp(incidenteAtisDTO.getEmmitingTime().getTime()));
		incidenteLocal.setTim_ult_mod_ts(new Timestamp(incidenteAtisDTO.getLastModificationTime().getTime()));
		incidenteLocal.setLng_ele_inf_cd(new Integer(incidenteAtisDTO.getInformationElementLength()));
		incidenteLocal.setObs_cit_ds(incidenteAtisDTO.getAppoitmentObservation());
		incidenteLocal.setTip_ave_mas_cd(incidenteAtisDTO.getBreakdownTypeCode());
		incidenteLocal.setStm_ave_mas_cd(incidenteAtisDTO.getMassiveBreakdownSyntomCode());
		incidenteLocal.setCod_pro_ser_cd(new Long(incidenteAtisDTO.getProductServiceCode()));
		incidenteLocal.setEst_ave_mas_cd(incidenteAtisDTO.getMassiveBreakdownStatusCode());
		incidenteLocal.setCps_cje_mas_ff(incidenteAtisDTO.getMassiveBreakdownCommitmentDate());
		//TODO: Leo .. cambio. fecha en base de datos es varchar(10)
		incidenteLocal.setFec_apt_ave_ts(new Timestamp(incidenteAtisDTO.getBreakdownOpenDate().getTime()));
//		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		incidenteLocal.setApt_ave_mas_ff(incidenteAtisDTO.getMassiveBreakdownOpenDate());
		incidenteLocal.setCie_ave_mas_ff(incidenteAtisDTO.getMassiveBreakdownCloseDate());
		incidenteLocal.setExt_ave_mas_ds(incidenteAtisDTO.getMassiveBreakdownExternalCode());
		incidenteLocal.setLng_cpe_inf_sn(new Integer(incidenteAtisDTO.getInformationComponentLengthSynonym()));
		incidenteLocal.setObs_ave_mas_ds(incidenteAtisDTO.getMassiveBreakdownObservation());
		incidenteLocal.setMot_ave_mas_cd(incidenteAtisDTO.getMassiveBreakdownMotive());
		incidenteLocal.setTip_cal_ati_cd(incidenteAtisDTO.getStreetType());
		incidenteLocal.setNom_cal_ds(incidenteAtisDTO.getStreetName());
		incidenteLocal.setNum_cal_nu(incidenteAtisDTO.getStreetNumber());
		incidenteLocal.setDsc_cmp_pri_ds(incidenteAtisDTO.getComplementsDescription1());
		incidenteLocal.setDsc_cmp_seg_ds(incidenteAtisDTO.getComplementsDescription2());
		incidenteLocal.setCoordenada_x(incidenteAtisDTO.getCoordenate_x());
		incidenteLocal.setCoordenada_y(incidenteAtisDTO.getCoordenate_y());
//		incidenteLocal.setCod_dpt(incidenteAtisDTO.getDepartmentCode());
//		incidenteLocal.setCod_loc(incidenteAtisDTO.getCityCode());
		
		LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
		LocalidadLocal localidadLocal=localidadLocalHome.findByPrimaryKey (new LocalidadKey (incidenteAtisDTO.getCityCode ()));
		//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
		DepartamentoLocalHome departamentoLocalHome = (DepartamentoLocalHome) HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
		DepartamentoLocal departamentoLocal=departamentoLocalHome.findByPrimaryKey (new DepartamentoKey (incidenteAtisDTO.getDepartmentCode ()));
		if(localidadLocal==null || departamentoLocal==null)
			throw new ATiempoAppEx("Codigo de Localidad y/o Departamento no v�lidos.Localidad:"+incidenteAtisDTO.getCityCode ()+" -- Departamento:"+incidenteAtisDTO.getDepartmentCode ());
		incidenteLocal.setCod_dpt(incidenteAtisDTO.getDepartmentCode());
		incidenteLocal.setCod_loc(incidenteAtisDTO.getCityCode());
		
//		CR17031 pcawen
		Long segmento = new Long(incidenteAtisDTO.getAccountSegmentCode());
		Long subSegm = new Long(incidenteAtisDTO.getAccountSubsegmentCode());
//		incidenteLocal.setCod_sgm_cta_cd(segmento);
//		incidenteLocal.setCod_sbg_cta_cd(subSegm);
		String producto = "";
		Long tipoLoc = new Long(0);
		//Si no viene el segmento y sub segmento se toma el valor por defecto
		if( !segmento.equals(new Long(0)) && !subSegm.equals(new Long(0))){
			incidenteLocal.setCod_sgm_cta_cd(segmento);
			incidenteLocal.setCod_sbg_cta_cd(subSegm);
			//Calculo prioridad. se ignora la recibida en la TR y se calcula en base a la tabla SLA_ST
			segmento = incidenteLocal.getCod_sgm_cta_cd();
			subSegm = incidenteLocal.getCod_sbg_cta_cd();
			//Obtengo producto
			String familiaInci=incidenteLocal.getIde_pro_cmr_cd();
			if(familiaInci!=null)
			{
				if(familiaInci.equals("L"))
				producto="LB";
				else if(familiaInci.equals("BA"))
				producto="BA";
				else if(familiaInci.equals("TV"))
				producto="TV";
			}
			//Obtengo tipo localidad
			String localidad = incidenteLocal.getCod_loc();
			LocalidadLocalHome locHome = (LocalidadLocalHome) HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
			LocalidadLocal locLocal = locHome.findByPrimaryKey(new LocalidadKey(localidad));
			tipoLoc = locLocal.getTipo_loc();
		}else{
			incidenteLocal.setCod_sgm_cta_cd(null);
			incidenteLocal.setCod_sbg_cta_cd(null);
			segmento = new Long(0);
			subSegm = new Long(0);
			producto = "XX";
		}
		Sla_stLocal slaLocal = null;
		Sla_stLocalHome slaHome = (Sla_stLocalHome) HomeFactory.getHome(Sla_stLocalHome.JNDI_NAME);
		try {
		    slaLocal = slaHome.findByPrimaryKey(new Sla_stKey(segmento, subSegm, producto, tipoLoc));
		} catch (FinderException e) {
			try{
				//Para otros Valores busco SLA por defecto
				slaLocal = slaHome.findByPrimaryKey(new Sla_stKey(new Long(0), new Long(0), "XX", new Long(0)));
			}catch (FinderException ex){
				log.error("No se pudo encontrar la SLA:",ex);
				throw new ATiempoAppEx(ATiempoAppEx.FINDER, e);
			}
		}
		
		if (incidenteAtisDTO.getBreakdownPriorityCode() == 'S'){
			   incidenteLocal.setCod_pra_ave_cd("5");
			}else{
			  incidenteLocal.setCod_pra_ave_cd(""+slaLocal.getPrioridad().toString());
		}
		
		//Calculo fecha de compromiso
		Integer sla1 = slaLocal.getSla1();
		Integer sla2 = slaLocal.getSla2();
		Date fecComp1 = UtilesWeb.agregaHorasFecha(incidenteLocal.getFec_apt_ave_ts(), sla1.intValue());
		//Date fecComp2 = UtilesWeb.agregaHorasFecha(incidenteLocal.getFec_apt_ave_ts(), sla2.intValue());
		incidenteLocal.setTim_fec_cps_ts(new Timestamp(fecComp1.getTime()));
		//Calculo fecha Compromiso

//		cr17031 - Fin
//		LocalidadLocal localidadLocal=localidadLocalHome.findByPrimaryKey (new LocalidadKey (incidenteAtisDTO.getCityCode ()));
//		DepartamentoLocal departamentoLocal=departamentoLocalHome.findByPrimaryKey (new DepartamentoKey (incidenteAtisDTO.getDepartmentCode ()));
//		if(localidadLocal==null || departamentoLocal==null)
//			throw new ATiempoAppEx("Codigo de Localidad y/o Departamento no v�lidos.Localidad:"+incidenteAtisDTO.getCityCode ()+" -- Departamento:"+incidenteAtisDTO.getDepartmentCode ());
//		incidenteLocal.setCod_dpt(incidenteAtisDTO.getDepartmentCode());
//		incidenteLocal.setCod_loc(incidenteAtisDTO.getCityCode());
		
		
		long codpsaveria=masiva?10000000:0;
		codpsaveria+=(incidenteLocal.getCod_ave_ori_sn().longValue()>0)?1000000:0;
		codpsaveria+=(incidenteLocal.getMot_ccl_icd_cd()!=null && !incidenteLocal.getMot_ccl_icd_cd().equals(""))?100000:0;

		incidenteLocal.setCod_ps_averia(new Long(codpsaveria));
		
		//TCS-gquevedo Jun 1, 2009 CR24382 INICIO
		//esto fue agregado para solucionar el problema que ocurrio con las Actividades instanciadas previas 
		//al montaje del nuevo FDL (para compatibilidad "hacia atras")
		short fdlShort = 1;
		incidenteLocal.setFdl_soportado(new Short(fdlShort));
		//TCS-gquevedo Jun 1, 2009 CR24382 FIN
		
	}

	public void cerrarIncidente (Long numeroIncidente) throws ATiempoAppEx
	{
		try
		{
			Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal incidente = incidenteATISHome.findByPrimaryKey (new Peticion_stKey(numeroIncidente)) ;
                
			// cambia su estado
                
			if (codigoEstadoAveriaCerrada.equals(incidente.getCod_est_ave_cd())) {
				log.warn ("Incidente " + numeroIncidente + " ya esta marcado como cerrado") ;
			} else {
				//marco el cierre..
				//Si esta en C o T , da lo mismo, igual se marca el cierre.
				incidente.setCod_est_ave_cd(codigoEstadoAveriaCerrada) ;
				Codigo_cierreLocal codigoCierreLocal=null;
				Codigo_cierre_peticionLocal codigoCierrePeticionLocal=null;
				Timestamp fechaCierre=null;
				String aju_flt_ser_in="N";
				//Si tiene cod cierre es pk se llego una peticion de cierre	o es una peticion de cierre			
				String cod_cie_ave_cd = incidente.getCod_cie_ave_cd();
				if(cod_cie_ave_cd!=null && !"".equals(cod_cie_ave_cd.trim())){
					Codigo_cierreLocalHome codHome= (Codigo_cierreLocalHome)HomeFactory.getHome(Codigo_cierreLocalHome.JNDI_NAME);
					Codigo_cierreKey codK =  new Codigo_cierreKey(cod_cie_ave_cd);
					codigoCierreLocal = codHome.findByPrimaryKey(codK);
					fechaCierre = incidente.getFec_cie_ave_ts();
				}else{//Es una peticion normal que se le agrego una solucion
					//Es obligatorio que tenga un cierre
					codigoCierrePeticionLocal= this.ultimoCierre(numeroIncidente);
//					PCawen - CR23830 - Begin
					if(codigoCierrePeticionLocal != null){
						codigoCierreLocal=codigoCierrePeticionLocal.getCodigo_cierre();//codigoCierreLocalHome.findByPrimaryKey(new Codigo_cierreKey(incidente.getCod_cie_ave_cd()));
						fechaCierre=codigoCierrePeticionLocal.getFecha();
					}else{
						//TODO setear aqucerrarIncidentei codigo cierre por defecto
						fechaCierre=new Timestamp(new Date().getTime());//CR23830
						log.error("No se encontr� codigoCierrePeticionLocal, tomando valores por defecto");
					}
				}
				//culpa del cliente?
				if(codigoCierreLocal!=null){
					if (!codigoCierreLocal.getResp_cliente().equals("S")) {
	
	//					//TODO: VMM Demora del incidente. Pasar a horas la diferencia
						long dms=fechaCierre.getTime()-incidente.getFec_apt_ave_ts().getTime();
						log.debug("Fecha Cierre:" + fechaCierre.getTime());
						log.debug("Fecha Apertura:" + incidente.getFec_apt_ave_ts());
						log.debug("Diferencia en Tpo:" + dms);
	//					//reviso cada uno de las familias de productos para ver si superaron el tope
						Collection col=incidente.getProducto_servicio_peticion();
						for (Iterator iter = col.iterator(); iter.hasNext();) {
							Producto_servicio_peticionLocal pspLocal = (Producto_servicio_peticionLocal) iter.next();
							int tpoHoras=pspLocal.getProducto_servicio_st().getFamilia_producto_servicio_st().getTiempo().intValue();
							long tpoReal=3600000*tpoHoras;
							if (dms>tpoReal) {
								aju_flt_ser_in="S";
							}
						}
					}
				}

				//seteo indicador de ajuste
				incidente.setAju_flt_ser_in(aju_flt_ser_in);
			}                
                
			this.enviaMensajeCierreTecnico (incidente ) ;

		}
		catch (NamingException nex)
		{
			log.error("NamingException:",nex);
			throw new ATiempoAppEx (ATiempoAppEx.NAMING, nex);
		}
		catch (FinderException fex)
		{
			log.error("FinderException:",fex);
			throw new ATiempoAppEx (ATiempoAppEx.FINDER, fex);
		} catch (Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
            
	}

	private Codigo_cierre_peticionLocal ultimoCierre(Long idPeticion) throws ATiempoAppEx{
		Codigo_cierre_peticionLocal codCierre = null;
		try{
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Codigo_cierre_peticionLocalHome codH = (Codigo_cierre_peticionLocalHome)HomeFactory.getHome(Codigo_cierre_peticionLocalHome.JNDI_NAME);
			Collection cods=codH.findByNumeroPeticionIDDesc(idPeticion);
			if (cods != null && cods.size()>0){ //Esta ordenado en forma descendente por lo tanto el primero es el ultimo
				codCierre =(Codigo_cierre_peticionLocal) cods.iterator().next(); 
			}
			return codCierre;
		}catch(Exception e){
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);			
		}
	}
	
	public void cancelarIncidente (Long numeroIncidente) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
 			Peticion_stLocal incidente = incidenteATISHome.findByPrimaryKey (new Peticion_stKey(numeroIncidente)) ;
			// cambia su estado
                
			if (incidente.getCod_est_ave_cd() == null)
				incidente.setCod_est_ave_cd(codigoEstadoAveriaCancelada) ;
			else {
				String estadoActual = incidente.getCod_est_ave_cd() ;
                    
				if (estadoActual.equals(codigoEstadoAveriaAbierta)) {
					incidente.setCod_est_ave_cd (codigoEstadoAveriaCancelada) ;
				}
				else if (estadoActual.equals(codigoEstadoAveriaCancelada))
					log.warn ("Incidente " + numeroIncidente + " ya esta marcado como cancelado") ;
                    
				else if (estadoActual.equals(codigoEstadoAveriaTerminada))
					log.info ("Incidente " + numeroIncidente + " fue terminado") ;

				else if (estadoActual.equals(codigoEstadoAveriaCerrada))
					log.info ("Incidente " + numeroIncidente + " fue cerrado") ;
				else
					incidente.setCod_est_ave_cd (codigoEstadoAveriaCancelada) ;
			}
		}catch (FinderException fex){
			log.error("No existe la Peticion:" + numeroIncidente + " para Cancelar",fex);
			//throw new ATiempoAppEx (ATiempoAppEx.FINDER, fex);
		} catch (Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	public Long terminarIncidente (TR005S incidenteAtis, boolean masiva) throws ATiempoAppEx
	{
		Long retorno = new Long(-1);
		Long numeroIncidente = new Long(incidenteAtis.getBreakdownNumber());
		try
		{
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal incidente = null;
			boolean inyectada = false;
			boolean marcado = false;
			try{
				//si ya existe la peticion le cambio el estado a terminada, sino genero el flujo con cierre de inmediato.
				incidente = incidenteATISHome.findByPrimaryKey (new Peticion_stKey(numeroIncidente)) ;
			}catch (FinderException fex){
				//Genero un flujo con estado terminado
				if(incidenteAtis.getBreakdownCloseCode()==null && incidenteAtis.getBreakdownCloseDate()== null || "".equals(incidenteAtis.getBreakdownCloseCode().trim()))
				{
					log.warn("No hay Codigo o Fecha de cierre. No se ingresa la peticion");
				}
				this.salvarIncidenteATIS(incidenteAtis,masiva);
				incidente = incidenteATISHome.findByPrimaryKey (new Peticion_stKey(numeroIncidente)) ;
				inyectada = true;
				log.debug("Se inyecto la peticion de termino");
				retorno = numeroIncidente;
			}

			if(!inyectada){
				//Para marcar el cierre tiene que venir el codigo de cierre y al fecha de cierre
				if(incidenteAtis.getBreakdownCloseCode()==null && incidenteAtis.getBreakdownCloseDate()== null || "".equals(incidenteAtis.getBreakdownCloseCode().trim()))
				{
					log.warn("No hay Codigo o Fecha de cierre. No se cambia el estado a terminado");
					marcado=false;
				}else{
					if (incidente.getCod_est_ave_cd() == null){
						incidente.setCod_est_ave_cd(codigoEstadoAveriaTerminada) ;
						marcado=true;
					}else {
						String estadoActual = incidente.getCod_est_ave_cd() ;
                    
						if (estadoActual.equals(codigoEstadoAveriaAbierta)) {
							marcado=true;
						}
						else if (estadoActual.equals(codigoEstadoAveriaCancelada))
							log.warn ("Incidente " + numeroIncidente + " ya esta marcado como cancelado") ;
                    
						else if (estadoActual.equals(codigoEstadoAveriaTerminada))
							log.info ("Incidente " + numeroIncidente + " fue terminado") ;

						else if (estadoActual.equals(codigoEstadoAveriaCerrada))
							log.info ("Incidente " + numeroIncidente + " fue cerrado") ;
						else{
							marcado=true;
						}
					}
				}
			}
			if(inyectada || marcado){ // Pongo el cierre y solucion
				Long idAutomatico = new Long (STConfig.getVariable("IDAUTOMATICO"));
				String actividadVerifica = STConfig.getVariable("COD_ACTIVIDAD_VERIFICACION_PETICION");
				//pongo la informacion para el cierre
				incidente.setCod_cie_ave_cd(incidenteAtis.getBreakdownCloseCode().trim());
				incidente.setFec_cie_ave_ts(new Timestamp(incidenteAtis.getBreakdownCloseDate().getTime()));
				Fecha fecCie= new Fecha(incidenteAtis.getBreakdownCloseDate());
				//No se a que actividad asociarle la solucion. Por mientras la de bloqueo.
				this.agregarSolucion(numeroIncidente,actividadVerifica,idAutomatico,incidenteAtis.getBreakdownCloseCode().trim(),fecCie.getFechaconFormato(9)); // Fecha:dd/MM/yyyy HH:mm
			}
			if(marcado){
				//si esta bloqueada, la desbloqueo
				String actividadBloqueo = STConfig.getVariable("COD_ACTIVIDAD_BLOQUEO_PETICION");
				long regla=0;
				ReglasInterfaces rI = new ReglasDelegate();
				regla = rI.getRegla(numeroIncidente);
				//ahora le cambio el estado a T
				incidente.setCod_est_ave_cd (codigoEstadoAveriaTerminada) ;
				if(regla == codigoReglaBloqueo ){
					ActividadFactorySTEJB actividadFactoryEJB = new ActividadFactorySTEJB();
					IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(actividadBloqueo);
					ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(numeroIncidente,actividadBloqueo);
					actDto.setObservacion("Lleg� el mensaje de termino de la petici�n. Se desbloquea y redirige al Cierre");
					actividadEJB.terminar(actDto);
				}
			}
		} catch (Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
		return retorno;
	}
	
	public int reiterarIncidente (TR005S incidenteAtis, boolean masiva) throws ATiempoAppEx
	{
		try
		{
			int resp=0;//no hizo reiteracion
			Long numeroIncidente = new Long (incidenteAtis.getBreakdownNumber());
			Peticion_stLocal incidente = null;
			try{
				Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				incidente = incidenteATISHome.findByPrimaryKey (new Peticion_stKey(numeroIncidente)) ;
				resp=1;//Encontro la original y reiter�
			}catch (FinderException fex){
				log.warn("No existe la Peticion:" + numeroIncidente + " para Reiterar");
				log.debug("Se intentara crear la reiteracion:" + numeroIncidente);
				incidente = this.salvarIncidenteATIS(incidenteAtis,masiva);
				resp=reiteracionGrabaNuevoIncidente;//No existe la original y la creo como nueva.
				//throw new ATiempoAppEx (ATiempoAppEx.FINDER, fex);
			}
			Integer reintento;
			if (incidente.getReintento() != null){
				reintento = incidente.getReintento();
			}else{
				reintento = new Integer(0);
			}
			incidente.setReintento(new Integer(reintento.intValue()+1));
			return resp;
		}catch (Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}

	//CR17031 - adocarmo - 10/10/2008 - inicio
	public boolean modificarCategoria (TR005S incidenteAtis) throws ATiempoAppEx
	{
		try
		{
			Long numeroIncidente = new Long (incidenteAtis.getBreakdownNumber());
			Peticion_stLocal incidente = null;
			boolean modificarCategoria = false;
			String categoryCode = incidenteAtis.getCategoryCode();
			try{
				Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				incidente = incidenteATISHome.findByPrimaryKey (new Peticion_stKey(numeroIncidente)) ;
				modificarCategoria = !categoryCode.equals(incidente.getCod_ctz_cd());

				if (modificarCategoria) {			
					incidente.setCod_ctz_cd(categoryCode);	
				}				
			}catch (FinderException fex){
				log.warn("No existe la Peticion:" + numeroIncidente + " para modificar categoria");
			}      			
			
//			actualizo categoria en bandeja
			log.debug("Actualizando peticion " + numeroIncidente);
			try {
				BintegradaLocalHome biLocalHome = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
				Collection peticiones =	biLocalHome.findByIdAplicacionNroPeticion(new Long(2), numeroIncidente);
				Iterator it = peticiones.iterator();
				while (it.hasNext()){
					BintegradaLocal biLocal = (BintegradaLocal)it.next();
					biLocal.setBi_cod_cat(categoryCode);
				}				
			} catch (Exception e) {
				log.error("Exception en Publicador:",e);
			}
			//******************************
			
			
			return modificarCategoria;
			
		}catch (Exception e)
		{
			log.error("Exception:",e);
			throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION, e);
		}
	}
	//CR17031 - adocarmo - 10/10/2008 - fin
        
		/*
		 * prepara y envia el mensaje de cierre a ATIS
		 *
		 * gracias to TCS, yo parle english
		 *
		 * @author francois
		 */

	public void ActividadManualMarcaCierreIncidente(Long numeroIncidente) throws ATiempoAppEx{
		Codigo_cierre_peticionLocal codLocal= this.ultimoCierre(numeroIncidente);
		if(codLocal!=null){//Si hay solucion agregada, pongo el cierre en el incidente con la fecha del momento
			Codigo_cierreKey cK = (Codigo_cierreKey) codLocal.getCodigo_cierre().getPrimaryKey();
			Peticion_stLocal incidente = null;
			try{
				Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				incidente = incidenteATISHome.findByPrimaryKey (new Peticion_stKey(numeroIncidente)) ;
			}catch (FinderException fex){
				log.warn("No existe la Peticion:" + numeroIncidente + " para agregar Cierre");
				throw new ATiempoAppEx (ATiempoAppEx.FINDER, fex);
			}catch (NamingException e)
			{
				log.error(" Creacion de Local Home Nulls",e);
				throw new ATiempoAppEx (ATiempoAppEx.NAMING,e);
			}
			incidente.setCod_cie_ave_cd(cK.cod_cierre);
			incidente.setFec_cie_ave_ts(new Fecha().getTimestamp());
		}//No hay solucion agregada, por lo tanto no copio el cierre
	}
	        
	private void enviaMensajeCierreTecnico (Peticion_stLocal incidente) throws ATiempoAppEx, FinderException, NamingException
	 {
			TR005E tr005e = new TR005E () ;
			
			
			tr005e.setAnswerIndicator(Utiles.sinNull(incidente.getInd_env_rpt_in(),"N").charAt(0));	
			try {
				tr005e.setAppoitmentDate(new Fecha(incidente.getFec_cit_ff(),"dd/MM/yyyy").getDate());
			} catch (FechaException e) {
				tr005e.setAppoitmentDate(new Date());
			}
			tr005e.setAppoitmentObservation(Utiles.sinNull(incidente.getObs_cit_ds()," "));
			tr005e.setBreakdownCancelationCode(Utiles.sinNull(incidente.getMot_ccl_icd_cd()," "));
			
			long atisRequestNumber = ((Peticion_stKey)incidente.getPrimaryKey()).cod_ave_cd.longValue() ;      
			
			// Si hay cierre, es pk se envio otra peticion que la dio por terminada y trajo el codigo de cierre.
			String cod_cie_ave_cd = incidente.getCod_cie_ave_cd();
			if(cod_cie_ave_cd!=null && !"".equals(cod_cie_ave_cd.trim())){
				tr005e.setBreakdownCloseCode(Utiles.sinNull(cod_cie_ave_cd," "));
				tr005e.setBreakdownCloseDate(new Fecha(incidente.getFec_cie_ave_ts()).getDate());
			}else{
				// Si no, se considera la solucion escogida.
				Codigo_cierre_peticionLocal codLocal= this.ultimoCierre(new Long(atisRequestNumber));
				//CR23830 - PCawen - 
				if(codLocal != null){
					Codigo_cierreKey cK = (Codigo_cierreKey) codLocal.getCodigo_cierre().getPrimaryKey();//SI no se encuentra codigo cierre poner uno por defecto
					tr005e.setBreakdownCloseCode(cK.cod_cierre);
					tr005e.setBreakdownCloseDate(new Fecha(codLocal.getFecha()).getDate());
				}else{
					//Seteo valores por defecto
					String codCierreDef = STConfig.getVariable("DEFAULT_COD_CIERRE");
					tr005e.setBreakdownCloseCode(codCierreDef);//Seting Codigode cierre by default 
					tr005e.setBreakdownCloseDate(new Fecha().getDate());//Seting current time
				}
				//CR23830 - Fin
			}
			tr005e.setBreakdownCommitmentDate(new Fecha(incidente.getTim_fec_cps_ts()).getDate());
            tr005e.setBreakdownNumber(atisRequestNumber);
			
			tr005e.setBreakdownObservation(Utiles.sinNull(incidente.getObs_ave_ds()," "));
			tr005e.setBreakdownOpenCode(Utiles.sinNull(incidente.getCod_apt_ave_cd()," "));
			tr005e.setBreakdownOpenDate(new Fecha(incidente.getFec_apt_ave_ts()).getDate());
			tr005e.setBreakdownOriginalCode(incidente.getCod_ave_ori_sn().longValue());
			tr005e.setBreakdownPriorityCode(Utiles.sinNull(incidente.getCod_pra_ave_cd(),"A").charAt(0));
			tr005e.setBreakdownReiterateIndicator(Utiles.sinNull(incidente.getInd_ave_rei_in(),"N").charAt(0));
			tr005e.setBreakdownSource(Utiles.sinNull(incidente.getOri_alt_icd_in(),"1").charAt(0));
			tr005e.setBreakdownStatusCode(Utiles.sinNull(incidente.getCod_est_ave_cd(),"A").charAt(0));
			tr005e.setBreakdownSymptomCode(Utiles.sinNull(incidente.getStm_ave_mas_cd()," "));
			tr005e.setBreakdownTestDescription(Utiles.sinNull(incidente.getDsc_rpt_pru_ds()," "));
			tr005e.setBreakdownTestResponseCode(Utiles.sinNull(incidente.getRpt_pru_ave_cd()," "));
			tr005e.setBreakdownTypeCode(Utiles.sinNull(incidente.getTip_ave_mas_cd()," "));
			tr005e.setCategoryCode(Utiles.sinNull(incidente.getCod_ctz_cd()," "));
			tr005e.setCityCode(Utiles.sinNull(incidente.getCod_loc()," "));
			tr005e.setClientCode(incidente.getCod_cli_cd().longValue());
			tr005e.setClientRelationshipCode(Utiles.sinNull(incidente.getCod_rel_cli_cd()," "));
			tr005e.setClimantDocumentNumber(Utiles.sinNull(incidente.getNum_doc_rte_nu()," "));
			tr005e.setClimantDocumentType(Utiles.sinNull(incidente.getTip_doc_rte_cd()," "));
			tr005e.setClimantDocumentVerification(Utiles.sinNull(incidente.getCct_doc_rte_cd()," "));
			tr005e.setClimantFirstLastname(Utiles.sinNull(incidente.getPri_ape_rte_sn()," "));
			tr005e.setClimantName(Utiles.sinNull(incidente.getNom_rte_sn()," "));
			tr005e.setClimantRelationshipOtherDescription(Utiles.sinNull(incidente.getRel_cli_otr_ds()," "));
			tr005e.setClimantSecondLastname(Utiles.sinNull(incidente.getSeg_ape_rte_sn()," "));
			tr005e.setCollectIndicator(Utiles.sinNull(incidente.getInd_cob_vst_in(),"N").charAt(0));
			String identificadorProd=incidente.getIde_pro_cmr_cd();
			if (identificadorProd.equals("BA")){ //Truco para saber bien las familias
				identificadorProd="L";
			}
			tr005e.setComercialProductIdentification(identificadorProd);
			tr005e.setComercialProductIdentificationNumber(Utiles.sinNull(incidente.getNum_ide_nu()," "));
			tr005e.setComercialProductIdentificationNumberTV(Utiles.sinNull(incidente.getNum_ide_nu_tv()," "));
			tr005e.setCommunicationMediaCode(Utiles.sinNull(incidente.getTip_mdi_cmc_cd(),"C").charAt(0));
			tr005e.setComplementsDescription1(Utiles.sinNull(incidente.getDsc_cmp_pri_ds()," "));
			tr005e.setComplementsDescription2(Utiles.sinNull(incidente.getDsc_cmp_seg_ds()," "));
			tr005e.setCompletedServiceIndicator(Utiles.sinNull(incidente.getInd_ser_efe_in(),"N").charAt(0));
			tr005e.setContactName(Utiles.sinNull(incidente.getNom_psn_cot_no()," "));
			tr005e.setContactName2(Utiles.sinNull(incidente.getSeg_psn_cot_sn()," "));
			tr005e.setContactPhone(Utiles.sinNull(incidente.getTel_cot_ds()," "));
			tr005e.setContactPhone2(Utiles.sinNull(incidente.getSeg_tel_cot_sn()," "));
			tr005e.setContractedProductServiceCode(incidente.getPro_ser_cto_cd().longValue());
			tr005e.setDaysToAlarm(incidente.getCan_dia_ala_nu().longValue());
			tr005e.setDelayIndicator(Utiles.sinNull(incidente.getInd_apz_in(),"N").charAt(0));
			tr005e.setDepartmentCode(Utiles.sinNull(incidente.getCod_dpt()," "));
			tr005e.setEmittingUser(Utiles.sinNull(incidente.getUsr_alt_no()," "));
			tr005e.setEmmitingTime(new Fecha(incidente.getIte_alt_sis_ts()).getDate());
			tr005e.setError(false);
			tr005e.setErrorMessage(" ");
			tr005e.setExtremeDefectIndicator(Utiles.sinNull(incidente.getInd_dft_emo_in(),"N").charAt(0));
		// id mensaje: lo mas probable es que este id no sirve de nada
		// mejor que sobre que falte
			DBManager dbSeq = new DBManager ();
			dbSeq.setDataSource (DBManager.JDBC_SOLTEC);
			Long idCorrelativoMensaje = new Long (dbSeq.seqNextValLong ("CORRELATIVO_MENSAJE"));
			log.debug("correlativo:" + idCorrelativoMensaje);
			tr005e.setId(idCorrelativoMensaje.toString ());
			tr005e.setInformationComponentLengthSynonym(incidente.getLng_cpe_inf_sn().intValue());
			tr005e.setInformationElementLength(incidente.getLng_ele_inf_cd().intValue());
			tr005e.setInformationElementLengthSynonym(0); //No se esta guardando
			tr005e.setLackOfServiceIndicator(Utiles.sinNull(incidente.getAju_flt_ser_in(),"N").charAt(0));
			tr005e.setLastModificationTime(new Fecha(incidente.getTim_ult_mod_ts()).getDate());
			tr005e.setLastModificationUser(Utiles.sinNull(incidente.getUsr_ult_mod_no()," "));
			tr005e.setMakeAppoitmentIndicator(Utiles.sinNull(incidente.getInd_age_cit_in(),"N").charAt(0));
			tr005e.setManagementAreaCode(incidente.getCod_are_ges_cd().longValue());
			tr005e.setMassiveBreakdownCloseDate(Utiles.sinNull(incidente.getCie_ave_mas_ff()," "));
			tr005e.setMassiveBreakdownCode(incidente.getCod_ave_mas_cd().longValue());
			tr005e.setMassiveBreakdownCommitmentDate(Utiles.sinNull(incidente.getCps_cje_mas_ff()," "));
			tr005e.setMassiveBreakdownExternalCode(Utiles.sinNull(incidente.getExt_ave_mas_ds()," "));
			tr005e.setMassiveBreakdownMotive(Utiles.sinNull(incidente.getMot_ave_mas_cd()," "));
			tr005e.setMassiveBreakdownObservation(Utiles.sinNull(incidente.getObs_ave_mas_ds()," "));
			tr005e.setMassiveBreakdownOpenDate(Utiles.sinNull(incidente.getApt_ave_mas_ff()," "));
			tr005e.setMassiveBreakdownStatusCode(Utiles.sinNull(incidente.getEst_ave_mas_cd()," "));
			tr005e.setMassiveBreakdownSyntomCode(Utiles.sinNull(incidente.getStm_ave_mas_cd()," "));
			tr005e.setMaterialAmountIndicator(Utiles.sinNull(incidente.getInd_imp_mtl_in(),"N").charAt(0));
			tr005e.setMaxResolutionTerm(incidente.getPzo_max_rsl_nu().longValue());
			tr005e.setNotificationWayCode(Utiles.sinNull(incidente.getFrm_frm_ntf_cd(),"N").charAt(0));
			tr005e.setProductServiceCode(incidente.getCod_pro_ser_cd().longValue());
			tr005e.setRequestNumber(incidente.getCod_pet_cd().longValue());
			tr005e.setSecondLineNumber(Utiles.sinNull(incidente.getNum_seg_lin_ds()," "));
			tr005e.setStreetName(Utiles.sinNull(incidente.getNom_cal_ds()," "));
			tr005e.setStreetNumber(Utiles.sinNull(incidente.getNum_cal_nu()," "));
			tr005e.setStreetType(Utiles.sinNull(incidente.getTip_cal_ati_cd()," "));
			tr005e.setUpdatedEquipmentIndicator(Utiles.sinNull(incidente.getInd_equ_atc_in(),"N").charAt(0));
//			CR17031 pcawen
			tr005e.setAccountSegmentCode(Utiles.longSinNullValue(incidente.getCod_sgm_cta_cd(),new Long(0)));
			tr005e.setAccountSubsegmentCode(Utiles.longSinNullValue(incidente.getCod_sbg_cta_cd(),new Long(0)));
			//Envia el mensaje
			new CierreIncidenteATISMQ ().enviarMensaje(tr005e) ;
			Date d= new Date();
			incidente.setFec_cie_ave_ts(new  Timestamp(d.getTime()));

		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces#agregarSolucion(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
		 */
		public CierrePeticionDTO agregarSolucion(Long numeroPeticion, String codigoActividad, Long idUser, String codCierre, String fecha) throws ATiempoAppEx {
			CierrePeticionDTO cierreAveria=null;
			try{
				//Busco los Parametros para hacer el create
				Codigo_cierreLocalHome codCierreHome = (Codigo_cierreLocalHome) HomeFactory.getHome(Codigo_cierreLocalHome.JNDI_NAME);
				Codigo_cierreKey codCierreK= new Codigo_cierreKey();
				codCierreK.cod_cierre=codCierre;
				Codigo_cierreLocal codCierreLocal = codCierreHome.findByPrimaryKey(codCierreK);
				
				Peticion_stLocalHome peticionHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stKey peticionK = new Peticion_stKey();
				peticionK.cod_ave_cd=numeroPeticion;
				Peticion_stLocal peticionLocal = peticionHome.findByPrimaryKey(peticionK);

				Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
				ActividadDTO act = null;
				ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
				ActividadSessionLocal actLocal = actHome.create();
				act = actLocal.recuperaActividad(codigoActividad, idAplicacion);
				
				UsuarioLocalHome usuarioLocalHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
				UsuarioKey uK =  new UsuarioKey();
				uK.usua_id = idUser;
				UsuarioLocal usuarioLocal= usuarioLocalHome.findByPrimaryKey(uK);

				Codigo_cierre_peticionLocalHome cierreLocalHome = (Codigo_cierre_peticionLocalHome) HomeFactory.getHome(Codigo_cierre_peticionLocalHome.JNDI_NAME);
				DBManager dbSeq = new DBManager ();
				dbSeq.setDataSource (DBManager.JDBC_SOLTEC);
				Long idCorrelativoCierre = new Long (dbSeq.seqNextValLong ("SOLTEC.ID_CIERRE_PETICION"));
				
				Fecha f = new Fecha(fecha,"dd/MM/yyyy HH:mm");
				log.debug("Cierre:" + codCierreLocal.getDescripcion_cierre());
				Codigo_cierre_peticionLocal cierreLocal = cierreLocalHome.create(idCorrelativoCierre,idUser,act.getIdActividad(),peticionLocal,codCierreLocal,f.getTimestamp());
				
//				peticionLocal.setCodigo_cierre_peticion_ultimo(cierreLocal);
				
				cierreAveria= new CierrePeticionDTO();
				cierreAveria.setCodCierre(codCierre);
				cierreAveria.setDescActividad(act.getDescActividad());
				cierreAveria.setDescCierre(codCierreLocal.getDescripcion_cierre());
				cierreAveria.setFechaCierre(f.getFechaconFormato(9));
				cierreAveria.setNombreUsuario(usuarioLocal.getUsua_login());

				// CR 26144
				if (cierreLocal.getCodigo_cierre().getObligatorio_modem() == 1){
					cierreAveria.setNecesitaModem("1");
				}else{
					cierreAveria.setNecesitaModem("0");
				}
				
				
				
			}catch(Exception e){
				log.error("Error al Agregar Cierre Solucion", e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
			}
			return cierreAveria;
		}
		
		public Collection historicoSoluciones(Long numeroPeticion) throws ATiempoAppEx{
			try {		
				Collection listaSoluciones = new ArrayList();
				Collection listaTemp=null;
				Codigo_cierre_peticionLocalHome cierreLocalHome = (Codigo_cierre_peticionLocalHome) HomeFactory.getHome(Codigo_cierre_peticionLocalHome.JNDI_NAME);
				listaTemp=cierreLocalHome.findByNumeroPeticion(numeroPeticion);
				for (Iterator iterCierre=listaTemp.iterator();iterCierre.hasNext();){
					Codigo_cierre_peticionLocal cierreLocal = (Codigo_cierre_peticionLocal) iterCierre.next();
					CierrePeticionDTO cierreAveria= new CierrePeticionDTO();
					Codigo_cierreKey codCK = (Codigo_cierreKey)cierreLocal.getCodigo_cierre().getPrimaryKey();
					cierreAveria.setCodCierre(codCK.cod_cierre);
					cierreAveria.setDescCierre(cierreLocal.getCodigo_cierre().getDescripcion_cierre());
					//Falta tratar la fecha con formato
					cierreAveria.setFechaCierre(cierreLocal.getFecha().toString());
					
					Long idAplicacion = new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_ATST_ID));
					ActividadDTO act = null;
					ActividadSessionLocalHome actHome = (ActividadSessionLocalHome)HomeFactory.getHome(ActividadSessionLocalHome.JNDI_NAME);
					ActividadSessionLocal actLocal = actHome.create();
					act = actLocal.getActividad(cierreLocal.getAct_id());
					cierreAveria.setDescActividad(act.getDescActividad());
					
					UsuarioLocalHome usuarioLocalHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
					UsuarioKey uK =  new UsuarioKey();
					uK.usua_id = cierreLocal.getUsua_id();
					UsuarioLocal usuarioLocal= usuarioLocalHome.findByPrimaryKey(uK);
					cierreAveria.setNombreUsuario(usuarioLocal.getUsua_login());

					// CR 26148
					if (cierreLocal.getCodigo_cierre().getObligatorio_modem() == 1){
						cierreAveria.setNecesitaModem("1");
					}else{
						cierreAveria.setNecesitaModem("0");
					}
	
					listaSoluciones.add(cierreAveria);
				}
				return listaSoluciones;
			} catch (Exception e) {
				log.error("Error al buscar historico soluciones. Pet:"+numeroPeticion, e);
				throw new ATiempoAppEx(ATiempoAppEx.EXCEPTION,e);
			}
		}
		
		public Peticion_stDTO getDatosPeticion (Long codigoAve)
		{	
			Peticion_stDTO datosPeticionDTO = new Peticion_stDTO();	
			
			log.debug("Id de la peticion en el bean ["+codigoAve+"]");
			
			try
			{
				//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
				Peticion_stLocalHome peticionStHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stKey key=new Peticion_stKey(codigoAve);
				Peticion_stLocal peticionLocal=peticionStHome.findByPrimaryKey(key);
				datosPeticionDTO.setCod_ave_cd(key.cod_ave_cd);
				datosPeticionDTO.setNom_rte_sn(peticionLocal.getNom_rte_sn());
				datosPeticionDTO.setPri_ape_rte_sn(peticionLocal.getPri_ape_rte_sn());
				datosPeticionDTO.setSeg_ape_rte_sn(peticionLocal.getSeg_ape_rte_sn());
				datosPeticionDTO.setTip_doc_rte_cd(peticionLocal.getTip_doc_rte_cd());
				datosPeticionDTO.setNum_doc_rte_nu(peticionLocal.getNum_doc_rte_nu());
				datosPeticionDTO.setCod_rel_cli_cd(peticionLocal.getCod_rel_cli_cd());
				datosPeticionDTO.setRel_cli_otr_ds(peticionLocal.getRel_cli_otr_ds());
				datosPeticionDTO.setEst_ave_mas_cd(peticionLocal.getEst_ave_mas_cd());
				datosPeticionDTO.setTel_cot_ds(peticionLocal.getTel_cot_ds());
				datosPeticionDTO.setSeg_tel_cot_sn(peticionLocal.getSeg_tel_cot_sn());
				datosPeticionDTO.setIde_pro_cmr_cd(peticionLocal.getIde_pro_cmr_cd());
				datosPeticionDTO.setTip_cal_ati_cd(peticionLocal.getTip_cal_ati_cd());
				datosPeticionDTO.setNum_cal_nu(peticionLocal.getNum_cal_nu());
				datosPeticionDTO.setNom_cal_ds(peticionLocal.getNom_cal_ds());
				datosPeticionDTO.setCod_apt_ave_cd(peticionLocal.getCod_apt_ave_cd());
				datosPeticionDTO.setFec_apt_ave_ts(peticionLocal.getFec_apt_ave_ts());
				datosPeticionDTO.setTim_fec_cps_ts(peticionLocal.getTim_fec_cps_ts());
				datosPeticionDTO.setCod_ctz_cd(peticionLocal.getCod_ctz_cd());
				datosPeticionDTO.setCod_pra_ave_cd(peticionLocal.getCod_pra_ave_cd());
				datosPeticionDTO.setInd_ave_rei_in(peticionLocal.getInd_ave_rei_in());
				datosPeticionDTO.setObs_ave_ds(peticionLocal.getObs_ave_ds());
				datosPeticionDTO.setEst_ave_mas_cd(peticionLocal.getEst_ave_mas_cd());
				datosPeticionDTO.setDsc_cmp_pri_ds(peticionLocal.getDsc_cmp_pri_ds());
				datosPeticionDTO.setDsc_cmp_seg_ds(peticionLocal.getDsc_cmp_seg_ds());
				datosPeticionDTO.setCod_pro_ser_cd(peticionLocal.getCod_pro_ser_cd());
				datosPeticionDTO.setCod_stm_ave_cd(peticionLocal.getCod_stm_ave_cd());
				datosPeticionDTO.setCod_ext_loc_cd(peticionLocal.getCod_ext_loc_cd());
				datosPeticionDTO.setNom_psn_cot_no(peticionLocal.getNom_psn_cot_no());
				datosPeticionDTO.setSeg_psn_cot_sn(peticionLocal.getSeg_psn_cot_sn());
				datosPeticionDTO.setTel_cot_ds(peticionLocal.getTel_cot_ds());
				datosPeticionDTO.setSeg_tel_cot_sn(peticionLocal.getSeg_tel_cot_sn());
				datosPeticionDTO.setMot_ccl_icd_cd(peticionLocal.getMot_ccl_icd_cd());
				datosPeticionDTO.setCod_apt_ave_cd(peticionLocal.getCod_apt_ave_cd());
				datosPeticionDTO.setCod_est_ave_cd(peticionLocal.getCod_est_ave_cd());
				datosPeticionDTO.setFec_cit_ff(peticionLocal.getFec_cit_ff());
				datosPeticionDTO.setInd_age_cit_in(peticionLocal.getInd_age_cit_in());
				datosPeticionDTO.setTip_ave_mas_cd(peticionLocal.getTip_ave_mas_cd());
				datosPeticionDTO.setEst_ave_mas_cd(peticionLocal.getEst_ave_mas_cd());
				datosPeticionDTO.setCie_ave_mas_ff(peticionLocal.getCie_ave_mas_ff());
				datosPeticionDTO.setObs_ave_mas_ds(peticionLocal.getObs_ave_mas_ds());
				datosPeticionDTO.setTip_ave_mas_cd(peticionLocal.getTip_ave_mas_cd());
				datosPeticionDTO.setTip_mdi_cmc_cd(peticionLocal.getTip_mdi_cmc_cd());
				datosPeticionDTO.setCod_ext_loc_cd(peticionLocal.getCod_ext_loc_cd());
				datosPeticionDTO.setFec_cie_ave_ts(peticionLocal.getFec_cie_ave_ts());
				datosPeticionDTO.setRpt_pru_ave_cd(peticionLocal.getRpt_pru_ave_cd());
				datosPeticionDTO.setCod_loc(peticionLocal.getCod_loc());
				datosPeticionDTO.setCod_dpt(peticionLocal.getCod_dpt());
				
				datosPeticionDTO.setCod_central(peticionLocal.getCod_central());
				//CR11773
				//datosPeticionDTO.setRpt_pru_ave_cd(peticionLocal.getRpt_pru_ave_cd());
				datosPeticionDTO.setDsc_rpt_pru_ds(peticionLocal.getDsc_rpt_pru_ds());
				//fin CR11773
				datosPeticionDTO.setNum_ide_nu(peticionLocal.getNum_ide_nu());
				
				//Reparaciones Multiples: se setea el campo de num_ide_nu_tv
				
				datosPeticionDTO.setNum_ide_nu_tv(peticionLocal.getNum_ide_nu_tv());
				datosPeticionDTO.setObs_cit_ds(peticionLocal.getObs_cit_ds());
				
				datosPeticionDTO.setCodPsAveria(peticionLocal.getCod_ps_averia());
				datosPeticionDTO.setCod_ave_ori_sn(peticionLocal.getCod_ave_ori_sn());
				
				datosPeticionDTO.setReintento(peticionLocal.getReintento());
				
				datosPeticionDTO.setCod_ave_mas_cd(peticionLocal.getCod_ave_mas_cd());
				datosPeticionDTO.setStm_ave_mas_cd(peticionLocal.getStm_ave_mas_cd());
				datosPeticionDTO.setCps_cje_mas_ff(peticionLocal.getCps_cje_mas_ff());
				datosPeticionDTO.setExt_ave_mas_ds(peticionLocal.getExt_ave_mas_ds());
				datosPeticionDTO.setCie_ave_mas_ff(peticionLocal.getCie_ave_mas_ff());
				datosPeticionDTO.setApt_ave_mas_ff(peticionLocal.getApt_ave_mas_ff());
//				CR17031 pcawen
				datosPeticionDTO.setCodSgmCtaCd(peticionLocal.getCod_sgm_cta_cd());
				datosPeticionDTO.setCodSbgCtaCd(peticionLocal.getCod_sbg_cta_cd());
//				CR17031 - Fin
				//TCS-gquevedo Jun 1, 2009 CR24382 INICIO
				datosPeticionDTO.setFdlSoportado(peticionLocal.getFdl_soportado());
				//TCS-gquevedo Jun 1, 2009 CR24382 FIN
				//Buscamos las descripiones para Localidad, Central, Departamento, PS
				
				if(peticionLocal.getCod_loc() != null){
					try{
						LocalidadLocalHome localidadLocalHome = (LocalidadLocalHome)HomeFactory.getHome(LocalidadLocalHome.JNDI_NAME);
						LocalidadLocal localidadLocal = localidadLocalHome.findByPrimaryKey(new LocalidadKey(peticionLocal.getCod_loc()));
					
						datosPeticionDTO.setDescLocalidad(localidadLocal.getDescripcion_localidad());
					
					}catch(Exception e){
						log.error("Error al cargar la descricion de la localidad:",e);
					}
				}
				Long cod_central = peticionLocal.getCod_central();
				if(cod_central != null){
					try{
						CentralLocalHome centralLocalHome = (CentralLocalHome)HomeFactory.getHome(CentralLocalHome.JNDI_NAME);
						CentralLocal centralLocal = centralLocalHome.findByPrimaryKey(new CentralKey(cod_central));
						datosPeticionDTO.setCod_central(cod_central);				
						datosPeticionDTO.setDescCentral(centralLocal.getDesc_central());
					
					}catch(Exception e){
						log.error("Error al cargar la descripcion de la central :",e);
					}
					
				}
		
				if(peticionLocal.getCod_dpt() != null){
					try{
						DepartamentoLocalHome departamentoLocalHome = (DepartamentoLocalHome)HomeFactory.getHome(DepartamentoLocalHome.JNDI_NAME);
						DepartamentoLocal departamentoLocal = departamentoLocalHome.findByPrimaryKey(new DepartamentoKey(peticionLocal.getCod_dpt()));
						datosPeticionDTO.setDescDepartamento(departamentoLocal.getDescripcion_departamento());
					
					}catch(Exception e){
						log.error("Error al cargar la descripcion del departamento :",e);
					}
				}
				if(peticionLocal.getCod_pro_ser_cd() != null){
					
					try{
						Producto_servicioLocalHome producto_servicioHome = (Producto_servicioLocalHome)HomeFactory.getHome(Producto_servicioLocalHome.JNDI_NAME);
						Producto_servicioLocal producto_servicioLocal = producto_servicioHome.findByPrimaryKey(new Producto_servicioKey(peticionLocal.getCod_pro_ser_cd()));
						
						datosPeticionDTO.setDescPsAveria(producto_servicioLocal.getPs_nombre());
						
					}
					catch (FinderException e) {
						log.error("Error en la carga de descripcion de ps",e);
					}
					
				}
				
			}
			catch (NamingException e){
				datosPeticionDTO = null;	
				log.debug("NamingException en Incidente:" + codigoAve);
			} catch (FinderException e) {
				datosPeticionDTO = null;
				log.debug("Error de tipo Finder:" + codigoAve );
			}			
			return datosPeticionDTO;		
		}

	public void grabarControlVisita(Long codAveCd, Fecha fechaHoraVisitaDesde, Fecha fechaHoraVisitaHasta) throws ATiempoAppEx
	{
		try
		{
			//TCS - CR25137 - Eliminaci�n Homes de Instancia - Performance Assessment - atipoldi - May 18, 2009
			ControlvisitaLocalHome controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
			Peticion_stLocalHome incidenteATISHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Tecnico_peticionLocalHome tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);
//			if(controlvisitaHome==null)
//				controlvisitaHome=(ControlvisitaLocalHome) HomeFactory.getHome(ControlvisitaLocalHome.JNDI_NAME);
//			if(incidenteATISHome==null)
//				incidenteATISHome=(Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
//			//creo tecnicoPeticionLocal
//			if (tecnicoPeticionHome==null)
//				tecnicoPeticionHome=(Tecnico_peticionLocalHome) HomeFactory.getHome(Tecnico_peticionLocalHome.JNDI_NAME);

			ControlvisitaLocal local=null;
			Peticion_stLocal incidente=null;
			Peticion_stKey peticionStKey=new Peticion_stKey(codAveCd);
			try
			{
				incidente=incidenteATISHome.findByPrimaryKey(peticionStKey);
			}
			catch(FinderException finderException)
			{
				throw new ATiempoAppEx(ATiempoAppEx.FINDER);
			}
			local=controlvisitaHome.create(new Fecha().getTimestamp(),incidente);
			local.setFechahora_llegada(fechaHoraVisitaDesde.getTimestamp());
			local.setFechahora_salida(fechaHoraVisitaHasta.getTimestamp());		
			
			//busco el tecnico
			Collection colTecnico = null;
			try {
				Peticion_stKey psetKey=(Peticion_stKey) incidente.getPrimaryKey();
				colTecnico =
					tecnicoPeticionHome.finderByPeticionyAp(psetKey.cod_ave_cd ,new Long(ApplicationConfig.getVariable("APP_ATST_ID")));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EJBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FinderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (Iterator it=colTecnico.iterator(); it.hasNext(); ) {
				Tecnico_peticionLocal tecPetiLocal = (Tecnico_peticionLocal) it.next();
				if ( tecPetiLocal.getEstado()==null || tecPetiLocal.getEstado().intValue() == 1 ) {
					local.setTecnico(tecPetiLocal.getTecnico().getCod_tecnico());
				}
			}

		}
		catch(NamingException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.NAMING,e);
		}
		catch(CreateException e)
		{
			throw new ATiempoAppEx(ATiempoAppEx.CREATE,e);
		}
	}	
	
	 /**
	  * DAVID: Nuevo m�todo para recuperar par�metros de la nueva tabla PropertiesBD.
	  * @param codigo
	  * @return
	  */
	 public String recuperarParametroFromPropertiesBD(String codigo) throws ATiempoAppEx{
	  String valor=null;
	  try {
	   Properties_BDLocalHome properties_BDLocalHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
	   Properties_BDLocal properties_BDLocal = properties_BDLocalHome.findByPrimaryKey(codigo);   
	   valor=properties_BDLocal.getValor();

	  }catch(NamingException e){
	   log.debug("NamingException en recuperarParametroFromPropertiesBD "+e.toString());
	   return null;
	  }catch(FinderException e){
	   log.debug("FinderException en recuperarParametroFromPropertiesBD "+e.toString());
	   return null;
	  }
	  return valor;
	 }
}
 
