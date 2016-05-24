/*
 * Created on 07-mar-07
 */
package co.com.telefonica.atiempo.vpistbba.dto.converters;

import java.util.Iterator;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.Address;
import co.com.telefonica.atiempo.interfaces.atiempo.Characteristic;
import co.com.telefonica.atiempo.interfaces.atiempo.Group;
import co.com.telefonica.atiempo.interfaces.atiempo.SubRequest;

import com.telefonica_chile.atiempo.utiles.Fecha;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author TCS
 */
public class PeticionConvertidor
{
	
	private static final Logger log = LoggerFactory.getLogger(PeticionConvertidor.class);
	/*
	 *splitPhones()
	 * Obtiene los telefonos de contacto y los devuelve en un array de String
	 */
	// TODO -- Método auxiliar para acceder a los 2 teléfonos -- Pablo L -- CR-10120
	private static String[] splitPhones(Group group,PeticionLocal pet){	
		String [] retorno = null;		
		for(Iterator i = group.getSubRequests().iterator();i.hasNext();)
		{			
			SubRequest subPeticionDTO = (SubRequest)i.next();	
			if(subPeticionDTO.getCharacteristic ()==null)
			continue;		
			for(Iterator j = subPeticionDTO.getCharacteristic().iterator();j.hasNext();)
			{				
				Characteristic caracteristicaDTO = (Characteristic) j.next ();
				long codigo = caracteristicaDTO.getCode();
				//TODO -- El valor del codigo donde vienen los 2 teléfonos es 999 -- Pablo L
				if(codigo == ComunInterfaces.codTelCot){
					retorno = loadPhones(caracteristicaDTO,pet);
					
					return retorno;
				}				
			}
		
		}
	
		return retorno;
	}
	
	//CR-23444 - PCawen - Informacion del ciclo de facturacion     -//
	private static String getInfCicFact(Group group){
		String retVal = null;
		for(Iterator i = group.getSubRequests().iterator();i.hasNext();){
			SubRequest subPeticionDTO = (SubRequest)i.next();	
			if(subPeticionDTO.getCharacteristic ()==null)
			continue;		
			for(Iterator j = subPeticionDTO.getCharacteristic().iterator();j.hasNext();){
				Characteristic caracteristicaDTO = (Characteristic) j.next ();
				long codigo = caracteristicaDTO.getCode();
				if(codigo == ComunInterfaces.codInfCicloFac){
					String infCicFac =  caracteristicaDTO.getRealValue();
					if(infCicFac != null && !infCicFac.equalsIgnoreCase("")){
						if(infCicFac.length()>4)
							retVal = infCicFac.substring(0,4);
						else
							retVal = infCicFac;
					}
				}
			}
		}
		return retVal;
	}
	//CR-23444 - PCawen - Informacion del ciclo de facturacion  - FIN\\
	
	/**
	 * @param caracteristicaDTO
	 * @return
	 */
	//Correccion defecto DF22282
	private static String[] loadPhones(Characteristic caracteristicaDTO,PeticionLocal pet) {
		String[] retorno;
		
		retorno = new String[2];					
		String tels =  caracteristicaDTO.getRealValue();
		String tel1 ="";
		String tel2 ="";
		
		
		if(tels.length() > 50){
			tel1 = tels.substring(0,50);
		    tel2 = tels.substring(50,tels.length());						
		}else{
			tel1 = tels;											
		}
		tel1 = tel1.trim();
		tel2 = tel2.trim();		
		String[] aux= null;
		if(tel1.length()>20){						
			aux =  badFormedPhones(tel1,pet);
			tel1 = aux[0];
			tel2 = aux[1];
		}else if(tel2.length()>20){
			aux =  badFormedPhones(tel2,pet);
			tel1 = aux[0];
			tel2 = aux[1];
		}					
		retorno[0] = tel1;
		retorno[1] = tel2;
		return retorno;
	}
//	Correccion defecto DF22282
	private static String[] badFormedPhones(String tel,PeticionLocal pet){
		String[] retorno;		
		retorno = new String[2];	
		int pos = -1;		
		pos = tel.indexOf(" ");
		if(pos!=-1){
			 retorno [0] = tel.substring(0,pos);
			 retorno [1] = tel.substring(pos,tel.length());	        
		}else{			
			retorno [0] = tel.substring(0,20);
			retorno [1] = "* Telefono truncado";
			log.warn("Telefono truncado " + tel + " - Peti numero "+pet.getPrimaryKey()) ;
		}
		return retorno;
	
	}

	public static void setEntity(PeticionLocal pet, Group group)
	{
		Peticion_atisLocal pa=pet.getFk_01_pet_atis();
		Address address=group.getAddress();
		pet.setPeti_fecha_ingreso(new Fecha().getTimestamp());
		pet.setPeti_fecha_compromiso(new Fecha(group.getCommitmentDate()).getTimestamp());
		
		pet.setCod_sgm_cli_cd(pa.getCod_sgm_cli_cd());
		pet.setCod_sbg_cli_cd(pa.getCod_sbg_cli_cd());
		
		//pet.setGranite_code(address.getGraniteCode());
		
		pet.setNom_int_ds(pa.getNom_int_ds());
		pet.setFec_sct_pet_ff(pa.getFec_sct_pet_ff());
		pet.setTip_doc_cd(pa.getTip_doc_cd());
		pet.setNum_doc_cli_cd(pa.getNum_doc_cli_cd());
		pet.setDig_ctl_doc_cd(pa.getDig_ctl_doc_cd());
		pet.setNom_ds(pa.getNom_ds());
		pet.setPri_ape_ds(pa.getPri_ape_ds());
		pet.setSeg_ape_ds(pa.getSeg_ape_ds());
		pet.setPri_ape_int_ds(pa.getPri_ape_int_ds());
		pet.setSeg_ape_int_ds(pa.getSeg_ape_int_ds());
		pet.setTel_cot_ds(pa.getTel_cot_ds());
		// TODO -- Sets para los 2 nuevos teléfonos de contacto -- Pablo L -- CR-10120	
		if(pet.getTel_cot_ds_1() == null || pet.getTel_cot_ds_1().equals("")){
			String [] telefonos = splitPhones(group,pet);
			if(telefonos != null){				
				pet.setTel_cot_ds_1(telefonos[0].trim());				
			    pet.setTel_cot_ds_2(telefonos[1].trim());				
			}
		}							
		//	TODO -- Fin Sets -- Pablo L
		pet.setCod_cnl_ven_cd(pa.getCod_cnl_ven_cd());
		pet.setCod_fza_ven_cd(pa.getCod_fza_ven_cd());
		pet.setCod_pet_pdr_cd(pa.getCod_pet_pdr_cd());
		pet.setCod_cta_cd(pa.getCod_cta_cd());
		pet.setCmb_est_pet_ff(pa.getCmb_est_pet_ff());
		pet.setTip_cli_cd(pa.getTip_cli_cd());
		
		pet.setCod_sbg_cta_cd(pa.getCod_sbg_cta_cd());
		pet.setCod_sgm_cta_cd(pa.getCod_sgm_cta_cd());
		
		pet.setTel_cot_ds(pa.getTel_cot_ds());
		pet.setNom_ste_pet_sn(pa.getNom_ste_pet_sn());
		pet.setPri_ape_ds(pa.getPri_ape_ds());
		pet.setSeg_ape_ds(pa.getSeg_ape_ds());
		pet.setObs_pet_ds(pa.getObs_pet_ds());
		pet.setFec_sct_pet_ff(pa.getFec_sct_pet_ff());
		pet.setTip_doc_cd(pa.getTip_doc_cd());
		pet.setNum_doc_cli_cd(pa.getNum_doc_cli_cd());
		pet.setDig_ctl_doc_cd(pa.getDig_ctl_doc_cd());
		//CR-23444 - PCawen - Informacion del ciclo de facturacion     -//
		pet.setInf_cicl_fac(getInfCicFact(group));
		//CR-23444 - PCawen - Informacion del ciclo de facturacion - FIN\\
		
		if (address.getFirstPathCharacters() !=null && !address.getFirstPathCharacters().equals(""))
		{
			pet.setDir_lt1_via_1(address.getFirstPathCharacters());
		}else
		{
			pet.setDir_lt1_via_1("-");
		}
		
		if (address.getSecondPathCharacters() !=null && !address.getSecondPathCharacters().equals("")){
			pet.setDir_lt2_via_1(address.getSecondPathCharacters());
		}else{
			pet.setDir_lt2_via_1("-");
		}
		
	
		pet.setDir_nro_via_1(String.valueOf(address.getPathNumber()));
		
		//Raúl Triviño se comentarea por defecto CQPRO00004684
		/*if (address.getFirstPathCharacters() !=null && !address.getFirstPathCharacters().equals("")){
		pet.setDir_lt1_via_1(address.getFirstPathCharacters());
		}else{
			
			pet.setDir_lt1_via_1("-");
		}
		
		if (address.getFirstPathCharacters() !=null && !address.getFirstPathCharacters().equals("")){
			pet.setDir_lt2_via_1(address.getSecondPathCharacters());
		}else{
			pet.setDir_lt2_via_1("-");
		}*/
		
		if (address.getPathZone() !=null && !address.getPathZone().equals("")){
			pet.setDir_zon_via_1(address.getPathZone());
		}else{
			pet.setDir_zon_via_1("-");
		}
		
		if (address.getPathType() !=null && !address.getPathType().equals("")){
			pet.setDir_tip_via_1(address.getPathType());
		}else{
			pet.setDir_tip_via_1("-");
		}
		
		pet.setDir_nro_via_2(String.valueOf(address.getPathNumber2()));

		if (address.getPathZone2() !=null && !address.getPathZone2().equals("")){
			pet.setDir_zon_via_2(address.getPathZone2());
		}else{
			pet.setDir_zon_via_2("-");
		}
		
		if (address.getPathType2() !=null && !address.getPathType2().equals("")){
			pet.setDir_tip_via_2(address.getPathType2());
		}else{
			pet.setDir_tip_via_2("-");
		}
		
		if (address.getFirstPathCharacters2() !=null && !address.getFirstPathCharacters2().equals("")){
			pet.setDir_lt1_via_2(address.getFirstPathCharacters2());
		}else{
			pet.setDir_lt1_via_2("-");
		}
		
		if (address.getSecondPathCharacters2() !=null && !address.getSecondPathCharacters2().equals("")){
			pet.setDir_lt2_via_2(address.getSecondPathCharacters2());
		}else{
			pet.setDir_lt2_via_2("-");
		}
		
		if (address.getPlaceType1() !=null && !address.getPlaceType1().equals("")){
			pet.setDir_tip_lg1(address.getPlaceType1());
		}else{
			pet.setDir_tip_lg1("-");
		}
		
		if (address.getPlaceNumber1() !=null && !address.getPlaceNumber1().equals("")){
			pet.setDir_nro_lg1(address.getPlaceNumber1());
		}else{
			pet.setDir_nro_lg1("-");
		}
		
		if (address.getPlaceType2() !=null && !address.getPlaceType2().equals("")){
			pet.setDir_tip_lg2(address.getPlaceType2());
		}else{
			pet.setDir_tip_lg2("-");
		}
		
		if (address.getPlaceNumber2() !=null && !address.getPlaceNumber2().equals("")){
			pet.setDir_nro_lg2(address.getPlaceNumber2());
		}else{
			pet.setDir_nro_lg2("-");
		}
		
		if (address.getPlaceType3() !=null && !address.getPlaceType3().equals("")){
			pet.setDir_tip_lg3(address.getPlaceType3());
		}else{
			pet.setDir_tip_lg3("-");
		}
		
		if (address.getPlaceNumber3() !=null && !address.getPlaceNumber3().equals("")){
			pet.setDir_nro_lg3(address.getPlaceNumber3());
		}else{
			pet.setDir_nro_lg3("-");
		}
		
		pet.setTip_cal_atis_cd(address.getStreetType());
		pet.setNom_cal_ds(address.getStreetName());
		pet.setNum_cal_nu(address.getStreetNumber());
		pet.setDsc_cmp_pri_ds(address.getComplementsDescription1());
		pet.setDsc_cmp_seg_ds(address.getComplementsDescription2());
		pet.setNom_slo_no(address.getSubCityName());
		pet.setCod_ext_loc_cd(address.getExternalCityCode());
		pet.setCod_ter_cd(address.getTerritory());
		pet.setEspe_id(new Integer(ComunInterfaces.estadoPeticionEnCurso));
	}
}
