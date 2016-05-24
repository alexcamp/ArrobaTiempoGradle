/*
 * Created on 22-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.dto.converters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR001S;

/**
 * @author TCS
 */
public class PeticionATISConvertidor {

	public static TR001S getDTO(Peticion_atisLocal entity)
	{
		TR001S dto = new TR001S();
		Peticion_atisKey key=(Peticion_atisKey) entity.getPrimaryKey();
		dto.setRequestNumber(key.cod_pet_cd.longValue());
		dto.setClientName(entity.getNom_ds());
		dto.setClientLastName(entity.getPri_ape_ds());
		dto.setClientSecondLastName(entity.getSeg_ape_ds());
		dto.setContactPhone(entity.getTel_cot_ds());
		dto.setClientSegmentCode(entity.getCod_sgm_cli_cd().longValue());
		dto.setClientSubSegmentCode(entity.getCod_sbg_cli_cd().longValue());
		dto.setEmittingDate(entity.getFec_sct_pet_ff());
		ArrayList groupings=new ArrayList();
		for(Iterator iterator=entity.getAgrupacion_atis().iterator();iterator.hasNext();)
		{
			Agrupacion_atisLocal agrupacion_atisLocal=(Agrupacion_atisLocal) iterator.next();
			groupings.add(AgrupacionConvertidor.getDTO(agrupacion_atisLocal));
		}
		dto.setGroupings(groupings);
		dto.setObservations(entity.getObs_pet_ds());
		dto.setSaleChannel(String.valueOf(entity.getCod_cnl_ven_cd()));
		dto.setSubmitterLastName(entity.getPri_ape_int_ds());
		dto.setSubmitterSecondLastName(entity.getSeg_ape_int_ds());
		return dto;
	}

	public static void setEntity(Peticion_atisLocal entity, TR001S dto)
	{
		entity.setCod_cnl_ven_cd(new Long(dto.getSaleChannel()));
		entity.setCod_sgm_cli_cd(new Long(dto.getClientSegmentCode()));
		entity.setCod_sbg_cli_cd(new Long(dto.getClientSubSegmentCode()));
		entity.setFec_sct_pet_ff(new Timestamp(dto.getEmittingDate().getTime()));
		
		String nombreCliente=dto.getClientName();
		if(nombreCliente!=null)
			nombreCliente=nombreCliente.trim();
		if(nombreCliente.length()>40)
			nombreCliente=nombreCliente.substring(0,40);
		entity.setNom_ds(nombreCliente);
		
		entity.setObs_pet_ds(dto.getObservations());
		entity.setPri_ape_ds(dto.getClientLastName());
		entity.setPri_ape_int_ds(dto.getSubmitterLastName());
		entity.setNom_int_ds(dto.getSubmitterName());
		entity.setSeg_ape_ds(dto.getClientSecondLastName());
		entity.setSeg_ape_int_ds(dto.getSubmitterSecondLastName());
		entity.setTel_cot_ds(dto.getContactPhone());
	}

}
