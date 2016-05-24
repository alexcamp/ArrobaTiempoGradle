/*
 * Created on 22-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.dto.converters;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;

import com.telefonica_chile.atiempo.utiles.Utiles;

import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.DepartamentoKey;
import co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.LocalidadKey;
import co.com.telefonica.atiempo.interfaces.atiempo.Address;
import co.com.telefonica.atiempo.interfaces.atiempo.Group;

/**
 * @author TCS
 */
public class AgrupacionConvertidor {
	
	public static Group getDTO(Agrupacion_atisLocal entity)
	{
		Group dto = new Group();
		Address address = new Address();
		Collection dirCol = entity.getDireccion_atis();
		Direccion_atisLocal direccion_atisLocal = null;
		for (Iterator it=dirCol.iterator(); it.hasNext(); ) {
			direccion_atisLocal = (Direccion_atisLocal) it.next(); 
		}

		LocalidadKey localidadKey=(LocalidadKey) direccion_atisLocal.getFk_02_localidad().getPrimaryKey();
		DepartamentoKey departamentoKey=(DepartamentoKey) direccion_atisLocal.getFk_02_dir_depto().getPrimaryKey();
		address.setCityCode(localidadKey.cod_loc);
		address.setComplementsDescription1(direccion_atisLocal.getDsc_cmp_pri_ds());
		address.setComplementsDescription2(direccion_atisLocal.getDsc_cmp_seg_ds());
		address.setExternalCityCode(direccion_atisLocal.getCod_ext_loc_cd());
		address.setDepartmentCode(departamentoKey.cod_dpt);
		address.setFirstPathCharacters(direccion_atisLocal.getDir_lt1_via_1());
		address.setFirstPathCharacters2(direccion_atisLocal.getDir_lt1_via_2());
		address.setPathNumber(new Integer(direccion_atisLocal.getDir_nro_via_1()).intValue());
		address.setPathNumber2(new Integer(direccion_atisLocal.getDir_nro_via_2()).intValue());
		address.setPathType(direccion_atisLocal.getDir_tip_via_1());
		address.setPathType2(direccion_atisLocal.getDir_tip_via_2());
		address.setPathZone(direccion_atisLocal.getDir_zon_via_1());
		address.setPathZone2(direccion_atisLocal.getDir_zon_via_2());
		address.setPlaceNumber1(direccion_atisLocal.getDir_nro_lg1());
		address.setPlaceNumber2(direccion_atisLocal.getDir_nro_lg2());
		address.setPlaceNumber3(direccion_atisLocal.getDir_nro_lg3());
		address.setPlaceType1(direccion_atisLocal.getDir_tip_lg1());
		address.setPlaceType2(direccion_atisLocal.getDir_tip_lg2());
		address.setPlaceType3(direccion_atisLocal.getDir_tip_lg3());
		address.setSecondPathCharacters(direccion_atisLocal.getDir_lt1_via_1());
		address.setSecondPathCharacters2(direccion_atisLocal.getDir_lt1_via_2());
		address.setStreetName(direccion_atisLocal.getNom_cal_ds());
		address.setStreetNumber(direccion_atisLocal.getNum_cal_nu());
		address.setStreetType(direccion_atisLocal.getTip_cal_atis_cd());
		address.setSubCityName(direccion_atisLocal.getNom_slo_no());
		address.setTerritory(direccion_atisLocal.getCod_ter_cd());
		dto.setAddress(address);
		dto.setChildrenGroupCount(entity.getCan_agr_hij_nu().intValue());
		dto.setComercialOperationType(entity.getTip_opr_cmr_cd().longValue());
		dto.setComercialProductCode(entity.getCod_pro_cmr_cd().longValue());
		dto.setComercialProductIdentification(entity.getIde_pro_cmr_cd());
		dto.setComercialProductIdentificationNumber(Utiles.sinNull(entity.getNum_ide_nu(),""));
		dto.setComercialProductSubtype(entity.getSbt_pro_cmr_cd().longValue());
		dto.setComercialProductType(entity.getTip_pro_cmr_cd().longValue());
		dto.setCommitmentDate(entity.getCps_agr_sub_ff());
		dto.setFatherComercialProductCode(entity.getPro_cmr_pdr_sn().longValue());
		dto.setFatherGroupCode(entity.getAgr_sub_pdr_cd().intValue());
		dto.setObservations(entity.getObs_agr_sub_ds());
		dto.setUseTypeCode(entity.getCod_tip_uso_cd().longValue());
		dto.setUseTypeName(entity.getNom_tip_uso_no());
		return dto;
	}

	/**
	 * @param peticion
	 * @param agrupacionDTO
	 */
	public static void setEntity(Agrupacion_atisLocal entity, Group dto)
	{
		entity.setCan_agr_hij_nu(new Integer(dto.getChildrenGroupCount()));
		entity.setCod_pro_cmr_cd(new Long(dto.getComercialProductCode()));
		entity.setPro_cmr_pdr_sn(new Long(dto.getFatherComercialProductCode()));
		entity.setSbt_pro_cmr_cd(new Long(dto.getComercialProductSubtype()));
		entity.setTip_pro_cmr_cd(new Long(dto.getComercialProductType()));
		entity.setCod_tip_uso_cd(new Long(dto.getUseTypeCode()));
		entity.setCps_agr_sub_ff(new Timestamp(dto.getCommitmentDate().getTime()));
		entity.setIde_pro_cmr_cd(dto.getComercialProductIdentification());
		entity.setNum_ide_nu(dto.getComercialProductIdentificationNumber());
		entity.setNom_tip_uso_no(dto.getUseTypeName());
		entity.setObs_agr_sub_ds(dto.getObservations());
		entity.setAgr_sub_pdr_cd(new Integer(dto.getFatherGroupCode()));
		entity.setTip_pro_cmr_cd(new Long(dto.getComercialProductType()));
	}

}
