/*
 * Created on 05-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto.convertidores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.telefonica.atiempo.interfaces.atiempo.TR005S;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.FechaException;

/**
 * @author alevera
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IncidenteATISConvertidor {

	public static TR005S getDTO(Peticion_stLocal incidenteLocal)
	{
		TR005S incidenteAtisDTO = new TR005S();
		incidenteAtisDTO.setClientCode(incidenteLocal.getCod_cli_cd().longValue());
		incidenteAtisDTO.setProductServiceCode(incidenteLocal.getPro_ser_cto_cd().longValue());
		incidenteAtisDTO.setComercialProductIdentificationNumber(incidenteLocal.getIde_pro_cmr_cd());
		if (incidenteLocal.getNum_ide_nu()==null || incidenteLocal.getNum_ide_nu().equals(""))
		{
			incidenteAtisDTO.setComercialProductIdentificationNumberTV(incidenteLocal.getNum_ide_nu_tv());
		}else{
			incidenteAtisDTO.setComercialProductIdentification(incidenteLocal.getNum_ide_nu());
		}

		incidenteAtisDTO.setBreakdownOpenCode(incidenteLocal.getCod_apt_ave_cd());
		incidenteAtisDTO.setBreakdownCloseCode(incidenteLocal.getCod_cie_ave_cd());
		incidenteAtisDTO.setBreakdownSymptomCode(incidenteLocal.getStm_ave_mas_cd());
		incidenteAtisDTO.setBreakdownTestResponseCode(incidenteLocal.getRpt_pru_ave_cd());
		incidenteAtisDTO.setManagementAreaCode(incidenteLocal.getCod_are_ges_cd().longValue());
		String straux=incidenteLocal.getCod_est_ave_cd();
		
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setBreakdownStatusCode(straux.charAt(0));
		}
		incidenteAtisDTO.setRequestNumber(incidenteLocal.getCod_pet_cd().longValue());
		incidenteAtisDTO.setMassiveBreakdownCode(incidenteLocal.getCod_ave_mas_cd().longValue());
		incidenteAtisDTO.setBreakdownOriginalCode(incidenteLocal.getCod_ave_ori_sn().longValue());
		incidenteAtisDTO.setClimantDocumentType(incidenteLocal.getTip_doc_rte_cd());
		incidenteAtisDTO.setClimantDocumentNumber(incidenteLocal.getNum_doc_rte_nu());
		incidenteAtisDTO.setClimantDocumentVerification(incidenteLocal.getCct_doc_rte_cd());
		incidenteAtisDTO.setClimantName(incidenteLocal.getNom_rte_sn());
		incidenteAtisDTO.setClimantFirstLastname(incidenteLocal.getPri_ape_rte_sn());
		incidenteAtisDTO.setClimantSecondLastname(incidenteLocal.getSeg_ape_rte_sn());
		incidenteAtisDTO.setClientRelationshipCode(incidenteLocal.getCod_rel_cli_cd());
		incidenteAtisDTO.setClimantRelationshipOtherDescription(incidenteLocal.getRel_cli_otr_ds());
		straux=incidenteLocal.getFrm_frm_ntf_cd();
		
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setNotificationWayCode(straux.charAt(0));
		}
		incidenteAtisDTO.setContactName(incidenteLocal.getNom_psn_cot_no());
		incidenteAtisDTO.setContactPhone(incidenteLocal.getTel_cot_ds());
		incidenteAtisDTO.setContactName2(""+incidenteLocal.getSeg_psn_cot_sn());
		incidenteAtisDTO.setContactPhone2(""+incidenteLocal.getSeg_tel_cot_sn());
		straux=incidenteLocal.getTip_mdi_cmc_cd();
		
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setCommunicationMediaCode(straux.charAt(0));
		}
		incidenteAtisDTO.setCategoryCode(incidenteLocal.getCod_ctz_cd());
		straux=incidenteLocal.getCod_pra_ave_cd();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setBreakdownPriorityCode(straux.charAt(0));
		}
		incidenteAtisDTO.setMaxResolutionTerm(incidenteLocal.getPzo_max_rsl_nu().longValue());
		incidenteAtisDTO.setDaysToAlarm(incidenteLocal.getCan_dia_ala_nu().longValue());
		//TODO: revisar con alejandro .. estaba con Ind_end_rpt_in
		straux=incidenteLocal.getInd_env_rpt_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setAnswerIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_cob_vst_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setCollectIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_apz_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setDelayIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_ave_rei_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setBreakdownReiterateIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_dft_emo_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setExtremeDefectIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getAju_flt_ser_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setLackOfServiceIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_imp_mtl_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setMaterialAmountIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_ser_efe_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setCompletedServiceIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_equ_atc_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setUpdatedEquipmentIndicator(straux.charAt(0));
		}
		straux=incidenteLocal.getInd_age_cit_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setMakeAppoitmentIndicator(straux.charAt(0));
		}
		
		try {
			SimpleDateFormat simpleDateFormatLocal=new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat simpleDateFormatCastor=new SimpleDateFormat("yyyy-MM-dd");
						
			incidenteAtisDTO.setAppoitmentDate(new org.exolab.castor.types.Date(simpleDateFormatCastor.format(simpleDateFormatLocal.parse(incidenteLocal.getFec_cit_ff())).toString()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch bloc
		}
		
		
		straux=incidenteLocal.getInd_env_rpt_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setAnswerIndicator(straux.charAt(0));
		}
		incidenteAtisDTO.setBreakdownCommitmentDate(new Date(incidenteLocal.getTim_fec_cps_ts().getTime()));
		incidenteAtisDTO.setBreakdownCloseDate(new Date(incidenteLocal.getFec_cie_ave_ts().getTime()));
		incidenteAtisDTO.setSecondLineNumber(incidenteLocal.getNum_seg_lin_ds());
		straux=incidenteLocal.getOri_alt_icd_in();
		if (straux!=null && straux.length()>0) {
			incidenteAtisDTO.setBreakdownSource(straux.charAt(0));
		}
		incidenteAtisDTO.setBreakdownCancelationCode(incidenteLocal.getMot_ccl_icd_cd());
		incidenteAtisDTO.setBreakdownTestDescription(incidenteLocal.getDsc_rpt_pru_ds());
		incidenteAtisDTO.setEmittingUser(incidenteLocal.getUsr_alt_no());
		incidenteAtisDTO.setLastModificationUser(incidenteLocal.getUsr_ult_mod_no());
		incidenteAtisDTO.setEmmitingTime(new Date(incidenteLocal.getIte_alt_sis_ts().getTime()));
		incidenteAtisDTO.setLastModificationTime(new Date(incidenteLocal.getTim_ult_mod_ts().getTime()));
		incidenteAtisDTO.setInformationElementLength(incidenteLocal.getLng_ele_inf_cd().intValue());
		incidenteAtisDTO.setAppoitmentObservation(incidenteLocal.getObs_cit_ds());
		incidenteAtisDTO.setBreakdownTypeCode(incidenteLocal.getTip_ave_mas_cd());
		incidenteAtisDTO.setMassiveBreakdownSyntomCode(incidenteLocal.getStm_ave_mas_cd());
		incidenteAtisDTO.setProductServiceCode(incidenteLocal.getCod_pro_ser_cd().longValue());
		incidenteAtisDTO.setMassiveBreakdownStatusCode(incidenteLocal.getEst_ave_mas_cd());
		incidenteAtisDTO.setMassiveBreakdownCommitmentDate(incidenteLocal.getCps_cje_mas_ff());
		//TODO: Leo. cambio. Modificado tb. en IncidentesServicesBean(187)
		try
		{
			incidenteAtisDTO.setBreakdownOpenDate(new Fecha(incidenteLocal.getApt_ave_mas_ff(),"dd/MM/yyyy").getDate());
		}
		catch (FechaException e)
		{
			//TODO: incorporar api log.
		}
		incidenteAtisDTO.setMassiveBreakdownCloseDate(incidenteLocal.getCie_ave_mas_ff());
		incidenteAtisDTO.setMassiveBreakdownExternalCode(incidenteLocal.getExt_ave_mas_ds());
		incidenteAtisDTO.setInformationComponentLengthSynonym(incidenteLocal.getLng_cpe_inf_sn().intValue());
		incidenteAtisDTO.setMassiveBreakdownObservation(incidenteLocal.getObs_ave_mas_ds());
		incidenteAtisDTO.setMassiveBreakdownMotive(incidenteLocal.getMot_ave_mas_cd());
		incidenteAtisDTO.setStreetType(incidenteLocal.getTip_cal_ati_cd());
		incidenteAtisDTO.setStreetName(incidenteLocal.getNom_cal_ds());
		incidenteAtisDTO.setStreetNumber(incidenteLocal.getNum_cal_nu());
		incidenteAtisDTO.setComplementsDescription1(incidenteLocal.getDsc_cmp_pri_ds());
		incidenteAtisDTO.setComplementsDescription2(incidenteLocal.getDsc_cmp_seg_ds());
		incidenteAtisDTO.setDepartmentCode(incidenteLocal.getCod_dpt());
		incidenteAtisDTO.setCityCode(incidenteLocal.getCod_loc());
		return incidenteAtisDTO;
	}

	public static void setEntity(Peticion_stLocal entity, TR005S dto)
	{
		//TODO rellena
	}

}
