/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.dto.converters;

import java.sql.Timestamp;

import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.SubRequest;

/**
 * @author TCS
 */
public class SubPeticionConvertidor {

	/**
	 * @param subPeticion
	 * @param subPeticionDTO
	 */
	public static SubRequest getDTO(Subpeticion_atisLocal entity) {
		SubRequest dto = new SubRequest();
		Subpeticion_atisKey subpeticion_atisKey=(Subpeticion_atisKey) entity.getPrimaryKey();
		dto.setCode(subpeticion_atisKey.cod_sub_cd.intValue());
		dto.setObservations(entity.getObs_sub_ds());
		dto.setProductServiceCode(entity.getCod_pro_ser_cd().longValue());
		dto.setProductServiceName(entity.getNom_pro_ser_no());
		dto.setProductServiceType(entity.getTip_pro_ser_cd().longValue());
		dto.setServiceEndDate(entity.getFin_ser_pro_ff());
		dto.setQuantityOfProductServices(String.valueOf(entity.getCan_pro_sub_nu()));
		dto.setServiceInitialDate(entity.getIni_ser_pro_ff());
		dto.setContractNumber(entity.getNum_cto_nu().longValue());
		return dto;
	}
		
	public static void setEntity(Subpeticion_atisLocal entity, SubRequest dto)
	{
		entity.setFin_ser_pro_ff(new Timestamp(dto.getServiceEndDate().getTime()));
		entity.setIni_ser_pro_ff(new Timestamp(dto.getServiceInitialDate().getTime()));
		entity.setObs_sub_ds(dto.getObservations());
		entity.setPro_ser_cto_cd(new Long(dto.getProductServiceCode()));
		entity.setNom_pro_ser_no(dto.getProductServiceName());
		entity.setTip_pro_ser_cd(new Long(dto.getProductServiceType()));
		entity.setCan_pro_sub_nu(new Long(dto.getQuantityOfProductServices()));
		entity.setNum_cto_nu(new Long(dto.getContractNumber()));
	}
	
	

}
